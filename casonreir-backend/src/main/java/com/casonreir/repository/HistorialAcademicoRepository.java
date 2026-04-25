package com.casonreir.repository;

import com.casonreir.model.HistorialAcademico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialAcademicoRepository extends JpaRepository<HistorialAcademico, Integer> {

    @Query("SELECT h FROM HistorialAcademico h JOIN h.becado b JOIN b.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<HistorialAcademico> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
