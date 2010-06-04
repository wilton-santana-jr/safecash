package com.appspot.safecash.dados;

import com.appspot.safecash.enuns.EnumStatusRequisicao;

public class Requisicao {
	
	private String descricao;
	private Usuario usuario;
	private EnumStatusRequisicao status;
	
	public Requisicao(String descricao, Usuario usuario,
			EnumStatusRequisicao status) {
		this.descricao = descricao;
		this.usuario = usuario;
		this.status = status;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public EnumStatusRequisicao getStatus() {
		return status;
	}
	
	public void setStatus(EnumStatusRequisicao status) {
		this.status = status;
	}	
}
