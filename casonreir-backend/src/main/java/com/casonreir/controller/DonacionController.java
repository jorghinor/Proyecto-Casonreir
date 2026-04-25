package com.casonreir.controller;

import com.casonreir.model.Donacion;
import com.casonreir.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {

    @Autowired
    private DonacionRepository donacionRepository;

    @GetMapping
    public List<Donacion> listarTodas() {
        return donacionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Donacion> crear(@RequestBody Donacion donacion) {
        return ResponseEntity.ok(donacionRepository.save(donacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtenerPorId(@PathVariable Integer id) {
        return donacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}