package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "actividad")
@Data
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_act")
    private TipoActividad tipoActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo;

    @Column(name = "nombre_actividad", length = 250)
    private String nombre;

    @Column(name = "detalle_actividad", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "ini_actividad")
    private LocalDate fechaInicio;

    @Column(name = "fin_actividad")
    private LocalDate fechaFin;

    @Column(name = "estado_actividad", length = 8)
    private String estado;
}