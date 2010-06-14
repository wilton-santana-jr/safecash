package com.appspot.safecash.repositorio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Projeto;
import com.google.appengine.api.datastore.Key;

public interface RepositorioProjeto extends Iterable<Projeto>{
	
	public void inserir(Projeto projeto);
	public Projeto procurar(Long id);
	public Projeto procurarPorID(Long id);
	public Iterator<Projeto> procurarPorDataInicial(Date data);
	public Iterator<Projeto> procurarPorDataFinal(Date data);
	public Iterator<Projeto> procurarPorResponsavel(Key chaveUsuario);
	public void remover(Projeto projeto);
	public void atualizar(Projeto projeto);
}
