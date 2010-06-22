<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.appspot.safecash.dados.Requisicao"%>
<%@page import="com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusRequisicao"%>
<%@page import="com.appspot.safecash.dados.Usuario"%>
<%@page import="com.appspot.safecash.dados.RequisicaoContrato"%>
<%@page import="com.appspot.safecash.dados.RequisicaoGeral"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();

Iterator<Requisicao> iteReq = fachada.getAllRequisicao();

String type = (String) request.getParameter("type");
String source = (String) request.getParameter("source");

if (type.equalsIgnoreCase("alter") && source.equals("")) {
%>
					<div class="cabecario">
						<span class ="data">DATA</span>
						<span class ="assunto">ASSUNTO</span>
						<span class="estado">ESTADO</span>
						<span class="requisitante">REQUISITANTE</span>						
					</div>
					<div class="corpo">
					<%
					if (iteReq != null && iteReq.hasNext()) {
						 while (iteReq.hasNext()) {
							 Requisicao r = iteReq.next();
							 Usuario user = fachada.buscar(r.getChaveUsuario());
					%>
						<br/>
						<span class="data"><%= new SimpleDateFormat("dd/MM/yyyy").format(r.getData()) %></span>
						<span class="assunto" id="<%= r.getKey() + "_a" %>"><%= r.getAssunto() %></span>
						<span class="estado <%= r.getStatus().toString().toLowerCase() %>"><%= r.getStatus().toString() %></span>
						<span class="requisitante" id="<%= r.getKey() + "_r" %>"><%= user.getNome() %> <a id="<%= r.getKey() %>" href="#">&gt;&gt;VISUALIZAR</a><input type="hidden" name="<%= r.getKey() %>" value="<%= r.getTipo().toString().toLowerCase().charAt(0)%>" /></span>
					<%
						}
					} else
						out.print("Não há  requisições cadastradas.");
					%>	
					</div>
<%
} else if (type.equalsIgnoreCase("list")) {
	
	if (source.equalsIgnoreCase("contrato")) {
		RequisicaoContrato current = (RequisicaoContrato) request.getAttribute("requisicao");
%>	
			<li><span class="titulo">Descri&ccedil;&atilde;o:  &nbsp;</span><%= current.getDescricao() %></li>
			<li><span class="titulo">Nome do Projeto:  &nbsp;</span><%= current.getNomeProjeto() %></li>
			<li><span class="titulo">Contratante:  &nbsp;</span><%= current.getResponsavel() %></li>
			<li><span class="titulo">CNPJ/CPF:  &nbsp;</span><%= current.getCNPJ_CPF() %></li>
			<li><span class="titulo">Valor:  &nbsp;</span><%= current.getValor() %></li>
			<li><span class="titulo">Estado: &nbsp;</span><select name="estado" ><option>Pendente</option> <option >Pronto</option> <option>OK</option></select></li>
			<li><span class="titulo">Obs:  &nbsp;</span><%= current.getObservacao() %></li>
<%
	} else if (source.equalsIgnoreCase("geral")) {
		RequisicaoGeral current = (RequisicaoGeral) request.getAttribute("requisicao");
%>
			<li><span class="titulo">Descrição: &nbsp;</span><%= current.getDescricao() %></li>
			<li><span class="titulo">Estado: &nbsp;</span><select name="estado" ><option>Pendente</option> <option >Pronto</option> <option>OK</option></select></li>	
<%
	}
} %>