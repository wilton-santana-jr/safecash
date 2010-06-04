package com.appspot.safecash.dados;

import java.util.Date;

import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;

public class Transacao {
	
	private String descricao;
	private Date data;
	private double valor;
	private EnumTipoTransacao tipo;
	private Conta conta;
	private EnumOrigemTransacao origem;
		
	public Transacao(String descricao, Date data, double valor,
			EnumTipoTransacao tipo, Conta conta, EnumOrigemTransacao origem) {
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
		this.conta = conta;
		this.origem = origem;
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
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public EnumOrigemTransacao getOrigem() {
		return origem;
	}
	public void setOrigem(EnumOrigemTransacao origem) {
		this.origem = origem;
	}
}