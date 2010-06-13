package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.appspot.safecash.repositorio.RepositorioTransacao;
import com.google.appengine.api.datastore.Key;


public class RepositorioTransacaoBT implements RepositorioTransacao {

	private static final String PROCURAR_T1 = "SELECT FROM " + Transacao.class.getName() 
												+ " " +  "WHERE tipo == param " + 
												"PARAMETERS EnumTipoTransacao param";

	private static final String PROCURAR_T2 = "SELECT FROM " + Transacao.class.getName() 
												+ " " +  "WHERE origem == param " + 
												"PARAMETERS EnumOrigemTransacao param";

	private static final String PROCURAR_T3 = "SELECT FROM " + Transacao.class.getName() 
												+ " " +  "WHERE data == param " + 
												"PARAMETERS Date param";
	private static final String PROCURAR_T4 = "SELECT FROM " + Transacao.class.getName() 
										+ " " + "WHERE data >= dataIni && data <= dataFim" 
											  + " PARAMETERS Date dataIni, Date dataFim";
	private PersistenceManager pm;
	
	public RepositorioTransacaoBT() { }
	
	@Override
	public void atualizar(Transacao transacao) {
		pm = PMF.get().getPersistenceManager();
		Transacao r = pm.getObjectById(Transacao.class, transacao.getKey());
		r.setData(transacao.getData());
		r.setDescricao(transacao.getDescricao());
		r.setOrigem(transacao.getOrigem());
		r.setTipo(transacao.getTipo());
		r.setValor(transacao.getValor());
		pm.close();	
	}

	@Override
	public void inserir(Transacao transacao) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(transacao);
		} finally{
			pm.close();
		}
	}

	@Override
	public Transacao procurar(Key chave) {
		Transacao ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Transacao.class, chave);
		
		pm.close();		
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Transacao> procurar(EnumTipoTransacao tipo) {
		List<Transacao> ret = new ArrayList<Transacao>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_T1);
		query.declareImports("import com.appspot.safecash.enuns.EnumTipoTransacao;");
		List<Transacao> result = (List<Transacao>) query.execute(tipo);
		for(Transacao r : result){
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
	public Iterator<Transacao> procurar(EnumOrigemTransacao origem) {
		List<Transacao> ret = new ArrayList<Transacao>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_T2);
		query.declareImports("import com.appspot.safecash.enuns.EnumOrigemTransacao;");
		List<Transacao> result = (List<Transacao>) query.execute(origem);
		for(Transacao r : result){
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
	public Iterator<Transacao> procurar(Date data) {
		List<Transacao> ret = new ArrayList<Transacao>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_T3);
		query.declareImports("import java.util.Date");
		List<Transacao> result = (List<Transacao>) query.execute(data);
		for(Transacao r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}

	@Override
	public void remover(Transacao transacao) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(transacao.getClass(), transacao.getKey()));
		pm.close();
	}

	@Override
	public Iterator<Transacao> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Transacao> extent = pm.getExtent(Transacao.class, false);
		List<Transacao> p = new ArrayList<Transacao>();
		for(Transacao atual : extent){
			p.add(atual);
		}
		pm.close();
		
		return p.iterator();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Transacao> procurar(Date dataInicio, Date dataFim) {
		List<Transacao> ret = new ArrayList<Transacao>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_T4);
		query.declareImports("import java.util.Date");
		List<Transacao> result = (List<Transacao>) query.execute(dataInicio, dataFim);
		for(Transacao r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}
}