package com.appspot.safecash.repositorio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.google.appengine.api.datastore.Key;

public interface RepositorioTransacao extends Iterable<Transacao> {
	
	public void inserir(Transacao transacao);
	public void remover(Transacao transacao);
	public void atualizar(Transacao transacao);
	public boolean existe(Transacao transacao);
	public Transacao procurar(Key chave);
	public Iterator<Transacao> procurar(EnumTipoTransacao tipo);
	public Iterator<Transacao> procurar(EnumOrigemTransacao origem);
	public Iterator<Transacao> procurar(Date data);
	public Iterator<Transacao> procurar(Date dataInicio, Date dataFim);
}
