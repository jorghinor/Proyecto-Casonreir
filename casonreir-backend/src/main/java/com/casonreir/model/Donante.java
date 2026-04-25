package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "donante")
@Data
public class Donante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_don")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_inf_pers")
    private InformacionPersonal informacionPersonal;

    @Column(name = "tipo_don", length = 100)
    private String tipoDonacion;

    @Column(name = "estado_don", length = 8)
    private String estado;
}
