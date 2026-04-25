package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reglamento")
@Data
public class Reglamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regla")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo;

    @Column(name = "reglamento_r", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "name1_r", length = 250)
    private String titulo;

    @Column(name = "name2_r", length = 250)
    private String descripcionCorta;
}
