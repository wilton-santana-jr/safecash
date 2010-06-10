package com.appspot.safecash.negocio;

import java.util.Iterator;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioRequisicao;

public class ControladorRequisicao {

	private RepositorioRequisicao repositorio;
	
	public ControladorRequisicao(){}
	
	public void inserir(Requisicao requisicao){
		this.repositorio.inserir(requisicao);
	}
	
	/*public Iterator<Requisicao> procurarPorUsuario(Usuario usuario) throws RequisicaoNaoExisteException{
		
		Iterator<Requisicao> retorno = this.repositorio.procurar(usuario);
		
		if(retorno == null){
			throw new RequisicaoNaoExisteException();
		}
		else{
			return retorno;
		}
	}*/
	
	public Iterator<Requisicao> procurarPorStatus(EnumStatusRequisicao status) throws RequisicaoNaoExisteException{
		
		Iterator<Requisicao> retorno = this.repositorio.procurar(status);
		
		if(retorno == null){
			throw new RequisicaoNaoExisteException();
		}
		else{
			return retorno;
		}
		
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