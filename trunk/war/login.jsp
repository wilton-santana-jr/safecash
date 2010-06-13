<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
if (session.getAttribute("login") != null) {
	if (session.getAttribute("permissao") == EnumPermissao.USER)
		response.sendRedirect("/user.jsp");
	else
		response.sendRedirect("/admin.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.appspot.safecash.enuns.EnumPermissao"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/login.css" />
<title>$afeCash - Administrador Financeiro</title>
</head>

<body>
<div id="tudo">
	<div id="logo"></div>
	<div id="login">
	  <form action="login" name="login" method="post">
		<label for="nome"> login:</label>&nbsp;
			<input name="login" id="nome" type="text" />
			<br/>
		<label for="senha"> senha:</label>
			<input name="senha" id="senha" type="password" />

		  <a href="#" onclick="javascript:document.login.submit()" title="logar" id="botao"> </a>
	  </form>
	 <div id="msg">
	  <label for="msg">
		<%	String msg = (String) request.getAttribute("msg");
	
			if (msg != null)
				out.print(request.getAttribute("msg")); %>
	  </label>
	  </div>
	</div>
</div>
</body>
</html>
