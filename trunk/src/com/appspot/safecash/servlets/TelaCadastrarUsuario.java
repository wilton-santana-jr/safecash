package com.appspot.safecash.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.UsuarioJaExisteException;
import com.appspot.safecash.negocio.exception.UsuarioNaoExisteException;

// FALTA MAPEAR PARA O WEB.XML

public class TelaCadastrarUsuario extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private String nome;
	private String permissao;
	private Fachada fachada;
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		this.fachada = Fachada.getInstance();
		
		this.login = req.getParameter("login");
		this.senha = req.getParameter("senha");
		this.nome = req.getParameter("nome");
		this.permissao = req.getParameter("permissao");
		
		Usuario usuarioTemp = new Usuario(this.login, senha, nome, null);
		boolean existeUsuario;
		
		try {
			existeUsuario = this.fachada.existeUsuario(usuarioTemp);
			if(!existeUsuario){
				if(this.permissao.equalsIgnoreCase("user")){
					Usuario usuario = new Usuario(this.login, this.senha, this.nome, EnumPermissao.USER);
					this.fachada.inserirUsuario(usuario);
				}
				else{
					Usuario usuario = new Usuario(this.login, this.senha, this.nome, EnumPermissao.ADMIN);
					this.fachada.inserirUsuario(usuario);
				}
			}
		} catch (UsuarioNaoExisteException e) {
			e.printStackTrace();
		} catch (UsuarioJaExisteException e) {
			e.printStackTrace();
		}
	}
}
