package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reporte")
@Data
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer id;

    // Relación con la persona a la que se le hace la nota
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    private InformacionPersonal persona;

    @Column(name = "nivel_reporte", nullable = false)
    private Integer nivel; // Ej: 1 (Bajo), 2 (Medio), 3 (Urgente)

    @Column(name = "titulo_reporte", length = 100, nullable = false)
    private String titulo;

    @Column(name = "texto_reporte", length = 1000, nullable = false)
    private String contenido;
}
