package com.casonreir.repository;

import com.casonreir.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT m FROM Medico m JOIN m.informacionPersonal ip " +
           "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.ci) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Medico> buscarPorTermino(@Param("term") String term, Pageable pageable);

    Page<Medico> findByEstado(String estado, Pageable pageable);
}
