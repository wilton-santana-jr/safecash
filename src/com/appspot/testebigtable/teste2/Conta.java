package com.appspot.testebigtable.teste2;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Conta {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private Date data;
	
	@Persistent
	private String descricao;
	
	@Persistent
	private double valor;
	
	@Persistent
	private EnumStatusConta status;
	
	@Persistent
	private List<Transacao> transacoes;
	
	public Conta(Date data, String descricao, double valor, EnumStatusConta status, List<Transacao> t) {
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
		this.transacoes = t;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
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