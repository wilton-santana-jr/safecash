package com.appspot.safecash.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.TransacaoJaExisteException;

// FALTAR MAPEAR NO WEB.XML

public class LivroServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String data;
	private Date date;
	private String valorString;
	private double valorDouble;
	private EnumTipoTransacao tipo;
	private EnumOrigemTransacao origem;
	private Fachada fachada  = Fachada.getInstance();
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		this.descricao = req.getParameter("descricao");
		this.data = req.getParameter("data");
		this.valorString = req.getParameter("valor");
		this.tipo = req.getParameter("tipo").equalsIgnoreCase("ENTRADA") ? EnumTipoTransacao.ENTRADA 
				: EnumTipoTransacao.SAIDA;
		this.origem = req.getParameter("origem").equalsIgnoreCase("CONTA") ? EnumOrigemTransacao.CONTA
				: EnumOrigemTransacao.CAIXA;
		
		this.process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res){
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(this.data);
			this.valorDouble = Double.parseDouble(this.valorString);
			
			Transacao transacao = new Transacao(this.descricao, this.date, this.valorDouble, this.tipo, this.origem);
			this.fachada.inserirTransacao(transacao);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (TransacaoJaExisteException e) {
			e.printStackTrace();
		}
	}
}
