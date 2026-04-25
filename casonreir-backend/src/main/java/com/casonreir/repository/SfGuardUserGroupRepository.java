package com.casonreir.repository;

import com.casonreir.model.SfGuardUser;
import com.casonreir.model.SfGuardUserGroup;
import com.casonreir.model.SfGuardUserGroupId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SfGuardUserGroupRepository extends JpaRepository<SfGuardUserGroup, SfGuardUserGroupId> {

    @Query("SELECT ug FROM SfGuardUserGroup ug WHERE LOWER(ug.user.username) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(ug.group.name) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<SfGuardUserGroup> buscarPorTermino(@Param("term") String term, Pageable pageable);
    // Busca todas las asignaciones de grupos para un usuario específico
    List<SfGuardUserGroup> findByUser(SfGuardUser user);
}
