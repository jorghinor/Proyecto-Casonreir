package com.casonreir.repository;

import com.casonreir.model.PadrinoBecado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PadrinoBecadoRepository extends JpaRepository<PadrinoBecado, Object> {

    @Query("SELECT pb FROM PadrinoBecado pb JOIN pb.becado b JOIN b.informacionPersonal ib JOIN pb.padrino p JOIN p.informacionPersonal ip " +
           "WHERE LOWER(ib.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
           "OR LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<PadrinoBecado> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
