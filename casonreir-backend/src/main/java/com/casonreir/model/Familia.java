package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "familia")
@Data
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fam")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_becado", nullable = false)
    private Becado becado;

    @Column(name = "nombre_f", length = 100)
    private String nombre;

    @Column(name = "apellido_patf", length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_matf", length = 100)
    private String apellidoMaterno;

    @Column(name = "direccion_f", length = 255)
    private String direccion;

    @Column(name = "telefono_f", length = 50)
    private String telefono;

    @Column(name = "parentesco_f", length = 80)
    private String parentesco;

    @Column(name = "observacion_f", columnDefinition = "TEXT")
    private String observacion;
}