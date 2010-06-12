package com.appspot.safecash.fachada;

import java.util.Date;

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
import com.appspot.safecash.enuns.EnumStatusConta;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
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
import com.appspot.safecash.negocio.exception.FuncionarioNaoExisteException;
import com.appspot.safecash.negocio.exception.ModeloNaoExisteException;
import com.appspot.safecash.negocio.exception.ProjetoJaExisteException;
import com.appspot.safecash.negocio.exception.ProjetoNaoExisteException;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.appspot.safecash.negocio.exception.TransacaoJaExisteException;
import com.appspot.safecash.negocio.exception.TransacaoNaoExisteException;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;
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
		this.controladorConta = new ControladorConta();
		this.controladorFuncionario = new ControladorFuncionario();
		this.controladorModelo = new ControladorModelo();
		this.controladorProjeto = new ControladorProjeto();
		this.controladorRequisicao = new ControladorRequisicao();
		this.controladorTransacao = new ControladorTransacao();
		this.controladorUsuario = new ControladorUsuario(new RepositorioUsuarioBT());
		
		// this.gerenciaSession();
	}

	/**
	 * M�todo que permite a cria��o de apenas uma inst�ncia da fachada.
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
	 * M�todo que ir� gerenciar cada sess�o aberta pelo programa.
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
	 * M�todo para inserir uma conta.
	 * 
	 * @param conta
	 * @throws ContaJaExisteException
	 */
	public void inserirConta(Conta conta) throws ContaJaExisteException {
		this.controladorConta.inserirConta(conta);
	}

	/**
	 * M�todo para procurar contas por projeto.
	 * 
	 * @param projeto
	 * @throws ContaNaoExisteException
	 */
	/*public void procurarContaPorProjeto(Projeto projeto) throws ContaNaoExisteException{
		this.controladorConta.procurar(projeto);
	}*/

	/**
	 * M�todo para procurar contas por datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @throws ContaNaoExisteException
	 */
	public void procurarContaPorData(Date dataInicial, Date dataFinal) throws ContaNaoExisteException{
		this.controladorConta.procurar(dataInicial, dataFinal);
	}

	/**
	 * M�todo para procurar contas por status.
	 * 
	 * @param status
	 * @throws ContaNaoExisteException
	 */
	public void procurarContaPorStatus(EnumStatusConta status) throws ContaNaoExisteException{
		this.controladorConta.procurar(status);
	}

	/**
	 * M�todo para remover uma conta.
	 * 
	 * @param conta
	 * @throws ContaNaoExisteException 
	 */
	public void removerConta(Conta conta) throws ContaNaoExisteException{
		this.controladorConta.remover(conta);
	}

	/**
	 * M�todo para atualizar uma conta.
	 * 
	 * @param conta
	 * @throws ContaNaoExisteException
	 */
	public void atualizarConta(Conta conta) throws ContaNaoExisteException{
		this.controladorConta.atualizar(conta);
	}

	//##################### FUNCION�RIO #####################//

	/**
	 * M�todo para inserir um funcion�rio.
	 * 
	 * @param funcionario
	 * @throws FuncionarioJaExisteException
	 */
	public void inserirFuncionario(Funcionario funcionario) throws FuncionarioJaExisteException{
		this.controladorFuncionario.inserir(funcionario);
	}

	/**
	 * M�todo para procurar um funcion�rio por nome.
	 * 
	 * @param nome
	 * @throws FuncionarioNaoExisteException
	 */
	/*public void procurarFuncionarioPorNome(String nome) throws FuncionarioNaoExisteException{
		this.controladorFuncionario.procurarPorNome(nome);
	}*/

	/**
	 * M�todo para procurar um funcion�rio por cargo.
	 * 
	 * @param cargo
	 * @throws FuncionarioNaoExisteException
	 */
	public void procurarFuncionarioPorCargo(String cargo) throws FuncionarioNaoExisteException{
		this.controladorFuncionario.procurarPorCargo(cargo);
	}

	/**
	 * M�todo para procurar um funcion�rio por CPF.
	 * 
	 * @param cpf
	 * @throws FuncionarioNaoExisteException
	 */
	/*public void procurarFuncionarioPorCPF(String cpf) throws FuncionarioNaoExisteException{
		this.controladorFuncionario.procurarPorCPF(cpf);
	}*/

	/**
	 * M�todo para remover um funcion�rio.
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
	 * M�todo que insere um modelo de documento.
	 * 
	 * @param modelo
	 */
	public void inserirModelo(Modelo modelo){
		this.controladorModelo.inserir(modelo);
	}

	/**
	 * M�todo que remove um modelo;
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	public void removerModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.remover(modelo);
	}

	/**
	 * M�todo que atualiza um modelo.
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	public void atualizarModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.atualizar(modelo);
	}

	/**
	 * M�todo que procura um modelo.
	 * 
	 * @param modelo
	 * @throws ModeloNaoExisteException
	 */
	/*public void procurarModelo(Modelo modelo) throws ModeloNaoExisteException{
		this.controladorModelo.procurar(modelo);
	}*/

	//##################### PROJETO #####################//##

	/**
	 * M�todo para inserir um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoJaExisteException
	 */
	public void inserirProjeto(Projeto projeto) throws ProjetoJaExisteException{
		this.controladorProjeto.inserir(projeto);
	}

	/**
	 * M�todo para atualizar um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoNaoExisteException
	 */
	public void atualizarProjeto(Projeto projeto) throws ProjetoNaoExisteException{
		this.controladorProjeto.atualizar(projeto);
	}

	/**
	 * M�todo para procurar um projeto por nome.
	 * 
	 * @param nome
	 * @throws ProjetoNaoExisteException
	 */
	/*public void procurarProjetoPorNome(String nome) throws ProjetoNaoExisteException{
		this.controladorProjeto.procurarPorNome(nome);
	}*/

	/**
	 * M�todo para procurar um projeto por usuario.
	 * 
	 * @param usuario
	 * @throws ProjetoNaoExisteException
	 */
	/*public void procurarProjetoPorUsuario(Usuario usuario) throws ProjetoNaoExisteException{
		this.controladorProjeto.procurarPorUsuario(usuario);
	}*/

	/**
	 * M�todo para remover um projeto.
	 * 
	 * @param projeto
	 * @throws ProjetoNaoExisteException
	 */
	public void removerProjeto(Projeto projeto) throws ProjetoNaoExisteException{
		this.controladorProjeto.remover(projeto);
	}

	//##################### REQUISI��O #####################//

	/**
	 * M�todo para inserir uma requisi��o.
	 * 
	 * @param requisicao
	 */
	public void inserirRequisicao(Requisicao requisicao){
		this.controladorRequisicao.inserir(requisicao);
	}

	/**
	 * M�todo para procurar requisi��o por usu�rio.
	 * 
	 * @param usuario
	 * @throws RequisicaoNaoExisteException
	 */
	/*public void procurarRequisicaoPorUsuario(Usuario usuario) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.procurarPorUsuario(usuario);
	}*/

	/**
	 * M�todo para procurar requisi��o por status.
	 * 
	 * @param status
	 * @throws RequisicaoNaoExisteException
	 */
	public void procurarRequisicaoPorStatus(EnumStatusRequisicao status) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.procurarPorStatus(status);
	}

	/**
	 * M�todo para remover requisi�ao.
	 * 
	 * @param requisicao
	 * @throws RequisicaoNaoExisteException
	 */
	public void removerRequisicao(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.remover(requisicao);
	}

	/**
	 * M�todo para atualizar uma requisi��o.
	 * 
	 * @param requisicao
	 * @throws RequisicaoNaoExisteException
	 */
	public void atualizarRequisicao(Requisicao requisicao) throws RequisicaoNaoExisteException{
		this.controladorRequisicao.atualizar(requisicao);
	}

	//##################### TRANSA��O #####################//

	/**
	 * M�todo para inserir uma transa��o.
	 * 
	 * @param transacao
	 * @throws TransacaoJaExisteException
	 */
	public void inserirTransacao(Transacao transacao) throws TransacaoJaExisteException{
		this.controladorTransacao.inserir(transacao);
	}

	/**
	 * M�todo para remover uma transa��o.
	 * 
	 * @param transacao
	 * @throws TransacaoNaoExisteException
	 */
	public void removerTransacao(Transacao transacao) throws TransacaoNaoExisteException{
		this.controladorTransacao.remover(transacao);
	}

	/**
	 * M�todo para atualizar uma transa��o.
	 * 
	 * @param transacao
	 * @throws TransacaoNaoExisteException
	 */
	public void atualizarTransacao(Transacao transacao) throws TransacaoNaoExisteException{
		this.controladorTransacao.atualizar(transacao);
	}

	//##################### USU�RIO #####################//

	/**
	 * M�todo para inserir um usu�rio.
	 * 
	 * @param usuario
	 * @throws UsuarioJaExisteException
	 */
	public void inserirUsuario(Usuario usuario) throws UsuarioJaExisteException{
		this.controladorUsuario.cadastrar(usuario);
	}

	/**
	 * M�todo para remover usu�rio.
	 * 
	 * @param usuario
	 * @throws UsuarioNaoExisteException
	 */
	public void removerUsuario(Usuario usuario) throws UsuarioNaoExisteException{
		Usuario u = this.controladorUsuario.buscar(usuario.getLogin());
		
		// remove as requisicoes associadas ao usuario que est� sendo removido
		Requisicao r;
		for(Key k : u.getChavesRequisicoes()){
			try {
				r = this.controladorRequisicao.procurar(k);
				this.controladorRequisicao.remover(r);
			} catch (RequisicaoNaoExisteException e) { }
		}
		
		/* o usu�rio n�o pode ser removido se for respons�vel
		 * por algum projeto. tem que fazer essa verifica��o ainda. */
		
		this.controladorUsuario.remover(usuario);
	}

	/**
	 * M�todo para atualizar usu�rio.
	 * 
	 * @param usuario
	 * @throws UsuarioNaoExisteException
	 */
	public void atualizarUsuario(Usuario usuario) throws UsuarioNaoExisteException{
		this.controladorUsuario.atualizar(usuario);
	}
	
	/**
	 * M�todo que retorna um boolean para informar se usu�rio existe.
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
		
		// pega as requisicoes feitas pelo usu�rio e armazena no atributo requisicoes
		for(Key k : ret.getChavesRequisicoes()){
			try {
				ret.getRequisicoes().add(this.controladorRequisicao.procurar(k));
			} catch (RequisicaoNaoExisteException e) {
				System.out.println(">> excecao em buscar Usuario (key)");
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public Usuario buscar(String login) throws UsuarioNaoExisteException{
		Usuario ret = this.controladorUsuario.buscar(login);
		
		// pega as requisicoes feitas pelo usu�rio e armazena no atributo requisicoes
		for(Key k : ret.getChavesRequisicoes()){
			try {
				ret.getRequisicoes().add(this.controladorRequisicao.procurar(k));
			} catch (RequisicaoNaoExisteException e) {
				System.out.println(">> excecao em buscar Usuario (login)");
				e.printStackTrace();
			}
		}
		
		return ret;
	}
}