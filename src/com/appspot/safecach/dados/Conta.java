package com.appspot.safecach.dados;

import java.util.Date;

public class Conta {
	
	private String nome;
	private String descricao;
	private Date data;
	private Projeto projeto;
	
	public Conta(String n, String d, Date da, Projeto p){
		this.nome = n;
		this.descricao = d;
		this.data = da;
		this.projeto = p;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
