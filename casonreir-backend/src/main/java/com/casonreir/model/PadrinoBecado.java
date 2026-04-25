package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.io.Serializable;

@Entity
@Table(name = "padrino_becado")
@Data
@IdClass(PadrinoBecadoId.class) // Clase para la clave primaria compuesta
public class PadrinoBecado implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_becado")
    private Becado becado;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_padrino")
    private Padrino padrino;

    @Column(name = "ini_bp")
    private LocalDate fechaInicio;

    @Column(name = "fin_bp")
    private LocalDate fechaFin;

    @Column(name = "estado_bp", length = 8)
    private String estado;

    @Column(name = "name1_bp")
    private String notas; // Campo para observaciones o notas
}

// Clase para la clave primaria compuesta
@Embeddable
@Data
class PadrinoBecadoId implements Serializable {
    private Integer becado;
    private Integer padrino;
}
