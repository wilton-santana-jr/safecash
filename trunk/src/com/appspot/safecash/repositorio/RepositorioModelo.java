package com.appspot.safecash.repositorio;

import com.appspot.safecash.dados.Modelo;

public interface RepositorioModelo extends Iterable<Modelo>{

	public void inserir(Modelo modelo);
	public Modelo procurar(Long id);
	public void remover(Modelo modelo);
	public void atualizar(Modelo modelo);
	public boolean existe(Modelo modelo);
}
