package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.negocio.exception.TransacaoJaExisteException;
import com.appspot.safecash.negocio.exception.TransacaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioTransacao;

public class ControladorTransacao {
	
	private RepositorioTransacao repositorio;
	
	public void inserirTransacao(Transacao transacao)throws TransacaoJaExisteException
	{
		if(!this.existe(transacao))
		{
			this.repositorio.inserir(transacao);
		}
		else
		{
			throw new TransacaoJaExisteException();
		}
	}
	public void removerTransacao(Transacao transacao)throws TransacaoNaoExisteException
	{
		if(this.existe(transacao))
		{
			this.repositorio.remover(transacao);
		}
		else
		{
			throw new TransacaoNaoExisteException();
		}
	}
	public void atualizarTransacao(Transacao transacao) throws TransacaoNaoExisteException
	{
		if(this.existe(transacao))
		{
			this.repositorio.atualizar(transacao);
		}
		else
		{
			throw new TransacaoNaoExisteException();
		}
	}
	private boolean existe(Transacao transacao)
	{
		return this.repositorio.existe(transacao);
	}
	
}
