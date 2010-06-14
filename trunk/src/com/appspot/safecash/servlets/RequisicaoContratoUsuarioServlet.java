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

public class RequisicaoContratoUsuarioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String statusString;
	private EnumStatusRequisicao statusEnum;
	private String contratante;
	private String CNPJ_CPF;
	private String nomeProjeto;
	private String endereco;
	private String valorString;
	private double valorDouble;
	//private ... arquivoProposta;
	private String obs;

	private HttpSession sessao;
	private EnumPermissao permissao;

	private Fachada fachada = Fachada.getInstance();

	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		this.sessao = req.getSession();
		this.permissao = (EnumPermissao) this.sessao.getAttribute("permissao");

		if(this.permissao.equals("USER"))
			this.processUser(req, res);
		else
			this.processAdmin(req, res);
	}
	
	private void processUser(HttpServletRequest req, HttpServletResponse res){

		this.descricao = req.getParameter("descricao");
		this.statusString = req.getParameter("status");
		this.contratante = req.getParameter("contratante");
		this.CNPJ_CPF = req.getParameter("CNPJ_CPF");
		this.nomeProjeto = req.getParameter("nomeProjeto");
		this.endereco = req.getParameter("endereco");
		this.valorString = req.getParameter("valor");
		this.obs = req.getParameter("obs");

		this.valorDouble = Double.parseDouble(this.valorString);

		if(this.statusString.equalsIgnoreCase("PENDENTE")){
			this.statusEnum = EnumStatusRequisicao.PENDENTE;
		}
		else if(this.statusString.equalsIgnoreCase("PRONTO")){
			this.statusEnum = EnumStatusRequisicao.PRONTO;
		}
		else{
			this.statusEnum = EnumStatusRequisicao.OK;
		}

		// Quando essa tela tiver feita, fazer o upload do arquivo.
		// http://code.google.com/intl/pt-BR/appengine/docs/java/blobstore/overview.html
		
		/*RequisicaoContrato contrato = new RequisicaoContrato(this.descricao, this.statusEnum, 
				this.contratante, this.CNPJ_CPF, this.nomeProjeto, 
				this.endereco, this.valorDouble, this.arquivoProposta,
				this.obs);
		
		this.fachada.inserirRequisicao(contrato);*/

	}

	private void processAdmin(HttpServletRequest req, HttpServletResponse res){
		
	}
}
