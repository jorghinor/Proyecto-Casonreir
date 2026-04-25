package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_actividad")
@Data
public class TipoActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_act")
    private Integer id;

    @Column(name = "nombre_tipo_act", length = 150)
    private String nombre;

    @Column(name = "estado_tipo_act", length = 8)
    private String estado;
}