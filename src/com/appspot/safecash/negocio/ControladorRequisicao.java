package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioRequisicao;

public class ControladorRequisicao {

	private RepositorioRequisicao repositorio;
	
	public ControladorRequisicao(){}
	
	public void inserir(Requisicao requisicao){
		this.repositorio.inserir(requisicao);
	}
	
	public void remover(Requisicao requisicao) throws RequisicaoNaoExisteException{
		if(this.existe(requisicao)){
			this.repositorio.remover(requisicao);
		}
		else{
			throw new RequisicaoNaoExisteException();
		}
	}
	
	public void atualizar(Requisicao requisicao) throws RequisicaoNaoExisteException{
		if(this.existe(requisicao)){
			this.repositorio.atualizar(requisicao);
		}
		else{
			throw new RequisicaoNaoExisteException();
		}
	}
	
	private boolean existe(Requisicao requisicao){
		return this.repositorio.existe(requisicao);
	}
}