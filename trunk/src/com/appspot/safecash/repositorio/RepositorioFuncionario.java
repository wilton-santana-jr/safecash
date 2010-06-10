package com.appspot.safecash.repositorio;

import com.appspot.safecash.dados.Funcionario;
import com.google.appengine.api.datastore.Key;

public interface RepositorioFuncionario extends Iterable<Funcionario> {
	
	public void inserir(Funcionario funcionario);
	public Funcionario procurar(String cpf);
	public Funcionario procurar(Key chave);
	public void remover(Funcionario funcionario);
	public void atualizar(Funcionario funcionario);
	public boolean existe(Funcionario funcionario);
}
