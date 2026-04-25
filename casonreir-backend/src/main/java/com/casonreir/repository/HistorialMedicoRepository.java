package com.casonreir.repository;

import com.casonreir.model.HistorialMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {

    @Query("SELECT h FROM HistorialMedico h JOIN h.becado b JOIN b.informacionPersonal ip " +
            "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(h.detalle) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(h.documento) LIKE LOWER(CONCAT('%', :term, '%'))") // Añadido buscar por documento
    Page<HistorialMedico> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
