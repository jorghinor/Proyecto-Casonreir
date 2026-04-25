package com.casonreir.repository;

import com.casonreir.model.TipoEspecialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEspecialidadRepository extends JpaRepository<TipoEspecialidad, Integer> {
    @Query("SELECT t FROM TipoEspecialidad t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<TipoEspecialidad> buscarPorNombre(@Param("term") String term, Pageable pageable);
}
