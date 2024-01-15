package com.connectjob.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectjob.model.Usuario;
import com.connectjob.repositories.UsuarioRepository;
import com.connectjob.services.UsuarioServices;

@Service
public class UsuarioServiceImpl implements UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
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
		return Optional.empty();
	}

}
