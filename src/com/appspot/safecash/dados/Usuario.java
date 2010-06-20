package com.appspot.safecash.dados;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.appspot.safecash.enuns.EnumPermissao;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Usuario {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private List<Key> chavesRequisicoes;
	
	@Persistent
	@Unique
	private String login;
	
	@Persistent
	private String senha;
	
	@Persistent
	private String nome;
	
	@Persistent
	private EnumPermissao permissao;
	
	@NotPersistent
	private List<Requisicao> requisicoes;
	
	public Usuario(List<Key> chavesRequisicoes, String login, String senha,
			String nome, EnumPermissao permissao) {
		this.chavesRequisicoes = chavesRequisicoes;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.permissao = permissao;
	}

	public List<Requisicao> getRequisicoes() {
		if(this.requisicoes == null)
			this.requisicoes = new ArrayList<Requisicao>();
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public Usuario(String login, String senha, String nome,
			EnumPermissao permissao) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.permissao = permissao;
		
		this.chavesRequisicoes = new ArrayList<Key>();
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public List<Key> getChavesRequisicoes() {
		return chavesRequisicoes;
	}

	public void setChavesRequisicoes(List<Key> chavesRequisicoes) {
		this.chavesRequisicoes = chavesRequisicoes;
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