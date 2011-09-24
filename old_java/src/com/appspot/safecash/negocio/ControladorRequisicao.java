package com.appspot.safecash.negocio;

import java.util.Iterator;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioRequisicao;
import com.google.appengine.api.datastore.Key;

public class ControladorRequisicao {

	private RepositorioRequisicao repositorio;
	
	public ControladorRequisicao(RepositorioRequisicao repositorio){
		this.repositorio = repositorio;
	}
	
	public void inserir(Requisicao requisicao){
		this.repositorio.inserir(requisicao);
	}
	
	public Requisicao procurar(Key chave) throws RequisicaoNaoExisteException{
		Requisicao r = this.repositorio.procurar(chave);
		
		if(r == null)
			throw new RequisicaoNaoExisteException();
		
		return r;
	}
	
	public Iterator<Requisicao> procurar(EnumStatusRequisicao status) throws RequisicaoNaoExisteException{
		
		Iterator<Requisicao> retorno = this.repositorio.procurar(status);
		
		if(retorno == null){
			throw new RequisicaoNaoExisteException();
		}
		else{
			return retorno;
		}
		
	}
	
	public void remover(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.repositorio.remover(requisicao);
	}
	
	public void atualizar(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.repositorio.atualizar(requisicao);
	}
	
	public Iterator<Requisicao> getAll(){
		return this.repositorio.iterator();
	}
}