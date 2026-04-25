package com.casonreir.repository;

import com.casonreir.model.Familia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Integer> {

    @Query("SELECT f FROM Familia f " +
           "WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(f.apellidoPaterno) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<Familia> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
