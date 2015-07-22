<%@ page import="com.expeditor.bo.*" %>
<%
	Commande commandeEnCours = (Commande)request.getSession().getAttribute("commandeEnCours");
	Boolean commandeRemplie = false;//(Boolean)request.getAttribute("commandeRemplie");
	Boolean bonDeLivraisonImprime = false;//(Boolean)request.getAttribute("bdlImprime");
%>
	
	<ul class="nav navbar-nav">
		<%
			if (commandeEnCours == null) {
		%>
		
		<li><a href="<%= request.getContextPath() %>/ProchaineCommande">Prochaine commande</a></li>
		
		<% 	
			}
			else if (!bonDeLivraisonImprime) {
		%>
		
		<li <% if (!commandeRemplie) { %>
			class="disabled"
			<% } %>
		><a href="#">Impression du bon de livraison</a></li>
		
		<% 	
			}
			else {
		%>
			<li><a href="#">Terminer la commande</a></li>
		<%
			}
		%>
       
    </ul>
     
