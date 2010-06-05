package com.appspot.testebigtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class RepositorioPessoa implements Iterable<Pessoa>{
	
	private PersistenceManager pm;
	
	public RepositorioPessoa(){}
	
	public boolean inserir(Pessoa pessoa){
		boolean teste = false;
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(pessoa);
			teste = true;
		} finally{
			pm.close();
		}
		return teste;
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Pessoa> procurar(String nome) throws NenhumaPessoaEncontradaException{
		pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery("select from Pessoa " +
                				  "where nome == nomeParam " +
                				  "order by dataNascimento desc " +
                				  "parameters String nomeParam");
		
		List<Pessoa> resultado = (List<Pessoa>) query.execute(nome);
		
		if(resultado.isEmpty())
			throw new NenhumaPessoaEncontradaException();
		
		pm.close();
		
		return resultado.iterator();
	}

	@Override
	public Iterator<Pessoa> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Pessoa> extent = pm.getExtent(Pessoa.class, false);
		List<Pessoa> p = new ArrayList<Pessoa>();
		for(Pessoa atual : extent){
			p.add(atual);
		}
		pm.close();
		return p.iterator();
	}
}
