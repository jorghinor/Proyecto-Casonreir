package com.casonreir.repository;

import com.casonreir.model.SfGuardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SfGuardGroupRepository extends JpaRepository<SfGuardGroup, Long> {

    /**
     * Busca un grupo por su nombre exacto.
     * Utilizado para validaciones de seguridad y configuración inicial.
     */
    Optional<SfGuardGroup> findByName(String name);
}
