package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "encargado")
@Data
public class Encargado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encargado")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo;

    @OneToOne
    @JoinColumn(name = "id_inf_pers")
    private InformacionPersonal informacionPersonal;

    @Column(name = "cargo_enc", length = 80)
    private String cargo;

    @Column(name = "ini_enc")
    private LocalDate fechaInicio;

    @Column(name = "fin_enc")
    private LocalDate fechaFin;

    @Column(name = "estado_enc", length = 8)
    private String estado;
}
