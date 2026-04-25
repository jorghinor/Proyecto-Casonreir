package com.casonreir.repository;

import com.casonreir.model.SfGuardGroupPermission;
import com.casonreir.model.SfGuardGroupPermissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SfGuardGroupPermissionRepository extends JpaRepository<SfGuardGroupPermission, SfGuardGroupPermissionId> {

    @Query("SELECT gp FROM SfGuardGroupPermission gp WHERE LOWER(gp.group.name) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(gp.permission.name) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<SfGuardGroupPermission> buscarPorTermino(@Param("term") String term, Pageable pageable);
}
