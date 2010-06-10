package com.appspot.safecash.repositorio;

import java.util.Iterator;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.google.appengine.api.datastore.Key;

public interface RepositorioRequisicao extends Iterable<Requisicao>{
	
	public void inserir(Requisicao requisicao);
	public Iterator<Requisicao> procurar(EnumStatusRequisicao status);
	public Requisicao procurar(Key key);
	public void remover(Requisicao requisicao);
	public void atualizar(Requisicao requisicao);
	public boolean existe(Requisicao requisicao);
}
