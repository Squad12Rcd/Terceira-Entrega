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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connectjob.model.Usuario;
import com.connectjob.model.Vaga;
import com.connectjob.services.UsuarioServices;
import com.connectjob.services.VagaServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServices usuarioServices;
	@Autowired
	private VagaServices vagaServices;

	@GetMapping("/home/{nome}")
	public String homeUsuario(@PathVariable String nome, Model model) {
		
		Usuario usuario = usuarioServices.findUsuarioByNome(nome);		
		model.addAttribute("usuario", usuario);
		List<Vaga> vagas = vagaServices.getAllVaga();
		model.addAttribute("vagas", vagas);

		return "homeusuario";
	}

	@GetMapping("/cursos/{id}")
	public String curso(@PathVariable Long id, Model model) {
		Usuario usuariolocalizado = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);

		return "cursos";
	}

	@GetMapping("/vagas/{id}")
	public String paginavagas(@PathVariable Long id, Model model) {
		Usuario usuariolocalizado = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);
		List<Vaga> vagas = vagaServices.getAllVaga();
		model.addAttribute("vagas", vagas);

		return "emprego";
	}

	@GetMapping("/contatos/{id}")
	public String paginacontatos(@PathVariable Long id, Model model) {
		Usuario usuariolocalizado = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuariolocalizado);

		return "contatos";
	}

	@GetMapping("/profile/{id}")
	public String perfilusuario(@PathVariable Long id, Model model) {
		Usuario usuario = usuarioServices.getUsuarioById(id);
		model.addAttribute("usuario", usuario);
		List<Vaga> vagasUsuario = vagaServices.findByUsuarioId(id);
		model.addAttribute("vagasUsuario", vagasUsuario);

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

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginUsuario() {

		return "login";
	}

	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
		usuarioServices.updateUsuario(id, usuario);

		redirectAttributes.addAttribute("perfilUserAtualizado", "Perfil atualizado com sucesso!");
		return "redirect:/usuario/profile/" + id;
	}

	@GetMapping("/deletar/{id}")
	public String deletarUsuario(@PathVariable Long id, HttpServletRequest request) {
		usuarioServices.deleteUsuario(id);
		
		HttpSession sessionUser = request.getSession(false);
		
        if (sessionUser != null) {
            sessionUser.invalidate();
        }

		return "redirect:/";
	}

	@PostMapping("/aplicarVaga/{id1}/{id2}")
	public String aplicarVaga(@PathVariable("id1") Long idUsuario, @PathVariable("id2") Long idVaga,
			RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioServices.getUsuarioById(idUsuario);
		Vaga vaga = vagaServices.getVagaById(idVaga);
		usuario.getVagas().add(vaga);
		usuarioServices.updateUsuario(idUsuario, usuario);
		redirectAttributes.addAttribute("vagaAplicada", "Vaga aplicada com sucesso!");

		return "redirect:/usuario/vagas/" + idUsuario;

	}

	@PostMapping("/buscarVagas/{id}")
	public String buscarVagas(@PathVariable("id") Long idUsuario, @RequestParam("area") String area, Model model) {

		Usuario localizarUsuario = usuarioServices.getUsuarioById(idUsuario);
		model.addAttribute("usuario", localizarUsuario);
		List<Vaga> vagas = vagaServices.findByArea(area);
		model.addAttribute("vagas", vagas);

		return "listarVagas";
	}

}
