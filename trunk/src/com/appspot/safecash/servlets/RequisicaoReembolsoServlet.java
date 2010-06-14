package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.RequisicaoReembolso;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;

// FALTA MAPEAR

public class RequisicaoReembolsoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Fachada fachada = Fachada.getInstance();
	private String descricao;
	private double valor;
	private EnumStatusRequisicao status;

	private HttpSession sessao;
	private EnumPermissao permissao;

	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		this.sessao = req.getSession();
		this.permissao = (EnumPermissao) this.sessao.getAttribute("permissao");

		this.descricao = req.getParameter("descricao");
		this.valor = Double.parseDouble(req.getParameter("valor"));

		if(this.permissao.equals("USER"))
			this.processUser(req, res);
		else
			this.processAdmin(req, res);
	}

	private void processUser(HttpServletRequest req, HttpServletResponse res){

		RequisicaoReembolso requisicao = new RequisicaoReembolso(this.descricao, EnumStatusRequisicao.PENDENTE, this.valor);
		this.fachada.inserirRequisicao(requisicao);
	}

	private void processAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		String status = req.getParameter("status");

		if(status.equalsIgnoreCase("PENDENTE")){
			this.status = EnumStatusRequisicao.PENDENTE;
		}
		else if(status.equalsIgnoreCase("PRONTO")){
			this.status = EnumStatusRequisicao.PRONTO;
		}
		else{
			this.status = EnumStatusRequisicao.OK;
		}

		try {
			RequisicaoReembolso requisicao = new RequisicaoReembolso(this.descricao, this.status, this.valor);
			this.fachada.atualizarRequisicao(requisicao);
			SendMsg.send(req, res, "Status alterado com sucesso.", "/usermanager.jsp");
		} catch (RequisicaoNaoExisteException e) {
			SendMsg.send(req, res, "Requisição não existe.", "/usermanager.jsp");
		}
	}
}
