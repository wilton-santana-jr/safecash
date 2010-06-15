<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/defalt.css" />
<link rel="stylesheet" href="css/caixa.css" />

<link rel="stylesheet" href="css/livro.css" />
<link rel="stylesheet" href="css/jquery.click-calendario-1.0.css" />


<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.click-calendario-1.0-min.js"></script>
<script type="text/javascript" src="js/livro.js"></script>
<title>Untitled Document</title>
</head>

<body>
<div id="tudo">
	<div id="dados">Usu&aacute;rio: <span class="cor">Bruno Medeiros</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &gt;&gt; <a href="#" class="cor" title="logout">logout</a></div>
	<div id="logo"></div>
	
	<div id="corpo">
		<div id="menu">
			<ul>
				<li><a href="#" title="P&aacute;gina Inicial"><span id="inicio"></span></a></li>
				<li><a href="#" title="Livro do Caixa"><span id="livro"></span></a></li>
				<li><a href="#" title="Contas"><span id="contas"></span></a></li>
				<li><a href="#" title="Projetos"><span id="projetos"></span></a></li>
				<li><a href="#" title="Requisi&ccedil;&atilde;o"><span id="requisicao"></span></a></li>
				<li><a href="#" title="Relat&oacute;rios"><span id="relatorios"></span></a></li>
				<li><a href="#" title="Modelos de documentos"><span id="modelos"></span></a></li>
				<li><span id="linha"></span></li>
			</ul>
		</div>
		
		<div id="conteudo">

			<a href="#" id="botao" title="Adicionar Conta"></a>
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
			<div id="livroConta" class="destaque cor">Livro Conta</div>
			
			<!--<form>
				<input type="checkbox" id="conta" name="conta" value="true" checked="checked"/><label for="conta"> Conta</label>
				<input type="checkbox" id="caixa" name="caixa" value="true"   checked="checked"/><label for="livro"> Livro</label>
			</form>-->
			
			<div id="titulo" class="arredonda"><span id="esq">ENTRADA</span><span>SA&Iacute;DA</span></div>
			
			<div id="informacoes">
				<div id="pagar">
					<div class="cabecario">
						<span class ="data">DATA</span>
						<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
						<span class="valor">VALOR</span>					</div>
				<div class="corpo">
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;ODESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;ODESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>				</div>
			</div>
			
			<div id="receber">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>				</div>
				<div class="corpo">
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>				</div>
			</div>
			
 	<!--------------------------------------------------------------------------------------------->
	
	<div id="livroCaixa" class="destaque cor">Livro Caixa</div>
			
			<div id="tituloCaixa" class="arredonda"><span id="esq">ENTRADA</span><span>SA&Iacute;DA</span></div>
			
			<div id="informacoesCaixa">
				<div id="pagarCaixa">
					<div class="cabecario">
						<span class ="data">DATA</span>
						<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
						<span class="valor">VALOR</span>					</div>
				<div class="corpo">
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;ODESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;ODESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>				</div>
			</div>
			
			<div id="receberCaixa">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>				</div>
				<div class="corpo">
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>
					<br/>
					<span class ="data">DD/MM/AAAA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O DA REQUISI&Ccedil;&Atilde;O</span>					<span class="valor">R$ 0,00</span>				</div>
			</div>
	
	
		</div>
	</div>

</div>
</div>
</div>
<div id="popUp" class="esconder">
	<a class="fechar" href="#" title="voltar"></a>
	<span id="nomePopUp" class="destaque cor">Defina a nova transa&ccedil;&atilde;o</span>
	
	<form action="
	#" method="post">
	<label class="titulo">DESCRI&Ccedil;&Atilde;O:</label><br/>
	<input type="text" name="descricao" size="55"/><br/><br/>
	<label class="titulo">TIPO:</label><br/>
	<input  id="entrada" type="radio" name="tipo" value="entrada"/><label for="entrada">ENTRADA</label><br/>
	<input  id="saida" type="radio" name="tipo" value="saida"/><label for="saida">SAIDA</label><br/><br/>
	<label class="titulo">ORIGEM:</label><br/>
	<input  id="conta" type="radio" name="origem" value="conta"/><label for="conta">CONTA</label><br/>
	<input  id="caixa" type="radio" name="origem" value="caixa"/><label for="caixa">CAIXA</label><br/>
	
	<input id="calendario" type="hidden" name="data" value="" />
	
	<a href="#"></a>
	</form>


</div> 
<div id="tela"></div>
</body>
</html>
