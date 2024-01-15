package com.connectjob.services;

import java.util.List;

import com.connectjob.model.Vaga;

public interface VagaServices {

	List<Vaga> getAllVaga();
	
	Vaga getVagaById(Long id);
	
	Vaga saveVaga(Vaga vaga);
	
	Vaga updateVaga(Long id, Vaga vagaAtualizada);
	
	void deleteVaga(Long id);

	List<Vaga> findByEmpresaId(Long id);
	
}
