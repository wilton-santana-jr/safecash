package com.appspot.testebigtable;

import java.util.List;

import javax.faces.context.FacesContext;

public class PessoaController {
	private PessoaDAO pessoaDao = PessoaDAOImp.getInstance();
	private Pessoa pessoa;

	public PessoaController() {
		pessoa = new Pessoa();
	}

	public void save() {
		pessoaDao.save(pessoa);
		pessoa = new Pessoa();
	}

	public void edit() {
		// pega o parametro passado no link
		String p = (String) FacesContext.getCurrentInstance().getExternalContext().
							getRequestParameterMap().get("id");
		Integer id = Integer.parseInt(p);
		pessoa = pessoaDao.getById(id);
	}

	public void delete() {
		// pega o parametro passado no link
		String p = (String) FacesContext.getCurrentInstance().getExternalContext().
							getRequestParameterMap().get("id");
		Integer id = Integer.parseInt(p);
		pessoaDao.delete(id);
	}

	// getters e setters
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoaDao.findAll();
	}
}
