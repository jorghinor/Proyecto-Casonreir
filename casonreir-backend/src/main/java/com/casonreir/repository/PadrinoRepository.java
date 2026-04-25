package com.casonreir.repository;

import com.casonreir.model.Padrino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PadrinoRepository extends JpaRepository<Padrino, Integer> {

    @Query("SELECT p FROM Padrino p JOIN p.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.ci) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Padrino> buscarPorTermino(@Param("term") String term, Pageable pageable);
    
    Page<Padrino> findByEstado(String estado, Pageable pageable);
}
