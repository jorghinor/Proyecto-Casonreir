package com.casonreir.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BecadoDTO {
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String ci;
    private LocalDate fechaNac;
    private String direccion;
    private Integer numHermanos;
    private LocalDate ingresoBecado;
    private String estadoBecado;
}