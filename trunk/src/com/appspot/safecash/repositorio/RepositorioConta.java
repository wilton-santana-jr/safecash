package com.appspot.safecash.repositorio;

import java.util.Date;
import java.util.Iterator;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.enuns.EnumStatusConta;
import com.google.appengine.api.datastore.Key;

public interface RepositorioConta extends Iterable<Conta> {
	
	public void inserir(Conta conta);
	public Conta procurar(Key chave);
	public Iterator<Conta> procurar(Date data);
	public Iterator<Conta> procurar(Date dataInicial, Date dataFinal);
	public Iterator<Conta> procurar(EnumStatusConta status);
	public void remover(Conta conta);
	public void atualizar(Conta conta);
}
