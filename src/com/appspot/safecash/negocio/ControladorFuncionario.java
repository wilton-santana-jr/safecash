package com.appspot.safecash.negocio;

import java.util.Iterator;

import com.appspot.safecash.dados.Funcionario;
import com.appspot.safecash.negocio.exception.FuncionarioJaExisteException;
import com.appspot.safecash.negocio.exception.FuncionarioNaoEncontradoException;
import com.appspot.safecash.negocio.exception.FuncionarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioFuncionario;
import com.google.appengine.api.datastore.Key;

public class ControladorFuncionario {

	private RepositorioFuncionario repositorio;
	
	public ControladorFuncionario(RepositorioFuncionario repositorio){
		this.repositorio = repositorio;
	}
	
	public void inserir(Funcionario funcionario) throws FuncionarioJaExisteException{
		if(!this.existe(funcionario)){
			this.repositorio.inserir(funcionario);
		}
		else{
			throw new FuncionarioJaExisteException();
		}
	}
	
	public void remover(Funcionario funcionario) throws FuncionarioNaoExisteException{
		if(this.existe(funcionario)){
			this.repositorio.remover(funcionario);
		}
		else{
			throw new FuncionarioNaoExisteException();
		}
	}
	
	public void atualizar(Funcionario funcionario) throws FuncionarioNaoExisteException{
		if(this.existe(funcionario)){
			this.repositorio.atualizar(funcionario);
		}
		else{
			throw new FuncionarioNaoExisteException();
		}
	}
	
	private boolean existe(Funcionario funcionario){
		return this.repositorio.existe(funcionario);
	}
	
	public Funcionario procurar(Key chave) throws FuncionarioNaoEncontradoException{
		Funcionario ret = this.repositorio.procurar(chave);
		
		if(ret == null)
			throw new FuncionarioNaoEncontradoException();
		
		return ret;
	}
	
	public Funcionario procurar(String cpf) throws FuncionarioNaoEncontradoException{
		Funcionario ret = this.repositorio.procurar(cpf);
		
		if(ret == null)
			throw new FuncionarioNaoEncontradoException();
		
		return ret;
	}
	
	public Iterator<Funcionario> getAll(){
		return this.repositorio.iterator();
	}
	
}
