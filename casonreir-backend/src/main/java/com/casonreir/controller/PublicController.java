package com.casonreir.controller;

import com.casonreir.model.Becado;
import com.casonreir.model.HistorialAcademico;
import com.casonreir.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class PublicController {

    @Autowired private NoticiaRepository noticiaRepository;
    @Autowired private ReglamentoRepository reglamentoRepository;
    @Autowired private CentroApoyoRepository centroApoyoRepository;
    @Autowired private BecadoRepository becadoRepository;
    @Autowired private HistorialAcademicoRepository historialAcademicoRepository;
    @Autowired private PadrinoRepository padrinoRepository;

    @GetMapping("/web")
    public String landingPage(@RequestParam(defaultValue = "0") int becadosPage, Model model) {
        // Cargamos noticias y reglamentos para mostrarlos en la web
        model.addAttribute("noticias", noticiaRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("reglamentos", reglamentoRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("centros", centroApoyoRepository.findAll());
        Page<Becado> becados = becadoRepository.findAll(PageRequest.of(Math.max(becadosPage, 0), 8, Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("becados", becados.getContent());
        model.addAttribute("becadosCurrentPage", becados.getNumber());
        model.addAttribute("becadosTotalPages", becados.getTotalPages());

        Map<Integer, List<Double>> notasPorBecado = new HashMap<>();
        for (HistorialAcademico h : historialAcademicoRepository.findAll()) {
            if (h.getBecado() == null || h.getBecado().getId() == null) continue;
            Double nota = h.getPromedioLibreta() != null
                    ? h.getPromedioLibreta().doubleValue()
                    : scoreFromRendimiento(h.getRendimiento());
            if (nota == null) continue;
            notasPorBecado.computeIfAbsent(h.getBecado().getId(), k -> new ArrayList<>()).add(nota);
        }

        Map<Integer, Double> promedioBecado = new HashMap<>();
        notasPorBecado.forEach((id, notas) -> promedioBecado.put(id, notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));
        model.addAttribute("promedioBecado", promedioBecado);

        List<Integer> mejoresBecados = promedioBecado.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        model.addAttribute("mejoresBecados", mejoresBecados);
        model.addAttribute("padrinos", padrinoRepository.findAll());
        return "public/index";
    }

    private Double scoreFromRendimiento(String rendimiento) {
        if (rendimiento == null) return null;
        return switch (rendimiento.trim().toUpperCase(Locale.ROOT)) {
            case "EXCELENTE" -> 95.0;
            case "MUY BUENO" -> 85.0;
            case "BUENO" -> 75.0;
            case "REGULAR" -> 65.0;
            case "REPROBADO", "APOYO NECESARIO" -> 51.0;
            default -> null;
        };
    }
}
