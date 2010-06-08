package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioUsuario;

public class ControladorUsuario {
	
	private RepositorioUsuario repositorio;
	
	public ControladorUsuario(){}
	
	public void inserir(Usuario usuario)throws UsuarioJaExisteException{
		if(!this.existe(usuario)){
			this.repositorio.inserir(usuario);
		}
		else{
			throw new UsuarioJaExisteException();
		}
	}
	
	public void remover(Usuario usuario)throws UsuarioNaoExisteException{
		if(this.existe(usuario)){
			this.repositorio.remover(usuario);
		}
		else{
			throw new UsuarioNaoExisteException();
		}
	}
	
	public void atualizar(Usuario usuario) throws UsuarioNaoExisteException{
		if(this.existe(usuario)){
			this.repositorio.atualizar(usuario);
		}
		else{
			throw new UsuarioNaoExisteException();
		}
	}
	
	public Usuario buscar(Usuario usuario) throws UsuarioNaoExisteException{
		
		Usuario retorno = null;
		
		if(this.existe(usuario)){
			retorno = usuario;
		}
		else{
			throw new UsuarioNaoExisteException();
		}
		return retorno;
	}
	
	private boolean existe(Usuario usuario){
		return this.repositorio.existe(usuario);
	}
}
