<jsp:include page="../theme.jsp" />
<%@ page import="com.expeditor.bo.*" %>
<%
	Commande enCours = (Commande)request.getSession().getAttribute("commandeEnCours");
	Boolean impression = (Boolean)request.getSession().getAttribute("impression");
	Boolean imp = (Boolean) request.getSession().getAttribute("imp");
	impression = impression != null;
	imp = imp != null;
	double poidsCarton = 300;
	if (enCours != null) {
%>

<div class="marginTop content">
	<div class="panel panel-default">
		<div class="panel-heading">
		<% if (!impression) { %>
		<h2>Commande en cours</h2>
		<%}else{ %>
		<h2>Commande n° <%= enCours.getId_commande()%></h2>	
		<%} %>
		</div>
		<div class="panel-body">
			<ul class="list-unstyled">
				<li><label class="col-sm-4 strong">Date de la commande</label>
					<mark class="col-sm-8"><%= enCours.getDate_commande() %></mark></li>
				<li><strong class="col-sm-4">Client</strong> <mark
						class="col-sm-8"><%= enCours.getClient() %></mark></li>
				<li><strong class="col-sm-4">Adresse</strong> <mark
						class="col-sm-8"><%= enCours.getAdresse() %></mark></li>
			</ul>
			<table class="table col-sm-12" id="monTableau"> 
				<thead>
					<th>Article</th>
					<th>Poids unitaire</th>
					<th>Quantité</th>
					<% if (!impression) { %>
					<th class="col-sm-2">Quantité mise en carton</th>
					<% } %>
					<th>Poids total</th>
				</thead>
				<tbody>
					<%
						int cpt = 0;
						double poidsTotal = 0;
						for (CommandeArticle art : enCours.getArticles()) {
							cpt++;
					%>
					<tr class="<% if (!impression) { %>danger <% } %>article" id="article<%= cpt%>">
						<td><%= art.getArticle().getNom() %></td>
						<td><%= art.getArticle().getPoids() %>g</td>
						<td id="qteVoulue<%= cpt%>"><%= art.getQuantite() %></td>
						<% if (!impression) { %>
						<td><input id="qteEntree<%= cpt%>" type="number" class="form-control"/></td>
						<% } %>	
						<td><%= art.getArticle().getPoids() * art.getQuantite() %>g</td>
					</tr>
					<%
						if(impression){
							poidsTotal += art.getArticle().getPoids() * art.getQuantite();
						}
					}
					if(impression){%>						
					<tr>
		  				<td colspan="3" class="text-right">Carton : </td>
		  				<td> <%=poidsCarton%>g</td>	
		  			</tr>
					<tr>
		  				<td colspan="3" class="text-right"><b>Poids total :</b></td>
		  				<td><b><%=poidsTotal + poidsCarton%>g</b></td>
					</tr>
					<%}%>
				</tbody>
			</table>

		</div>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		if(<%=imp%>==true){
			javascript:window.print();			
		}
		<% 
			cpt = 0;
			for(CommandeArticle art : enCours.getArticles()) {
				cpt++;
		%>
		
		$('#qteEntree<%= cpt%>').change(function() {
			if ($('#qteEntree<%= cpt%>').prop('value') == $('#qteVoulue<%= cpt%>').html()) {
				if ($('#article<%= cpt%>').hasClass('danger')) {
					$('#article<%= cpt%>').addClass('success').removeClass('danger');
				}
			} else if ($('#qteEntree<%= cpt%>').prop('value') != $('#qteVoulue<%= cpt%>').html()) {
				if ($('#article<%= cpt%>').hasClass('success')) { 
					$('#article<%= cpt%>').addClass('danger').removeClass('success')
				}
			}
			if($('#btnImpression').hasClass('disabled')) {
				if(!$('.article').hasClass('danger')) {
					$('#btnImpression').removeClass('disabled');
				}
			}
			else {
				if($('.article').hasClass('danger')) {
					$('#btnImpression').addClass('disabled');
				}
			}
			
		});
		
		<% }
		request.getSession().removeAttribute("imp");%>
		
	});
</script>
<% } %>
</body>

</html>