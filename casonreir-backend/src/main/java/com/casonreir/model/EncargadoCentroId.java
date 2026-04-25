package com.casonreir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoCentroId implements Serializable {
    private Integer encargado;    // Coincide con el nombre del atributo en EncargadoCentro
    private Integer centroApoyo;  // Coincide con el nombre del atributo en EncargadoCentro
}
