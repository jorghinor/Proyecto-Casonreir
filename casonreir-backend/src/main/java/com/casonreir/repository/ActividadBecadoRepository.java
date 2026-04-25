package com.casonreir.repository;

import com.casonreir.model.ActividadBecado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ActividadBecadoRepository extends JpaRepository<ActividadBecado, Integer> {

    @Query("SELECT ab FROM ActividadBecado ab JOIN ab.becado b JOIN b.informacionPersonal ip " +
            "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<ActividadBecado> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
