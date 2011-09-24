package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.RequisicaoContrato;
import com.appspot.safecash.dados.RequisicaoGeral;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.fachada.Fachada;
import com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class RequisicaoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada = Fachada.getInstance();
	
	private Key chave; 
	private Requisicao reqTemp;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

		HttpSession sessao = req.getSession();
		EnumPermissao permissao = (EnumPermissao) sessao.getAttribute("permissao");
		
		String strChave = req.getParameter("chave");
		chave = KeyFactory.createKey(strChave.substring(0, strChave.indexOf("(")), Long.parseLong(strChave.substring(strChave.indexOf('(') + 1, strChave.length() - 1)));

		try {
			reqTemp = fachada.procurarRequisicao(chave);
		} catch (RequisicaoNaoExisteException e) { e.printStackTrace(); }
		
		
		if(permissao.equals("USER"))
			this.processUser(req, res);
		else
			this.processAdmin(req, res);
	}		
		
	private void processUser(HttpServletRequest req, HttpServletResponse res){
		//
	}
	
	private void processAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String type = req.getParameter("type");
		String source = req.getParameter("source");
		
		if (type.equalsIgnoreCase("list")) {
			
			if (this.reqTemp instanceof RequisicaoGeral) {
				
				System.out.println("Geral");
				
				RequisicaoGeral reqGeralTemp = (RequisicaoGeral) reqTemp;
				req.setAttribute("requisicao", reqGeralTemp);
			} else if (this.reqTemp instanceof RequisicaoContrato) {
				System.out.println("Contrato");
				
				RequisicaoContrato reqContratoTemp = (RequisicaoContrato) reqTemp;
				req.setAttribute("requisicao", reqContratoTemp);
			}
				
			SendMsg.send(req, res, "Listando..", "append/requisicaoPost.jsp?type=" + type + "&source=" + source);
			
		} else if (type.equalsIgnoreCase("alter")) {
			String status = req.getParameter("status");
			
			if(status.equalsIgnoreCase("PENDENTE"))
				reqTemp.setStatus(EnumStatusRequisicao.PENDENTE);
			else if(status.equalsIgnoreCase("PRONTO"))
				reqTemp.setStatus(EnumStatusRequisicao.PRONTO);
			else
				reqTemp.setStatus(EnumStatusRequisicao.OK);
			
			try {
				this.fachada.atualizarRequisicao(reqTemp);

				SendMsg.send(req, res, "Teste", "append/requisicaoPost.jsp?type=" + type + "&source=");
			} catch (RequisicaoNaoExisteException e) {
				SendMsg.send(req, res, "Requisição não existe.", "/requisicao.jsp");
			}
		}
	}
}