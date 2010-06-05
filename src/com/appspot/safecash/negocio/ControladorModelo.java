package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Modelo;
import com.appspot.safecash.negocio.exception.ModeloJaExisteException;
import com.appspot.safecash.repositorio.RepositorioModelo;

public class ControladorModelo {

	private RepositorioModelo repositorio;
	
	public ControladorModelo(){}
	
	public void inserir(Modelo modelo) throws ModeloJaExisteException{
		if(!this.existe(modelo)){
			this.repositorio.inserir(modelo);
		}
		else{
			throw new ModeloJaExisteException();
		}
	}
	
	private boolean existe(Modelo modelo){
		return this.repositorio.existe(modelo);
	}
}
