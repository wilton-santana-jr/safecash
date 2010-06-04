package com.appspot.safecash.repositorio;

import java.util.Iterator;

import com.appspot.safecash.dados.Funcionario;

public interface RepositorioFuncionario extends Iterable<Funcionario> {
	
	public void inserir(Funcionario funcionario);
	public Iterator<Funcionario> procurarPorNome(String nome);
	public Iterator<Funcionario> procurarPorCargo(String cargo);
	public Funcionario procurarPorCPF(String cpf);
	public void remover(Funcionario funcionario);
	public void atualizar(Funcionario funcionario);
	public boolean existe(Funcionario funcionario);
}
