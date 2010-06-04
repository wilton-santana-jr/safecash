package com.appspot.safecash.dados;

import com.appspot.safecash.enuns.EnumStatusRequisicao;

public class RequisicaoReembolso extends Requisicao {

	private double valor;
	
	public RequisicaoReembolso(String descricao, Usuario usuario,
			EnumStatusRequisicao status, double valor) {
		super(descricao, usuario, status);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
