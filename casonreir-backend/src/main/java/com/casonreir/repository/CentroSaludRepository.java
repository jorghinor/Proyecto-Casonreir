package com.casonreir.repository;

import com.casonreir.model.CentroSalud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroSaludRepository extends JpaRepository<CentroSalud, Integer> {

    @Query("SELECT c FROM CentroSalud c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(c.direccion) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(c.telefono) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<CentroSalud> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
