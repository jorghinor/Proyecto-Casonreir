package com.casonreir.repository;

import com.casonreir.model.TipoCentroSalud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCentroSaludRepository extends JpaRepository<TipoCentroSalud, Integer> {
    @Query("SELECT t FROM TipoCentroSalud t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<TipoCentroSalud> buscarPorNombre(@Param("term") String term, Pageable pageable);
}
