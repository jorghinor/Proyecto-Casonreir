package com.casonreir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "encargado_centro")
@IdClass(EncargadoCentroId.class)
@Data
public class EncargadoCentro implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_encargado")
    private Encargado encargado;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo;

    @Column(name = "ini_enc_cen")
    private LocalDate fechaInicio;

    @Column(name = "fin_enc_cen")
    private LocalDate fechaFin;

    @Column(name = "estato_enc_cen", length = 8)
    private String estado;
}
