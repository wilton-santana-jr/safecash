package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Projeto;
import com.google.appengine.api.datastore.Key;

public class RepositorioProjetoBT implements RepositorioProjeto {

	private static final String PROCURAR_P1 = "SELECT FROM " + Projeto.class.getName() 
											  + " " +  "WHERE dataInicio == param " + 
											  "PARAMETERS Date param";
	private static final String PROCURAR_P2 = "SELECT FROM " + Projeto.class.getName() 
	  										  + " " +  "WHERE dataFim == param " + 
	  										  "PARAMETERS Date param";
	private static final String PROCURAR_P3 = "SELECT FROM " + Projeto.class.getName() 
	  										  + " " +  "WHERE chaveResponsavel == param " + 
	  										  "PARAMETERS Key param";
	private PersistenceManager pm;
	
	public RepositorioProjetoBT() { }
	
	@Override
	public void atualizar(Projeto projeto) {
		pm = PMF.get().getPersistenceManager();
		Projeto p = pm.getObjectById(Projeto.class, projeto.getId());
		p.setChaveResponsavel(projeto.getChaveResponsavel());
		p.setChaveContaEntrada(projeto.getChaveContaEntrada());
		p.setChaveContaSaida(projeto.getChaveContaSaida());
		p.setChavesEquipe(projeto.getChavesEquipe());
		p.setDataFim(projeto.getDataFim());
		p.setDataInicio(projeto.getDataInicio());
		p.setNome(projeto.getNome());
		p.setValor(projeto.getValor());
		pm.close();	
	}

	@Override
	public void inserir(Projeto projeto) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(projeto);
		} finally{
			pm.close();
		}
	}

	@Override
	public Projeto procurar(Long id) {
		Projeto ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Projeto.class, id);
		
		pm.close();		
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Projeto> procurarPorDataFinal(Date data) {
		List<Projeto> ret = new ArrayList<Projeto>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_P2);
		query.declareImports("import java.util.Date");
		List<Projeto> result = (List<Projeto>) query.execute(data);
		for(Projeto r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Projeto> procurarPorDataInicial(Date data) {
		List<Projeto> ret = new ArrayList<Projeto>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_P1);
		query.declareImports("import java.util.Date");
		List<Projeto> result = (List<Projeto>) query.execute(data);
		for(Projeto r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Projeto> procurarPorResponsavel(Key chaveUsuario) {
		List<Projeto> ret = new ArrayList<Projeto>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_P3);
		query.declareImports("import com.google.appengine.api.datastore.Key;");
		List<Projeto> result = (List<Projeto>) query.execute(chaveUsuario);
		for(Projeto r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}
	
	@Override
	public Projeto procurarPorID(Long id) {
		Projeto ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Projeto.class, id);
		
		pm.close();		
		return ret;
	}

	@Override
	public void remover(Projeto projeto) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(projeto.getClass(), projeto.getId()));
		pm.close();
	}

	@Override
	public Iterator<Projeto> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Projeto> extent = pm.getExtent(Projeto.class, false);
		List<Projeto> p = new ArrayList<Projeto>();
		for(Projeto atual : extent){
			p.add(atual);
		}
		pm.close();
		
		return p.iterator();
	}
}