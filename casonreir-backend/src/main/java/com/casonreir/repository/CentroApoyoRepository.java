package com.casonreir.repository;

import com.casonreir.model.CentroApoyo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroApoyoRepository extends JpaRepository<CentroApoyo, Integer> {

    @Query("SELECT c FROM CentroApoyo c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(c.direccion) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<CentroApoyo> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
