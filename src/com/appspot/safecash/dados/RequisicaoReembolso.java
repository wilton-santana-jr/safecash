package com.appspot.safecash.dados;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.appspot.safecash.enuns.EnumStatusRequisicao;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(customStrategy = "complete-table")
public class RequisicaoReembolso extends Requisicao {

	@Persistent
	private double valor;
	
	public RequisicaoReembolso(String descricao, EnumStatusRequisicao status, double valor) {
		super(descricao, status);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
