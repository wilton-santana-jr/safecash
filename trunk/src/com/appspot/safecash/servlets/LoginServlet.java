package com.appspot.safecash.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	//private Fachada fachada = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String login = req.getParameter("nome");
		String senha = req.getParameter("senha");
		
		PrintWriter out = res.getWriter();
		
		/*
		try {
			Boolean existeUsuario = this.fachada.existeUsuario(usuario);
			if(existeUsuario){
				// abrir sessão do usuário
			}
		} catch (UsuarioNaoExisteException e) {
			e.printStackTrace();
		}*/
	}
}