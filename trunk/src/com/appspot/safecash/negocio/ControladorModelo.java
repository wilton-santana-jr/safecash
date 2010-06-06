package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Modelo;
import com.appspot.safecash.negocio.exception.ModeloNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioModelo;

public class ControladorModelo {

	private RepositorioModelo repositorio;

	public ControladorModelo(){}

	public void inserir(Modelo modelo){
		this.repositorio.inserir(modelo);
	}

	public void remover(Modelo modelo) throws ModeloNaoExisteException{
		if(this.existe(modelo)){
			this.repositorio.remover(modelo);
		}
		else{
			throw new ModeloNaoExisteException();
		}
	}

	public void atualizar(Modelo modelo) throws ModeloNaoExisteException{
		if(this.existe(modelo)){
			this.repositorio.atualizar(modelo);
		}
		else{
			throw new ModeloNaoExisteException();
		}
	}

	public Modelo procurar(Modelo modelo) throws ModeloNaoExisteException{

		Modelo retorno;

		if(this.existe(modelo)){
			retorno = this.repositorio.procurar(modelo.getNome());
		}
		else{
			throw new ModeloNaoExisteException();
		}
		return retorno;
	}

	private boolean existe(Modelo modelo){
		return this.repositorio.existe(modelo);
	}
}
