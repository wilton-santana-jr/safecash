<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusConta"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusRequisicao"%>
<%@page import="com.appspot.safecash.dados.Requisicao"%>
<%
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();
// Iterator<Requisicao> iteReq = fachada.procurarRequisicaoPorStatus(EnumStatusRequisicao.PENDENTE);
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
		<div id="menu">
			<ul>
				<li><a href="#" title="P&aacute;gina Inicial"><span id="inicio"></span></a></li>
				<li><a href="#" title="Livro do Caixa"><span id="livro"></span></a></li>
				<li><a href="#" title="Contas"><span id="contas"></span></a></li>
				<li><a href="#" title="Projetos"><span id="projetos"></span></a></li>
				<li><a href="#" title="Capaxita&ccedil;&atilde;o"><span id="capacitacao"></span></a></li>
				<li><a href="#" title="Relat&oacute;rios"><span id="relatorios"></span></a></li>
				<li><a href="#" title="Modelos de documentos"><span id="modelos"></span></a></li>
				<li><span id="linha"></span></li>
			</ul>
		</div>
		
		<div id="conteudo">
			<div id="pagar">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>
				</div>
				<div class="corpo">
					<br/>
					<% //CONTAS A PAGAR %>
					<span class ="data">DD/MM/AAAA</span><span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span><span class="valor">R$ 0,00</span>
				</div>
			</div>
			<div id="requisicoes">
				<ul>
					<%
					//if (iteReq != null && iteReq.hasNext()) {
					//	while (iteReq.hasNext()) {
					//		Requisicao req = iteReq.next();
					%>
					<li><h4><%// out.print(fachada.buscar(req.getChaveUsuario()).getNome()); %></h4>
						<%// out.print(req.getDescricao()); %>
						<br/>
					</li>
					<%
					//	}
					//}
					//else
					//	out.print("<li><h4>Não requisições pendentes.<br/></h4></li>");
					%>
				</ul>
			</div>
			<div id="receber">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>
				</div>
				<div class="corpo">
				<% //CONTAS A RECEBER %>
					<br/>
					<span class ="data">DD/MM/AAAA</span><span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span><span class="valor">R$ 0,00</span>
				</div>
			</div>
			<div id="verTodos">
				<a href="#" title="ver todas as contas"> <span id="esq">&gt;&gt; &nbsp;&nbsp;VER TODAS AS CONTAS</span></a>
				<a href="#" title="ver todas as contas dos usu&aacute;rios"> <span id="dir"> &gt;&gt; &nbsp;&nbsp;VER TODAS AS REQUISI&Ccedil;&Otilde;ES</span></a>
			</div>
		</div>
	</div>

</div>


</body>
</html>
