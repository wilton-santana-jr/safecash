<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/defalt.css" />
<link rel="stylesheet" href="css/relatorios.css" />
<title>$afeCash - Administrador Financeiro</title>
</head>

<body>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor"><% out.print(session.getAttribute("nome")); %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="/logoff" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<jsp:include page="includes/menu.txt"></jsp:include>
		
		<div id="conteudo">
			<div  class="destaque cor">Escolha o relat&oacute;rio &agrave; ser gerado</div>
			<div class="relatorio"><a class="figura" href="#" title="Gerar Relat&oacute;rio"></a><span>Relat&oacute;rios de todas as transa&ccedil;&otilde;es</span></div>
			<div class="relatorio"><a class="figura" href="#" title="Gerar Relat&oacute;rio"></a><span>Relat&oacute;rios de todas as contas</span></div>
			<div class="relatorio"><a class="figura" href="#" title="Gerar Relat&oacute;rio"></a><span>Relat&oacute;rios de todos os projetos</span></div>
			<div class="relatorio"><a class="figura" href="#" title="Gerar Relat&oacute;rio"></a><span>Relat&oacute;rios de todas as requisi&ccedil;&otilde;es</span></div>
			 		
		</div>
	</div>

</div>

</body>
</html>
