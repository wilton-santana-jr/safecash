package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.fachada.exception.UsuarioNaoPodeSerRemovidoException;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

public class GerenciaUsuarioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada = Fachada.getInstance();
	private String login;
	
	//private final String url = "append/adminPost.jsp?typeAction=";
	private final String url = "append/adminPost.jsp";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		String type = req.getParameter("type");
		this.login = req.getParameter("login");
		
		System.out.println(type);
		System.out.println(login);
		
		if (type.equalsIgnoreCase("add"))
			add(req, res);
		else if (type.equalsIgnoreCase("remove"))
			remove(req, res);
		else
			update(req, res);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String senha = req.getParameter("senha");
		String nome = req.getParameter("nome");
		EnumPermissao permissao = req.getParameter("permissao").equalsIgnoreCase("USER") ? EnumPermissao.USER : EnumPermissao.ADMIN;
		
		try {
			Usuario usuario = new Usuario(login, senha,
					nome, permissao);
			
			this.fachada.inserirUsuario(usuario);
			SendMsg.send(req, res, "Usuário cadastrado com sucesso.", url);
		} catch (UsuarioJaExisteException e) {
			SendMsg.send(req, res, "Usuário já cadastrado.", url);
		}
	}
	
	private void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			
			if (session.getAttribute("login").equals(this.login))
				throw new UsuarioNaoPodeSerRemovidoException();
			
			fachada.removerUsuario(fachada.buscar(login));
			SendMsg.send(req, res, "Usuário removido com sucesso.", url);
		} catch (UsuarioNaoExisteException e) {
			SendMsg.send(req, res, "Usuário não cadastrado.", url);
		} catch (UsuarioNaoPodeSerRemovidoException e) {
			SendMsg.send(req, res, "Usuário não pôde ser removido.", url);
		}
	}
	
	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Usuario user = fachada.buscar(login);
			
			String senha = req.getParameter("senha");
			String nome = req.getParameter("nome");
			EnumPermissao permissao = req.getParameter("permissao").equalsIgnoreCase("USER") ? EnumPermissao.USER : EnumPermissao.ADMIN;
			
			Usuario userNew = new Usuario(user.getLogin(), senha, nome, permissao);
			userNew.setKey(user.getKey());
			
			userNew.setChavesRequisicoes(user.getChavesRequisicoes());
			
			fachada.atualizarUsuario(userNew);
			SendMsg.send(req, res, "Usuário atualizado com sucesso.", url);
		} catch (UsuarioNaoExisteException e) {
			SendMsg.send(req, res, "Usuário não cadastrado.", url);
		}
	}
}