package com.connectjob.services;

import java.util.List;
import java.util.Optional;

import com.connectjob.model.Empresa;

public interface EmpresaServices {

	List<Empresa> getAllEmpresa();
	
	Empresa getEmpresaById(Long id);
	
	Empresa saveEmpresa(Empresa empresa);
	
	Empresa updateEmpresa(Long id, Empresa empresaAtualizada);
	
	void deleteEmpresa(Long id);
	
	Optional<Empresa> findEmpresaByEmail(String email);
}