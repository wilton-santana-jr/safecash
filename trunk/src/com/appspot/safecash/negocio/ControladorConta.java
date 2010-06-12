package com.appspot.safecash.negocio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.enuns.EnumStatusConta;
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

	/*public Iterator<Conta> procurar(Projeto projeto) throws ContaNaoExisteException{

		Iterator<Conta> retorno = this.repositorio.procurar(projeto);

		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		else{
			return retorno;
		}
	}*/

	public Iterator<Conta> procurar(Date dataInicial, Date dataFinal) throws ContaNaoExisteException{

		Iterator<Conta> retorno = this.repositorio.procurar(dataInicial, dataFinal);

		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		else{
			return retorno;
		}
	}

	public Iterator<Conta> procurar(EnumStatusConta status) throws ContaNaoExisteException{
		
		Iterator<Conta> retorno = this.repositorio.procurar(status);
		
		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		else{
			return retorno;
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