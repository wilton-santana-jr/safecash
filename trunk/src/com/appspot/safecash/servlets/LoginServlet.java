package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private Fachada fachada = Fachada.getInstance();
	private String login;
	private String senha;
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		this.login = req.getParameter("login");
		this.senha = req.getParameter("senha");
		
		this.process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			if (login == null ||senha == null
					||  login.equals("") || senha.equals("")) {
				SendMsg.send(req, res, "Campos em branco.", "/login.jsp");
			} else {
				Usuario user = fachada.buscar(login);
				if (user.getSenha().equals(senha)) {
					// SESSION
				} else
					throw new UsuarioNaoExisteException();
			}
		} catch (UsuarioNaoExisteException e) {
			SendMsg.send(req, res, "Usuário e/ou senha incorreto(s).", "/login.jsp");
		}
	}
}