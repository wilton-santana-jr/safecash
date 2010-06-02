package com.appspot.testebigtable;

import java.util.List;

public interface PessoaDAO {
	
	public void save(Pessoa pessoa);

	public void delete(Integer id);

	public Pessoa getById(Integer id);

	public List<Pessoa> findAll();
}
