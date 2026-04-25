package com.casonreir.repository;

import com.casonreir.model.EncargadoCentro;
import com.casonreir.model.EncargadoCentroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoCentroRepository extends JpaRepository<EncargadoCentro, EncargadoCentroId> {
}
