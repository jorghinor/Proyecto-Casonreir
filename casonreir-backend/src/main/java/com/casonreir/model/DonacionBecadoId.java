package com.casonreir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// ESTA CLASE DEBE SER PUBLIC Y ESTAR FUERA O DENTRO COMO PUBLIC
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonacionBecadoId implements Serializable {
    private Integer donacion;
    private Integer becado;
}
