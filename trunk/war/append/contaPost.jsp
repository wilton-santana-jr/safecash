<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.appspot.safecash.dados.Transacao"%>
<%@page import="com.appspot.safecash.dados.Conta"%>
<%@page import="com.appspot.safecash.enuns.EnumTipoConta"%>
<%@page import="com.appspot.safecash.negocio.exception.ContaNaoExisteException"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();

List<Transacao> trans = (List<Transacao>) request.getAttribute("trans");

String action = request.getParameter("action");

String msg = (String) request.getAttribute("msg");
	
if (msg != null) {
%>
<script type="text/javascript">alert('<%= request.getAttribute("msg") %>');</script>
<%
}
%>
<%
if (action.equalsIgnoreCase("trans")) {
	for (Transacao t : trans) {
%>
			<span class="data"> <%= new SimpleDateFormat("dd/MM/yyyy").format(t.getData()) %> </span><span class="descricao"><%= t.getDescricao() %></span><span class="valor">R$ <%= String.format("%.2f", t.getValor()) %></span><span class="tipo"><%= t.getTipo().toString() %></span><span class="estado"><%= t.getOrigem().toString() %></span>
<%
	}
} else {
	
	Iterator<Conta> iteContasPg = null;
	Iterator<Conta> iteContasRec = null;

	int limitContas = 6;

	try {
		iteContasPg = fachada.procurarConta(EnumTipoConta.SAIDA);
	} catch (ContaNaoExisteException exp) {
		iteContasPg = null;
	}

	try {
		iteContasRec = fachada.procurarConta(EnumTipoConta.ENTRADA);
	} catch (ContaNaoExisteException exp) {
		iteContasRec = null;
	}
%>
				<div id="pagar">
					<div class="cabecario">
						<span class ="data">DATA</span>
						<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
						<span class="valor">VALOR</span>
					</div>
				<div class="corpo">
					<br/>
					<%
					int i = 0;
					if (iteContasRec != null && iteContasRec.hasNext()) {
						while (iteContasRec.hasNext() && i < limitContas) {
							Conta conta = iteContasRec.next();
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData())); %></span><span class ="descricao"><%  if (conta.getDescricao().length() > 32) out.print(conta.getDescricao().substring(0, 27) + "[...]"); else out.print(conta.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", conta.getValor())); %></span>
					<%
							i++;
						}
					}
					else
						out.print("Não há contas a receber.");
					%>
				</div>
			</div>
			<div id="receber">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>
				</div>
				<div class="corpo">
					<br/>
					<%
					i = 0;
					if (iteContasPg != null && iteContasPg.hasNext()) {
						while (iteContasPg.hasNext() && i < limitContas) {
							Conta conta = iteContasPg.next();
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData())); %></span><span class ="descricao"><% if (conta.getDescricao().length() > 32) out.print(conta.getDescricao().substring(0, 27) + "[...]"); else out.print(conta.getDescricao());%></span><span class="valor">R$ <% out.print(String.format("%.2f", conta.getValor())); %></span>
					<%
							i++;
						}
					}
					else
						out.print("Não há contas a pagar.");
					%>
				</div>
			</div>
<%
}
%>