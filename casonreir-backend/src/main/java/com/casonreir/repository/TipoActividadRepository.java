package com.casonreir.repository;

import com.casonreir.model.TipoActividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Integer> {
    @Query("SELECT t FROM TipoActividad t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<TipoActividad> buscarPorNombre(@Param("term") String term, Pageable pageable);

    Page<TipoActividad> findByEstado(String estado, Pageable pageable);

    @Query("""
           SELECT t FROM TipoActividad t
           WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))
             AND t.estado = :estado
           """)
    Page<TipoActividad> buscarPorNombreYEstado(@Param("term") String term, @Param("estado") String estado, Pageable pageable);
}
