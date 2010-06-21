package com.appspot.safecash.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.enuns.EnumTipoRequisicao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private Fachada fachada = Fachada.getInstance();
	private String login;
	private String senha;
	
	private Usuario user;
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.login = req.getParameter("login");
		this.senha = req.getParameter("senha");
		
		this.process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			/*try {
				fachada.inserirTransacao(new Transacao("TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO", new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2010"), 44.78, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA));
				fachada.inserirTransacao(new Transacao("TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO TRANSACAO", new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2010"), 44.78, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CONTA));
			} catch (TransacaoJaExisteException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			*/
			try {
				fachada.inserirUsuario(new Usuario("l", "123", "Luis", EnumPermissao.USER));
				fachada.inserirUsuario(new Usuario("t", "123", "Tiago", EnumPermissao.USER));
				
				fachada.inserirRequisicao(new Requisicao("Assunto 1", "22DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO"
						, EnumStatusRequisicao.PENDENTE, EnumTipoRequisicao.GERAL, new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2010"), fachada.buscar("l").getKey()));
				fachada.inserirRequisicao(new Requisicao("Assunto 2", "33DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO"
						, EnumStatusRequisicao.PENDENTE, EnumTipoRequisicao.CONTRATO, new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2010"), fachada.buscar("t").getKey()));
			} catch (UsuarioJaExisteException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				fachada.inserirUsuario(new Usuario("admin", "admin", "admin", EnumPermissao.ADMIN));
			} catch (UsuarioJaExisteException e2) {e2.printStackTrace();}
			
			
			/*
			try {
				fachada.inserirUsuario(new Usuario("jow", "123", "Jonathas", EnumPermissao.ADMIN));
				fachada.inserirUsuario(new Usuario("b", "123", "Bruno", EnumPermissao.USER));
				fachada.inserirUsuario(new Usuario("l", "123", "Luis", EnumPermissao.USER));
				fachada.inserirUsuario(new Usuario("t", "123", "Tiago", EnumPermissao.USER));
				
				fachada.inserirRequisicao(new Requisicao("11DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO", EnumStatusRequisicao.PENDENTE, fachada.buscar("b").getKey()));
				fachada.inserirRequisicao(new Requisicao("22DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO", EnumStatusRequisicao.PENDENTE, fachada.buscar("l").getKey()));
				fachada.inserirRequisicao(new Requisicao("33DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO DESCRICAO", EnumStatusRequisicao.PENDENTE, fachada.buscar("t").getKey()));
			} catch (UsuarioJaExisteException e) {
				e.printStackTrace();
			}
			
			Date data1 = new Date(), data2 = null, data3 = null;
			try {			 
				data2 = new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2010");
				data3 = new SimpleDateFormat("dd/MM/yyyy").parse("04/12/2010");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Conta c1 = new Conta(data2, "RECEBER RECEBER RECEBER RECEBER RECEBER", 123324.67, EnumStatusConta.ATRASADO, EnumTipoConta.ENTRADA);
			Conta c2 = new Conta(data3, "PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR", 53343.5, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			Conta c3 = new Conta(data3, "PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR", 53343.5, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			Conta c4 = new Conta(data3, "PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR", 53343.5, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			Conta c5 = new Conta(data3, "444PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR0", 233.5, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			Conta c6 = new Conta(data3, "4554PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR1", 88.5, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			Conta c7 = new Conta(data3, "666PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR PAGAR2", 111.4, EnumStatusConta.PENDENTE, EnumTipoConta.SAIDA);
			
			fachada.inserirConta(c1);
			fachada.inserirConta(c5);
			fachada.inserirConta(c6);
			fachada.inserirConta(c7);
			*////////////////////////////////////
			
			//fachada.inserirRequisicao(new Requisicao("11DESCRICAO DESCRICAO DESCRICAO DESCRICAO", EnumStatusRequisicao.PENDENTE));
			//fachada.inserirRequisicao(new Requisicao("22DESCRICAO DESCRICAO DESCRICAO DESCRICAO", EnumStatusRequisicao.PENDENTE));
			
			if (login == null ||senha == null
					||  login.equals("") || senha.equals("")) {
				SendMsg.send(req, res, "Campos em branco.", "/login.jsp");
			} else {
				user = fachada.buscar(login);
				if (user.getSenha().equals(senha)) {
					managerSession(req, res);
					
					if (user.getPermissao() == EnumPermissao.USER)
						//res.sendRedirect("/user.jsp");
						res.getWriter().print("USER");
					else
						res.sendRedirect("/admin.jsp");
						//res.getWriter().print("ADMIN");
				} else
					throw new UsuarioNaoExisteException();
			}
		} catch (UsuarioNaoExisteException e) {
			SendMsg.send(req, res, "Usuário e/ou senha incorreto(s).", "/login.jsp");
		}
	}
	
	private void managerSession (HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(true);
		
		session.setAttribute("login", this.login);
		session.setAttribute("senha", this.senha);
		session.setAttribute("nome", this.user.getNome());
		session.setAttribute("senha", this.user.getPermissao());
		session.setAttribute("key", this.user.getKey());
		
		session.setMaxInactiveInterval(300);
	}
}