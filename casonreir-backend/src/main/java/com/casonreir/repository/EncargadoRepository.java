package com.casonreir.repository;

import com.casonreir.model.Encargado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoRepository extends JpaRepository<Encargado, Integer> {
    @Query("SELECT e FROM Encargado e JOIN e.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(e.cargo) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Encargado> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
