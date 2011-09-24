package com.appspot.safecash.dados;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.enuns.EnumTipoRequisicao;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(customStrategy = "complete-table")
public class Requisicao {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	//@Persistent
	private String assunto;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private Date data;
	
	@Persistent
	private EnumStatusRequisicao status;
	
	@Persistent
	private EnumTipoRequisicao tipo;

	@Persistent
	private Key chaveUsuario;

	public Requisicao(String assunto, String descricao, EnumStatusRequisicao status, EnumTipoRequisicao tipo, Date data) {
		this.assunto = assunto;
		this.descricao = descricao;
		this.status = status;
		this.tipo = tipo;
		this.data = data;
	}
	
	public Requisicao(String assunto, String descricao, EnumStatusRequisicao status,
			EnumTipoRequisicao tipo, Date data, Key chaveUsuario) {
		this.assunto = assunto;
		this.descricao = descricao;
		this.status = status;
		this.chaveUsuario = chaveUsuario;
		this.tipo = tipo;
		this.data = data;
	}

	public Key getChaveUsuario() {
		return chaveUsuario;
	}

	public void setChaveUsuario(Key chaveUsuario) {
		this.chaveUsuario = chaveUsuario;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EnumStatusRequisicao getStatus() {
		return status;
	}

	public void setStatus(EnumStatusRequisicao status) {
		this.status = status;
	}
	
	public EnumTipoRequisicao getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoRequisicao tipo) {
		this.tipo = tipo;
	}
	
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}