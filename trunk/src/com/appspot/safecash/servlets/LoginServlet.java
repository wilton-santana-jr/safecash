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
			//*
			try {
				fachada.inserirUsuario(new Usuario("jow", "123", "Jonathas", EnumPermissao.ADMIN));
			} catch (UsuarioJaExisteException e) {
				e.printStackTrace();
			}
			//*////////////////////////////////////
			
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
		
		session.setMaxInactiveInterval(300);
	}
}