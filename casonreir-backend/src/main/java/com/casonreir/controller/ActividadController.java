package com.casonreir.controller;

import com.casonreir.dto.RegistroActividadDTO;
import com.casonreir.model.Actividad;
import com.casonreir.model.ActividadBecado;
import com.casonreir.repository.ActividadRepository;
import com.casonreir.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ActividadService actividadService;

    /**
     * Lista todas las actividades disponibles.
     * Reemplaza la funcionalidad básica del index en Symfony.
     */
    @GetMapping
    public List<Actividad> listarTodas() {
        return actividadRepository.findAll();
    }

    /**
     * Obtiene el detalle de una actividad específica por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Actividad> obtenerPorId(@PathVariable Integer id) {
        return actividadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para registrar masivamente becados en una actividad.
     * Utiliza la lógica de negocio definida en ActividadService.
     */
    @PostMapping("/registrar")
    public ResponseEntity<List<ActividadBecado>> registrarBecados(@RequestBody RegistroActividadDTO dto) {
        List<ActividadBecado> registros = actividadService.registrarBecadosEnActividad(dto);
        return ResponseEntity.ok(registros);
    }
}