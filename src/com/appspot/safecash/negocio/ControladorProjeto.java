package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.negocio.exception.ProjetoJaExisteException;
import com.appspot.safecash.negocio.exception.ProjetoNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioProjeto;

public class ControladorProjeto {
	
	private RepositorioProjeto repositorio;
	
	public ControladorProjeto(){}
	
	public void inserir(Projeto projeto)throws ProjetoJaExisteException
	{
		if(!this.existe(projeto))
		{
			this.repositorio.inserir(projeto);
		}
		else
		{
			throw new ProjetoJaExisteException();
		}
	}
	
	public void atualizar(Projeto projeto) throws ProjetoNaoExisteException
	{
		if(this.existe(projeto))
		{
			this.repositorio.atualizar(projeto);
		}
		else
		{
			throw new ProjetoNaoExisteException();
		}
	}
	
	
	/*public Iterator<Projeto> procurarPorNome(String nome) throws ProjetoNaoExisteException
	{
		
		Iterator<Projeto> projeto = this.repositorio.procurar(nome); 
		if(projeto == null)
		{
			throw new ProjetoNaoExisteException();
		}
		return projeto;
	}*/
	
	/*public Iterator<Projeto> procurarPorUsuario(Usuario usuario) throws ProjetoNaoExisteException
	{
		
		Iterator<Projeto> projeto = this.repositorio.procurar(usuario); 
		if(projeto == null)
		{
			throw new ProjetoNaoExisteException();
		}
		return projeto;
	}*/
	
	public void remover(Projeto projeto)throws ProjetoNaoExisteException
	{
		if(this.existe(projeto))
		{
			this.repositorio.remover(projeto);
		}
		else
		{
			throw new ProjetoNaoExisteException();
		}
	}
	
	private boolean existe(Projeto projeto)
	{
		return this.repositorio.existe(projeto);
	}

}
