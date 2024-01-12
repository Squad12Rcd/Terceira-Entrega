package com.connectjob.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 80)
	private String nome;	
	
	@Column(nullable = false, length = 18, unique = true)
	private String cnpj;
	
	@Column(nullable = false, length = 250)
	private String senha;
	
	@Column(nullable = false, length = 80, unique = true)
    private String email;
	


	public Empresa() {
		super();
	}

	

	public Empresa(Long id, String nome, String cnpj, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.senha = senha;
		this.email = email;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	
	}