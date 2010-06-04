package com.appspot.safecash.dados;

import java.util.Date;
import java.util.List;

public class Projeto {
	
	private String nome;
	private Usuario responsavel;
	private List<Funcionario> equipe;
	private double valor;
	private Date dataInicio;
	private Date dataFim;
	
	public Projeto(String nome, Usuario responsavel, List<Funcionario> equipe,
			double valor, Date dataInicio, Date dataFim) {
		this.nome = nome;
		this.responsavel = responsavel;
		this.equipe = equipe;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Usuario getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	public List<Funcionario> getEquipe() {
		return equipe;
	}
	
	public void setEquipe(List<Funcionario> equipe) {
		this.equipe = equipe;
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