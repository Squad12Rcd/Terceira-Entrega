package com.connectjob.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connectjob.model.Empresa;
import com.connectjob.model.Vaga;
import com.connectjob.services.EmpresaServices;
import com.connectjob.services.VagaServices;



@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaServices empresaServices;

	@Autowired
	private VagaServices vagaServices;
	

	@GetMapping("/home/{idEmpresa}")
	public String HomeEmpresa (@PathVariable(value = "idEmpresa") Long id, Model model) {
		
			Empresa empresaLocalizada =  empresaServices.getEmpresaById(id);			
	        model.addAttribute("empresa", empresaLocalizada);	    	
			List<Vaga> vagas = vagaServices.findByEmpresaId(id);		
			model.addAttribute("vagas", vagas);
			
			return "HomeEmpresa";	
		
	}

	@GetMapping("/perfilEmpresa/{id}")
	public String perfilempresa(@PathVariable Long id, Model model) {
		Empresa empresaLocalizada = empresaServices.getEmpresaById(id);
		model.addAttribute("empresa", empresaLocalizada);
		return "perfilEmpresa";
	}
	
	@GetMapping("/cadastro")
	public String formCadastroEmpresa(Model model) {
		Empresa empresa = new Empresa();
		model.addAttribute("empresa", empresa);
		return "cadastro-empresa";
	}
		

	@PostMapping("/cadastrar")
	public String cadastrarEmpresa(@ModelAttribute("empresa") Empresa empresa) {	
		empresaServices.saveEmpresa(empresa);					
		return "/HomeEmpresa";
	}		
	

	@GetMapping("editar/{id}")
	public String formEditarEmpresa(@PathVariable Long id, Model model) {
		Empresa empresa = empresaServices.getEmpresaById(id);
		model.addAttribute("empresa", empresa);
		return "editarempresa";
	}
	

	@PostMapping("/editar/{id}")
	public String editarEmpresa(@PathVariable Long id, @ModelAttribute("empresa") Empresa empresa) {
		empresaServices.updateEmpresa(id, empresa);
		return "/HomeEmpresa";
	}


	@GetMapping("/deletar/{id}")
	public String deletarEmpresa(@PathVariable Long id) {
		empresaServices.deleteEmpresa(id);
		return "index";
	}
	
	/* ÁREA VAGAS */
	
	@GetMapping("/areaVagas/{idEmpresa}")
	public String areaVagas(@PathVariable Long idEmpresa, Model model) {
		Empresa empresaLocalizada = empresaServices.getEmpresaById(idEmpresa);
		model.addAttribute("empresa", empresaLocalizada);
		List<Vaga> vagas = vagaServices.findByEmpresaId(idEmpresa);
		model.addAttribute("vagasEmpresa", vagas);
		
		return "area-vagas";
	}
	
	@GetMapping("/gerenciarVagas/{id}")
	public String gerenciarVagas(@PathVariable Long id, Model model) {
		Empresa empresaLocalizada = empresaServices.getEmpresaById(id);
		model.addAttribute("empresa", empresaLocalizada);
		List<Vaga> vagas = vagaServices.findByEmpresaId(id);
		model.addAttribute("vagasEmpresa", vagas);
		
		return "gerenciarVagas";
	}
	
	
	
	
	// FIM ÁREA VAGAS


}
