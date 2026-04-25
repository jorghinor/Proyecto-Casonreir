package com.casonreir.repository;

import com.casonreir.model.SfGuardUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SfGuardUserPermissionRepository extends JpaRepository<SfGuardUserPermission, SfGuardUserPermission.SfGuardUserPermissionId> {
}
