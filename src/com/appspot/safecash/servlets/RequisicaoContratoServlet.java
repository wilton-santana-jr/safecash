package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.RequisicaoContrato;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;

// FALTA MAPEAR

public class RequisicaoContratoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String contratante;
	private String CNPJ_CPF;
	private String nomeProjeto;
	private String endereco;
	private double valor;
	//private ... arquivoProposta;
	private String obs;

	private HttpSession sessao;
	private EnumPermissao permissao;
	private EnumStatusRequisicao status;

	private Fachada fachada = Fachada.getInstance();

	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		this.sessao = req.getSession();
		this.permissao = (EnumPermissao) this.sessao.getAttribute("permissao");

		if(this.permissao == EnumPermissao.USER)
			this.processUser(req, res);
		else
			this.processAdmin(req, res);
	}

	private void processUser(HttpServletRequest req, HttpServletResponse res){

		this.descricao = req.getParameter("descricao");
		this.contratante = req.getParameter("contratante");
		this.CNPJ_CPF = req.getParameter("CNPJ_CPF");
		this.nomeProjeto = req.getParameter("nomeProjeto");
		this.endereco = req.getParameter("endereco");
		this.valor = Double.parseDouble(req.getParameter("valor"));
		this.obs = req.getParameter("obs");

		// Quando essa tela tiver feita, fazer o upload do arquivo.
		// http://code.google.com/intl/pt-BR/appengine/docs/java/blobstore/overview.html

		/*RequisicaoContrato contrato = new RequisicaoContrato(this.descricao, EnumStatusRequisicao.PENDENTE, 
				this.contratante, this.CNPJ_CPF, this.nomeProjeto, 
				this.endereco, this.valor, this.arquivoProposta,
				this.obs);

		this.fachada.inserirRequisicao(contrato);*/

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
		
		/*try{
		RequisicaoContrato requisicao = new RequisicaoContrato(this.descricao, this.status, 
				this.contratante, this.CNPJ_CPF, this.nomeProjeto, 
				this.endereco, this.valor, this.arquivoProposta,
				this.obs);
		this.fachada.atualizarRequisicao(requisicao);
			SendMsg.send(req, res, "Status alterado com sucesso.", "/usermanager.jsp");
		} catch (RequisicaoNaoExisteException e) {
			SendMsg.send(req, res, "Requisição não existe.", "/usermanager.jsp");
		}*/
	}
}
