package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_donacion")
@Data
public class TipoDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipod")
    private Integer id;

    @Column(name = "nombre_tipod", length = 100)
    private String nombre;

    @Column(name = "estado_tipod", length = 8)
    private String estado;
}