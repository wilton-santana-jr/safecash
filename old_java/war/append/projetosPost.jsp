<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="com.appspot.safecash.dados.Transacao"%>
<%@page import="java.text.SimpleDateFormat"%><div class="items">
<%@page import="java.util.List"%>
<%
//Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

String last = request.getParameter("last");

List<Transacao> transEntrada = (List<Transacao>) (request.getAttribute("transEntrada"));
List<Transacao> transSaida = (List<Transacao>) (request.getAttribute("transSaida"));

Fachada fachada = Fachada.getInstance();
if (last.equalsIgnoreCase("all")) {
%>
	<!-- wrapper for scrollable items -->
	

		<div>
			<!-- jan-abril-->
			<div class="meses">
				<div id="esqUm">
					<div class="cabecario"><span>JANEIRO</span></div>
					<div class="corpo">
						<ul>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO1</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
						</ul>
					</div>
				</div>
				<div id="dirUm">
					<div class="cabecario"><span>FEVEREIRO</span></div>
					<div class="corpo">
						<ul>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO1</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
						</ul>
					</div>
				</div>
				<div id="esqDois">
					<div class="cabecario"><span>MAR&Ccedil;O</span></div>
					<div class="corpo">
						<ul>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO1</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
						</ul>
					</div>
				</div>
				<div id="dirDois">
					<div class="cabecario"><span>ABRIL</span></div>
					<div class="corpo">
						<ul>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO1</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
							<li><a href="#" title="Visualizar Projeto">Nome Projeto PROJETO2</a></li>
						</ul>
					</div>
				</div>
			</div>
			
		</div>
			<div class="meses">
				<div id="esqUm">
					<div class="cabecario"><span>MAIO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="dirUm">
					<div class="cabecario"><span>JUNHO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="esqDois">
					<div class="cabecario"><span>JULHO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="dirDois">
					<div class="cabecario"><span>AGOSTO</span></div>
					<div class="corpo"></div>
				</div>
			</div>
		<div>

			<!-- maio-agost-->
			<div class="meses">
				<div id="esqUm">
					<div class="cabecario"><span>SETEMBRO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="dirUm">
					<div class="cabecario"><span>OUTUBRO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="esqDois">
					<div class="cabecario"><span>NOVEMBRO</span></div>
					<div class="corpo"></div>
				</div>
				<div id="dirDois">
					<div class="cabecario"><span>DEZEMBRO</span></div>
					<div class="corpo"></div>
				</div>
			</div>
			
		</div>

			
	</div>

<%
} else if (last.equalsIgnoreCase("entrada")) {
	for (Transacao t : transEntrada) {
%>	
		<span class="data"> <%= new SimpleDateFormat("dd/MM/yyyy").format(t.getData()) %> </span><span class="descricao"><%= t.getDescricao() %></span><span class="valor">R$ <%= String.format("%.2f", t.getValor()) %></span><span class="tipo"><%= t.getTipo().toString() %></span><span class="estado"><%= t.getOrigem().toString() %></span>
<%
	}
} else if (last.equalsIgnoreCase("saida")) {
	for (Transacao t : transSaida) {
%>
		<span class="data"> <%= new SimpleDateFormat("dd/MM/yyyy").format(t.getData()) %> </span><span class="descricao"><%= t.getDescricao() %></span><span class="valor">R$ <%= String.format("%.2f", t.getValor()) %></span><span class="tipo"><%= t.getTipo().toString() %></span><span class="estado"><%= t.getOrigem().toString() %></span>
<%
	}
}
%>