package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "centro_apoyo")
@Data
public class CentroApoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cea")
    private Integer id;

    @Column(name = "nombre_cea", length = 250)
    private String nombre;

    @Column(name = "direccion_cea", length = 250)
    private String direccion;

    @Column(name = "telefono_cea", length = 70)
    private String telefono; // Cambiado de Integer a String para evitar errores de tipo

    @Column(columnDefinition = "TEXT")
    private String mision;

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    @Column(columnDefinition = "TEXT")
    private String historia;

    @Column(name = "name1_cea")
    private String name1;
}
