package com.casonreir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "donacion_becado")
@Data
@IdClass(DonacionBecadoId.class)
public class DonacionBecado implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_donacion")
    private Donacion donacion;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_becado")
    private Becado becado;

    @Column(name = "fecha_db")
    private LocalDate fecha;

    @Column(name = "cantidad_db")
    private Double cantidad;

    @Column(name = "observacion_db", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "name1_db")
    private String name1;

    @Column(name = "name2_db")
    private String name2;

    @Column(name = "name3_db")
    private String name3;
}

