package com.connectjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.connectjob.enums.UserRole;
import com.connectjob.model.Usuario;
import com.connectjob.services.UsuarioServices;

@Configuration
public class Runner implements CommandLineRunner{

	@Autowired
	private UsuarioServices usuarioServices;
	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario = new Usuario();
		
		usuario.setId((long)1);
		usuario.setCpf("1");
		usuario.setEmail("renato@gmail.com");
		usuario.setNome("Renato Marques");
		usuario.setSenha("renato");
		usuario.setRole(UserRole.ADMIN);
		usuarioServices.saveUsuario(usuario);
		
		
	}

}
