package com.casonreir.repository;

import com.casonreir.model.Institucion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    @Query("SELECT i FROM Institucion i WHERE LOWER(i.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(i.direccion) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Institucion> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
