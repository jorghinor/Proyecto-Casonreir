package com.casonreir.repository;

import com.casonreir.model.Becado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BecadoRepository extends JpaRepository<Becado, Integer> {

    @Query("SELECT b FROM Becado b JOIN b.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.ci) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Becado> buscarPorTermino(@Param("term") String term, Pageable pageable);
    
    Page<Becado> findByEstadoBecado(String estado, Pageable pageable);
}
