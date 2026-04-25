package com.casonreir.repository;

import com.casonreir.model.Donacion;
import com.casonreir.model.TipoDonacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Integer> {

    @Query("SELECT d FROM Donacion d LEFT JOIN d.padrino p LEFT JOIN p.informacionPersonal ip " +
           "WHERE LOWER(d.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Donacion> buscarPorTermino(@Param("term") String term, Pageable pageable);
    
    Page<Donacion> findByTipoDonacion(TipoDonacion tipo, Pageable pageable);
}
