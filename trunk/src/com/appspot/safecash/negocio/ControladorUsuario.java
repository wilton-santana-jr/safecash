package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioUsuario;
import com.google.appengine.api.datastore.Key;

public class ControladorUsuario {
	
	private RepositorioUsuario repositorio;
	
	public ControladorUsuario(RepositorioUsuario repositorio){
		this.repositorio = repositorio;
	}
	
	public void cadastrar(Usuario usuario)throws UsuarioJaExisteException{
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
	
	public Usuario buscar(String login) throws UsuarioNaoExisteException{		
		Usuario retorno = this.repositorio.procurar(login);
		
		if(retorno == null){
			throw new UsuarioNaoExisteException();			
		}		
		
		return retorno;
	}
	
	public Usuario buscar(Key chave) throws UsuarioNaoExisteException{		
		Usuario retorno = this.repositorio.procurar(chave);
		
		if(retorno == null){
			throw new UsuarioNaoExisteException();			
		}		
		
		return retorno;
	}
	
	private boolean existe(Usuario usuario){
		return this.repositorio.existe(usuario);
	}
}
