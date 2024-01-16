package com.connectjob.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connectjob.model.Empresa;

import com.connectjob.model.Vaga;
import com.connectjob.services.EmpresaServices;

import com.connectjob.services.VagaServices;



@Controller
@RequestMapping("/vaga")
public class VagaController {

	@Autowired
	private VagaServices vagaServices;
	
	@Autowired
	private EmpresaServices empresaServices;
	
	
	@GetMapping("/vagasEmpresa/{id}")
	public String vagasEmpresa(@PathVariable("id") Long id, Model model) {

		Vaga vaga = new Vaga();
		model.addAttribute("vaga", vaga);
		Empresa empresaLocalizada = empresaServices.getEmpresaById(id);
		model.addAttribute("empresa", empresaLocalizada);	
		
		return "area-vagas";
	}
	
	@GetMapping("/cadastro/{idEmpresa}")
	public String formVaga(@PathVariable("idEmpresa") Long idEmpresa, Model model) {

		Vaga vaga = new Vaga();
		model.addAttribute("vaga", vaga);
		Empresa empresaLocalizada = empresaServices.getEmpresaById(idEmpresa);
		model.addAttribute("empresa", empresaLocalizada);	
		
		return "cadastro-vagas";
	}

	@PostMapping("/cadastrar/{idEmpresa}")
	public String cadastrarVaga(@PathVariable("idEmpresa") Long idEmpresa, @ModelAttribute("vaga") Vaga vaga, RedirectAttributes redirectAttributes) {
		vagaServices.saveVaga(vaga);
		redirectAttributes.addAttribute("cadastrado", "Vaga cadastrada com sucesso!");
		return "redirect:/empresa/gerenciarVagas/" + idEmpresa;
	}
	
	//formulario edicao
	@GetMapping("/editar/{id1}/{id2}") 
	public String formEditarVaga(@PathVariable("id1") Long idEmpresa, @PathVariable("id2") Long idVaga , Model model) {
		Vaga vaga = vagaServices.getVagaById(idVaga);
		model.addAttribute("vaga", vaga);
		Empresa empresa = empresaServices.getEmpresaById(idEmpresa);
		model.addAttribute("empresa", empresa);
		
		return "editarVaga";
	}
	
	//inserir dados no banco
	@PostMapping("/editar/{id1}/{id2}")
	public String editarVaga(@PathVariable("id1") Long idEmpresa, @PathVariable("id2") Long idVaga, @ModelAttribute("vaga") Vaga vaga , RedirectAttributes redirectAttributes ) {
		vagaServices.updateVaga(idVaga, vaga);
		redirectAttributes.addAttribute("atualizar", "Vaga atualizada com sucesso!");
		return "redirect:/empresa/gerenciarVagas/" + idEmpresa;
		
		
	}
	
	//deletar vaga
	@GetMapping("/deletar/{id1}/{id2}")
	public String deletarVaga(@PathVariable("id1") Long idEmpresa, @PathVariable("id2") Long idVaga, @ModelAttribute("vaga") Vaga vaga , RedirectAttributes redirectAttributes ) {
		vagaServices.deleteVaga(idVaga);
		redirectAttributes.addAttribute("deletar", "Vaga deletada com sucesso!");
		return "redirect:/empresa/gerenciarVagas/" + idEmpresa;
	}
}
