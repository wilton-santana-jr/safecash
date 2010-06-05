package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.negocio.exception.RequisicaoJaExisteException;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioRequisicao;

public class ControladorTransacao {
	
	private RepositorioRequisicao repositorio;
	
	public void inserirTransacao(Requisicao requisicao)throws RequisicaoJaExisteException
	{
		if(!this.existe(requisicao))
		{
			this.repositorio.remover(requisicao);
		}
		else
		{
			throw new RequisicaoJaExisteException();
		}
	}
	public void removerTransacao(Requisicao requisicao)throws RequisicaoNaoExisteException
	{
		if(this.existe(requisicao))
		{
			this.repositorio.remover(requisicao);
		}
		else
		{
			throw new RequisicaoNaoExisteException();
		}
	}
	public void atualizarTransacao(Requisicao requisicao) throws RequisicaoNaoExisteException
	{
		if(this.existe(requisicao))
		{
			this.repositorio.remover(requisicao);
		}
		else
		{
			throw new RequisicaoNaoExisteException();
		}
	}
	private boolean existe(Requisicao requisicao)
	{
		return this.repositorio.existe(requisicao);
	}
	
}
