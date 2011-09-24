<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
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
<link rel="stylesheet" href="css/modelos.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/funcao.js"></script>
<title>$afeCash - Administrador Financeiro</title>
</head>

<body>
<div id="tela" class="esconder"></div>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor"><% out.print(session.getAttribute("nome")); %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="/logoff" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<jsp:include page="includes/menu.txt"></jsp:include>		
		<div id="conteudo">


			<form>
			<!--<label class="label" for="campo">ARMAZENAR UM MODELO:&nbsp;&nbsp;&nbsp;</label>
				<input class="campo" size="50" type="text" id="campo" name="endereco" value="file:///G:/Interface/PageModelos.png"/>-->
				<a class="botao" href="#" title="armazenar"></a>
			</form>
			<div id="livroConta" class="destaque cor">Modelos Armazenados no Sistema</div>
			
			
			<div id="lista">
				<ul>
					<li id="1"><a href="#" title="Maior Detalhamento">NOME DO MODELO ARMAZENADO</a></li>
					<li id="2"><a href="#" title="Maior Detalhamento">NOME DO MODELO CONTRATO</a></li>
					<li id="3"><a href="#" title="Maior Detalhamento">PEDIDO NÚMERO 1</a></li>
					<li id="4"><a href="#" title="Maior Detalhamento">CURSO IN-COMPANY</a></li>
				</ul>
			</div>
			
		
			
		
	  </div>
			
	</div>

</div>

<div id="popUp" class="esconder">
				<a class="fechar" href="#" title="voltar"></a>
				<span id="nomePopUp" class="destaque cor">nome do modelo</span>
				<br/><br/>
				<h3>Descrição:</h3>
				<input id="valor" type="hidden" name="idModelo" value="teste" />
				<div>
				<p>text text text text text text text otextotext otextot extot e xtot extote xtotext o tex totextotextote xtotex totext otextotex totext otext o texto</p>
				</div>
				<a class="remover" href="#" title="Excluir Modelo"></a>
				<a class="download" href="#" title="Download do Modelo"></a>
	</div>
	
<div id="popUp" class="esconder">
				<a class="fechar" href="#" title="voltar"></a>
				<span id="nomePopUp" class="destaque cor">nome do modelo</span>
				<br/><br/>
				<h3>Descrição:</h3>
				<input id="valor" type="hidden" name="idModelo" value="teste" />
				<div>
				<p>text text text text text text text otextotext otextot extot e xtot extote xtotext o tex totextotextote xtotex totext otextotex totext otext o texto</p>
				</div>
				<a class="remover" href="#" title="Excluir Modelo"></a>
				<a class="download" href="#" title="Download do Modelo"></a>
</div>

<div id="popUpInserir" class="esconder">
				<a class="fecharInserir" href="#" title="voltar"></a>
				<span id="nomePopUpInserir" class="destaque cor">Defina o novo modelo</span>
				<br/><br/>
				<form action="#" method="post">
					<label for="nome">NOME DO MODELO:</label>
					<input type="text" name="nome" id="nome" size="55" />
					<label for="endereco">CAMINHO DO MODELO:</label>
					<input type="file" name="endereco" id="endereco" size="37" value="PROCURAR"/>
					<label for="descricao">DESCRI&Ccedil;&Atilde;O DO MODELO:</label>
					<textarea id="descricao" name="descricao" m ></textarea>
				</form>
				<a class="armazenar" href="#" title="Armazenar"></a>
			
</div>


</body>

</html>
