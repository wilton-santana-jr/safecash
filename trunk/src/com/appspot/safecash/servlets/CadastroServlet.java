package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;

public class CadastroServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private String nome;
	private EnumPermissao permissao;
	private Fachada fachada = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		this.login = req.getParameter("login");
		this.senha = req.getParameter("senha");
		this.nome = req.getParameter("nome");
		this.permissao = req.getParameter("permissao").equalsIgnoreCase("USER")
							? EnumPermissao.USER : EnumPermissao.ADMIN;
		
		this.process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Usuario usuario = new Usuario(this.login, this.senha,
					this.nome, permissao);
			
			this.fachada.inserirUsuario(usuario);
			SendMsg.send(req, res, "Usuário cadastrado com sucesso.", "/usermanager.jsp");
		} catch (UsuarioJaExisteException e) {
			SendMsg.send(req, res, "Usuário já cadastrado.", "/usermanager.jsp");
		}
	}
}