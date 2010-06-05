<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.appspot.testebigtable.RepositorioPessoa"%>
<%@page import="com.appspot.testebigtable.Pessoa"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Foi!</title>
	</head>
	
	<%! RepositorioPessoa repositorio = null; %>
	<%! boolean teste = false; %>
	<%
		if(repositorio == null)
			repositorio = new RepositorioPessoa();
	
		String nome = request.getParameter("nome");
		String nac = request.getParameter("nacionalidade");
		String d = request.getParameter("data");	
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(d); 
		
		
		Pessoa x = new Pessoa(nome, nac, data);
		
		teste = repositorio.inserir(x);
	%>
	
	<body>
		<%
			if(teste)
				out.println("<h2>Usuário cadastrado com sucesso!</h2>");
			else
				out.println("<h2>Deu merda!</h2>");
		%>
		<br /> <br />
		<a href="index.html">Voltar</a>
	</body>
</html>