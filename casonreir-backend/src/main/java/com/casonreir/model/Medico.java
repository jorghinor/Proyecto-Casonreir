package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medico")
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_med")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_inf_pers")
    private InformacionPersonal informacionPersonal;

    @ManyToOne
    @JoinColumn(name = "id_tipo_esp")
    private TipoEspecialidad especialidad;

    @Column(name = "estado_med", length = 8)
    private String estado;
}
