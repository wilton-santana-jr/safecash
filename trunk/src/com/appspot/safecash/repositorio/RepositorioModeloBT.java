package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.appspot.safecash.dados.Modelo;

public class RepositorioModeloBT implements RepositorioModelo {

	private PersistenceManager pm;
	
	public RepositorioModeloBT() { }
	
	@Override
	public void atualizar(Modelo modelo) {
		pm = PMF.get().getPersistenceManager();
		Modelo m = pm.getObjectById(Modelo.class, modelo.getId());
		m.setDescricao(modelo.getDescricao());
		m.setNome(modelo.getNome());
		//m.setBlob(modelo.getBlob());
		pm.close();	
	}

	@Override
	public boolean existe(Modelo modelo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inserir(Modelo modelo) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(modelo);
		} finally{
			pm.close();
		}
	}

	@Override
	public Modelo procurar(Long id) {
		Modelo ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Modelo.class, id);
		
		pm.close();		
		return ret;
	}

	@Override
	public void remover(Modelo modelo) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(modelo.getClass(), modelo.getId()));
		pm.close();
	}

	@Override
	public Iterator<Modelo> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Modelo> extent = pm.getExtent(Modelo.class, false);
		List<Modelo> p = new ArrayList<Modelo>();
		for(Modelo atual : extent){
			p.add(atual);
		}
		pm.close();
		return p.iterator();
	}
}