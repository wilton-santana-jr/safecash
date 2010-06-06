<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.appspot.testebigtable.RepositorioPessoa"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.testebigtable.Pessoa"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Foi! (2)</title>
	</head>
	
	<%! RepositorioPessoa repositorio = null; %>
	<%! Iterator<Pessoa> it = null; %>
	<%
		if(repositorio == null)
			repositorio = new RepositorioPessoa();
	%>
	
	<body>
		<%
			repositorio.remover(new Long(request.getParameter("id")));
			out.println("usuario removido");	
		%>
	</body>
</html>