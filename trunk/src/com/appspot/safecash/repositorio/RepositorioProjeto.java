package com.appspot.safecash.repositorio;

import java.util.Iterator;

import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.dados.Usuario;

public interface RepositorioProjeto extends Iterable<Projeto>{
	
	public void inserir(Projeto projeto);
	public Iterator<Projeto> procurar(Usuario responsavel);
	public Iterator<Projeto> procurar(String nome);
	public void remover(Projeto projeto);
	public void atualizar(Projeto projeto);
	public boolean existe(Projeto projeto);
}
