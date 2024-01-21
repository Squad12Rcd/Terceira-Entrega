package com.connectjob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectjob.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	
}
