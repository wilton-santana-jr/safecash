package com.appspot.safecash.dados;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Transacao {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private boolean conta;
	
	@Persistent
	private Date data;
	
	@Persistent
	private double valor;
	
	@Persistent
	private EnumTipoTransacao tipo;
	
	@Persistent
	private EnumOrigemTransacao origem;
	
	public Transacao(String descricao, Date data, double valor,
			EnumTipoTransacao tipo, EnumOrigemTransacao origem) {
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
		this.origem = origem;
		
		this.setConta(false);
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public EnumTipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(EnumTipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public EnumOrigemTransacao getOrigem() {
		return origem;
	}
	public void setOrigem(EnumOrigemTransacao origem) {
		this.origem = origem;
	}
	
	public boolean isConta() {
		return conta;
	}

	public void setConta(boolean conta) {
		this.conta = conta;
	}
}