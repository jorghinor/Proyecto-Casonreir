package com.casonreir.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO para el registro masivo de becados en una actividad.
 * Reemplaza la lógica de recepción de parámetros de sfWebRequest.
 */
@Data
public class RegistroActividadDTO {
    private Integer actividadId;
    private List<Integer> becadosIds;
    private String tipoAsistencia;
}