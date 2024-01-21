package com.connectjob.servicesImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectjob.model.Empresa;
import com.connectjob.model.Role;
import com.connectjob.model.Usuario;
import com.connectjob.repositories.EmpresaRepository;
import com.connectjob.repositories.RoleRepository;
import com.connectjob.repositories.UsuarioRepository;
import com.connectjob.services.EmpresaServices;
import com.connectjob.services.UsuarioServices;

@Service
public class UsuarioServiceImpl implements UsuarioServices, UserDetailsService ,EmpresaServices{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario getUsuarioById(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElse(null);
	}

	@Override
	@Transactional
	public Usuario saveUsuario(Usuario usuario) {
		Role role = roleRepository.findByAuthority("ROLE_USUARIO");
		if (role == null) { 
			throw new IllegalStateException("'ROLE_USUARIO' não encontrada.");
		}
		usuario.setRoles((List<Role>) Arrays.asList(role));
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario updateUsuario(Long id, Usuario usuarioAtualizado) {
		Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNome(usuarioAtualizado.getNome());
			usuarioExistente.setCpf(usuarioAtualizado.getCpf());
			usuarioExistente.setEmail(usuarioAtualizado.getEmail());
			usuarioExistente.setSenha(usuarioAtualizado.getSenha());
			return usuarioRepository.save(usuarioExistente);
		} else {
			throw new RuntimeException("Usuario com o ID " + id + "não encontrado.");
		}
	}

	@Override
	public void deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}


	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        if (usuario != null) { 
			return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(),
					mapRolesToAuthorities(usuario.getRoles()));
		} else { 
			throw new UsernameNotFoundException("Username ou senha inválidos.");
		}
    } 
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<? extends GrantedAuthority> mapRoles = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
		return mapRoles;
	}

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public List<Empresa> getAllEmpresa() {
		return empresaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empresa getEmpresaById(Long id) {
		return empresaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empresa saveEmpresa(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@Override
	public Empresa updateEmpresa(Long id, Empresa empresaAtualizada) {
		Empresa empresaExistente = empresaRepository.findById(id).orElse(null);
		if (empresaExistente != null) {
			empresaExistente.setNome(empresaAtualizada.getNome());
			empresaExistente.setCnpj(empresaAtualizada.getCnpj());
			empresaExistente.setEmail(empresaAtualizada.getEmail());
			empresaExistente.setSenha(empresaAtualizada.getSenha());
			return empresaRepository.save(empresaExistente);
		} else {
			throw new RuntimeException("Empresa com o ID " + id + "não encontrado.");
		}
	}
	@Override
	public void deleteEmpresa(Long id) {
		empresaRepository.deleteById(id);
	}

}
