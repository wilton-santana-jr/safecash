package com.appspot.safecash.repositorio;

import java.util.Iterator;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumStatusRequisicao;

public interface RepositorioRequisicao {
	
	public void inserir(Requisicao requisicao);
	public Iterator<Requisicao> procurar(Usuario usuario);
	public Iterator<Requisicao> procurar(EnumStatusRequisicao status);
	public void remover(Requisicao requisicao);
	public void atualizar(Requisicao requisicao);
	public boolean existe(Requisicao requisicao);
}
