package com.appspot.safecash.negocio;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.negocio.exception.ContaJaExisteException;
import com.appspot.safecash.negocio.exception.ContaNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioConta;

public class ControladorConta {

	private RepositorioConta repositorio;
	
	public ControladorConta(){}
	
	public void inserirConta(Conta conta) throws ContaJaExisteException{
		if(!this.existe(conta)){
			this.repositorio.inserir(conta);
		}
		else{
			throw new ContaJaExisteException();
		}
	}
	
	public void remover(Conta conta) throws ContaNaoExisteException{
		if(this.existe(conta)){
			this.repositorio.remover(conta);
		}
		else{
			throw new ContaNaoExisteException();
		}
	}
	
	public void atualizar(Conta conta) throws ContaNaoExisteException{
		if(!this.existe(conta)){
			throw new ContaNaoExisteException();
		}
		else{
			this.repositorio.atualizar(conta);
		}
	}
	
	private boolean existe(Conta conta){
		return this.repositorio.existe(conta);
	}
}