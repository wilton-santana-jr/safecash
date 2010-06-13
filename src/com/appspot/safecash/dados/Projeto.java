package com.appspot.safecash.dados;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
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

	@NotPersistent
	private Conta contaSaida;
	
	@NotPersistent
	private Conta contaEntrada;

	public Projeto(List<Key> chavesEquipe, Key chaveContaSaida,
			Key chaveContaEntrada, Key chaveResponsavel, String nome,
			String descricao, double valor, Date dataInicio, Date dataFim,
			Conta contaSaida, Conta contaEntrada) {
		this.chavesEquipe = chavesEquipe;
		this.chaveContaSaida = chaveContaSaida;
		this.chaveContaEntrada = chaveContaEntrada;
		this.chaveResponsavel = chaveResponsavel;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.contaSaida = contaSaida;
		this.contaEntrada = contaEntrada;
	}

	public Projeto(String nome, String descricao, double valor,
			Date dataInicio, Date dataFim, Conta contaSaida, Conta contaEntrada) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.contaSaida = contaSaida;
		this.contaEntrada = contaEntrada;
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

	public Key getChaveContaSaida() {
		return chaveContaSaida;
	}

	public void setChaveContaSaida(Key chaveContaSaida) {
		this.chaveContaSaida = chaveContaSaida;
	}

	public Key getChaveContaEntrada() {
		return chaveContaEntrada;
	}

	public void setChaveContaEntrada(Key chaveContaEntrada) {
		this.chaveContaEntrada = chaveContaEntrada;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Conta getContaSaida() {
		return contaSaida;
	}

	public void setContaSaida(Conta contaSaida) {
		this.contaSaida = contaSaida;
	}

	public Conta getContaEntrada() {
		return contaEntrada;
	}

	public void setContaEntrada(Conta contaEntrada) {
		this.contaEntrada = contaEntrada;
	}
}