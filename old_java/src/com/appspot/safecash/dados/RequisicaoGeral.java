package com.appspot.safecash.dados;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.enuns.EnumTipoRequisicao;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(customStrategy = "complete-table")
public class RequisicaoGeral extends Requisicao {

	@Persistent
	private double valor;
	
	public RequisicaoGeral(String assunto, String descricao, EnumStatusRequisicao status, double valor, Date data) {
		super(assunto, descricao, status, EnumTipoRequisicao.GERAL, data);
		this.valor = valor;
	}

	public RequisicaoGeral(String assunto, String descricao, EnumStatusRequisicao status, double valor, Date data, Key key) {
		super(assunto, descricao, status, EnumTipoRequisicao.GERAL, data, key);
		this.valor = valor;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
