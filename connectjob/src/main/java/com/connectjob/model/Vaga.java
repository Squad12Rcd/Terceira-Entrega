package com.connectjob.model;

import java.time.LocalDate;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaga")
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	private String titulo;
	private int quantidade;
	private String modalidade;
	private String tipoContrato;
	private String nivel;
	private String localizacao;
	private String salario;
	private String area;
	
	@Column(name = "data_cadastro")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@ManyToMany
	@JoinTable(name = "usuario_vagas",
	   joinColumns = @JoinColumn(name = "vaga_id"),
	   inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	   private List<Usuario> usuario;
	
	
	public Vaga(Long id, String descricao, String titulo, int quantidade, String modalidade, String tipoContrato,
			String nivel, String localizacao, String salario, String area, LocalDate dataCadastro, Empresa empresa,
			List<Usuario> usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.titulo = titulo;
		this.quantidade = quantidade;
		this.modalidade = modalidade;
		this.tipoContrato = tipoContrato;
		this.nivel = nivel;
		this.localizacao = localizacao;
		this.salario = salario;
		this.area = area;
		this.dataCadastro = dataCadastro;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public List<Usuario> getCandidato() {
		return usuario;
	}

	public void setCandidato(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Vaga() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	

}
