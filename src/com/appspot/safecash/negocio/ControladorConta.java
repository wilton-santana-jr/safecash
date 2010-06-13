package com.appspot.safecash.negocio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.enuns.EnumStatusConta;
import com.appspot.safecash.negocio.exception.ContaNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioConta;
import com.google.appengine.api.datastore.Key;

public class ControladorConta {

	private RepositorioConta repositorio;

	public ControladorConta(RepositorioConta repositorio){
		this.repositorio = repositorio;
	}

	public void inserir(Conta conta) {
		this.repositorio.inserir(conta);
	}

	public Iterator<Conta> buscar(Date dataInicial, Date dataFinal) throws ContaNaoExisteException{
		Iterator<Conta> retorno = this.repositorio.procurar(dataInicial, dataFinal);

		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		
		return retorno;
	}
	
	public Iterator<Conta> buscar(Date data) throws ContaNaoExisteException{
		Iterator<Conta> retorno = this.repositorio.procurar(data);

		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		
		return retorno;
	}

	public Iterator<Conta> buscar(EnumStatusConta status) throws ContaNaoExisteException{
		
		Iterator<Conta> retorno = this.repositorio.procurar(status);
		
		if(retorno == null){
			throw new ContaNaoExisteException();
		}
		
		return retorno;
	}
	
	public Conta buscar(Key chave) throws ContaNaoExisteException{
		Conta c = this.repositorio.procurar(chave);
		
		if(c == null)
			throw new ContaNaoExisteException();
		
		return c;
	}

	public void remover(Conta conta) throws ContaNaoExisteException{
		try{
			this.repositorio.remover(conta);
		}catch (Exception e){
			throw new ContaNaoExisteException();
		}
	}

	public void atualizar(Conta conta) throws ContaNaoExisteException{
		try{
			this.repositorio.atualizar(conta);
		} catch(Exception e){
			throw new ContaNaoExisteException();
		}
	}

	public Iterator<Conta> getAll(){
		return this.repositorio.iterator();
	}
}