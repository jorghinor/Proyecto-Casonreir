package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "actividad_becado")
@Data
public class ActividadBecado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ab")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiado a EAGER
    @JoinColumn(name = "id_becado")
    private Becado becado;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiado a EAGER
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    @Column(name = "fecha_ab")
    private LocalDate fecha;

    @Column(name = "tipo_ab", length = 15)
    private String tipo;

    @Column(name = "name1_ab")
    private String name1;

    @Column(name = "name2_ab")
    private String name2;

    @Column(name = "name3_ab")
    private String name3;
}
