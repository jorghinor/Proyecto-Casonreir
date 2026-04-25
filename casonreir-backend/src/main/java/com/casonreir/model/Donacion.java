package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "donacion")
@Data
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst")
    private Institucion institucion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_padrino")
    private Padrino padrino;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipod")
    private TipoDonacion tipoDonacion;

    @Column(name = "nombre_d")
    private String nombre;

    @Column(name = "fecha_d")
    private LocalDate fecha;

    @Column(name = "cantidad_d")
    private Double cantidad;
}
