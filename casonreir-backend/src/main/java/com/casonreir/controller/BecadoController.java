package com.casonreir.controller;

import com.casonreir.dto.BecadoDTO;
import com.casonreir.model.Becado;
import com.casonreir.repository.BecadoRepository;
import com.casonreir.service.BecadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de Becados.
 * Proporciona endpoints para listar y crear becados integrando la lógica de negocio.
 */
@RestController
@RequestMapping("/api/becados")
public class BecadoController {

    @Autowired
    private BecadoRepository becadoRepository;

    @Autowired
    private BecadoService becadoService;

    /**
     * Obtiene la lista completa de becados.
     * GET http://localhost:8082/api/becados
     */
    @GetMapping
    public List<Becado> listarTodos() {
        return becadoRepository.findAll();
    }

    /**
     * Crea un nuevo becado junto con su información personal de forma atómica.
     * POST http://localhost:8082/api/becados
     */
    @PostMapping
    public ResponseEntity<Becado> crearBecado(@RequestBody BecadoDTO dto) {
        Becado nuevoBecado = becadoService.guardarBecadoCompleto(dto);
        return ResponseEntity.ok(nuevoBecado);
    }
}