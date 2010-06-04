package com.appspot.safecash.dados;

public class Modelo {
	
	private String nome;
	private String descricao;
	private byte[] dados;
	
	public Modelo(String n, String d, byte[] data){
		this.nome = n;
		this.descricao = d;
		this.dados = data;
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
	
	public byte[] getDados() {
		return dados;
	}
	
	public void setDados(byte[] dados) {
		this.dados = dados;
	}
}
