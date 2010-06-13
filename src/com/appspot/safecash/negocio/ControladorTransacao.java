package com.appspot.safecash.negocio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.appspot.safecash.negocio.exception.NenhumaTransacaoEncontradaException;
import com.appspot.safecash.negocio.exception.TransacaoJaExisteException;
import com.appspot.safecash.negocio.exception.TransacaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioTransacao;

public class ControladorTransacao {
	
	private RepositorioTransacao repositorio;
	
	public ControladorTransacao(RepositorioTransacao repo){
		this.repositorio = repo;
	}
	
	public void inserir(Transacao transacao)throws TransacaoJaExisteException {
		if(!this.existe(transacao)) {
			this.repositorio.inserir(transacao);
		}
		else {
			throw new TransacaoJaExisteException();
		}
	}
	
	public void remover(Transacao transacao)throws TransacaoNaoExisteException {
		if(this.existe(transacao)) {
			this.repositorio.remover(transacao);
		}
		else {
			throw new TransacaoNaoExisteException();
		}
	}
	
	public void atualizar(Transacao transacao) throws TransacaoNaoExisteException {
		if(this.existe(transacao)) {
			this.repositorio.atualizar(transacao);
		}
		else {
			throw new TransacaoNaoExisteException();
		}
	}
	
	private boolean existe(Transacao transacao) {
		return this.repositorio.existe(transacao);
	}	
	
	public Iterator<Transacao> procurar(EnumTipoTransacao tipo) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(tipo);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> procurar(EnumOrigemTransacao origem) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(origem);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> procurar(Date data) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(data);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> procurar(Date dataInicio, Date dataFim) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(dataInicio, dataFim);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> getAll(){
		return this.repositorio.iterator();
	}
}