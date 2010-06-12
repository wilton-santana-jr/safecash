package com.appspot.safecash.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.safecash.dados.Usuario;
import com.google.appengine.api.datastore.Key;

public class RepositorioUsuarioBT implements RepositorioUsuario {

	private static final String PROCURAR_U = "SELECT FROM " + Usuario.class.getName() 
						+ " " +  "WHERE login == param " + "PARAMETERS String param";
	private PersistenceManager pm;
	
	public RepositorioUsuarioBT() { }
	
	@Override
	public void atualizar(Usuario usuario) {
		pm = PMF.get().getPersistenceManager();
		Usuario u = pm.getObjectById(Usuario.class, usuario.getKey());
		u.setNome(usuario.getNome());
		u.setSenha(usuario.getSenha());
		u.setPermissao(usuario.getPermissao());
		u.setChavesRequisicoes(usuario.getChavesRequisicoes());
		pm.close();
	}

	@Override
	public boolean existe(Usuario usuario) {
		Usuario u = this.procurar(usuario.getLogin());
		return u != null;
	}

	@Override
	public void inserir(Usuario usuario) {
		pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(usuario);
		} finally{
			pm.close();
		}
	}

	@Override
	public Usuario procurar(Key chave) {
		Usuario ret = null;
		pm = PMF.get().getPersistenceManager();
		ret = pm.getObjectById(Usuario.class, chave);
		
		pm.close();		
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Usuario procurar(String login) {
		Usuario ret = null;
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(PROCURAR_U);
		List<Usuario> result = (List<Usuario>) query.execute(login);
		if(result.size() == 1)
			ret = result.get(0);
		
		pm.close();		
		return ret;
	}

	@Override
	public void remover(Usuario usuario) {
		pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(usuario.getClass(), usuario.getKey()));
		pm.close();
	}

	@Override
	public Iterator<Usuario> iterator() {
		pm = PMF.get().getPersistenceManager();
		
		Extent<Usuario> extent = pm.getExtent(Usuario.class, false);
		List<Usuario> p = new ArrayList<Usuario>();
		for(Usuario atual : extent){
			p.add(atual);
		}
		pm.close();
		
		return p.iterator();
	}
}