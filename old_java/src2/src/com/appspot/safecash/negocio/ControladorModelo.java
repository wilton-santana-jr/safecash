package com.appspot.safecash.negocio;

import java.util.Iterator;

import com.appspot.safecash.dados.Modelo;
import com.appspot.safecash.negocio.exception.ModeloNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioModelo;

public class ControladorModelo {

	private RepositorioModelo repositorio;

	public ControladorModelo(RepositorioModelo repositorio){
		this.repositorio = repositorio;
	}

	public void inserir(Modelo modelo){
		this.repositorio.inserir(modelo);
	}

	public void remover(Modelo modelo) throws ModeloNaoExisteException{
		this.repositorio.remover(modelo);
	}

	public void atualizar(Modelo modelo) throws ModeloNaoExisteException{
		this.repositorio.atualizar(modelo);
	}

	public Modelo procurar(Long id) throws ModeloNaoExisteException{
		Modelo m = this.repositorio.procurar(id);
		
		if(m == null)
			throw new ModeloNaoExisteException();
		
		return m;
	}
	
	public Iterator<Modelo> getAll(){
		return this.repositorio.iterator();
	}
}
