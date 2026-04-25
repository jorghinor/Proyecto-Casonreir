package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "padrino")
@Data
public class Padrino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_padrino")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_inf_pers")
    private InformacionPersonal informacionPersonal;

    @Column(name = "estado_padrino", length = 8)
    private String estado;

    @Column(name = "profesion", length = 80)
    private String profesion;

    @Column(name = "name1_padrino")
    private String name1;

    @Column(name = "name2_padrino")
    private String name2;
}
