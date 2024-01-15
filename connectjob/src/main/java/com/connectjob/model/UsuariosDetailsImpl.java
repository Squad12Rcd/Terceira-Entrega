package com.connectjob.model;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UsuariosDetailsImpl  implements UserDetails {


	    private static final long serialVersionUID = 1L;
		private Usuario usuario;

	    public UsuariosDetailsImpl(Usuario usuario) {
	        this.usuario = usuario;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	    	 
	    	        return Collections.emptyList();
	    }

	    @Override
	    public String getPassword() {
	    	String encoded = new BCryptPasswordEncoder().encode(usuario.getSenha());
	        return encoded;
	    }

	    @Override
	    public String getUsername() {
	        return usuario.getEmail();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	       
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	       
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	      
	        return true;
	    }
	    
	}


