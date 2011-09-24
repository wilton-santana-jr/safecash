package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.fachada.Fachada;

public class UploadModeloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private String nome;
	private String url;
	private String descricao;
	private Fachada fachada = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		this.nome = req.getParameter("nome");
		this.url = req.getParameter("url");
		this.descricao = req.getParameter("url");
		
		this.process(req, res);
	}

	private void process(HttpServletRequest req, HttpServletResponse res){
		
		// Quando essa tela tiver feita, fazer o upload do arquivo.
		// http://code.google.com/intl/pt-BR/appengine/docs/java/blobstore/overview.html
	}
}
