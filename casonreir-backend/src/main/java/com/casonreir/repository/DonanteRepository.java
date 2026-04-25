package com.casonreir.repository;

import com.casonreir.model.Donante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Integer> {

    @Query("SELECT d FROM Donante d JOIN d.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Donante> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
