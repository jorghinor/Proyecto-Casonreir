package com.casonreir.repository;

import com.casonreir.model.SfGuardPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SfGuardPermissionRepository extends JpaRepository<SfGuardPermission, Long> {
}
