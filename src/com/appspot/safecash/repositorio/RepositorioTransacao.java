package com.appspot.safecash.repositorio;

import com.appspot.safecash.dados.Transacao;

public interface RepositorioTransacao extends Iterable<Transacao> {
	
	public void inserir(Transacao transacao);
	
}
