package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.google.appengine.api.datastore.Key;

public class RepositorioRequisicaoBT implements RepositorioRequisicao {

	private static final String PROCURAR_R = "SELECT FROM " + Requisicao.class.getName() 
											+ " " +  "WHERE status == param " + 
											"PARAMETERS EnumStatusRequisicao param";
	private PersistenceManager pm;
		
	public RepositorioRequisicaoBT() { }
	
	@Override
	public void atualizar(Requisicao requisicao) {
		pm = PMF.get().getPersistenceManager();
		Requisicao r = pm.getObjectById(Requisicao.class, requisicao.getKey());
		r.setDescricao(requisicao.getDescricao());
		r.setStatus(requisicao.getStatus());
		pm.close();	
	}

	@Override
	public boolean existe(Requisicao requisicao) {
		// falta implementar!
		// o que vai diferenciar duas requisicoes ?
		return false;
	}

	@Override
	public void inserir(Requisicao requisicao) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(requisicao);
		} finally{
			pm.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Requisicao> procurar(EnumStatusRequisicao status) {
		List<Requisicao> ret = new ArrayList<Requisicao>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_R);
		List<Requisicao> result = (List<Requisicao>) query.execute(status);
		for(Requisicao r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}

	@Override
	public Requisicao procurar(Key key) {
		Requisicao ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Requisicao.class, key);
		
		pm.close();		
		return ret;
	}

	@Override
	public void remover(Requisicao requisicao) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(requisicao.getClass(), requisicao.getKey()));
		pm.close();
	}

	@Override
	public Iterator<Requisicao> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Requisicao> extent = pm.getExtent(Requisicao.class, false);
		List<Requisicao> p = new ArrayList<Requisicao>();
		for(Requisicao atual : extent){
			p.add(atual);
		}
		pm.close();
		
		return p.iterator();
	}
}