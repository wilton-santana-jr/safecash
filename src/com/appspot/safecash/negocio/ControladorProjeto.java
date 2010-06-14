package com.appspot.safecash.negocio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.negocio.exception.ProjetoJaExisteException;
import com.appspot.safecash.negocio.exception.ProjetoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioProjeto;
import com.google.appengine.api.datastore.Key;

public class ControladorProjeto {
	
	private RepositorioProjeto repositorio;
	
	public ControladorProjeto(RepositorioProjeto repositorio){
		this.repositorio = repositorio;
	}
	
	public void inserir(Projeto projeto)throws ProjetoJaExisteException	{
			this.repositorio.inserir(projeto);
	}
	
	public void atualizar(Projeto projeto) throws ProjetoNaoExisteException {
			this.repositorio.atualizar(projeto);		
	}	
	
	public Iterator<Projeto> procurarPorResponsavel(Key chaveUsuario) throws ProjetoNaoExisteException {
		Iterator<Projeto> projeto = this.repositorio.procurarPorResponsavel(chaveUsuario); 
		
		if(projeto == null)	
			throw new ProjetoNaoExisteException();
		
		return projeto;
	}
	
	public Projeto procurarPorID(Long id) throws ProjetoNaoExisteException{
		Projeto p = this.repositorio.procurar(id);
		
		if(p == null)
			throw new ProjetoNaoExisteException();
		
		return p;	
	}
	
	public Iterator<Projeto> procurarPorDataInicial(Date data) throws ProjetoNaoExisteException{
		Iterator<Projeto> p = this.repositorio.procurarPorDataInicial(data);
		
		if(p == null)
			throw new ProjetoNaoExisteException();
		
		return p;
	}
	
	public Iterator<Projeto> procurarPorDataFinal(Date data) throws ProjetoNaoExisteException{
		Iterator<Projeto> p = this.repositorio.procurarPorDataInicial(data);	
		
		if(p == null)
			throw new ProjetoNaoExisteException();
		
		return p;
	}
	
	public void remover(Projeto projeto)throws ProjetoNaoExisteException {
			this.repositorio.remover(projeto);
	}
	
	public Iterator<Projeto> getAll(){
		return this.repositorio.iterator();
	}
}
