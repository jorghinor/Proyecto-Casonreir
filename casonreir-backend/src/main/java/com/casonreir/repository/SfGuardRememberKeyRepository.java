package com.casonreir.repository;

import com.casonreir.model.SfGuardRememberKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SfGuardRememberKeyRepository extends JpaRepository<SfGuardRememberKey, Long> {
}
