package com.appspot.safecash.repositorio;


import com.appspot.safecash.dados.Usuario;

public interface RepositorioUsuario {
	public void inserir(Usuario usuario);
	public void remover(Usuario usuario);
	public void atualizar(Usuario usuario);
	public boolean existe(Usuario usuario);

}
