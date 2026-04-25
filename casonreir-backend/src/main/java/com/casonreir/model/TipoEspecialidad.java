package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_especialidad")
@Data
public class TipoEspecialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_esp")
    private Integer id;

    @Column(name = "nombre_te", length = 80)
    private String nombre;

    @Column(name = "estado_te", length = 8)
    private String estado;
}
