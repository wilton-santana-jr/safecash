package com.appspot.safecash.dados;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Projeto {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private List<Key> chavesEquipe;
	
	@Persistent
	private Key chaveContaSaida;
	
	@Persistent 
	private Key chaveContaEntrada;
	
	@Persistent
	private Key chaveResponsavel;
	
	@Persistent
	private String nome;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private double valor;
	
	@Persistent
	private Date dataInicio;
	
	@Persistent
	private Date dataFim;

	public Projeto(List<Key> chavesEquipe, Key chavesConta,
			Key chaveResponsavel, String nome, double valor, Date dataInicio,
			Date dataFim) {
		this.chavesEquipe = chavesEquipe;
		this.chaveContaSaida = chavesConta;
		this.chaveResponsavel = chaveResponsavel;
		this.nome = nome;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Projeto(String nome, double valor, Date dataInicio, Date dataFim) {
		this.nome = nome;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Key> getChavesEquipe() {
		return chavesEquipe;
	}

	public void setChavesEquipe(List<Key> chavesEquipe) {
		this.chavesEquipe = chavesEquipe;
	}

	public Key getChavesConta() {
		return chaveContaSaida;
	}

	public void setChavesConta(Key chavesConta) {
		this.chaveContaSaida = chavesConta;
	}

	public Key getChaveResponsavel() {
		return chaveResponsavel;
	}

	public void setChaveResponsavel(Key chaveResponsavel) {
		this.chaveResponsavel = chaveResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}