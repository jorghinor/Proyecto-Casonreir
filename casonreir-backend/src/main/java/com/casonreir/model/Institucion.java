package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "institucion")
@Data
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inst")
    private Integer id;

    @Column(name = "nombre_inst", length = 150)
    private String nombre;

    @Column(name = "direccion_inst", length = 200)
    private String direccion;

    @Column(name = "telefono_inst", length = 50)
    private String telefono;
}
