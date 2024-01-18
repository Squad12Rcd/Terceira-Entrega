package com.connectjob.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connectjob.model.Usuario;
import com.connectjob.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>{

	List<Vaga> findByEmpresaId(Long id);

	List<Vaga> findCandidatoById(Long candidatoId);
	
	// List<Usuario> findUsuariosById(Long vagaId);
	
	@Query("SELECT v.usuario FROM Vaga v WHERE v.id = :vagaId")
    List<Usuario> findUsuarioById(Long vagaId);
}
