package com.connectjob.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectjob.model.Empresa;
import com.connectjob.repositories.EmpresaRepository;
import com.connectjob.services.EmpresaServices;

@Service
public class EmpresaServiceImpl implements EmpresaServices {

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
