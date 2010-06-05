package com.appspot.safecash.repositorio;


import com.appspot.safecash.dados.Usuario;

public interface RepositorioUsuario {
	public void inserir(Usuario usuario);
	public void remover(Usuario projeto);
	public void atualizar(Usuario projeto);
	public boolean existe(Usuario projeto);

}
