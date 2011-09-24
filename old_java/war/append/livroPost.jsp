<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.dados.Transacao"%>
<%@page import="com.appspot.safecash.enuns.EnumOrigemTransacao"%>
<%@page import="com.appspot.safecash.negocio.exception.NenhumaTransacaoEncontradaException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.appspot.safecash.enuns.EnumTipoTransacao"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<%
String msg = (String) request.getAttribute("msg");
	
if (msg != null) {
%>
<script type="text/javascript">alert('<%= request.getAttribute("msg") %>');</script>
<%
}
%>
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