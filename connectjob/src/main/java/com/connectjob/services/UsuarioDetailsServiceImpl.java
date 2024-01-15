package com.connectjob.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connectjob.model.Usuario;
import com.connectjob.model.UsuariosDetailsImpl;
import com.connectjob.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{
	 @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Usuario usuario = usuarioRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

	        System.out.println(usuario.getNome());
	        return new UsuariosDetailsImpl(usuario);
	    }    
	}


