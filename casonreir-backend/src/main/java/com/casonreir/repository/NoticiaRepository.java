package com.casonreir.repository;

import com.casonreir.model.Noticia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

    @Query("SELECT n FROM Noticia n WHERE LOWER(n.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(n.detalle) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Noticia> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
