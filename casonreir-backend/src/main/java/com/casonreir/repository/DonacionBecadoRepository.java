package com.casonreir.repository;

import com.casonreir.model.DonacionBecado;
import com.casonreir.model.DonacionBecadoId; // Importa la clase ID
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionBecadoRepository extends JpaRepository<DonacionBecado, DonacionBecadoId> {
    //                                                                            ^^^^^^^^^^^^^^^^
    // Aquí es donde se define que la clave primaria es el objeto DonacionBecadoId

    @Query("SELECT db FROM DonacionBecado db JOIN db.becado b JOIN b.informacionPersonal ip " +
            "WHERE LOWER(ip.nombre) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(ip.apellidoPat) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<DonacionBecado> buscarPorBecado(@Param("term") String term, Pageable pageable);
}
