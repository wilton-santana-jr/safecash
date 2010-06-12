package com.appspot.safecash.repositorio;


import com.appspot.safecash.dados.Usuario;
import com.google.appengine.api.datastore.Key;

public interface RepositorioUsuario extends Iterable<Usuario> {
	
	public void inserir(Usuario usuario);
	public void remover(Usuario usuario);
	public Usuario procurar(String login);
	public Usuario procurar(Key chave);
	public void atualizar(Usuario usuario);
	public boolean existe(Usuario usuario);

}
