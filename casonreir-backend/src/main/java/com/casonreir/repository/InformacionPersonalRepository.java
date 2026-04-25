package com.casonreir.repository;

import com.casonreir.model.InformacionPersonal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionPersonalRepository extends JpaRepository<InformacionPersonal, Integer> {

    @Query("SELECT i FROM InformacionPersonal i WHERE LOWER(i.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(i.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(i.ci) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<InformacionPersonal> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
