<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.appspot.testebigtable.RepositorioPessoa"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.testebigtable.Pessoa"%><html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Testando o Big Table (AGAIN)</title>
	</head>
	
	<%! RepositorioPessoa repositorio = null; %>
	
	<body>
		<h1>Pessoas cadastradas</h1>
		<%
			if(repositorio == null)
				repositorio = new RepositorioPessoa();
			
			Iterator<Pessoa> it = repositorio.iterator();
			if(it != null && it.hasNext()){
				Pessoa current = null;
				while(it.hasNext()){
					current = it.next();
					out.println(current + "<br />");
				}
			}
			else
				out.println("nenhum usuario cadastrado");
		%>
	</body>


</html>