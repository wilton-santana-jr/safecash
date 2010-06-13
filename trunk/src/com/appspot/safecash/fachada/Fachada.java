package com.appspot.safecash.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.dados.Funcionario;
import com.appspot.safecash.dados.Modelo;
import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumStatusConta;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.fachada.exception.UsuarioNaoPodeSerRemovidoException;
import com.appspot.safecash.negocio.ControladorConta;
import com.appspot.safecash.negocio.ControladorFuncionario;
import com.appspot.safecash.negocio.ControladorModelo;
import com.appspot.safecash.negocio.ControladorProjeto;
import com.appspot.safecash.negocio.ControladorRequisicao;
import com.appspot.safecash.negocio.ControladorTransacao;
import com.appspot.safecash.negocio.ControladorUsuario;
import com.appspot.safecash.negocio.exception.ContaJaExisteException;
import com.appspot.safecash.negocio.exception.ContaNaoExisteException;
import com.appspot.safecash.negocio.exception.FuncionarioJaExisteException;
import com.appspot.safecash.negocio.exception.FuncionarioNaoEncontradoException;
import com.appspot.safecash.negocio.exception.FuncionarioNaoExisteException;
import com.appspot.safecash.negocio.exception.ModeloNaoExisteException;
import com.appspot.safecash.negocio.exception.NenhumaTransacaoEncontradaException;
import com.appspot.safecash.negocio.exception.ProjetoJaExisteException;
import com.appspot.safecash.negocio.exception.ProjetoNaoExisteException;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.negocio.exception.TransacaoJaExisteException;
import com.appspot.safecash.negocio.exception.TransacaoNaoExisteException;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;
import com.appspot.safecash.repositorio.RepositorioContaBT;
import com.appspot.safecash.repositorio.RepositorioFuncionarioBT;
import com.appspot.safecash.repositorio.RepositorioRequisicaoBT;
import com.appspot.safecash.repositorio.RepositorioTransacaoBT;
import com.appspot.safecash.repositorio.RepositorioUsuarioBT;
import com.google.appengine.api.datastore.Key;

public class Fachada {

	private static Fachada singleton = null;

	private ControladorConta controladorConta;
	private ControladorFuncionario controladorFuncionario;
	private ControladorModelo controladorModelo;
	private ControladorProjeto controladorProjeto;
	private ControladorRequisicao controladorRequisicao;
	private ControladorTransacao controladorTransacao;
	private ControladorUsuario controladorUsuario;

	// vai ter isso aqui na fachada mesmo?
	private HttpSession session;
	public HttpServletRequest request;
	public HttpServletResponse response;

	private Fachada() {
		this.controladorConta = new ControladorConta(new RepositorioContaBT());
		this.controladorFuncionario = new ControladorFuncionario(new RepositorioFuncionarioBT());
		this.controladorModelo = new ControladorModelo();
		this.controladorProjeto = new ControladorProjeto();
		this.controladorRequisicao = new ControladorRequisicao(new RepositorioRequisicaoBT());
		this.controladorTransacao = new ControladorTransacao(new RepositorioTransacaoBT());
		this.controladorUsuario = new ControladorUsuario(new RepositorioUsuarioBT());
		
		// this.gerenciaSession();
	}

	/**
	 * Método que permite a criação de apenas uma instância da fachada.
	 * 
	 * @return Fachada
	 */
	public static Fachada getInstance(){
		if(Fachada.singleton == null){
			Fachada.singleton = new Fachada();
		}
		return Fachada.singleton;
	}

	/**
	 * Método que irá gerenciar cada sessão aberta pelo programa.
	 * 
	 * @param login
	 * @param senha
	 * @throws UsuarioNaoExisteException 
	 */
	public void gerenciaSession(Usuario usuario) throws UsuarioNaoExisteException{
		this.session = this.request.getSession(true);
		//http://www.guj.com.br/posts/list/127559.java
		//http://www.stardeveloper.com/articles/display.html?article=2001062001&page=2
		
		Usuario temp = this.controladorUsuario.buscar(usuario.getLogin());
		
		this.session.setAttribute("user", temp.getNome());
	}

	
	//##################### CONTA #####################//
	
	/**
	 * Método para inserir uma conta.
	 * 
	 * @param conta
	 * @throws ContaJaExisteException
	 */
	public void inserirConta(Conta conta) {
		this.controladorConta.inserir(conta);
	}
	
	/**
	 * Método auxiliar para carregar as Transacoes de uma Conta.
	 * 
	 * @param it  Iterator<Conta>
	 * @return Iterator<Conta>
	 */
	private Iterator<Conta> carregarTransacoes(Iterator<Conta> it) {
		List<Conta> contas = new ArrayList<Conta>();
		Conta atual = null;
		while(it.hasNext()){
			atual = it.next();
			for(Key k : atual.getChavesTransacoes()){
				try {
					atual.getTransacoes().add(this.controladorTransacao.buscar(k));
				} catch (TransacaoNaoExisteException e) { }
			}
			contas.add(atual);
		}
		
		return contas.iterator();
	}
	
	/**
	 * Método para procurar contas por datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @throws ContaNaoExisteException
	 */
	public Iterator<Conta> procurarConta(Date dataInicial, Date dataFinal) throws ContaNaoExisteException{
		Iterator<Conta> it = this.controladorConta.buscar(dataInicial, dataFinal);
		
		// adiciona as transacoes de cada Conta
		return this.carregarTransacoes(it);
	}
	
	/**
	 * Método para procurar contas de uma determinada data.
	 * 
	 * @param data
	 * @throws ContaNaoExisteException
	 */
	public Iterator<Conta> procurarConta(Date data) throws ContaNaoExisteException{
		Iterator<Conta> it = this.controladorConta.buscar(data);
		
		// adiciona as transacoes de cada Conta
		return this.carregarTransacoes(it);
	}

	/**
	 * Método para procurar contas por status.
	 * 
	 * @param status
	 * @throws ContaNaoExisteException
	 */
	public Iterator<Conta> procurarConta(EnumStatusConta status) throws ContaNaoExisteException{
		Iterator<Conta> it = this.controladorConta.buscar(status);
		
		// adiciona as transacoes de cada Conta
		return carregarTransacoes(it);
	}
	
	/**
	 * Método para procurar uma conta por sua chave.
	 * 
	 * @param a chave
	 * @throws ContaNaoExisteException
	 */
	public Conta procurarConta(Key chave) throws ContaNaoExisteException{
		return this.controladorConta.buscar(chave);
	}

	/**
	 * Método para remover uma conta.
	 * 
	 * @param conta
	 * @throws ContaNaoExisteException 
	 */
	public void removerConta(Conta conta) throws ContaNaoExisteException{
		this.controladorConta.remover(conta);
	}

	/**
	 * Método para atualizar uma conta.
	 * 
	 * @param conta
	 * @throws ContaNaoExisteException
	 */
	public void atualizarConta(Conta conta) throws ContaNaoExisteException{
		this.controladorConta.atualizar(conta);
	}

	//##################### FUNCIONÁRIO #####################//

	/**
	 * Método para inserir um funcionário.
	 * 
	 * @param funcionario
	 * @throws FuncionarioJaExisteException
	 */
	public void inserirFuncionario(Funcionario funcionario) throws FuncionarioJaExisteException{
		this.controladorFuncionario.inserir(funcionario);
	}

	/**
	 * Método para procurar um funcionário por CPF.
	 * 
	 * @param cpf
	 * @throws FuncionarioNaoEncontradoException 
	 * @throws FuncionarioNaoExisteException
	 */
	public Funcionario procurarFuncionario(String cpf) throws FuncionarioNaoEncontradoException {
		return this.controladorFuncionario.procurar(cpf);
	}
	
	public Funcionario procurarFuncionario(Key chave) throws FuncionarioNaoEncontradoException {
		return this.controladorFuncionario.procurar(chave);
	}

	/**
	 * Método para remover um funcionário.
	 * 
	 * @param funcionario
	 * @throws FuncionarioNaoExisteException
	 */
	public void removerFuncionario(Funcionario funcionario) throws FuncionarioNaoExisteException{
		this.controladorFuncionario.remover(funcionario);
	}

	public void atualizarFuncionario(Funcionario funcionario) throws FuncionarioNaoExisteException{
		this.controladorFuncionario.atualizar(funcionario);
	}

	//##################### MODELO #####################//

	/**
	 * Método que insere um modelo de documento.
	 * 
	 * @param modelo
	 */
	public void inserirModelo(Modelo modelo){
		this.controladorModelo.inserir(modelo);
	}

	/**
	 * Método que remove um modelo;
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	public void removerModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.remover(modelo);
	}

	/**
	 * Método que atualiza um modelo.
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	public void atualizarModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.atualizar(modelo);
	}

	/**
	 * Método que procura um modelo.
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	/*public void procurarModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.procurar(modelo);
	}*/

	//##################### PROJETO #####################//##

	/**
	 * Método para inserir um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoJaExisteException
	 */
	public void inserirProjeto(Projeto projeto) throws ProjetoJaExisteException{
		this.controladorProjeto.inserir(projeto);
	}

	/**
	 * Método para atualizar um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoNaoExisteException
	 */
	public void atualizarProjeto(Projeto projeto) throws ProjetoNaoExisteException{
		this.controladorProjeto.atualizar(projeto);
	}

	/**
	 * Método para procurar um projeto por nome.
	 * 
	 * @param nome
	 * @throws ProjetoNaoExisteException
	 */
	/*public void procurarProjetoPorNome(String nome) throws ProjetoNaoExisteException{
		this.controladorProjeto.procurarPorNome(nome);
	}*/

	/**
	 * Método para procurar um projeto por usuario.
	 * 
	 * @param usuario
	 * @throws ProjetoNaoExisteException
	 */
	/*public void procurarProjetoPorUsuario(Usuario usuario) throws ProjetoNaoExisteException{
		this.controladorProjeto.procurarPorUsuario(usuario);
	}*/

	/**
	 * Método para remover um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoNaoExisteException
	 */
	public void removerProjeto(Projeto projeto) throws ProjetoNaoExisteException{
		this.controladorProjeto.remover(projeto);
	}

	//##################### REQUISIÇÂO #####################//

	/**
	 * Método para inserir uma requisição.
	 * 
	 * @param requisicao
	 */
	public void inserirRequisicao(Requisicao requisicao){
		this.controladorRequisicao.inserir(requisicao);
	}

	/**
	 * Método para procurar requisição por usuário.
	 * 
	 * @param usuario
	 * @throws RequisicaoNaoExisteException
	 */
	/*public void procurarRequisicaoPorUsuario(Usuario usuario) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.procurarPorUsuario(usuario);
	}*/

	/**
	 * Método para procurar requisição por status.
	 * 
	 * @param status
	 * @return 
	 * @throws RequisicaoNaoExisteException
	 */
	public Iterator<Requisicao> procurarRequisicaoPorStatus(EnumStatusRequisicao status) throws RequisicaoNaoExisteException{
		return this.controladorRequisicao.procurarPorStatus(status);
	}

	/**
	 * Método para remover requisiçao.
	 * 
	 * @param requisicao
	 * @throws RequisicaoNaoExisteException
	 */
	public void removerRequisicao(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.remover(requisicao);
	}

	/**
	 * Método para atualizar uma requisição.
	 * 
	 * @param requisicao
	 * @throws RequisicaoNaoExisteException
	 */
	public void atualizarRequisicao(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.atualizar(requisicao);
	}

	//##################### TRANSAÇÂO #####################//

	/**
	 * Método para inserir uma transação.
	 * 
	 * @param transacao
	 * @throws TransacaoJaExisteException
	 */
	public void inserirTransacao(Transacao transacao) throws TransacaoJaExisteException{
		this.controladorTransacao.inserir(transacao);
	}
	
	public Iterator<Transacao> buscar(EnumOrigemTransacao origem) throws NenhumaTransacaoEncontradaException{
		return this.controladorTransacao.buscar(origem);
	}
	
	public Iterator<Transacao> buscar(Date data) throws NenhumaTransacaoEncontradaException{
		return this.controladorTransacao.buscar(data);
	}
	
	public Iterator<Transacao> buscar(Date dataInicio, Date dataFim) throws NenhumaTransacaoEncontradaException{
		return this.controladorTransacao.buscar(dataInicio, dataFim);
	}

	/**
	 * Método para remover uma transação.
	 * 
	 * @param transacao
	 * @throws TransacaoNaoExisteException
	 */
	public void removerTransacao(Transacao transacao) throws TransacaoNaoExisteException{
		this.controladorTransacao.remover(transacao);
	}

	/**
	 * Método para atualizar uma transação.
	 * 
	 * @param transacao
	 * @throws TransacaoNaoExisteException
	 */
	public void atualizarTransacao(Transacao transacao) throws TransacaoNaoExisteException{
		this.controladorTransacao.atualizar(transacao);
	}

	//##################### USUÁRIO #####################//

	/**
	 * Método para inserir um usuário.
	 * 
	 * @param usuario
	 * @throws UsuarioJaExisteException
	 */
	public void inserirUsuario(Usuario usuario) throws UsuarioJaExisteException{
		this.controladorUsuario.cadastrar(usuario);
	}

	/**
	 * Método para remover usuário.
	 * 
	 * @param usuario
	 * @throws UsuarioNaoExisteException
	 * @throws UsuarioNaoPodeSerRemovidoException 
	 */
	public void removerUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioNaoPodeSerRemovidoException{
		Usuario u = this.controladorUsuario.buscar(usuario.getLogin());
		
		// remove as requisicoes associadas ao usuario que está sendo removido
		Requisicao r;
		for(Key k : u.getChavesRequisicoes()){
			try {
				r = this.controladorRequisicao.procurar(k);
				this.controladorRequisicao.remover(r);
			} catch (RequisicaoNaoExisteException e) { }
		}
		
		// o usuário não pode ser removido se for responsável por algum projeto. 
		try {
			this.controladorProjeto.procurarPorUsuario(u.getKey());
		} catch (ProjetoNaoExisteException e) {
			throw new UsuarioNaoPodeSerRemovidoException();
		}
		
		this.controladorUsuario.remover(usuario);
	}

	/**
	 * Método para atualizar usuário.
	 * 
	 * @param usuario
	 * @throws UsuarioNaoExisteException
	 */
	public void atualizarUsuario(Usuario usuario) throws UsuarioNaoExisteException{
		this.controladorUsuario.atualizar(usuario);
	}
	
	/**
	 * Método que retorna um boolean para informar se usuário existe.
	 * Comumente usado no Servlet pra logar.
	 * 
	 * @param usuario
	 * @throws UsuarioNaoExisteException
	 */
	public boolean existeUsuario(Usuario usuario) {
		try {
			this.controladorUsuario.buscar(usuario.getLogin());
			return true;
		} catch (UsuarioNaoExisteException e) {
			return false;
		}		
	}
	
	public Usuario buscar(Key chave) throws UsuarioNaoExisteException{
		Usuario ret = this.controladorUsuario.buscar(chave);
		
		// pega as requisicoes feitas pelo usuário e armazena no atributo requisicoes
		for(Key k : ret.getChavesRequisicoes()){
			try {
				ret.getRequisicoes().add(this.controladorRequisicao.procurar(k));
			} catch (RequisicaoNaoExisteException e) {
				System.out.println(">> excecao em buscar Usuario (key)");
			}
		}
		
		return ret;
	}
	
	public Usuario buscar(String login) throws UsuarioNaoExisteException{
		Usuario ret = this.controladorUsuario.buscar(login);
		
		// pega as requisicoes feitas pelo usuário e armazena no atributo requisicoes
		for(Key k : ret.getChavesRequisicoes()){
			try {
				ret.getRequisicoes().add(this.controladorRequisicao.procurar(k));
			} catch (RequisicaoNaoExisteException e) {
				System.out.println(">> excecao em buscar Usuario (login)");
			}
		}
		
		return ret;
	}
}