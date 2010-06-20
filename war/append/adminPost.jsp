<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.appspot.safecash.fachada.Fachada"%>
<%@page import="com.appspot.safecash.dados.Usuario"%>
<%
// Verifica consistência da Seção
if (session.getAttribute("login") == null) {
	response.sendRedirect("login.jsp");
	response.reset();
}

Fachada fachada = Fachada.getInstance();
Iterator<Usuario> iteUsr = fachada.getAllUsuario();
%>
<%
String msg = (String) request.getAttribute("msg");
	
if (msg != null) {
%>
<script type="text/javascript">alert('<%= request.getAttribute("msg") %>');</script>
<%
}
%>
			<%
				if (iteUsr != null && iteUsr.hasNext()) {
					while (iteUsr.hasNext()) {
						Usuario usr = iteUsr.next();
				%>
				<li><input type="radio" name="usuario" value="<%= usr.getLogin() %>" id="<%= usr.getLogin() %>"/><label for="<%= usr.getLogin() %>"><%= usr.getNome() %></label></li>
				<%
					}
				}
				else
					out.print("<li>Não há Usuarios cadastrados.<br/></li>");
			%>