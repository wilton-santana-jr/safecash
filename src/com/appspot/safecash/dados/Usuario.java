package com.appspot.safecash.dados;

import com.appspot.safecash.enuns.EnumPermissao;

public class Usuario {
	
	private String login;
	private String senha;
	private String nome;
	private EnumPermissao permissao;
	
	public Usuario(String login, String senha, String nome,
			EnumPermissao permissao) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.permissao = permissao;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public EnumPermissao getPermissao() {
		return permissao;
	}
	
	public void setPermissao(EnumPermissao permissao) {
		this.permissao = permissao;
	}	
}