package com.appspot.safecash.negocio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.appspot.safecash.negocio.exception.NenhumaTransacaoEncontradaException;
import com.appspot.safecash.negocio.exception.TransacaoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioTransacao;
import com.google.appengine.api.datastore.Key;

public class ControladorTransacao {
	
	private RepositorioTransacao repositorio;
	
	public ControladorTransacao(RepositorioTransacao repo){
		this.repositorio = repo;
	}
	
	public void inserir(Transacao transacao) {
		this.repositorio.inserir(transacao);
	}
	
	public void remover(Transacao transacao) {
		this.repositorio.remover(transacao);
	}
	
	public void atualizar(Transacao transacao) {
		this.repositorio.atualizar(transacao);
	}
	
	public Iterator<Transacao> buscar(EnumTipoTransacao tipo) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(tipo);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> buscar(EnumOrigemTransacao origem) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(origem);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> buscar(Date data) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(data);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Iterator<Transacao> buscar(Date dataInicio, Date dataFim) throws NenhumaTransacaoEncontradaException{
		Iterator<Transacao> it = this.repositorio.procurar(dataInicio, dataFim);
		
		if(it == null || !it.hasNext())
			throw new NenhumaTransacaoEncontradaException();
		
		return it;
	}
	
	public Transacao buscar(Key chave) throws TransacaoNaoExisteException{
		Transacao t = this.repositorio.procurar(chave);
		
		if(t == null)
			throw new TransacaoNaoExisteException();
		
		return t;
	}
	
	public Iterator<Transacao> getAll(){
		return this.repositorio.iterator();
	}
}