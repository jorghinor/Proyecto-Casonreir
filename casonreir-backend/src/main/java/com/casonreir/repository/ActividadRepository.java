package com.casonreir.repository;

import com.casonreir.model.Actividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    @Query("SELECT a FROM Actividad a " +
           "WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(a.detalle) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Actividad> buscarPorTermino(@Param("term") String term, Pageable pageable);
    
    Page<Actividad> findByEstado(String estado, Pageable pageable);
}
