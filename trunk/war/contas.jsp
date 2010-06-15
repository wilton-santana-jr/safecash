<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="java.util.Iterator"%>
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

<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/defalt.css" />
<link rel="stylesheet" href="css/caixa.css" />

<link rel="stylesheet" href="css/contas.css" />
<title>$afe Cash - Administrador Financeiro</title>
</head>

<body>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor"><% out.print(session.getAttribute("nome")); %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="/logoff" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<jsp:include page="includes/menu.txt"></jsp:include>
		
		<div id="conteudo">
			<a href="#" id="botao" title="Adicionar Conta"></a>
			<form>
				<select name= "mes" onchange="scripting(this.value)">
					<option value="">M&Ecirc;S</option>
					<option value="janeiro">Janeiro</option>
					<option value="fevereiro">Fevereiro</option>
					<option value="marco">Mar&ccedil;o</option>
					<option value="abril">Abril</option>
					<option value="maio">Maio</option>
					<option value="junho">Junho</option>
					<option value="julho">Julho</option>
					<option value="agosto">Agosto</option>
					<option value="setembro">Setembro</option>
					<option value="outubro">Outubro</option>
					<option value="novembro">Novembro</option>
					<option value="dezembro">Dezembro</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<select name= "ano" onchange="scripting(this.value)">
					<option value="">ANO</option>
					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
				</select>
			</form>
			<div id="titulo" class="arredonda"><span id="esq">ENTRADA</span><span>SA&Iacute;DA</span></div>
			
			<div id="informacoes">
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
						out.print("<span class = 'data'>Não há contas a receber.</span");
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
						out.print("<span class = 'data'>Não há contas a pagar.</span");
					%>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

</body>
</html>
