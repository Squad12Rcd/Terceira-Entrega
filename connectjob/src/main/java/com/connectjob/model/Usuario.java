package com.connectjob.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80, unique = true)
	private String nome;

	@Column(nullable = false, length = 250, unique = true)
	private String senha;

	@Column(nullable = false, length = 250, unique = true)
	private String email;
	
	@Column(nullable = false, length = 250, unique = true)
	private String cpf;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "usuario_vagas", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "vaga_id"))
	private Set<Vaga> vagas = new HashSet<>();

	public Usuario(Long id, String nome, String senha, String email, String cpf, List<Role> roles, Set<Vaga> vagas) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.roles = roles;
		this.vagas = vagas;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(Set<Vaga> vagas) {
		this.vagas = vagas;
	}
}