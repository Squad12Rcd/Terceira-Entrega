package com.connectjob.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.connectjob.model.Usuario;
import com.connectjob.model.Vaga;



@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>{

	List<Vaga> findByEmpresaId(Long id);
	
	List<Vaga> findByEmpresaNome(String nome);

	List<Vaga> findCandidatoById(Long candidatoId);
	
	List<Vaga> findByArea(String area);
	
	@Query("SELECT v.usuario FROM Vaga v WHERE v.id = :vagaId")
    List<Usuario> findUsuarioById(Long vagaId);

}