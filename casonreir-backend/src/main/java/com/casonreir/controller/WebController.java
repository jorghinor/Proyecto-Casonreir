package com.casonreir.controller;

import com.casonreir.model.*;
import com.casonreir.repository.*;
import com.casonreir.service.NotificationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalDate;
import jakarta.servlet.http.HttpServletResponse;
//import com.casonreir.model.DonacionBecadoId;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color; // Para colores en el PDF si es necesario
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class WebController {

    // INYECCIÓN DE LOS 33 REPOSITORIOS
    @Autowired private BecadoRepository becadoRepository;
    @Autowired private PadrinoRepository padrinoRepository;
    @Autowired private DonacionRepository donacionRepository;
    @Autowired private InformacionPersonalRepository infoRepository;
    @Autowired private FamiliaRepository familiaRepository;
    @Autowired private ActividadRepository actividadRepository;
    @Autowired private MedicoRepository medicoRepository;
    @Autowired private EncargadoRepository encargadoRepository;
    @Autowired private DonanteRepository donanteRepository;
    @Autowired private NoticiaRepository noticiaRepository;
    @Autowired private ReglamentoRepository reglamentoRepository;
    @Autowired private ReporteRepository reporteRepository;
    @Autowired private SfGuardUserRepository userRepository;
    @Autowired private SfGuardGroupRepository groupRepository;
    @Autowired private SfGuardPermissionRepository permissionRepository;
    @Autowired private TipoDonacionRepository tipoDonacionRepository;
    @Autowired private TipoActividadRepository tipoActividadRepository;
    @Autowired private TipoCentroSaludRepository tipoCentroSaludRepository;
    @Autowired private TipoEspecialidadRepository tipoEspecialidadRepository;
    @Autowired private CentroApoyoRepository centroApoyoRepository;
    @Autowired private CentroSaludRepository centroSaludRepository;
    @Autowired private InstitucionRepository institucionRepository;
    @Autowired private HistorialMedicoRepository historialMedicoRepository;
    @Autowired private HistorialAcademicoRepository historialAcademicoRepository;
    @Autowired private PadrinoBecadoRepository padrinoBecadoRepository;
    @Autowired private DonacionBecadoRepository donacionBecadoRepository;
    @Autowired private ActividadBecadoRepository actividadBecadoRepository;
    @Autowired private EncargadoCentroRepository encargadoCentroRepository;
    @Autowired private SfGuardUserGroupRepository userGroupRepository;
    @Autowired private SfGuardUserPermissionRepository userPermissionRepository;
    @Autowired private SfGuardGroupPermissionRepository groupPermissionRepository;
    @Autowired private SfGuardRememberKeyRepository rememberKeyRepository;
    @Autowired private SfGuardForgotPasswordRepository forgotPasswordRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private NotificationEmailService notificationEmailService;

    // Método auxiliar para enviar contadores a todos los modelos de Thymeleaf
    /*private void addGlobalAttributes(Model model) {
        model.addAttribute("totalBecados", becadoRepository.count());
        model.addAttribute("totalPadrinos", padrinoRepository.count());
        model.addAttribute("totalActividades", actividadRepository.count());

        double totalRecaudado = donacionRepository.findAll().stream()
                .mapToDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0).sum();
        model.addAttribute("totalRecaudado", String.format("%.2f", totalRecaudado));
    }*/

    // --- ACCESO Y DASHBOARD ---
    @GetMapping("/login") public String login() { return "login"; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalBecados", becadoRepository.count());
        model.addAttribute("totalPadrinos", padrinoRepository.count());
        model.addAttribute("totalDonaciones", donacionRepository.count());
        model.addAttribute("totalActividades", actividadRepository.count());
        model.addAttribute("becados", becadoRepository.findAll(PageRequest.of(0, 6)).getContent());
        model.addAttribute("padrinos", padrinoRepository.findAll(PageRequest.of(0, 4)).getContent());
        model.addAttribute("donaciones", donacionRepository.findAll(PageRequest.of(0, 3)).getContent());
        double totalRecaudado = donacionRepository.findAll().stream()
                .mapToDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0).sum();
        model.addAttribute("totalRecaudado", totalRecaudado);

        // --- NUEVO: DATOS PARA GRÁFICAS ---
        // 1. Datos para Torta (Estado Becados)
        long activos = becadoRepository.findAll().stream().filter(b -> "ACTIVO".equalsIgnoreCase(b.getEstadoBecado())).count();
        long inactivos = becadoRepository.findAll().stream().filter(b -> "INACTIVO".equalsIgnoreCase(b.getEstadoBecado())).count();
        model.addAttribute("becadosActivos", activos);
        model.addAttribute("becadosInactivos", inactivos);

        // 2. Datos para Barras (Rendimiento Académico)
        Map<String, Long> rendMap = historialAcademicoRepository.findAll().stream()
                .filter(h -> h.getRendimiento() != null)
                .collect(Collectors.groupingBy(HistorialAcademico::getRendimiento, Collectors.counting()));

        model.addAttribute("labelsRend", rendMap.keySet());
        model.addAttribute("valoresRend", rendMap.values());
        // ----------------------------------

        return "index";
    }

    //@GetMapping("/configuracion") public String hub() { return "config/hub"; }
    @GetMapping("/config/hub") // Ruta corregida para el Hub
    public String hub() { return "config/hub"; }
    // --- API BÚSQUEDA RÁPIDA DE CONTACTO ---
    @GetMapping("/api/becados/buscar-contacto")
    @ResponseBody
    public List<Map<String, String>> buscarContacto(@RequestParam String term) {
        List<Becado> resultados = becadoRepository.findAll().stream()
                .filter(b -> b.getInformacionPersonal() != null && (
                        b.getInformacionPersonal().getNombre().toLowerCase().contains(term.toLowerCase()) ||
                                b.getInformacionPersonal().getApellidoPat().toLowerCase().contains(term.toLowerCase())))
                .limit(5)
                .collect(Collectors.toList());

        List<Map<String, String>> response = new ArrayList<>();
        for (Becado b : resultados) {
            Map<String, String> data = new HashMap<>();
            data.put("id", b.getId().toString());
            data.put("nombre", b.getInformacionPersonal().getNombre() + " " + b.getInformacionPersonal().getApellidoPat());
            data.put("telefono", b.getInformacionPersonal().getTelefonoCel() != null ? b.getInformacionPersonal().getTelefonoCel().toString() : "S/N");
            data.put("email", b.getInformacionPersonal().getCorreoE() != null ? b.getInformacionPersonal().getCorreoE() : "S/N");

            String tutor = "No asignado";
            List<Familia> fam = familiaRepository.findAll().stream()
                    .filter(f -> f.getBecado() != null && f.getBecado().getId().equals(b.getId()))
                    .toList();
            if (!fam.isEmpty()) tutor = fam.get(0).getNombre() + " " + fam.get(0).getApellidoPaterno(); // Usando el campo apellido_patf de tu BD
            data.put("tutor", tutor);

            response.add(data);
        }
        return response;
    }
    // ==========================================
    // MÓDULO: BECADOS
    // ==========================================
    @GetMapping("/becados")
    public String listarBecados(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, @RequestParam(required = false) String estado, Model model) {
        Page<Becado> pg;
        if (buscar != null && !buscar.isEmpty()) pg = becadoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        else if (estado != null && !estado.isEmpty()) pg = becadoRepository.findByEstadoBecado(estado, PageRequest.of(page, 10));
        else pg = becadoRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("becados", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("estado", estado);
        return "becados/lista";
    }

    @GetMapping("/becados/nuevo") public String formularioNuevoBecado(Model model) {
        Becado b = new Becado(); b.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("becado", b); return "becados/formulario";
    }

    @PostMapping("/becados/guardar") @Transactional public String guardarBecado(@ModelAttribute Becado b) {
        infoRepository.save(b.getInformacionPersonal());
        becadoRepository.save(b); return "redirect:/becados";
    }

    @GetMapping("/becados/editar/{id}") public String editarBecado(@PathVariable Integer id, Model model) {
        model.addAttribute("becado", becadoRepository.findById(id).orElseThrow());
        return "becados/formulario";
    }

    @GetMapping("/becados/eliminar/{id}") public String eliminarBecado(@PathVariable Integer id) {
        becadoRepository.deleteById(id); return "redirect:/becados";
    }

    @GetMapping("/becados/detalle/{id}") public String detalleBecado(@PathVariable Integer id, Model model) {
        Becado becado = becadoRepository.findById(id).orElseThrow();
        model.addAttribute("becado", becado);
        model.addAttribute("familiares", familiaRepository.findAll().stream().filter(f -> f.getBecado() != null && f.getBecado().getId().equals(id)).toList());
        model.addAttribute("historialAcademico", historialAcademicoRepository.findAll().stream().filter(h -> h.getBecado() != null && h.getBecado().getId().equals(id)).toList());
        model.addAttribute("historialMedico", historialMedicoRepository.findAll().stream().filter(h -> h.getBecado() != null && h.getBecado().getId().equals(id)).toList());
        return "becados/detalle";
    }
    // ==========================================
    // REPORTE: EXPEDIENTE ÚNICO DE BECADO (PDF)
    // ==========================================
    @GetMapping("/becados/expediente/{id}")
    public void descargarExpediente(@PathVariable Integer id, HttpServletResponse response) {
        try {
            Becado b = becadoRepository.findById(id).orElseThrow();
            InformacionPersonal p = b.getInformacionPersonal();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Expediente_" + p.getNombre() + ".pdf");

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // FUENTES
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // TÍTULO
            Paragraph title = new Paragraph("EXPEDIENTE INTEGRAL DEL BECADO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // 1. INFORMACIÓN PERSONAL Y FOTO
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setWidths(new float[]{3f, 1f});

            PdfPCell dataCell = new PdfPCell();
            dataCell.setBorder(Rectangle.NO_BORDER);
            dataCell.addElement(new Paragraph("Nombre: " + p.getNombre() + " " + p.getApellidoPat(), dataFont));
            dataCell.addElement(new Paragraph("CI: " + p.getCi() + " " + p.getLugarCi(), dataFont));
            dataCell.addElement(new Paragraph("Fecha Nac: " + p.getFechaNac(), dataFont));
            dataCell.addElement(new Paragraph("Dirección: " + p.getDireccion(), dataFont));
            infoTable.addCell(dataCell);

            // Foto
            if (p.getFoto() != null) {
                try {
                    Image img = Image.getInstance("uploads/fotos_becados/" + p.getFoto());
                    img.scaleToFit(80, 80);
                    PdfPCell photoCell = new PdfPCell(img);
                    photoCell.setBorder(Rectangle.NO_BORDER);
                    photoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    infoTable.addCell(photoCell);
                } catch (Exception e) { infoTable.addCell(new PdfPCell(new Phrase("[Sin Foto]"))); }
            } else {
                infoTable.addCell(new PdfPCell(new Phrase(" ")));
            }
            document.add(infoTable);

            document.add(new Paragraph("\n2. HISTORIAL MÉDICO", headerFont));
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            List<HistorialMedico> hm = historialMedicoRepository.findAll().stream()
                    .filter(h -> h.getBecado() != null && h.getBecado().getId().equals(id)).toList();
            if (hm.isEmpty()) {
                document.add(new Paragraph("No registra antecedentes médicos.", dataFont));
            } else {
                for (HistorialMedico h : hm) {
                    document.add(new Paragraph("- " + h.getFecha() + ": " + h.getDetalle(), dataFont));
                }
            }

            document.add(new Paragraph("\n3. HISTORIAL ACADÉMICO", headerFont));
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            List<HistorialAcademico> ha = historialAcademicoRepository.findAll().stream()
                    .filter(h -> h.getBecado() != null && h.getBecado().getId().equals(id)).toList();
            if (ha.isEmpty()) {
                document.add(new Paragraph("No registra historial académico.", dataFont));
            } else {
                for (HistorialAcademico h : ha) {
                    document.add(new Paragraph("- " + h.getFecha() + " (" + h.getTipo() + "): " + h.getRendimiento(), dataFont));
                }
            }

            document.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    // ==========================================
    // MÓDULO: PADRINOS
    // ==========================================
    @GetMapping("/padrinos")
    public String listarPadrinos(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, @RequestParam(required = false) String estado, Model model) {
        Page<Padrino> pg;
        if (buscar != null && !buscar.isEmpty()) pg = padrinoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        else if (estado != null && !estado.isEmpty()) pg = padrinoRepository.findByEstado(estado, PageRequest.of(page, 10));
        else pg = padrinoRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("padrinos", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "padrinos/lista";
    }

    @GetMapping("/padrinos/nuevo")
    public String formularioNuevoPadrino(Model model) {
        Padrino p = new Padrino();
        p.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("padrino", p);
        return "padrinos/formulario";
    }

    @GetMapping("/padrinos/editar/{id}")
    public String editarPadrino(@PathVariable Integer id, Model model) {
        Padrino p = padrinoRepository.findById(id).orElseThrow();
        // Si no tiene info personal, la creamos para evitar errores en el form
        if (p.getInformacionPersonal() == null) p.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("padrino", p);
        return "padrinos/formulario";
    }

    @PostMapping("/padrinos/guardar")
    @Transactional
    public String guardarPadrino(@ModelAttribute Padrino p) {
        if (p.getInformacionPersonal() != null) {
            infoRepository.save(p.getInformacionPersonal());
        }
        padrinoRepository.save(p);
        return "redirect:/padrinos";
    }

    @GetMapping("/padrinos/eliminar/{id}")
    public String eliminarPadrino(@PathVariable Integer id) {
        padrinoRepository.deleteById(id);
        return "redirect:/padrinos";
    }

    // ==========================================
    // MÓDULO: DONACIONES
    // ==========================================
    @GetMapping("/donaciones")
    public String listarDonaciones(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, @RequestParam(required = false) Integer tipoId, Model model) {
        Page<Donacion> pg;
        if (buscar != null && !buscar.isEmpty()) pg = donacionRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        else if (tipoId != null) {
            TipoDonacion td = new TipoDonacion(); td.setId(tipoId);
            pg = donacionRepository.findByTipoDonacion(td, PageRequest.of(page, 10));
        } else pg = donacionRepository.findAll(PageRequest.of(page, 10));
        double suma = pg.getContent().stream().mapToDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0).sum();
        model.addAttribute("donaciones", pg.getContent());
        model.addAttribute("totalPagina", suma);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("tipos", tipoDonacionRepository.findAll());
        //addGlobalAttributes(model); // <--- AÑADIR ESTA LÍNEA
        return "donaciones/lista";
    }

    @GetMapping("/donaciones/nuevo") public String formularioNuevaDonacion(Model model) {
        model.addAttribute("donacion", new Donacion());
        model.addAttribute("padrinos", padrinoRepository.findAll());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        model.addAttribute("tipos", tipoDonacionRepository.findAll());
        return "donaciones/formulario";
    }

    @PostMapping("/donaciones/guardar") public String guardarDonacion(@ModelAttribute Donacion d) {
        donacionRepository.save(d); return "redirect:/donaciones";
    }

    @GetMapping("/donaciones/editar/{id}") public String editarDonacion(@PathVariable Integer id, Model model) {
        model.addAttribute("donacion", donacionRepository.findById(id).orElseThrow());
        model.addAttribute("padrinos", padrinoRepository.findAll());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        model.addAttribute("tipos", tipoDonacionRepository.findAll());
        return "donaciones/formulario";
    }

    @GetMapping("/donaciones/eliminar/{id}") public String eliminarDonacion(@PathVariable Integer id) {
        donacionRepository.deleteById(id); return "redirect:/donaciones";
    }

    // ==========================================
    // MÓDULO: ACTIVIDADES
    // ==========================================
    @GetMapping("/actividades")
    public String listarActividades(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, @RequestParam(required = false) String estado, Model model) {
        Page<Actividad> pg;
        if (buscar != null && !buscar.isEmpty()) pg = actividadRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        else if (estado != null && !estado.isEmpty()) pg = actividadRepository.findByEstado(estado, PageRequest.of(page, 10));
        else pg = actividadRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("actividades", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("estado", estado);
        return "actividades/lista";
    }

    @GetMapping("/actividades/nuevo") public String formularioNuevaActividad(Model model) {
        model.addAttribute("actividad", new Actividad());
        model.addAttribute("tipos", tipoActividadRepository.findAll());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "actividades/formulario";
    }

    @PostMapping("/actividades/guardar") public String guardarActividad(@ModelAttribute Actividad a) {
        actividadRepository.save(a); return "redirect:/actividades";
    }

    @GetMapping("/actividades/editar/{id}") public String editarActividad(@PathVariable Integer id, Model model) {
        model.addAttribute("actividad", actividadRepository.findById(id).orElseThrow());
        model.addAttribute("tipos", tipoActividadRepository.findAll());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "actividades/formulario";
    }

    @GetMapping("/actividades/eliminar/{id}") public String eliminarActividad(@PathVariable Integer id) {
        actividadRepository.deleteById(id); return "redirect:/actividades";
    }

    // ==========================================
    // MÓDULO: FAMILIAS
    // ==========================================
    @GetMapping("/familias")
    public String listarFamilias(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, Model model) {
        Page<Familia> pg = (buscar != null && !buscar.isEmpty()) ? familiaRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) : familiaRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("familias", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "familias/lista";
    }
    @GetMapping("/familias/nuevo") // Ruta unificada
    public String formularioNuevaFamilia(Model model) {
        model.addAttribute("familia", new Familia());
        model.addAttribute("becados", becadoRepository.findAll());
        return "familias/formulario";
    }

    @PostMapping("/familias/guardar") public String guardarFamilia(@ModelAttribute Familia f) {
        familiaRepository.save(f); return "redirect:/familias";
    }

    @GetMapping("/familias/editar/{id}") public String editarFamilia(@PathVariable Integer id, Model model) {
        model.addAttribute("familia", familiaRepository.findById(id).orElseThrow());
        model.addAttribute("becados", becadoRepository.findAll());
        return "familias/formulario";
    }

    @GetMapping("/familias/eliminar/{id}") public String eliminarFamilia(@PathVariable Integer id) {
        familiaRepository.deleteById(id); return "redirect:/familias";
    }

    // ==========================================
    // SECCIÓN CONFIGURACIÓN: MÉDICOS, USUARIOS, ENCARGADOS
    // ==========================================
    @GetMapping("/config/usuarios")
    public String listarUsuarios(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, Model model) {
        Page<SfGuardUser> pg = (buscar != null && !buscar.isEmpty()) ? userRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) : userRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("usuarios", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/usuario_lista";
    }

    @GetMapping("/config/usuarios/nuevo") public String fNU(Model model) {
        model.addAttribute("user", new SfGuardUser()); return "config/usuario_formulario";
    }

    @PostMapping("/config/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("user") SfGuardUser user) {
        // Encriptar contraseña si es nueva o ha cambiado
        if (user.getId() == null || (user.getPassword() != null && !user.getPassword().startsWith("$2a$"))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if (user.getId() != null && (user.getPassword() == null || user.getPassword().isEmpty())) {
            // Si es edición y no se envió password, mantener la anterior
            SfGuardUser existing = userRepository.findById(user.getId()).orElseThrow();
            user.setPassword(existing.getPassword());
        }

        if (user.getCreatedAt() == null) user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setAlgorithm("bcrypt");

        userRepository.save(user);
        return "redirect:/config/usuarios";
    }

    @GetMapping("/config/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        SfGuardUser user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "config/usuario_formulario";
    }

    @GetMapping("/config/usuarios/eliminar/{id}") public String delU(@PathVariable Long id) {
        userRepository.deleteById(id); return "redirect:/config/usuarios";
    }

    @GetMapping("/config/medicos")
    public String listarMedicos(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, Model model) {
        Page<Medico> pg = (buscar != null && !buscar.isEmpty()) ? medicoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) : medicoRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("medicos", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/medico_lista";
    }

    @GetMapping("/config/medicos/nuevo") public String fNM(Model model) {
        Medico m = new Medico(); m.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("medico", m); model.addAttribute("especialidades", tipoEspecialidadRepository.findAll()); return "config/medico_formulario";
    }

    @PostMapping("/config/medicos/guardar") @Transactional public String gM(@ModelAttribute Medico m) {
        infoRepository.save(m.getInformacionPersonal()); medicoRepository.save(m); return "redirect:/config/medicos";
    }

    @GetMapping("/config/medicos/editar/{id}") public String eM(@PathVariable Integer id, Model model) {
        model.addAttribute("medico", medicoRepository.findById(id).orElseThrow());
        model.addAttribute("especialidades", tipoEspecialidadRepository.findAll()); return "config/medico_formulario";
    }

    @GetMapping("/config/medicos/eliminar/{id}") public String delM(@PathVariable Integer id) {
        medicoRepository.deleteById(id); return "redirect:/config/medicos";
    }

    @GetMapping("/config/encargados")
    public String listarEncargados(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String buscar, Model model) {
        Page<Encargado> pg = (buscar != null && !buscar.isEmpty()) ? encargadoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) : encargadoRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("encargados", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/encargado_lista";
    }

    @GetMapping("/config/encargados/nuevo")
    public String formularioNuevoEncargado(Model model) {
        Encargado encargado = new Encargado();
        encargado.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("encargado", encargado);
        model.addAttribute("centros", centroApoyoRepository.findAll()); // Repositorio inyectado arriba
        return "config/encargado_formulario";
    }

    @PostMapping("/config/encargados/guardar")
    @Transactional
    public String guardarEncargado(@ModelAttribute Encargado encargado) {
        infoRepository.save(encargado.getInformacionPersonal());
        encargadoRepository.save(encargado);
        return "redirect:/config/encargados";
    }

    @GetMapping("/config/encargados/editar/{id}")
    public String editarEncargado(@PathVariable Integer id, Model model) {
        Encargado encargado = encargadoRepository.findById(id).orElseThrow();
        if (encargado.getInformacionPersonal() == null) {
            encargado.setInformacionPersonal(new InformacionPersonal());
        }
        model.addAttribute("encargado", encargado);
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "config/encargado_formulario";
    }

    @GetMapping("/config/encargados/eliminar/{id}")
    public String eliminarEncargado(@PathVariable Integer id) {
        encargadoRepository.deleteById(id);
        return "redirect:/config/encargados";
    }

    // --- CRUD HISTORIAL MÉDICO ---
    // ==========================================
    // MÓDULO: HISTORIAL MÉDICO (Completo)
    // ==========================================
    @GetMapping("/config/historial-medico")
    public String listarHM(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        // El buscador ya filtra por nombre de becado o diagnóstico
        Page<HistorialMedico> pg = (buscar != null && !buscar.isEmpty()) ?
                historialMedicoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                historialMedicoRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("historiales", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/historial_medico_lista";
    }

    @GetMapping("/config/historial-medico/nuevo")
    public String formularioNuevoHM(Model model) {
        model.addAttribute("historial", new HistorialMedico());
        model.addAttribute("becados", becadoRepository.findAll());
        model.addAttribute("medicos", medicoRepository.findAll());
        return "config/historial_medico_formulario";
    }

    @PostMapping("/config/historial-medico/guardar")
    @Transactional
    public String guardarHM(
            @ModelAttribute("historial") HistorialMedico h,
            @RequestParam(value = "fileDocumento", required = false) org.springframework.web.multipart.MultipartFile file) {

        // Lógica de GUARDADO DE ARCHIVO (PDF/Imagen)
        if (file != null && !file.isEmpty()) {
            try {
                String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                java.nio.file.Path ruta = java.nio.file.Paths.get("uploads/documentos_medicos/" + nombreArchivo);
                java.nio.file.Files.createDirectories(ruta.getParent()); // Crea la carpeta si no existe
                java.nio.file.Files.copy(file.getInputStream(), ruta, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                h.setDocumento(nombreArchivo); // Guardamos el nombre en el campo 'documento' (name1_hm)
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        historialMedicoRepository.save(h);
        return "redirect:/config/historial-medico";
    }

    @GetMapping("/config/historial-medico/editar/{id}")
    public String editarHM(@PathVariable Integer id, Model model) {
        model.addAttribute("historial", historialMedicoRepository.findById(id).orElseThrow());
        model.addAttribute("becados", becadoRepository.findAll());
        model.addAttribute("medicos", medicoRepository.findAll());
        return "config/historial_medico_formulario";
    }

    @GetMapping("/config/historial-medico/eliminar/{id}")
    public String eliminarHM(@PathVariable Integer id) {
        historialMedicoRepository.deleteById(id);
        return "redirect:/config/historial-medico";
    }

    @GetMapping("/config/historial-medico/detalle/{id}")
    public String detalleHM(@PathVariable Integer id, Model model) {
        HistorialMedico historial = historialMedicoRepository.findById(id).orElseThrow();
        model.addAttribute("historial", historial);
        return "config/historial_medico_detalle";
    }

    // --- OTROS MANTENIMIENTOS ---
    @GetMapping("/config/centrosalud")
    public String listarCentrosSalud(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String contacto,
            Model model) {

        Page<CentroSalud> pg;
        if (buscar != null && !buscar.isEmpty()) {
            pg = centroSaludRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        } else {
            pg = centroSaludRepository.findAll(PageRequest.of(page, 10));
        }

        model.addAttribute("centros", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("totalItems", pg.getTotalElements());
        model.addAttribute("totalPagesDisplay", pg.getTotalPages() > 0 ? pg.getTotalPages() : 1);
        model.addAttribute("buscar", buscar);
        model.addAttribute("contacto", contacto);

        return "config/centrosalud_lista";
    }

    @GetMapping("/config/centrosalud/nuevo")
    public String formularioNuevoCentroSalud(Model model) {
        model.addAttribute("centroSalud", new CentroSalud());
        // ESTA LÍNEA ES LA QUE CARGA EL SELECTOR:
        model.addAttribute("tipos", tipoCentroSaludRepository.findAll());
        return "config/centrosalud_formulario";
    }

    @PostMapping("/config/centrosalud/guardar")
    public String guardarCentroSalud(@ModelAttribute CentroSalud cs) {
        centroSaludRepository.save(cs);
        return "redirect:/config/centrosalud";
    }

    @GetMapping("/config/centrosalud/editar/{id}")
    public String editarCentroSalud(@PathVariable Integer id, Model model) {
        model.addAttribute("centroSalud", centroSaludRepository.findById(id).orElseThrow());
        // TAMBIÉN DEBE ESTAR AQUÍ PARA LA EDICIÓN:
        model.addAttribute("tipos", tipoCentroSaludRepository.findAll());
        return "config/centrosalud_formulario";
    }

    @GetMapping("/config/centrosalud/eliminar/{id}")
    public String eliminarCentroSalud(@PathVariable Integer id) {
        centroSaludRepository.deleteById(id);
        return "redirect:/config/centrosalud";
    }

    //@GetMapping("/config/reportes") public String lr(@RequestParam(defaultValue = "0") int p, Model m) { Page<Reporte> pg = reporteRepository.findAll(PageRequest.of(p, 10)); m.addAttribute("reportes", pg.getContent()); m.addAttribute("currentPage", p); m.addAttribute("totalPages", pg.getTotalPages()); return "config/reporte_lista"; }
    //@GetMapping("/config/noticias") public String ln(@RequestParam(defaultValue = "0") int p, Model m) { Page<Noticia> pg = noticiaRepository.findAll(PageRequest.of(p, 10)); m.addAttribute("noticias", pg.getContent()); m.addAttribute("currentPage", p); m.addAttribute("totalPages", pg.getTotalPages()); return "config/noticia_lista"; }
    //@GetMapping("/config/reglamentos") public String lreg(@RequestParam(defaultValue = "0") int p, Model m) { Page<Reglamento> pg = reglamentoRepository.findAll(PageRequest.of(p, 10)); m.addAttribute("reglamentos", pg.getContent()); m.addAttribute("currentPage", p); m.addAttribute("totalPages", pg.getTotalPages()); return "config/reglamento_lista"; }

    // --- TABLAS MAESTRAS (TIPOS) ---
    @GetMapping("/config/tipos-actividad")
    public String listarTiposActividad(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String estado,
            Model model) {
        Page<TipoActividad> pg;
        boolean tieneBusqueda = buscar != null && !buscar.isBlank();
        boolean tieneEstado = estado != null && !estado.isBlank();

        if (tieneBusqueda && tieneEstado) pg = tipoActividadRepository.buscarPorNombreYEstado(buscar, estado, PageRequest.of(page, 10));
        else if (tieneBusqueda) pg = tipoActividadRepository.buscarPorNombre(buscar, PageRequest.of(page, 10));
        else if (tieneEstado) pg = tipoActividadRepository.findByEstado(estado, PageRequest.of(page, 10));
        else pg = tipoActividadRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("items", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("estado", estado);
        return "config/tipo_actividad_lista";
    }

    @GetMapping("/config/tipos-actividad/nuevo")
    public String nuevoTipoActividad(Model model) {
        TipoActividad tipoActividad = new TipoActividad();
        tipoActividad.setEstado("ACTIVO");
        model.addAttribute("tipoActividad", tipoActividad);
        return "config/tipo_actividad_formulario";
    }

    @PostMapping("/config/tipos-actividad/guardar")
    public String guardarTipoActividad(@ModelAttribute TipoActividad tipoActividad) {
        tipoActividadRepository.save(tipoActividad);
        return "redirect:/config/tipos-actividad";
    }

    @GetMapping("/config/tipos-actividad/editar/{id}")
    public String editarTipoActividad(@PathVariable Integer id, Model model) {
        model.addAttribute("tipoActividad", tipoActividadRepository.findById(id).orElseThrow());
        return "config/tipo_actividad_formulario";
    }

    @GetMapping("/config/tipos-actividad/eliminar/{id}")
    public String eliminarTipoActividad(@PathVariable Integer id) {
        tipoActividadRepository.deleteById(id);
        return "redirect:/config/tipos-actividad";
    }

    @GetMapping("/config/tipos-donacion")
    public String listarTiposDonacion(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String estado,
            Model model) {
        Page<TipoDonacion> pg;
        boolean tieneBusqueda = buscar != null && !buscar.isBlank();
        boolean tieneEstado = estado != null && !estado.isBlank();

        if (tieneBusqueda && tieneEstado) pg = tipoDonacionRepository.buscarPorNombreYEstado(buscar, estado, PageRequest.of(page, 10));
        else if (tieneBusqueda) pg = tipoDonacionRepository.buscarPorNombre(buscar, PageRequest.of(page, 10));
        else if (tieneEstado) pg = tipoDonacionRepository.findByEstado(estado, PageRequest.of(page, 10));
        else pg = tipoDonacionRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("items", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        model.addAttribute("estado", estado);
        return "config/tipo_donacion_lista";
    }

    @GetMapping("/config/tipos-donacion/nuevo")
    public String nuevoTipoDonacion(Model model) {
        TipoDonacion tipoDonacion = new TipoDonacion();
        tipoDonacion.setEstado("ACTIVO");
        model.addAttribute("tipoDonacion", tipoDonacion);
        return "config/tipo_donacion_formulario";
    }

    @PostMapping("/config/tipos-donacion/guardar")
    public String guardarTipoDonacion(@ModelAttribute TipoDonacion tipoDonacion) {
        tipoDonacionRepository.save(tipoDonacion);
        return "redirect:/config/tipos-donacion";
    }

    @GetMapping("/config/tipos-donacion/editar/{id}")
    public String editarTipoDonacion(@PathVariable Integer id, Model model) {
        model.addAttribute("tipoDonacion", tipoDonacionRepository.findById(id).orElseThrow());
        return "config/tipo_donacion_formulario";
    }

    @GetMapping("/config/tipos-donacion/eliminar/{id}")
    public String eliminarTipoDonacion(@PathVariable Integer id) {
        tipoDonacionRepository.deleteById(id);
        return "redirect:/config/tipos-donacion";
    }

    //@GetMapping("/config/tipos-especialidad") public String lTE(@RequestParam(defaultValue = "0") int p, Model m) { Page<TipoEspecialidad> pg = tipoEspecialidadRepository.findAll(PageRequest.of(p, 10)); m.addAttribute("items", pg.getContent()); m.addAttribute("currentPage", p); m.addAttribute("totalPages", pg.getTotalPages()); m.addAttribute("modulo", "Especialidad"); m.addAttribute("url", "/config/tipos-especialidad"); return "config/maestro_lista"; }
    // ==========================================
    // MÓDULO: INSTITUCIONES
    // ==========================================
    @GetMapping("/config/instituciones")
    public String listarInstituciones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<Institucion> pg;
        if (buscar != null && !buscar.isEmpty()) {
            pg = institucionRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        } else {
            pg = institucionRepository.findAll(PageRequest.of(page, 10));
        }

        model.addAttribute("instituciones", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);

        return "config/institucion_lista";
    }

    @GetMapping("/config/instituciones/nuevo")
    public String formularioNuevaInstitucion(Model model) {
        model.addAttribute("institucion", new Institucion());
        return "config/institucion_formulario";
    }

    @PostMapping("/config/instituciones/guardar")
    public String guardarInstitucion(@ModelAttribute Institucion inst) {
        institucionRepository.save(inst);
        return "redirect:/config/instituciones";
    }

    @GetMapping("/config/instituciones/editar/{id}")
    public String editarInstitucion(@PathVariable Integer id, Model model) {
        model.addAttribute("institucion", institucionRepository.findById(id).orElseThrow());
        return "config/institucion_formulario";
    }

    @GetMapping("/config/instituciones/eliminar/{id}")
    public String eliminarInstitucion(@PathVariable Integer id) {
        institucionRepository.deleteById(id);
        return "redirect:/config/instituciones";
    }
    // ==========================================
// MÓDULO: CENTROS DE APOYO (C.E.A.)
// ==========================================
    @GetMapping("/config/centrosapoyo")
    public String listarCentrosApoyo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<CentroApoyo> pg;
        if (buscar != null && !buscar.isEmpty()) {
            pg = centroApoyoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        } else {
            pg = centroApoyoRepository.findAll(PageRequest.of(page, 10));
        }

        model.addAttribute("centrosApoyo", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);

        return "config/centroapoyo_lista";
    }

    @GetMapping("/config/centroapoyo/nuevo")
    public String formularioNuevoCentroApoyo(Model model) {
        model.addAttribute("centro", new CentroApoyo());
        return "config/centroapoyo_formulario";
    }

    @PostMapping("/config/centroapoyo/guardar")
    public String guardarCentroApoyo(@ModelAttribute CentroApoyo c) {
        centroApoyoRepository.save(c);
        return "redirect:/config/centrosapoyo";
    }

    @GetMapping("/config/centroapoyo/editar/{id}")
    public String editarCentroApoyo(@PathVariable Integer id, Model model) {
        model.addAttribute("centro", centroApoyoRepository.findById(id).orElseThrow());
        return "config/centroapoyo_formulario";
    }

    @GetMapping("/config/centroapoyo/eliminar/{id}")
    public String eliminarCentroApoyo(@PathVariable Integer id) {
        centroApoyoRepository.deleteById(id);
        return "redirect:/config/centrosapoyo";
    }
    // ==========================================
    // MÓDULO: DOCUMENTACIÓN (Noticias y Reglamentos)
    // ==========================================
    @GetMapping("/config/noticias")
    public String listarNoticias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<Noticia> pg = (buscar != null && !buscar.isEmpty()) ?
                noticiaRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                noticiaRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("noticias", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/noticia_lista";
    }

    @GetMapping("/config/noticias/nuevo")
    public String formularioNuevaNoticia(Model model) {
        model.addAttribute("noticia", new Noticia());
        return "config/noticia_formulario";
    }

    @PostMapping("/config/noticias/guardar")
    public String guardarNoticia(@ModelAttribute Noticia n) {
        if (n.getFechaInicio() == null) n.setFechaInicio(LocalDate.now());
        noticiaRepository.save(n);
        return "redirect:/config/noticias";
    }

    @GetMapping("/config/noticias/editar/{id}")
    public String editarNoticia(@PathVariable Integer id, Model model) {
        model.addAttribute("noticia", noticiaRepository.findById(id).orElseThrow());
        return "config/noticia_formulario";
    }

    @GetMapping("/config/noticias/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Integer id) {
        noticiaRepository.deleteById(id);
        return "redirect:/config/noticias";
    }

    // ==========================================
    // MÓDULO: REGLAMENTOS
    // ==========================================
    @GetMapping("/config/reglamentos")
    public String listarReglamentos(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Reglamento> pg = reglamentoRepository.findAll(PageRequest.of(page, 10));

        // Decodificación de caracteres HTML y limpieza de etiquetas para la lista
        pg.getContent().forEach(r -> {
            if (r.getContenido() != null) {
                String texto = r.getContenido()
                        .replace("&aacute;", "á").replace("&eacute;", "é")
                        .replace("&iacute;", "í").replace("&oacute;", "ó")
                        .replace("&uacute;", "ú").replace("&Aacute;", "Á")
                        .replace("&Eacute;", "É").replace("&Iacute;", "Í")
                        .replace("&Oacute;", "Ó").replace("&Uacute;", "Ú")
                        .replace("&ntilde;", "ñ").replace("&Ntilde;", "Ñ")
                        .replace("&nbsp;", " ").replace("&ldquo;", "\"")
                        .replace("&rdquo;", "\"").replace("&middot;", "·");

                // Eliminamos etiquetas HTML (<p>, <li>, etc.) para que no se vean en la tabla
                String limpio = texto.replaceAll("<[^>]*>", " ");
                // Limpiamos espacios múltiples y asignamos al campo de descripción
                limpio = limpio.replaceAll("\\s+", " ").trim();
                r.setDescripcionCorta(limpio);
            }
        });
        model.addAttribute("reglamentos", pg.getContent());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        return "config/reglamento_lista";
    }

    @GetMapping("/config/reglamentos/nuevo")
    public String formularioNuevoReglamento(Model model) {
        model.addAttribute("reglamento", new Reglamento());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "config/reglamento_formulario";
    }

    @PostMapping("/config/reglamentos/guardar")
    public String guardarReglamento(@ModelAttribute Reglamento r) {
        reglamentoRepository.save(r);
        return "redirect:/config/reglamentos";
    }

    @GetMapping("/config/reglamentos/editar/{id}")
    public String editarReglamento(@PathVariable Integer id, Model model) {
        model.addAttribute("reglamento", reglamentoRepository.findById(id).orElseThrow());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "config/reglamento_formulario";
    }

    @GetMapping("/config/reglamentos/eliminar/{id}")
    public String eliminarReglamento(@PathVariable Integer id) {
        reglamentoRepository.deleteById(id);
        return "redirect:/config/reglamentos";
    }
    // ==========================================
    // MÓDULO: DONANTES EXTERNOS
    // ==========================================
    @GetMapping("/config/donantes")
    public String listarDonantes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<Donante> pg = (buscar != null && !buscar.isEmpty()) ?
                donanteRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                donanteRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("donantes", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/donante_lista";
    }

    @GetMapping("/config/donantes/nuevo")
    public String formularioNuevoDonante(Model model) {
        Donante d = new Donante();
        d.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("donante", d);
        return "config/donante_formulario";
    }

    @PostMapping("/config/donantes/guardar")
    @Transactional
    public String guardarDonante(@ModelAttribute Donante d) {
        if (d.getInformacionPersonal() != null) {
            infoRepository.save(d.getInformacionPersonal());
        }
        donanteRepository.save(d);
        return "redirect:/config/donantes";
    }

    @GetMapping("/config/donantes/editar/{id}")
    public String editarDonante(@PathVariable Integer id, Model model) {
        Donante d = donanteRepository.findById(id).orElseThrow();
        if (d.getInformacionPersonal() == null) d.setInformacionPersonal(new InformacionPersonal());
        model.addAttribute("donante", d);
        return "config/donante_formulario";
    }

    @GetMapping("/config/donantes/eliminar/{id}")
    public String eliminarDonante(@PathVariable Integer id) {
        donanteRepository.deleteById(id);
        return "redirect:/config/donantes";
    }
    // ==========================================
    // MÓDULO: HISTORIAL ACADÉMICO
    // ==========================================
    @GetMapping("/config/historial-academico")
    public String listarHA(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        // El buscador ya filtra por nombre de becado o libreta
        Page<HistorialAcademico> pg = (buscar != null && !buscar.isEmpty()) ?
                historialAcademicoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                historialAcademicoRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("historialesAcademicos", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/historial_academico_lista";
    }

    @GetMapping("/config/historial-academico/nuevo")
    public String formularioNuevoHA(Model model) {
        model.addAttribute("historialAcademico", new HistorialAcademico());
        model.addAttribute("becados", becadoRepository.findAll());
        return "config/historial_academico_formulario";
    }

    @PostMapping("/config/historial-academico/guardar")
    public String guardarHA(
            @ModelAttribute("historialAcademico") HistorialAcademico h,
            @RequestParam(value = "fileLibreta", required = false) org.springframework.web.multipart.MultipartFile file) {

        // Lógica de GUARDADO DE ARCHIVO (PDF/Imagen)
        if (file != null && !file.isEmpty()) {
            try {
                // Limpiamos el nombre del archivo (quitamos espacios y caracteres raros)
                String originalName = file.getOriginalFilename();
                String cleanName = (originalName != null) ? originalName.replaceAll("[^a-zA-Z0-9.-]", "_") : "archivo";
                String nombreArchivo = System.currentTimeMillis() + "_" + cleanName;

                java.nio.file.Path ruta = java.nio.file.Paths.get("uploads/libretas/" + nombreArchivo);
                java.nio.file.Files.createDirectories(ruta.getParent());
                java.nio.file.Files.copy(file.getInputStream(), ruta, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                h.setLibreta(nombreArchivo); // Actualizamos el nombre en el objeto
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } else if (h.getId() != null) {
            // 2. Si estamos editando y NO se subió archivo, mantenemos el que ya tenía
            HistorialAcademico anterior = historialAcademicoRepository.findById(h.getId()).orElse(null);
            if (anterior != null) h.setLibreta(anterior.getLibreta());
        }

        historialAcademicoRepository.save(h);
        return "redirect:/config/historial-academico";
    }

    @GetMapping("/config/historial-academico/editar/{id}")
    public String editarHA(@PathVariable Integer id, Model model) {
        model.addAttribute("historialAcademico", historialAcademicoRepository.findById(id).orElseThrow());
        model.addAttribute("becados", becadoRepository.findAll());
        return "config/historial_academico_formulario";
    }

    @GetMapping("/config/historial-academico/eliminar/{id}")
    public String eliminarHA(@PathVariable Integer id) {
        historialAcademicoRepository.deleteById(id);
        return "redirect:/config/historial-academico";
    }

    @GetMapping("/config/historial-academico/detalle/{id}")
    public String detalleHA(@PathVariable Integer id, Model model) {
        HistorialAcademico historial = historialAcademicoRepository.findById(id).orElseThrow();
        model.addAttribute("historial", historial);
        return "config/historial_academico_detalle";
    }
    // ==========================================
    // MÓDULO: INFORMACIÓN PERSONAL (Maestro de Personas)
    // ==========================================
    @GetMapping("/config/personas")
    public String listarPersonas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<InformacionPersonal> pg = (buscar != null && !buscar.isEmpty()) ?
                infoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                infoRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("personas", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/persona_lista";
    }

    @GetMapping("/config/personas/nuevo")
    public String formularioNuevaPersona(Model model) {
        model.addAttribute("persona", new InformacionPersonal());
        return "config/persona_formulario";
    }

    @PostMapping("/config/personas/guardar")
    @Transactional
    public String guardarPersona(
            @ModelAttribute("persona") InformacionPersonal p,
            @RequestParam(value = "fileFoto", required = false) org.springframework.web.multipart.MultipartFile fileFoto,
            @RequestParam(value = "fileCertificado", required = false) org.springframework.web.multipart.MultipartFile fileCert) {

        // 1. Lógica para guardar la FOTO
        if (fileFoto != null && !fileFoto.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_foto_" + fileFoto.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
                java.nio.file.Path path = java.nio.file.Paths.get("uploads/fotos_becados/" + fileName);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.copy(fileFoto.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                p.setFoto(fileName);
            } catch (java.io.IOException e) { e.printStackTrace(); }
        } else if (p.getId() != null) {
            infoRepository.findById(p.getId()).ifPresent(old -> p.setFoto(old.getFoto()));
        }

        // 2. Lógica para guardar el CERTIFICADO
        if (fileCert != null && !fileCert.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_cert_" + fileCert.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
                java.nio.file.Path path = java.nio.file.Paths.get("uploads/certificados/" + fileName);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.copy(fileCert.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                p.setCertificado(fileName);
            } catch (java.io.IOException e) { e.printStackTrace(); }
        } else if (p.getId() != null) {
            infoRepository.findById(p.getId()).ifPresent(old -> p.setCertificado(old.getCertificado()));
        }

        infoRepository.save(p);
        return "redirect:/config/personas";
    }

    @GetMapping("/config/personas/editar/{id}")
    public String editarPersona(@PathVariable Integer id, Model model) {
        model.addAttribute("persona", infoRepository.findById(id).orElseThrow());
        return "config/persona_formulario";
    }

    @GetMapping("/config/personas/eliminar/{id}")
    public String eliminarPersona(@PathVariable Integer id) {
        infoRepository.deleteById(id);
        return "redirect:/config/personas";
    }

    @GetMapping("/config/personas/pdf/{id}")
    public void descargarFichaPersona(@PathVariable Integer id, HttpServletResponse response) {
        try {
            InformacionPersonal p = infoRepository.findById(id).orElseThrow();

            // Configurar respuesta para PDF
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Ficha_" + p.getNombre() + "_" + p.getApellidoPat() + ".pdf";
            response.setHeader(headerKey, headerValue);

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            // TÍTULO
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            Paragraph title = new Paragraph("FICHA DE INFORMACIÓN PERSONAL", fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // FOTO (Si existe)
            if (p.getFoto() != null) {
                try {
                    String photoPath = "uploads/fotos_becados/" + p.getFoto();
                    Image img = Image.getInstance(photoPath);
                    img.scaleToFit(120, 120);
                    img.setAlignment(Image.ALIGN_RIGHT);
                    document.add(img);
                } catch (Exception e) {
                    document.add(new Paragraph("[Imagen no disponible]"));
                }
            }
            // TABLA DE DATOS
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            document.add(new Paragraph("NOMBRE COMPLETO: ", fontBold));
            document.add(new Paragraph(p.getNombre() + " " + p.getApellidoPat() + " " + (p.getApellidoMat() != null ? p.getApellidoMat() : "")));

            document.add(new Paragraph("CI: ", fontBold));
            document.add(new Paragraph(p.getCi() + " (" + p.getLugarCi() + ")"));

            document.add(new Paragraph("FECHA NACIMIENTO: ", fontBold));
            document.add(new Paragraph(p.getFechaNac() != null ? p.getFechaNac().toString() : "N/A"));

            document.add(new Paragraph("CELULAR: ", fontBold));
            document.add(new Paragraph(p.getTelefonoCel()));

            document.add(new Paragraph("CORREO ELECTRÓNICO: ", fontBold));
            document.add(new Paragraph(p.getCorreoE()));

            document.add(new Paragraph("DIRECCIÓN: ", fontBold));
            document.add(new Paragraph(p.getDireccion()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("CERTIFICADO REGISTRADO: ", fontBold));
            document.add(new Paragraph(p.getCertificado() != null ? p.getCertificado() : "No adjunto"));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ==========================================
    // MÓDULO: PADRINO - BECADO (Asignaciones de Apoyo)
    // ==========================================
    @GetMapping("/config/padrino-becado")
    public String listarPadrinoBecado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<PadrinoBecado> pg = (buscar != null && !buscar.isEmpty()) ?
                padrinoBecadoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                padrinoBecadoRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("asignaciones", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/padrinobecado_lista";
    }

    @GetMapping("/config/padrino-becado/nuevo")
    public String formularioNuevaAsignacion(Model model) {
        model.addAttribute("asignacion", new PadrinoBecado());
        model.addAttribute("becados", becadoRepository.findAll());
        model.addAttribute("padrinos", padrinoRepository.findAll());
        return "config/padrinobecado_formulario";
    }

    @GetMapping("/config/padrino-becado/editar/{becadoId}/{padrinoId}")
    public String editarAsignacion(@PathVariable Integer becadoId, @PathVariable Integer padrinoId, Model model) {
        // Buscamos el registro específico entre todas las asignaciones
        PadrinoBecado asignacion = padrinoBecadoRepository.findAll().stream()
                .filter(a -> a.getBecado().getId().equals(becadoId) && a.getPadrino().getId().equals(padrinoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        model.addAttribute("asignacion", asignacion);
        model.addAttribute("becados", becadoRepository.findAll());
        model.addAttribute("padrinos", padrinoRepository.findAll());
        return "config/padrinobecado_formulario";
    }

    @PostMapping("/config/padrino-becado/guardar")
    @Transactional
    public String guardarAsignacion(@ModelAttribute("asignacion") PadrinoBecado pb) {
        if (pb.getFechaInicio() == null) pb.setFechaInicio(LocalDate.now());
        padrinoBecadoRepository.save(pb);
        notificationEmailService.sendPadrinoAsignadoNotification(pb.getPadrino(), pb.getBecado(), pb.getFechaInicio());
        return "redirect:/config/padrino-becado";
    }

    @GetMapping("/config/padrino-becado/eliminar/{becadoId}/{padrinoId}")
    public String eliminarAsignacion(@PathVariable Integer becadoId, @PathVariable Integer padrinoId) {
        PadrinoBecado pb = new PadrinoBecado();
        Becado b = new Becado(); b.setId(becadoId);
        Padrino p = new Padrino(); p.setId(padrinoId);
        pb.setBecado(b);
        pb.setPadrino(p);
        padrinoBecadoRepository.delete(pb);
        return "redirect:/config/padrino-becado";
    }
    // ==========================================
    // MÓDULO: DONACIONES POR BECADO
    // ==========================================
    @GetMapping("/donaciones-becados")
    public String listarDonacionesBecados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<DonacionBecado> pg = (buscar != null && !buscar.isEmpty()) ?
                donacionBecadoRepository.buscarPorBecado(buscar, PageRequest.of(page, 10)) :
                donacionBecadoRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("donacionesBecados", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "donaciones/donacion_becado_lista";
    }

    @GetMapping("/donaciones-becados/nuevo")
    public String formularioNuevaDonacionBecado(Model model) {
        model.addAttribute("donacionBecado", new DonacionBecado());
        model.addAttribute("donaciones", donacionRepository.findAll());
        model.addAttribute("becados", becadoRepository.findAll());
        return "donaciones/donacion_becado_formulario";
    }

    @GetMapping("/donaciones-becados/editar/{donacionId}/{becadoId}")
    public String editarDonacionBecado(@PathVariable Integer donacionId, @PathVariable Integer becadoId, Model model) {
        DonacionBecadoId id = new DonacionBecadoId();
        id.setDonacion(donacionId);
        id.setBecado(becadoId);

        model.addAttribute("donacionBecado", donacionBecadoRepository.findById(id).orElseThrow());
        model.addAttribute("donaciones", donacionRepository.findAll());
        model.addAttribute("becados", becadoRepository.findAll());
        return "donaciones/donacion_becado_formulario";
    }

    @PostMapping("/donaciones-becados/guardar")
    public String guardarDonacionBecado(@ModelAttribute DonacionBecado db) {
        if (db.getFecha() == null) db.setFecha(LocalDate.now());
        donacionBecadoRepository.save(db);
        return "redirect:/donaciones-becados";
    }

    @GetMapping("/donaciones-becados/eliminar/{donacionId}/{becadoId}")
    public String eliminarDonacionBecado(@PathVariable Integer donacionId, @PathVariable Integer becadoId) {
        DonacionBecado db = new DonacionBecado();
        Donacion d = new Donacion(); d.setId(donacionId);
        Becado b = new Becado(); b.setId(becadoId);
        db.setDonacion(d);
        db.setBecado(b);
        donacionBecadoRepository.delete(db);
        return "redirect:/donaciones-becados";
    }
    // ==========================================
    // MÓDULOS DE SEGURIDAD (Grupos y Permisos)
    // ==========================================
    @GetMapping("/config/grupos")
    public String listarGrupos(Model model) {
        model.addAttribute("grupos", groupRepository.findAll());
        return "config/grupo_lista";
    }

    @GetMapping("/config/grupos/nuevo")
    public String nuevoGrupo(Model model) {
        model.addAttribute("grupo", new SfGuardGroup());
        return "config/grupo_formulario";
    }

    @PostMapping("/config/grupos/guardar")
    public String guardarGrupo(@ModelAttribute SfGuardGroup group) {
        if (group.getCreatedAt() == null) group.setCreatedAt(LocalDateTime.now());
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
        return "redirect:/config/grupos";
    }

    // --- EDICIÓN DE GRUPOS ---
    @GetMapping("/config/grupos/editar/{id}")
    public String editarGrupo(@PathVariable Long id, Model model) {
        model.addAttribute("grupo", groupRepository.findById(id).orElseThrow());
        return "config/grupo_formulario";
    }

    @GetMapping("/config/permisos")
    public String listarPermisos(Model model) {
        model.addAttribute("permisos", permissionRepository.findAll());
        return "config/permiso_lista";
    }

    @GetMapping("/config/permisos/nuevo")
    public String nuevoPermiso(Model model) {
        model.addAttribute("permiso", new SfGuardPermission());
        return "config/permiso_formulario";
    }

    // --- EDICIÓN DE PERMISOS ---
    @GetMapping("/config/permisos/editar/{id}")
    public String editarPermiso(@PathVariable Long id, Model model) {
        model.addAttribute("permiso", permissionRepository.findById(id).orElseThrow());
        return "config/permiso_formulario";
    }

    // ==========================================
    // MÓDULOS DE TIPOS (Especialidad y Centro Salud)
    // ==========================================
    @GetMapping("/config/tipos-especialidad")
    public String listarTiposEspecialidad(Model model) {
        model.addAttribute("items", tipoEspecialidadRepository.findAll());
        model.addAttribute("titulo", "Especialidades Médicas");
        model.addAttribute("url", "tipos-especialidad");
        return "config/maestro_simple_lista";
    }

    @GetMapping("/config/tipos-especialidad/nuevo")
    public String nuevaEspecialidad(Model model) {
        model.addAttribute("especialidad", new TipoEspecialidad());
        return "config/tipo_especialidad_formulario";
    }

    @PostMapping("/config/tipos-especialidad/guardar")
    public String guardarEspecialidad(@ModelAttribute TipoEspecialidad especialidad) {
        tipoEspecialidadRepository.save(especialidad);
        return "redirect:/config/tipos-especialidad";
    }

    // --- EDICIÓN DE ESPECIALIDADES ---
    @GetMapping("/config/tipos-especialidad/editar/{id}")
    public String editarEspecialidad(@PathVariable Integer id, Model model) {
        model.addAttribute("especialidad", tipoEspecialidadRepository.findById(id).orElseThrow());
        return "config/tipo_especialidad_formulario";
    }

    @GetMapping("/config/tipos-centrosalud")
    public String listarTiposCentroSalud(Model model) {
        model.addAttribute("items", tipoCentroSaludRepository.findAll());
        model.addAttribute("titulo", "Tipos de Centro de Salud");
        model.addAttribute("url", "tipos-centrosalud");
        return "config/maestro_simple_lista";
    }

    @GetMapping("/config/tipos-centrosalud/nuevo")
    public String nuevoTipoCentro(Model model) {
        model.addAttribute("tipoCentro", new TipoCentroSalud());
        return "config/tipo_centrosalud_formulario";
    }

    @PostMapping("/config/tipos-centrosalud/guardar")
    public String guardarTipoCentro(@ModelAttribute TipoCentroSalud tipoCentro) {
        tipoCentroSaludRepository.save(tipoCentro);
        return "redirect:/config/tipos-centrosalud";
    }

    // --- EDICIÓN DE TIPOS DE CENTRO DE SALUD ---
    @GetMapping("/config/tipos-centrosalud/editar/{id}")
    public String editarTipoCentro(@PathVariable Integer id, Model model) {
        model.addAttribute("tipoCentro", tipoCentroSaludRepository.findById(id).orElseThrow());
        return "config/tipo_centrosalud_formulario";
    }
    // ==========================================
    // MÓDULO: ENCARGADO - CENTRO (Asignaciones)
    // ==========================================
    @GetMapping("/config/encargados-centros")
    public String listarEncargadosCentros(Model model) {
        model.addAttribute("lista", encargadoCentroRepository.findAll());
        return "config/encargado_centro_lista";
    }

    @GetMapping("/config/encargados-centros/nuevo")
    public String nuevoEncargadoCentro(Model model) {
        model.addAttribute("encargadoCentro", new EncargadoCentro());
        model.addAttribute("encargados", encargadoRepository.findAll());
        model.addAttribute("centros", centroApoyoRepository.findAll());
        return "config/encargado_centro_formulario";
    }

    @PostMapping("/config/encargados-centros/guardar")
    @Transactional
    public String guardarEncargadoCentro(@ModelAttribute EncargadoCentro ec) {
        if (ec.getFechaInicio() == null) ec.setFechaInicio(LocalDate.now());
        encargadoCentroRepository.save(ec);
        return "redirect:/config/encargados-centros";
    }

    @GetMapping("/config/encargados-centros/eliminar/{idEnc}/{idCea}")
    @Transactional
    public String eliminarEncargadoCentro(@PathVariable Integer idEnc, @PathVariable Integer idCea) {
        EncargadoCentroId id = new EncargadoCentroId(idEnc, idCea);
        encargadoCentroRepository.deleteById(id);
        return "redirect:/config/encargados-centros";
    }
    // ==========================================
    // MÓDULO: ACTIVIDADES POR BECADO
    // ==========================================
    @GetMapping("/config/actividades-becados")
    public String listarActividadesBecados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<ActividadBecado> pg;
        if (buscar != null && !buscar.isEmpty()) {
            pg = actividadBecadoRepository.buscarPorTermino(buscar, PageRequest.of(page, 10));
        } else {
            pg = actividadBecadoRepository.findAll(PageRequest.of(page, 10));
        }

        model.addAttribute("lista", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/actividad_becado_lista";
    }

    @GetMapping("/config/actividades-becados/nuevo")
    public String nuevaActividadBecado(Model model) {
        model.addAttribute("actividadBecado", new ActividadBecado());
        model.addAttribute("actividades", actividadRepository.findAll());
        model.addAttribute("becados", becadoRepository.findAll());
        return "config/actividad_becado_formulario";
    }

    @PostMapping("/config/actividades-becados/guardar")
    public String guardarActividadBecado(@ModelAttribute ActividadBecado ab) {
        actividadBecadoRepository.save(ab);
        return "redirect:/config/actividades-becados";
    }

    @GetMapping("/config/actividades-becados/editar/{id}")
    public String editarActividadBecado(@PathVariable Integer id, Model model) {
        model.addAttribute("actividadBecado", actividadBecadoRepository.findById(id).orElseThrow());
        model.addAttribute("actividades", actividadRepository.findAll());
        model.addAttribute("becados", becadoRepository.findAll());
        return "config/actividad_becado_formulario";
    }

    @GetMapping("/config/actividades-becados/eliminar/{id}")
    public String eliminarActividadBecado(@PathVariable Integer id) {
        actividadBecadoRepository.deleteById(id);
        return "redirect:/config/actividades-becados";
    }
    // ==========================================
    // MÓDULO: REPORTES (Principal)
    // ==========================================
    @GetMapping("/config/reportes")
    public String reportesMain(Model model) {
        return "config/reportes_main";
    }
    // --- REPORTE: DONACIONES POR BECADO ---

    // 1. Fragmento HTML para AJAX
    @GetMapping("/config/reportes/donaciones-becado-html")
    public String reporteDonacionesBecadoHtml(Model model) {
        model.addAttribute("donacionesBecados", donacionBecadoRepository.findAll());
        return "config/reports/donaciones_becado_fragment :: report";
    }

    // ==========================================
    // REPORTE: DONACIONES POR BECADO (PDF)
    // ==========================================
    @GetMapping("/config/reportes/donaciones-becado-pdf")
    public void reporteDonacionesBecadoPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Donaciones_Becados.pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Estilos de fuente
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            Paragraph title = new Paragraph("REPORTE DE DONACIONES POR BECADO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Crear tabla con 5 columnas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Definir anchos de columnas
            float[] columnWidths = {2f, 2f, 1f, 1.5f, 3f};
            table.setWidths(columnWidths);

            // Encabezados de la tabla
            String[] headers = {"Becado", "Donación Ref.", "Monto", "Fecha", "Observación"};
            for (String columnHeader : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(columnHeader, headerFont));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                table.addCell(cell);
            }

            // Llenar tabla con datos
            for (DonacionBecado db : donacionBecadoRepository.findAll()) {
                String nombreBecado = (db.getBecado() != null && db.getBecado().getInformacionPersonal() != null)
                        ? db.getBecado().getInformacionPersonal().getNombre() + " " + db.getBecado().getInformacionPersonal().getApellidoPat()
                        : "N/A";

                table.addCell(new Phrase(nombreBecado, dataFont));
                table.addCell(new Phrase(db.getDonacion() != null ? db.getDonacion().getNombre() : "S/N", dataFont));
                table.addCell(new Phrase("Bs. " + (db.getCantidad() != null ? db.getCantidad() : "0"), dataFont));
                table.addCell(new Phrase(db.getFecha() != null ? db.getFecha().toString() : "S/F", dataFont));
                table.addCell(new Phrase(db.getObservacion() != null ? db.getObservacion() : "", dataFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // --- REPORTE: HISTORIAL MÉDICO ---

    @GetMapping("/config/reportes/historial-medico-html")
    public String reporteHistorialMedicoHtml(Model model) {
        model.addAttribute("historiales", historialMedicoRepository.findAll());
        return "config/reports/historial_medico_fragment :: report";
    }

    @GetMapping("/config/reportes/historial-medico-pdf")
    public void reporteHistorialMedicoPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Historial_Medico.pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

            Paragraph title = new Paragraph("REPORTE HISTORIAL MÉDICO DE BECADOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1.5f, 2f, 1.5f, 3.5f, 1f, 1.5f});

            String[] headers = {"Fecha", "Becado", "Médico", "Diagnóstico/Detalle", "Costo", "Documento"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (HistorialMedico hm : historialMedicoRepository.findAll()) {
                table.addCell(new Phrase(hm.getFecha() != null ? hm.getFecha().toString() : "", dataFont));
                table.addCell(new Phrase(hm.getBecado() != null ? hm.getBecado().getInformacionPersonal().getNombre() + " " + hm.getBecado().getInformacionPersonal().getApellidoPat() : "N/A", dataFont));
                table.addCell(new Phrase(hm.getMedico() != null ? hm.getMedico().getInformacionPersonal().getNombre() : "N/A", dataFont));
                table.addCell(new Phrase(hm.getDetalle() != null ? hm.getDetalle() : "", dataFont));
                table.addCell(new Phrase("Bs. " + (hm.getCosto() != null ? hm.getCosto() : "0"), dataFont));
                table.addCell(new Phrase(hm.getDocumento() != null ? "SÍ" : "NO", dataFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // --- REPORTE: HISTORIAL ACADÉMICO ---

    @GetMapping("/config/reportes/historial-academico-html")
    public String reporteHistorialAcademicoHtml(Model model) {
        model.addAttribute("historiales", historialAcademicoRepository.findAll());
        return "config/reports/historial_academico_fragment :: report";
    }

    @GetMapping("/config/reportes/historial-academico-pdf")
    public void reporteHistorialAcademicoPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Historial_Academico.pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

            Paragraph title = new Paragraph("REPORTE HISTORIAL ACADÉMICO DE BECADOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2f, 1.5f, 1.5f, 1.5f, 3.5f});

            String[] headers = {"Becado", "Fecha", "Tipo", "Rendimiento", "Observaciones"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (HistorialAcademico ha : historialAcademicoRepository.findAll()) {
                String nombre = (ha.getBecado() != null && ha.getBecado().getInformacionPersonal() != null)
                        ? ha.getBecado().getInformacionPersonal().getNombre() + " " + ha.getBecado().getInformacionPersonal().getApellidoPat()
                        : "N/A";

                table.addCell(new Phrase(nombre, dataFont));
                table.addCell(new Phrase(ha.getFecha() != null ? ha.getFecha().toString() : "", dataFont));
                table.addCell(new Phrase(ha.getTipo() != null ? ha.getTipo() : "", dataFont));
                table.addCell(new Phrase(ha.getRendimiento() != null ? ha.getRendimiento() : "", dataFont));
                table.addCell(new Phrase(ha.getObservacion() != null ? ha.getObservacion() : "", dataFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // --- REPORTE: PADRINOS ---

    @GetMapping("/config/reportes/padrinos-html")
    public String reportePadrinosHtml(Model model) {
        model.addAttribute("padrinos", padrinoRepository.findAll());
        return "config/reports/padrinos_fragment :: report";
    }

    @GetMapping("/config/reportes/padrinos-pdf")
    public void reportePadrinosPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Padrinos.pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

            Paragraph title = new Paragraph("REPORTE GENERAL DE PADRINOS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2.5f, 1.5f, 1.5f, 2.5f, 1.5f});

            String[] headers = {"Nombre Completo", "CI", "Teléfono", "Correo Electrónico", "Estado"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                table.addCell(cell);
            }

            for (Padrino p : padrinoRepository.findAll()) {
                String nombre = (p.getInformacionPersonal() != null)
                        ? p.getInformacionPersonal().getNombre() + " " + p.getInformacionPersonal().getApellidoPat()
                        : "N/A";
                String ci = (p.getInformacionPersonal() != null) ? p.getInformacionPersonal().getCi() : "N/A";
                String telf = (p.getInformacionPersonal() != null && p.getInformacionPersonal().getTelefonoCel() != null)
                        ? p.getInformacionPersonal().getTelefonoCel().toString() : "S/N";
                String email = (p.getInformacionPersonal() != null) ? p.getInformacionPersonal().getCorreoE() : "S/N";

                table.addCell(new Phrase(nombre, dataFont));
                table.addCell(new Phrase(ci, dataFont));
                table.addCell(new Phrase(telf, dataFont));
                table.addCell(new Phrase(email, dataFont));
                table.addCell(new Phrase(p.getEstado() != null ? p.getEstado() : "ACTIVO", dataFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // --- REPORTE: DONACIONES GENERAL ---

    @GetMapping("/config/reportes/donaciones-general-html")
    public String reporteDonacionesGeneralHtml(Model model) {
        model.addAttribute("donaciones", donacionRepository.findAll());
        return "config/reports/donaciones_general_fragment :: report";
    }

    @GetMapping("/config/reportes/donaciones-general-pdf")
    public void reporteDonacionesGeneralPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Donaciones_General.pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

            Paragraph title = new Paragraph("REPORTE GENERAL DE DONACIONES RECIBIDAS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1.5f, 3f, 2f, 2.5f, 1.5f});

            String[] headers = {"Fecha", "Referencia/Detalle", "Categoría", "Donante", "Monto (Bs)"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);
                table.addCell(cell);
            }

            for (Donacion d : donacionRepository.findAll()) {
                table.addCell(new Phrase(d.getFecha() != null ? d.getFecha().toString() : "", dataFont));
                table.addCell(new Phrase(d.getNombre() != null ? d.getNombre() : "", dataFont));
                table.addCell(new Phrase(d.getTipoDonacion() != null ? d.getTipoDonacion().getNombre() : "S/C", dataFont));

                // Determinar el donante (Padrino o Institución)
                String donante = "Anónimo";
                if (d.getPadrino() != null && d.getPadrino().getInformacionPersonal() != null) {
                    donante = d.getPadrino().getInformacionPersonal().getNombre() + " " + d.getPadrino().getInformacionPersonal().getApellidoPat();
                } else if (d.getInstitucion() != null) {
                    donante = d.getInstitucion().getNombre();
                }
                table.addCell(new Phrase(donante, dataFont));

                table.addCell(new Phrase(d.getCantidad() != null ? d.getCantidad().toString() : "0", dataFont));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // --- REPORTE: FUNDACIÓN GENERAL (RESUMEN EJECUTIVO) ---

    @GetMapping("/config/reportes/fundacion-general-html")
    public String reporteFundacionGeneralHtml(Model model) {
        model.addAttribute("totalBecados", becadoRepository.count());
        model.addAttribute("totalPadrinos", padrinoRepository.count());
        model.addAttribute("totalDonaciones", donacionRepository.count());
        model.addAttribute("totalSedes", centroApoyoRepository.count());
        model.addAttribute("totalMedicos", medicoRepository.count());

        double sumaTotal = donacionRepository.findAll().stream()
                .mapToDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0).sum();
        model.addAttribute("recaudadoTotal", sumaTotal);

        return "config/reports/fundacion_general_fragment :: report";
    }

    @GetMapping("/config/reportes/fundacion-general-pdf")
    public void reporteFundacionGeneralPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_General_Fundacion.pdf");

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            Paragraph title = new Paragraph("ESTADO GENERAL DE LA FUNDACIÓN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("Generado el: " + LocalDate.now()));
            document.add(new Paragraph(" "));

            // Sección Estadísticas en el PDF
            document.add(new Paragraph("INDICADORES CLAVE", subTitleFont));
            document.add(new Paragraph("------------------------------------------------------------"));
            document.add(new Paragraph("Total de Becados registrados: " + becadoRepository.count(), dataFont));
            document.add(new Paragraph("Total de Padrinos activos: " + padrinoRepository.count(), dataFont));
            document.add(new Paragraph("Sedes de Apoyo (C.E.A.): " + centroApoyoRepository.count(), dataFont));
            document.add(new Paragraph("Cuerpo Médico asociado: " + medicoRepository.count(), dataFont));

            double total = donacionRepository.findAll().stream()
                    .mapToDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0).sum();

            document.add(new Paragraph(" "));
            document.add(new Paragraph("RESUMEN FINANCIERO", subTitleFont));
            document.add(new Paragraph("------------------------------------------------------------"));
            document.add(new Paragraph("Total Recaudado Histórico: Bs. " + total, dataFont));
            document.add(new Paragraph("Número de Donaciones recibidas: " + donacionRepository.count(), dataFont));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ==========================================
    // MÓDULO: ASIGNACIÓN DE ROLES (User-Group)
    // ==========================================
    @GetMapping("/config/usuarios-grupos")
    public String listarUsuariosGrupos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<SfGuardUserGroup> pg = (buscar != null && !buscar.isEmpty()) ?
                userGroupRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                userGroupRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("lista", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/user_group_lista";
    }

    @GetMapping("/config/usuarios-grupos/nuevo")
    public String nuevoUsuarioGrupo(Model model) {
        model.addAttribute("userGroup", new SfGuardUserGroup());
        model.addAttribute("usuarios", userRepository.findAll());
        model.addAttribute("grupos", groupRepository.findAll());
        return "config/user_group_formulario";
    }

    @PostMapping("/config/usuarios-grupos/guardar")
    @Transactional
    public String guardarUsuarioGrupo(@ModelAttribute SfGuardUserGroup ug) {
        if (ug.getCreatedAt() == null) ug.setCreatedAt(LocalDateTime.now());
        ug.setUpdatedAt(LocalDateTime.now());
        userGroupRepository.save(ug);
        return "redirect:/config/usuarios-grupos";
    }

    @GetMapping("/config/usuarios-grupos/eliminar/{userId}/{groupId}")
    @Transactional
    public String eliminarUsuarioGrupo(@PathVariable Long userId, @PathVariable Long groupId) {
        SfGuardUserGroupId id = new SfGuardUserGroupId(userId, groupId);
        userGroupRepository.deleteById(id);
        return "redirect:/config/usuarios-grupos";
    }
    // ==========================================
    // MÓDULO: PERMISOS POR GRUPO
    // ==========================================
    @GetMapping("/config/grupos-permisos")
    public String listarGruposPermisos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<SfGuardGroupPermission> pg = (buscar != null && !buscar.isEmpty()) ?
                groupPermissionRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                groupPermissionRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("lista", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/group_permission_lista";
    }

    @GetMapping("/config/grupos-permisos/nuevo")
    public String nuevoGrupoPermiso(Model model) {
        model.addAttribute("groupPermission", new SfGuardGroupPermission());
        model.addAttribute("grupos", groupRepository.findAll());
        model.addAttribute("permisos", permissionRepository.findAll());
        return "config/group_permission_formulario";
    }

    @PostMapping("/config/grupos-permisos/guardar")
    @Transactional
    public String guardarGrupoPermiso(@ModelAttribute SfGuardGroupPermission gp) {
        gp.setCreatedAt(LocalDateTime.now());
        gp.setUpdatedAt(LocalDateTime.now());
        groupPermissionRepository.save(gp);
        return "redirect:/config/grupos-permisos";
    }

    @GetMapping("/config/grupos-permisos/eliminar/{groupId}/{permId}")
    @Transactional
    public String eliminarGrupoPermiso(@PathVariable Long groupId, @PathVariable Long permId) {
        SfGuardGroupPermissionId id = new SfGuardGroupPermissionId(groupId, permId);
        groupPermissionRepository.deleteById(id);
        return "redirect:/config/grupos-permisos";
    }
    // ==========================================
    // MÓDULO: NOTAS INTERNAS (MEMOS/REPORTES)
    // ==========================================
    @GetMapping("/config/memos")
    public String listarMemos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String buscar,
            Model model) {

        Page<Reporte> pg = (buscar != null && !buscar.isEmpty()) ?
                reporteRepository.buscarPorTermino(buscar, PageRequest.of(page, 10)) :
                reporteRepository.findAll(PageRequest.of(page, 10));

        model.addAttribute("lista", pg.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("buscar", buscar);
        return "config/memo_lista";
    }

    @GetMapping("/config/memos/nuevo")
    public String nuevoMemo(Model model) {
        model.addAttribute("memo", new Reporte());
        model.addAttribute("personas", infoRepository.findAll()); // Lista de todas las personas
        return "config/memo_formulario";
    }

    @PostMapping("/config/memos/guardar")
    public String guardarMemo(@ModelAttribute Reporte memo) {
        reporteRepository.save(memo);
        return "redirect:/config/memos";
    }

    @GetMapping("/config/memos/editar/{id}")
    public String editarMemo(@PathVariable Integer id, Model model) {
        model.addAttribute("memo", reporteRepository.findById(id).orElseThrow());
        model.addAttribute("personas", infoRepository.findAll());
        return "config/memo_formulario";
    }

    @GetMapping("/config/memos/eliminar/{id}")
    public String eliminarMemo(@PathVariable Integer id) {
        reporteRepository.deleteById(id);
        return "redirect:/config/memos";
    }
    // --- REPORTE: CONSOLIDADO DE DONACIONES ---

    @GetMapping("/config/reportes/donaciones-consolidado-html")
    public String reporteDonacionesConsolidadoHtml(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            Model model) {

        List<Donacion> donaciones;
        if (inicio != null && fin != null) {
            donaciones = donacionRepository.findAll().stream()
                    .filter(d -> d.getFecha() != null && !d.getFecha().isBefore(inicio) && !d.getFecha().isAfter(fin))
                    .collect(Collectors.toList());
        } else {
            donaciones = donacionRepository.findAll();
        }

        // Agrupar totales por tipo
        Map<String, Double> totalesPorTipo = donaciones.stream()
                .collect(Collectors.groupingBy(
                        d -> d.getTipoDonacion() != null ? d.getTipoDonacion().getNombre() : "Sin Categoría",
                        Collectors.summingDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0)
                ));

        model.addAttribute("totalesPorTipo", totalesPorTipo);
        model.addAttribute("totalGeneral", totalesPorTipo.values().stream().mapToDouble(Double::doubleValue).sum());
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);

        return "config/reports/donaciones_consolidado_fragment :: report";
    }

    @GetMapping("/config/reportes/donaciones-consolidado-pdf")
    public void reporteDonacionesConsolidadoPdf(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Consolidado_Donaciones.pdf");

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            Paragraph title = new Paragraph("REPORTE CONSOLIDADO DE DONACIONES", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            String rango = (inicio != null && fin != null) ? "Rango: " + inicio + " al " + fin : "Historial Completo";
            Paragraph sub = new Paragraph(rango, dataFont);
            sub.setAlignment(Element.ALIGN_CENTER);
            document.add(sub);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);
            table.addCell(new PdfPCell(new Phrase("Categoría", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Total Recaudado (Bs.)", headerFont)));

            List<Donacion> donaciones;
            if (inicio != null && fin != null) {
                donaciones = donacionRepository.findAll().stream()
                        .filter(d -> d.getFecha() != null && !d.getFecha().isBefore(inicio) && !d.getFecha().isAfter(fin))
                        .toList();
            } else {
                donaciones = donacionRepository.findAll();
            }

            Map<String, Double> totales = donaciones.stream()
                    .collect(Collectors.groupingBy(
                            d -> d.getTipoDonacion() != null ? d.getTipoDonacion().getNombre() : "Sin Categoría",
                            Collectors.summingDouble(d -> d.getCantidad() != null ? d.getCantidad() : 0.0)
                    ));

            for (Map.Entry<String, Double> entry : totales.entrySet()) {
                table.addCell(new Phrase(entry.getKey(), dataFont));
                table.addCell(new Phrase("Bs. " + String.format("%.2f", entry.getValue()), dataFont));
            }

            document.add(table);
            document.add(new Paragraph("\nTotal General: Bs. " + String.format("%.2f", totales.values().stream().mapToDouble(D -> D).sum()), headerFont));

            document.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
