package com.casonreir.repository;

import com.casonreir.model.TipoDonacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDonacionRepository extends JpaRepository<TipoDonacion, Integer> {
    @Query("SELECT t FROM TipoDonacion t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<TipoDonacion> buscarPorNombre(@Param("term") String term, Pageable pageable);

    Page<TipoDonacion> findByEstado(String estado, Pageable pageable);

    @Query("""
           SELECT t FROM TipoDonacion t
           WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))
             AND t.estado = :estado
           """)
    Page<TipoDonacion> buscarPorNombreYEstado(@Param("term") String term, @Param("estado") String estado, Pageable pageable);
}
