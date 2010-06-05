package com.appspot.testebigtable;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class RepositorioPessoa {
	
	private PersistenceManager pm;
	
	public RepositorioPessoa(){}
	
	public void inserir(Pessoa pessoa){
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(pessoa);
		} finally{
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Pessoa> procurar(String nome) throws NenhumaPessoaEncontradaException{
		Query query = pm.newQuery("select from Pessoa " +
                				  "where nome == nomeParam " +
                				  "order by dataNascimento desc " +
                				  "parameters String nomeParam");
		
		List<Pessoa> resultado = (List<Pessoa>) query.execute(nome);
		
		if(resultado.isEmpty())
			throw new NenhumaPessoaEncontradaException();
		
		return resultado.iterator();
	}
}
