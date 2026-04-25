package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_centro_salud")
@Data
public class TipoCentroSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cs")
    private Integer id;

    @Column(name = "nombre_tcs", length = 100)
    private String nombre;

    @Column(name = "estado_tcs", length = 8)
    private String estado;
}
