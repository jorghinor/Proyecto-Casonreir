package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "historial_academico")
@Data
public class HistorialAcademico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ha")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_becado")
    private Becado becado;

    @Column(name = "fecha_ha")
    private LocalDate fecha;

    @Column(name = "libreta_ha", length = 150)
    private String libreta;

    @Column(name = "observacion_ha", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "rendimiento_ha", length = 30)
    private String rendimiento;

    @Column(name = "tipo_ha", length = 30)
    private String tipo;

    @Column(name = "promedio_ha", precision = 5, scale = 2)
    private BigDecimal promedioLibreta;

    // Campos adicionales del script original
    @Column(name = "name1_ha")
    private String name1;

    @Column(name = "name2_ha")
    private String name2;
}
