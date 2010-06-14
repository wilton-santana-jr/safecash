<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.appspot.safecash.repositorio.RepositorioRequisicaoBT"%>
<%@page import="com.appspot.safecash.dados.Requisicao"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusRequisicao"%>
<%@page import="com.appspot.safecash.dados.RequisicaoContrato"%>
<%@page import="com.appspot.safecash.dados.RequisicaoReembolso"%>
<%@page import="java.util.Iterator"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	RepositorioRequisicaoBT repo = new RepositorioRequisicaoBT();
	//repo.inserir(new Requisicao("desc1", EnumStatusRequisicao.OK));
	//repo.inserir(new RequisicaoContrato("desc2", EnumStatusRequisicao.PENDENTE, "", "", "", "", 0.05, null, ""));
	//repo.inserir(new RequisicaoReembolso("desc3", EnumStatusRequisicao.OK, 15.5));
	out.println("inseriu<br><br>");
	
	Iterator<Requisicao> it = repo.iterator();
	Requisicao r = null;
	while(it.hasNext()){
		r = it.next();
		
		out.print(r.getDescricao() + "  " + r.getStatus() + "  ");
		if(r instanceof RequisicaoContrato)
			out.print("CONTRATO");
		else if(r instanceof RequisicaoReembolso)
			out.print("REEMBOLSO");
		else
			out.print("COMUM");
		
		out.println("<br>");
	}
	
%>
</body>
</html>