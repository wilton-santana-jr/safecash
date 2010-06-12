package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumStatusConta;

public class RepositorioContaBT implements RepositorioConta {

	private static final String PROCURAR_C1 = "SELECT FROM " + Transacao.class.getName() 
											  + " " +  "WHERE data == param " + 
											  "PARAMETERS Date param";
	private static final String PROCURAR_C2 = "SELECT FROM " + Transacao.class.getName() 
	  										+ " WHERE data >= dataIni && data <= dataFim" 
	  										+ " PARAMETERS Date dataIni Date dataFim";
	private static final String PROCURAR_C3 = "SELECT FROM " + Conta.class.getName() 
											  + " " +  "WHERE status == param " + 
											  "PARAMETERS EnumStatusConta param";
	private PersistenceManager pm;
	
	public RepositorioContaBT() { }
	
	@Override
	public void atualizar(Conta conta) {
		pm = PMF.get().getPersistenceManager();
		Conta c = pm.getObjectById(Conta.class, conta.getKey());
		c.setChavesTransacoes(conta.getChavesTransacoes());
		c.setData(conta.getData());
		c.setDescricao(conta.getDescricao());
		c.setStatus(conta.getStatus());
		c.setValor(conta.getValor());
		pm.close();	
	}

	@Override
	public boolean existe(Conta conta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inserir(Conta conta) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(conta);
		} finally{
			pm.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Conta> procurar(Date data) {
		List<Conta> ret = new ArrayList<Conta>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_C1);
		query.declareImports("import java.util.Date");
		List<Conta> result = (List<Conta>) query.execute(data);
		for(Conta r : result){
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
	public Iterator<Conta> procurar(Date dataInicial, Date dataFinal) {
		List<Conta> ret = new ArrayList<Conta>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_C2);
		query.declareImports("import java.util.Date");
		List<Conta> result = (List<Conta>) query.execute(dataInicial, dataFinal);
		for(Conta r : result){
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
	public Iterator<Conta> procurar(EnumStatusConta status) {
		List<Conta> ret = new ArrayList<Conta>();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_C3);
		query.declareImports("import com.appspot.safecash.enuns.EnumStatusConta;");
		List<Conta> result = (List<Conta>) query.execute(status);
		for(Conta r : result){
			ret.add(r);
		}
		
		pm.close();		
		
		if(ret.size() > 0)
			return ret.iterator();
		else
			return null;
	}

	@Override
	public void remover(Conta conta) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(conta.getClass(), conta.getKey()));
		pm.close();
	}

	@Override
	public Iterator<Conta> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Conta> extent = pm.getExtent(Conta.class, false);
		List<Conta> p = new ArrayList<Conta>();
		for(Conta atual : extent){
			p.add(atual);
		}
		pm.close();
		
		return p.iterator();
	}
}
