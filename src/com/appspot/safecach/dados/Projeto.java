package com.appspot.safecach.dados;

public class Projeto {
	
	private String nome;
	private String descricao;
	// desenvolvedores
	private String gerente;
	
	private Projeto(String n, String d, String g){
		this.nome = n;
		this.descricao = d;
		this.gerente = g;
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

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
}
