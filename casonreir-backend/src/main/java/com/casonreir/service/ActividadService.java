package com.casonreir.service;

import com.casonreir.dto.RegistroActividadDTO;
import com.casonreir.model.Actividad;
import com.casonreir.model.ActividadBecado;
import com.casonreir.model.Becado;
import com.casonreir.repository.ActividadBecadoRepository;
import com.casonreir.repository.ActividadRepository;
import com.casonreir.repository.BecadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private BecadoRepository becadoRepository;

    @Autowired
    private ActividadBecadoRepository actividadBecadoRepository;

    /**
     * Procesa el registro masivo de becados en una actividad.
     * La anotación @Transactional asegura que la operación sea atómica.
     */
    @Transactional
    public List<ActividadBecado> registrarBecadosEnActividad(RegistroActividadDTO dto) {
        Actividad actividad = actividadRepository.findById(dto.getActividadId())
                .orElseThrow(() -> new RuntimeException("Error: La actividad con ID " + dto.getActividadId() + " no existe."));

        List<ActividadBecado> registros = new ArrayList<>();

        for (Integer becadoId : dto.getBecadosIds()) {
            Becado becado = becadoRepository.findById(becadoId)
                    .orElseThrow(() -> new RuntimeException("Error: El becado con ID " + becadoId + " no existe."));

            ActividadBecado registro = new ActividadBecado();
            registro.setActividad(actividad);
            registro.setBecado(becado);
            registro.setFecha(LocalDate.now());
            registro.setTipo(dto.getTipoAsistencia());

            registros.add(actividadBecadoRepository.save(registro));
        }
        return registros;
    }
}