package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "centro_salud")
@Data
public class CentroSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro_salud")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_cs")
    private TipoCentroSalud tipoCentroSalud;

    @Column(name = "nombre_cs", length = 200)
    private String nombre;

    @Column(name = "direccion_cs", length = 255)
    private String direccion;

    @Column(name = "telefono_cs", length = 70)
    private String telefono;

    @Column(name = "observacion_cs", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "estado_cs", length = 8)
    private String estado;

    @Column(name = "name1_cs", length = 200)
    private String name1;

    @Column(name = "name2_cs", length = 200)
    private String name2;

    @Column(name = "name3_cs", length = 200)
    private String name3;
}
