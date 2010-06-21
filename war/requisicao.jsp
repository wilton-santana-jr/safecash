<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();

Iterator<Requisicao> iteReq = fachada.getAllRequisicao();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.dados.Requisicao"%>
<%@page import="com.appspot.safecash.negocio.exception.RequisicaoNaoExisteException"%>
<%@page import="com.appspot.safecash.enuns.EnumStatusRequisicao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.appspot.safecash.dados.Usuario"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link  type="text/css" rel="stylesheet" href="css/defalt.css"/>
<link   type="text/css" rel="stylesheet" href="css/caixa.css"/>
<link  type="text/css"  rel="stylesheet" href="css/requisicao.css"/>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/requisicao.js"></script>
<title>$afeCash - Administrador Financeiro</title>
</head>

<body>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor"><% out.print(session.getAttribute("nome")); %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="/logoff" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<jsp:include page="includes/menu.txt"></jsp:include>
		
	<div id="conteudo">
	
			<form>
				<select name= "mes"onchange="scripting(this.value)">
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
				<select name= "ano"onchange="scripting(this.value)">
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
			<div id="titulo" class="arredonda">REQUISI&Ccedil;&Otilde;ES DO MÊS DE JUNHO</div>
				<div id="informacoes">
					<div class="cabecario">
						<span class ="data">DATA</span>
						<span class ="assunto">ASSUNTO</span>
						<span class="estado">ESTADO</span>
						<span class="requisitante">REQUISITANTE</span>						
					</div>
					<div class="corpo">
					<%
					if (iteReq != null && iteReq.hasNext()) {
						 while (iteReq.hasNext()) {
							 Requisicao r = iteReq.next();
							 Usuario user = fachada.buscar(r.getChaveUsuario());
					%>
						<br/>
						<span class ="data"><%= new SimpleDateFormat("dd/MM/yyyy").format(r.getData()) %></span>
						<span class ="assunto"><%= r.getAssunto() %></span>
						<span class="estado <%= r.getStatus().toString().toLowerCase() %>"><%= r.getStatus().toString() %></span>
						<span class="requisitante"><%= user.getNome() %> <a id="<%= user.getKey() %>" href="#">&gt;&gt;VISUALIZAR</a><input type="hidden" name="<%= user.getKey() %>" value="<%= r.getTipo().toString().toLowerCase().charAt(0)%>" /></span>
					<%
						}
					} else
						out.print("Não há  requisições cadastradas.");
					%>	
					</div>
				</div>
	
	  </div>
	</div>

</div>
<div id="popUpContrato" class="esconder">
<a class="fecharContrato" href="#" title="voltar"></a>
	<span id="nomePopUpContrato" class="destaque cor">REQUISIÇÃO TIPO: CONTRATO</span>
	<div id="informacoesContrato">	
		<ul>
			<li><span class="titulo">Descri&ccedil;&atilde;o:</span><br/>descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição
			</li>
			<li><span class="titulo">Nome do Projeto:</span>bbgvdjbvjksdb
			</li>
			<li><span class="titulo">Contratante:</span>vbngfbjfb nkfdj	
			</li>
			<li><span class="titulo">CNPJ/CPF:</span>32160251315
			</li>
			<li><span class="titulo">Valor:</span>6562196
			</li>
			<li><span class="titulo">Estado: &nbsp;</span><select name="estado" ><option>Pendente</option> <option >Pronto</option> <option>OK</option></select>
			
			</li>
			<li><span class="titulo">Obs:</span>fmnvfdm.,nxc,mv nxc mv n 
			</li>
		</ul>
		<input type="hidden" name="id"  />
	</div>
	<a class="responderContrato" href="#" title="Responder a requisi&ccedil;&atilde;o"></a>
	<a class="salvarContrato" href="#" title="Salvar altera&ccedil;&otilde;es  na requisi&ccedil;&atilde;o"></a>
	<a class="baixarContrato" href="#" title="Baixar Proposta"></a>
</div>

<div id="popUpGeral" class="esconder">
<a class="fecharGeral" href="#" title="voltar"></a>
	<span id="nomePopUpGeral" class="destaque cor">REQUISIÇÃO TIPO: Geral</span>
	<div id="informacoesGeral">	
		<ul>
			<li><span class="titulo">Descri&ccedil;&atilde;o:</span><br/>descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição descrição descrição descrição descriçãodescriçãodescrição descrição descrição		</li>
			<li><span class="titulo">Estado: &nbsp;</span><select name="estado" ><option>Pendente</option> <option >Pronto</option> <option>OK</option></select>
			</li>	
		</ul>
		<input type="hidden" name="id"  />
	</div>
	<a class="responderGeral" href="#" title="Responder a requisi&ccedil;&atilde;o"></a>
	<a class="salvarGeral" href="#" title="Salvar altera&ccedil;&otilde;es  na requisi&ccedil;&atilde;o"></a>
</div>
<div id="tela" class="esconder"></div>
</body>
</html>

