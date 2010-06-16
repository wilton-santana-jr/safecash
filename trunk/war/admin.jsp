<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusConta"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusRequisicao"%>
<%@page import="com.appspot.safecash.enuns.EnumTipoConta"%>
<%@page import="com.appspot.safecash.dados.Conta"%>
<%@page import="com.appspot.safecash.dados.Requisicao"%>
<%@page import="com.appspot.safecash.negocio.exception.ContaNaoExisteException"%>
<%@page import="com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
// Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();

Iterator<Conta> iteContasPg = null;
Iterator<Conta> iteContasRec = null;

int limitContas = 6;
int limitRequi = 4;

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

Iterator<Requisicao> iteReq = null;

try {
	iteReq = fachada.procurarRequisicao(EnumStatusRequisicao.PENDENTE);
} catch (RequisicaoNaoExisteException exp) {
	iteReq = null;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/defalt.css" />
<link rel="stylesheet" href="css/admin.css" />
<title>$afeCash - Administrador Financeiro</title>
</head>

<body>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor"><% out.print(session.getAttribute("nome")); %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="/logoff" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<jsp:include page="includes/menu.txt"></jsp:include>
		<div id="conteudo">
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
					if (iteContasPg != null && iteContasPg.hasNext()) {
						while (iteContasPg.hasNext() && i < limitContas) {
							Conta conta = iteContasPg.next();
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData())); %></span><span class ="descricao"><% if (conta.getDescricao().length() > 42) out.print(conta.getDescricao().substring(0, 37) + "[...]"); else out.print(conta.getDescricao());%></span><span class="valor">R$ <% out.print(String.format("%.2f", conta.getValor())); %></span>
					<%
							i++;
						}
					}
					else
						out.print("Não há contas a pagar.");
					%>
				</div>
			</div>
			<div id="requisicoes">
				<ul>
					<%
					if (iteReq != null && iteReq.hasNext()) {
						i = 0;
						while (iteReq.hasNext() && i < limitRequi) {
							Requisicao req = iteReq.next();
					%>
					<li><h4><% out.print(fachada.buscar(req.getChaveUsuario()).getNome()); %></h4>
						<% out.print(req.getDescricao()); %>
						<br/>
					</li>
					<%
							i++;
						}
					}
					else
						out.print("<li><h4>Não há requisições pendentes.<br/></h4></li>");
					%>
				</ul>
				<a href="#" title="ver todas as contas dos usu&aacute;rios"> <span id="dir"> &gt;&gt; &nbsp;&nbsp;VER TODAS AS REQUISI&Ccedil;&Otilde;ES</span></a>
			</div>
			<div id="receber">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>
				</div>
				<div class="corpo">
				<%
					if (iteContasRec != null && iteContasRec.hasNext()) {
						i = 0;
						while (iteContasRec.hasNext() && i < limitContas) {
							Conta conta = iteContasRec.next();
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData())); %></span><span class ="descricao"><%  if (conta.getDescricao().length() > 42) out.print(conta.getDescricao().substring(0, 37) + "[...]"); else out.print(conta.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", conta.getValor())); %></span>
					<%
							i++;
						}
					}
					else
						out.print("Não há contas a receber.");
					%>
				</div>
				<a href="#" title="ver todas as contas"> <span id="esq">&gt;&gt; &nbsp;&nbsp;VER TODAS AS CONTAS</span></a>
			</div>
			<div id="verTodos">
				<a href="#" id="botao" title="Gerenciar usuários"></a>
			</div>
		</div>
	</div>

</div>


</body>
</html>
