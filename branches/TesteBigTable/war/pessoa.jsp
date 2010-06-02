<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<f:view>
		<head>
			<title><h:outputText value="Desvendando JSF" /></title>
		</head>

		<body>
			<h:form>
				<table>
					<tr>
						<td><h:outputText value="Nome" />:</td>
						<td><h:inputText value="#{pessoaController.pessoa.nome}" /></td>
					</tr>
					<tr>
						<td><h:outputText value="Nacionalidade" />:</td>
						<td><h:inputText value="#{pessoaController.pessoa.nacionalidade}" /></td>
					</tr>
					<tr>
						<td><h:commandButton value="salvar" action="#{pessoaController.save}" /></td>
					</tr>
				</table>
				
				<h:dataTable value="#{pessoaController.pessoas}" var="p" 
				rendered="#{not empty pessoaController.pessoas}" border="1">
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="Nome" />
						</f:facet>
		
						<h:commandLink value="#{p.nome}" action="#{pessoaController.edit}">
							<f:param name="id" value="#{p.id}" />
						</h:commandLink>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="Nacionalidade" />
						</f:facet>
						<h:commandLink value="#{p.nacionalidade}" action="#{pessoaController.edit}">
							<f:param name="id" value="#{p.id}" />
						</h:commandLink>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="Excluir" />
						</f:facet>
						<h:commandLink value="excluir" action="#{pessoaController.delete}">
							<f:param name="id" value="#{p.id}" />
						</h:commandLink>
					</h:column>
				</h:dataTable>
			</h:form>
		</body>
	</f:view>
</html>