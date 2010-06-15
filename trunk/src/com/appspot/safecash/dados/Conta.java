package com.appspot.safecash.dados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.safecash.enuns.EnumStatusConta;
import com.appspot.safecash.enuns.EnumTipoConta;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Conta {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private List<Key> chavesTransacoes;
	
	@Persistent
	private Date data;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private double valor;
	
	@Persistent
	private EnumStatusConta status;
	
	@Persistent
	private EnumTipoConta tipo;
	
	@NotPersistent
	private List<Transacao> transacoes;
	
	public Conta(List<Key> chavesTransacoes, Date data, String descricao,
			double valor, EnumStatusConta status, EnumTipoConta tipo,
			List<Transacao> transacoes) {
		this.chavesTransacoes = chavesTransacoes;
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
		this.tipo = tipo;
		this.transacoes = transacoes;
	}

	public Conta(Date data, String descricao, double valor,
			EnumStatusConta status, EnumTipoConta tipo) {
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
		this.tipo = tipo;
	}

	public EnumTipoConta getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoConta tipo) {
		this.tipo = tipo;
	}

	public List<Transacao> getTransacoes() {
		if(this.transacoes == null)
			this.transacoes = new ArrayList<Transacao>();
		
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public List<Key> getChavesTransacoes() {
		return chavesTransacoes;
	}

	public void setChavesTransacoes(List<Key> chavesTransacoes) {
		this.chavesTransacoes = chavesTransacoes;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
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
	
	public EnumStatusConta getStatus() {
		return status;
	}
	
	public void setStatus(EnumStatusConta status) {
		this.status = status;
	}	
}