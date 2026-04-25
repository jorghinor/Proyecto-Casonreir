package com.casonreir.repository;

import com.casonreir.model.Reporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    @Query("SELECT r FROM Reporte r WHERE LOWER(r.titulo) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(r.persona.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Reporte> buscarPorTermino(@Param("term") String term, Pageable pageable);
}

