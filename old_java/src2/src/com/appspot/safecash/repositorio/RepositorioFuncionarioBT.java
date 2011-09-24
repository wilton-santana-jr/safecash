package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Funcionario;
import com.google.appengine.api.datastore.Key;

public class RepositorioFuncionarioBT implements RepositorioFuncionario {

	private static final String PROCURAR_F = "SELECT FROM " + Funcionario.class.getName() 
								+ " " +  "WHERE CPF == param " + "PARAMETERS String param";
	private PersistenceManager pm;
		
	public RepositorioFuncionarioBT() { }
	
	@Override
	public void atualizar(Funcionario funcionario) {
		pm = PMF.get().getPersistenceManager();
		Funcionario f = pm.getObjectById(Funcionario.class, funcionario.getKey());
		f.setCargo(funcionario.getCargo());
		f.setEmail(funcionario.getEmail());
		f.setEndereco(funcionario.getEndereco());
		f.setNome(funcionario.getNome());
		f.setSalario(funcionario.getSalario());
		f.setTelefone(funcionario.getTelefone());
		pm.close();		
	}

	@Override
	public boolean existe(Funcionario funcionario) {
		Funcionario f = this.procurar(funcionario.getCPF());
		return f != null;
	}

	@Override
	public void inserir(Funcionario funcionario) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(funcionario);
		} finally{
			pm.close();
		}
	}

	@Override
	public Funcionario procurar(Key chave) {
		Funcionario ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Funcionario.class, chave);
		
		pm.close();		
		return ret;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Funcionario procurar(String cpf) {
		Funcionario ret = null;
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_F);
		List<Funcionario> result = (List<Funcionario>) query.execute(cpf);
		if(result.size() == 1)
			ret = result.get(0);
		
		pm.close();		
		return ret;
	}

	@Override
	public void remover(Funcionario funcionario) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(funcionario.getClass(), funcionario.getKey()));
		pm.close();
	}

	@Override
	public Iterator<Funcionario> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Funcionario> extent = pm.getExtent(Funcionario.class, false);
		List<Funcionario> p = new ArrayList<Funcionario>();
		for(Funcionario atual : extent){
			p.add(atual);
		}
		pm.close();
		return p.iterator();
	}
}