package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.RequisicaoContrato;
import com.appspot.safecash.dados.RequisicaoGeral;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.google.appengine.api.datastore.Key;

public class RepositorioRequisicaoBT implements RepositorioRequisicao {

	private static final String PROCURAR_R1 = "SELECT FROM " + Requisicao.class.getName() 
											  + " " +  "WHERE status == param " + 
											  "PARAMETERS EnumStatusRequisicao param";
	private static final String PROCURAR_R2 = "SELECT FROM " + RequisicaoContrato.class.getName() 
											  + " " +  "WHERE status == param " + 
											  "PARAMETERS EnumStatusRequisicao param";
	private static final String PROCURAR_R3 = "SELECT FROM " + RequisicaoGeral.class.getName() 
											  + " " +  "WHERE status == param " + 
											  "PARAMETERS EnumStatusRequisicao param";
	private PersistenceManager pm;
		
	public RepositorioRequisicaoBT() { }
	
	@Override
	public void atualizar(Requisicao requisicao) {
		pm = PMF.get().getPersistenceManager();
		Requisicao r = pm.getObjectById(requisicao.getClass(), requisicao.getKey());
		r.setDescricao(requisicao.getDescricao());
		r.setStatus(requisicao.getStatus());
		if(r instanceof RequisicaoGeral)
			((RequisicaoGeral) r).setValor(((RequisicaoGeral) requisicao).getValor());
		else if(r instanceof RequisicaoContrato){
			((RequisicaoContrato) r).setArquivoProposta(((RequisicaoContrato) requisicao).getArquivoProposta());
			((RequisicaoContrato) r).setCNPJ_CPF(((RequisicaoContrato) requisicao).getCNPJ_CPF());
			((RequisicaoContrato) r).setEndereco(((RequisicaoContrato) requisicao).getEndereco());
			((RequisicaoContrato) r).setNomeProjeto(((RequisicaoContrato) requisicao).getNomeProjeto());
			((RequisicaoContrato) r).setObservacao(((RequisicaoContrato) requisicao).getObservacao());
			((RequisicaoContrato) r).setResponsavel(((RequisicaoContrato) requisicao).getResponsavel());
			((RequisicaoContrato) r).setValor(((RequisicaoContrato) requisicao).getValor());
		}
		pm.close();	
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
		Query query = pm.newQuery(PROCURAR_R1);
		query.declareImports("import com.appspot.safecash.enuns.EnumStatusRequisicao;");
		List<Requisicao> result = (List<Requisicao>) query.execute(status);
		for(Requisicao r : result){
			ret.add(r);
			System.out.println("01");
		}
		
		query = pm.newQuery(PROCURAR_R2);
		query.declareImports("import com.appspot.safecash.enuns.EnumStatusRequisicao;");
		result = (List<Requisicao>) query.execute(status);
		for(Requisicao r : result){
			ret.add(r);
			System.out.println("02");
		}
		
		query = pm.newQuery(PROCURAR_R3);
		query.declareImports("import com.appspot.safecash.enuns.EnumStatusRequisicao;");
		result = (List<Requisicao>) query.execute(status);
		for(Requisicao r : result){
			ret.add(r);
			System.out.println("03");
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
		if(key.getKind().equals(Requisicao.class.getSimpleName()))
			ret = pm.getObjectById(Requisicao.class, key);
		if(key.getKind().equals(RequisicaoContrato.class.getSimpleName()))
			ret = pm.getObjectById(RequisicaoContrato.class, key); 
		else if(key.getKind().equals(RequisicaoGeral.class.getSimpleName()))
			ret = pm.getObjectById(RequisicaoGeral.class, key);
		
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
		
		Extent<Requisicao> extent = pm.getExtent(Requisicao.class, true);
		List<Requisicao> p = new ArrayList<Requisicao>();
		for(Requisicao atual : extent){
			p.add(atual);
		}
		
		Extent<RequisicaoContrato> extent2 = pm.getExtent(RequisicaoContrato.class, false);
		for(RequisicaoContrato atual : extent2){
			p.add(atual);
		}
		
		Extent<RequisicaoGeral> extent3 = pm.getExtent(RequisicaoGeral.class, false);
		for(RequisicaoGeral atual : extent3){
			p.add(atual);
		}
		
		pm.close();
		
		return p.iterator();
	}
}