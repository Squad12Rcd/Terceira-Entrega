package com.connectjob.model;



import java.util.HashSet;
import java.util.Set;

import com.connectjob.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Column(nullable = true, length = 80)
	private String nome;

	@Column(nullable = true, length = 250)
	private String senha;
	
	@Column(nullable = true, length = 80)
    private String email;
	
	@Column(nullable = true, length = 14)
	private String cpf;
	
	@Column(nullable = true, length = 80)
	private UserRole role;
	

    @ManyToMany
	@JoinTable(name = "usuario_vagas",
	joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "vaga_id"))
	private Set<Vaga> vagas = new HashSet<>();



	
	public Usuario() {
	}


	public Usuario(Long id, String nome, String senha, String email, String cpf, UserRole role, Set<Vaga> vagas) {

		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.role = role;
		this.vagas = vagas;
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


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public Set<Vaga> getVagas() {
		return vagas;
	}


	public void setVagas(Set<Vaga> vagas) {
		this.vagas = vagas;
	}
	
	

	
}