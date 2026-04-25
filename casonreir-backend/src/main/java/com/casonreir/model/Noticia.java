package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "noticia")
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cea")
    private CentroApoyo centroApoyo; // Si existe id_cea en la tabla noticia

    @Column(name = "nombre_noticia", length = 250)
    private String nombre; // Antes 'titulo'

    @Column(name = "detalle_noticia", columnDefinition = "TEXT")
    private String detalle; // Antes 'contenido'

    @Column(name = "ini_noticia")
    private LocalDate fechaInicio; // Antes 'fecha'

    @Column(name = "fin_noticia")
    private LocalDate fechaFin;

    @Column(name = "estado_noticia", length = 8)
    private String estado;

    // Si 'autor_n' no existe en bd.sql, este campo no debería estar o debería ser mapeado a otra cosa.
    // Basado en bd.sql, 'autor_n' no existe.
    // private String autor;
}
