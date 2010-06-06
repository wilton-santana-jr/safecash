package com.appspot.safecash.negocio;

import java.util.Iterator;

import com.appspot.safecash.dados.Funcionario;
import com.appspot.safecash.negocio.exception.FuncionarioJaExisteException;
import com.appspot.safecash.negocio.exception.FuncionarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioFuncionario;

public class ControladorFuncionario {

	private RepositorioFuncionario repositorio;
	
	public ControladorFuncionario(){}
	
	public void inserir(Funcionario funcionario) throws FuncionarioJaExisteException{
		if(!this.existe(funcionario)){
			this.repositorio.inserir(funcionario);
		}
		else{
			throw new FuncionarioJaExisteException();
		}
	}
	
	public Iterator<Funcionario> procurarPorNome(String nome) throws FuncionarioNaoExisteException{
		
		Iterator<Funcionario> retorno = this.repositorio.procurarPorNome(nome);
		
		if(retorno == null){
			throw new FuncionarioNaoExisteException();
		}
		else{
			return retorno;
		}
	}
	public Iterator<Funcionario> procurarPorCargo(String cargo) throws FuncionarioNaoExisteException{
		
		Iterator<Funcionario> retorno = this.procurarPorCargo(cargo);
		
		if(retorno == null){
			throw new FuncionarioNaoExisteException();
		}
		else{
			return retorno;
		}
	}
	
	public Funcionario procurarPorCPF(String cpf) throws FuncionarioNaoExisteException{
		
		Funcionario retorno = this.repositorio.procurarPorCPF(cpf);
		
		if(retorno == null){
			throw new FuncionarioNaoExisteException();
		}
		else{
			return retorno;
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
}
