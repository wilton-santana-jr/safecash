<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.dados.Transacao"%>
<%@page import="com.appspot.safecash.enuns.EnumOrigemTransacao"%>
<%@page import="com.appspot.safecash.negocio.exception.NenhumaTransacaoEncontradaException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.appspot.safecash.enuns.EnumTipoTransacao"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();

Iterator<Transacao> iteTransConta = null;
Iterator<Transacao> iteTransCaixa = null;

try {
	iteTransConta = fachada.buscar(EnumOrigemTransacao.CONTA);
} catch (NenhumaTransacaoEncontradaException exp) {
	iteTransConta = null;
}

try {
	iteTransCaixa = fachada.buscar(EnumOrigemTransacao.CAIXA);
} catch (NenhumaTransacaoEncontradaException exp) {
	iteTransCaixa = null;
}

ArrayList<Transacao> transContaEntrada = new ArrayList<Transacao>();
ArrayList<Transacao> transContaSaida = new ArrayList<Transacao>();

ArrayList<Transacao> transCaixaEntrada = new ArrayList<Transacao>();
ArrayList<Transacao> transCaixaSaida = new ArrayList<Transacao>();

while (iteTransConta != null && iteTransConta.hasNext()) {
	Transacao c = iteTransConta.next();
	if (c.getTipo() == EnumTipoTransacao.ENTRADA)
		transContaEntrada.add(c);
	else
		transContaSaida.add(c);
}

while (iteTransCaixa != null && iteTransCaixa.hasNext()) {
	Transacao c = iteTransCaixa.next();
	if (c.getTipo() == EnumTipoTransacao.ENTRADA)
		transCaixaEntrada.add(c);
	else
		transCaixaSaida.add(c);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.text.SimpleDateFormat"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="css/defalt.css" />
<link rel="stylesheet" href="css/caixa.css" />

<link rel="stylesheet" href="css/livro.css" />
<link rel="stylesheet" href="css/jquery.click-calendario-1.0.css" />

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.click-calendario-1.0-min.js"></script>
<script type="text/javascript" src="js/livro.js"></script>
<title>$afeCash - Administrador Financeiro</title>
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
					<%
					if (transContaEntrada.size() != 0) {
						for (Transacao c : transContaEntrada) {
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(c.getData())); %></span><span class ="descricao"><%  if (c.getDescricao().length() > 32) out.print(c.getDescricao().substring(0, 27) + "[...]"); else out.print(c.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", c.getValor())); %></span>
					<%
						}
					}
					else
						out.print("Não há transações de conta a receber.");
					%>
					</div>
			</div>
			
			<div id="receber">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>				</div>
				<div class="corpo">
					<br/>
					<%
					if (transContaSaida.size() != 0) {
						for (Transacao c : transContaSaida) {
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(c.getData())); %></span><span class ="descricao"><%  if (c.getDescricao().length() > 32) out.print(c.getDescricao().substring(0, 27) + "[...]"); else out.print(c.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", c.getValor())); %></span>
					<%
						}
					}
					else
						out.print("Não há transações de conta a pagar.");
					%>
					</div>
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
					<%
					if (transCaixaEntrada.size() != 0) {
						for (Transacao c : transCaixaEntrada) {
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(c.getData())); %></span><span class ="descricao"><%  if (c.getDescricao().length() > 32) out.print(c.getDescricao().substring(0, 27) + "[...]"); else out.print(c.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", c.getValor())); %></span>
					<%
						}
					}
					else
						out.print("Não há transações de caixa a receber.");
					%>
					</div>
			</div>
			
			<div id="receberCaixa">
				<div class="cabecario">
					<span class ="data">DATA</span>
					<span class ="descricao">DESCRI&Ccedil;&Atilde;O</span>
					<span class="valor">VALOR</span>				</div>
				<div class="corpo">
					<br/>
					<%
					if (transCaixaSaida.size() != 0) {
						for (Transacao c : transCaixaSaida) {
					%>
					<span class ="data"><% out.print(new SimpleDateFormat("dd/MM/yyyy").format(c.getData())); %></span><span class ="descricao"><%  if (c.getDescricao().length() > 32) out.print(c.getDescricao().substring(0, 27) + "[...]"); else out.print(c.getDescricao()); %></span><span class="valor">R$ <% out.print(String.format("%.2f", c.getValor())); %></span>
					<%
						}
					}
					else
						out.print("Não há transações de caixa a pagar.");
					%>
					</div>
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
	
	</form>
	<a class="inserir" href="#"></a>


</div> 
<div id="tela"></div>
</body>
</html>
