package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "historial_medico")
@Data
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hm")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_becado")
    private Becado becado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_med")
    private Medico medico;

    @Column(name = "fecha_hm")
    private LocalDate fecha;

    @Column(name = "historial_hm", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "costo_hm")
    private Double costo;

    // Campos "Fantasma" restaurados para recetas, documentos o info extra
    @Column(name = "name1_hm", length = 255)
    private String documento;

    @Column(name = "name2_hm")
    private String name2; // Informe de Especialista / Documento 2

    @Column(name = "name3_hm")
    private String name3; // Datos adicionales 1

    @Column(name = "name4_hm")
    private String name4; // Datos adicionales 2
}
