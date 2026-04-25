package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "becado")
@Data
public class Becado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_becado")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inf_pers")
    private InformacionPersonal informacionPersonal;

    @Column(name = "num_hermanos")
    private Integer numHermanos;

    @Column(name = "ingreso_becado")
    private LocalDate ingresoBecado;

    @Column(name = "salida_becado")
    private LocalDate salidaBecado;

    @Column(name = "estado_becado", length = 8)
    private String estadoBecado;

    @Column(name = "name1_becado", length = 250)
    private String hobby;

    @Column(name = "name2_becado", length = 250)
    private String aspiracion;

    @Column(name = "name3_becado", length = 250)
    private String talento;

    @Column(name = "name4_becado", length = 250)
    private String observacionExtra;
}
