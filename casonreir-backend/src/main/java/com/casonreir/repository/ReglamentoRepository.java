package com.casonreir.repository;

import com.casonreir.model.Reglamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReglamentoRepository extends JpaRepository<Reglamento, Integer> {

    @Query("SELECT r FROM Reglamento r WHERE LOWER(r.titulo) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(r.contenido) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Reglamento> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
