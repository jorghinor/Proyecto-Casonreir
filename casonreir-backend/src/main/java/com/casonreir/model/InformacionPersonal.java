package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "informacion_personal")
@Data
public class InformacionPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inf_pers")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_pat", nullable = false, length = 100)
    private String apellidoPat;

    @Column(name = "apellido_mat", length = 100)
    private String apellidoMat;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    @Column(name = "foto")
    private String foto;

    @Column(name = "certificado")
    private String certificado;

    @Column(name = "ci", length = 20)
    private String ci;

    @Column(name = "lugar_ci")
    private String lugarCi;

    @Column(name = "est_civil")
    private String estadoCivil;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "lugar_nac")
    private String lugarNac;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_cel")
    private String telefonoCel;

    @Column(name = "correo_e")
    private String correoE;
}
