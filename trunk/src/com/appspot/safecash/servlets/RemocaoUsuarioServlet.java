package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.fachada.exception.UsuarioNaoPodeSerRemovidoException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

public class RemocaoUsuarioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private Fachada fachada = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		this.login = req.getParameter("users");
		this.process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			fachada.removerUsuario(fachada.buscar(login));
			SendMsg.send(req, res, "Usuário removido com sucesso.", "/usermanager.jsp");
		} catch (UsuarioNaoExisteException e) {
			SendMsg.send(req, res, "Usuário não cadastrado.", "/usermanager.jsp");
		} catch (UsuarioNaoPodeSerRemovidoException e) {
			SendMsg.send(req, res, "Usuário não pode ser removido.", "/usermanager.jsp");
		}
	}
}