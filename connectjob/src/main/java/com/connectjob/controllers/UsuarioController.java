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

import com.connectjob.model.Usuario;
import com.connectjob.model.Vaga;
import com.connectjob.services.UsuarioServices;
import com.connectjob.services.VagaServices;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServices usuarioServices;
	@Autowired
	private VagaServices vagaServices;
	

	@GetMapping("/home/{id}")
	public String listaUsuario (@PathVariable Long id, Model model) {
		Usuario usuariolocalizado =  usuarioServices.getUsuarioById(id);		
		model.addAttribute("usuario", usuariolocalizado);
		
		return "homeusuario";
	}
	

	@GetMapping("/cursos/{id}")
	public String curso (@PathVariable Long id, Model model) {
		Usuario usuariolocalizado =  usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);
		return "cursos";
	}
	
	@GetMapping("/vagas/{id}")
	public String paginavagas (@PathVariable Long id, Model model) {
		Usuario usuariolocalizado =  usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);
		List<Vaga> vagas = vagaServices.getAllVaga();
		model.addAttribute("vagas", vagas);
		
		return "emprego";
	}	
	
	@GetMapping("/contatos/{id}")
	public String paginacontatos (@PathVariable Long id, Model model) {
		Usuario usuariolocalizado =  usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);
		return "contatos";
	}	
	
	@GetMapping("/profile/{id}")
	public String perfilusuario(@PathVariable Long id, Model model) {
		Usuario usuario = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuario);
		return "userprofile";
	}	
	

		@GetMapping("/cadastro")
		public String formCadastroUsuario(Model model) {
			Usuario usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			return "cadastro";
		}
		

		@PostMapping("/cadastrar")
		public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
			usuarioServices.saveUsuario(usuario);
						
			return "homeusuario";
		}
		
		@GetMapping("/login")
		public String loginUsuario () {
			
			return "login";

		}		

	@GetMapping("/editar/{id}")
	public String formEditarUsuario(@PathVariable Long id, Model model) {
		Usuario usuario = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuario);
		return "editarusuario";
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario) {		
		usuarioServices.updateUsuario(id, usuario);
		return "homeusuario";
	}
	

	@GetMapping("/deletar/{id}")
	public String deletarUsuario(@PathVariable Long id) {
		usuarioServices.deleteUsuario(id);
		return "index";
	}
}
