package com.connectjob.services;

import java.util.List;

import com.connectjob.model.Usuario;

public interface UsuarioServices {

	List<Usuario> getAllUsuarios();
		
	Usuario getUsuarioById(Long id);
	
	Usuario saveUsuario(Usuario usuario);
	
	Usuario updateUsuario(Long id, Usuario usuarioAtualizado);
	
	void deleteUsuario(Long id);
}
