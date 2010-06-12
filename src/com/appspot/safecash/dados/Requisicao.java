package com.appspot.safecash.dados;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public class Requisicao {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private EnumStatusRequisicao status;
	
	@Persistent
	private Key chaveUsuario;

	public Requisicao(String descricao, EnumStatusRequisicao status) {
		this.descricao = descricao;
		this.status = status;
	}
	
	public Requisicao(String descricao, EnumStatusRequisicao status,
			Key chaveUsuario) {
		this.descricao = descricao;
		this.status = status;
		this.chaveUsuario = chaveUsuario;
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
}
