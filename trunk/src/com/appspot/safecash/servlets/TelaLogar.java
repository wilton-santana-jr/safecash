package com.appspot.safecash.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

public class TelaLogar extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private Fachada fachada = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario(login, senha, null, null);
		
		out.print("Login: " + login);
		out.print("Senha: " + senha);
		
		try {
			Boolean existeUsuario = this.fachada.existeUsuario(usuario);
			if(existeUsuario){
				// abrir sessão do usuário
			}
		} catch (UsuarioNaoExisteException e) {
			e.printStackTrace();
		}
	}
}