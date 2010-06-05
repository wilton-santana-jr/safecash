package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioUsuario;

public class ControladorUsuario {
	
	RepositorioUsuario repositorio;
	
	public void inserirUsuario(Usuario usuario)throws UsuarioJaExisteException
	{
		if(!this.existe(usuario))
		{
			this.repositorio.inserir(usuario);
		}
		else
		{
			throw new UsuarioJaExisteException();
		}
	}
	
	public void atualizarUsuario(Usuario usuario) throws UsuarioNaoExisteException
	{
		if(this.existe(usuario))
		{
			this.repositorio.atualizar(usuario);
		}
		else
		{
			throw new UsuarioNaoExisteException();
		}
	}
	
	public void removerUsuario(Usuario usuario)throws UsuarioNaoExisteException
	{
		if(this.existe(usuario))
		{
			this.repositorio.remover(usuario);
		}
		else
		{
			throw new UsuarioNaoExisteException();
		}
	}
	
	private boolean existe(Usuario usuario)
	{
		return this.repositorio.existe(usuario);
	}


}
