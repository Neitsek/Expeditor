<jsp:include page="../theme.jsp" />
<jsp:include page="../datepicker.jsp" />

<script type="text/javascript" src='lib/bootstrap/selectpicker/js/bootstrap-select.min.js'></script>
<link href='lib/bootstrap/selectpicker/css/bootstrap-select.min.css' rel="stylesheet">
<link href='manager/css/expeditor.css' rel="stylesheet">
<script type="text/javascript" src='manager/js/expeditor.js'></script>
<script type="text/javascript" src='lib/jquery/plugin/jquery-datatable/jquery.dataTables.min.js'></script>

<%@page import="com.expeditor.bo.*"%>
<%@page import="java.util.ArrayList" %>

<% ArrayList<Employe> listeEmploye = (ArrayList<Employe>) request.getAttribute("listeEmploye"); %>

<% 
ArrayList<Commande> listeCommande = new ArrayList<Commande>();
listeCommande = (ArrayList<Commande>) request.getAttribute("listeCommande"); %>
	
	<div><h2>Suivi des commandes</h2></div>
<%  
	if (request.getAttribute("info") != null){
		%><div class="form-group alert alert-warning" role="alert"><%= request.getAttribute("info") %></div><%
	}
%>

	<form id="filtrage" method="POST" action="<%=request.getContextPath()%>/SuiviCommandeEmploye" class="col-sm-12">		
		<div class="col-sm-3">
			<div class="form-group">
				<select name="employeSel" class="form-control">
				<option value="0">Sélectionner un employé</option>
					<% 
					if(listeEmploye != null){
						for(Employe employe : listeEmploye) { %>
					    <option value="<%= employe.getId() %>"><%= employe.getNom() %> - <%= employe.getPrenom() %>
					  <%} 
					}else{ %>
					    <option value="0">Aucun employé</option>
				  	<%}%>
				</select>
			</div>
			<div class="form-group">
				<select id="selectEtat" name="etatSel" class="selectpicker" multiple data-selected-text-format="count>3">
				    <option value="TER" selected="selected">Terminé</option>
				    <option value="ATT">En attente</option>
				    <option value="EC">En cours</option>
				</select>
			</div>
		</div>
		<div class="col-sm-5">
			<div class="form-group">
				<label class="control-label col-sm-3">Début préparation</label>
				<div class="input-group date">
			    	<input type="text" disabled class="form-control dateCommande col-sm-6" id="dateDébut" name="dateDebut"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
			    </div>
		    </div>
		    <div class="form-group">
				<label class="control-label col-sm-3">Fin préparation</label>
				<div class="input-group date">
			    	<input type="text" disabled class="form-control dateCommande col-sm-6" id="dateFin" name="dateFin"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
			    </div>
		    </div>
		</div>  
		<div class="col-sm-2">
			<div id="submit" class="form-group">
				<input type="hidden" name="reload" value="reload"/>
				<input type="submit" class="btn btn-default" value="Rechercher"/>
			</div>
		</div>
		<% if(listeCommande != null){
			if(listeCommande.size() > 0){ %>
			<div class="col-sm-2">
				<div class="form-group">
					<label>Nombre de commandes traités : <h3><%= listeCommande.size() %></h3></label>
				</div>
			</div>
			<%}%>
		<%}%>
	</form>
	
	<table id="tableCommande" class="table table-striped table-bordere">
  		<thead>
  			<th>Date Commande</th>
  			<th>Client</th>
  			<th>Adresse</th>
  			<th>Etat</th>
  			<th>Début préparation</th>
  			<th>Fin préparation</th>
  		</thead>
  		<tbody>
  		
  			
  			<% if(listeCommande != null){
  				for(Commande commande : listeCommande) { 
					%>
  			  			<tr>
  			  				<td><%= commande.getDate_commande()  %></td>
  			  				<td><%= commande.getClient()  %></td>
  			  				<td><%= commande.getAdresse()  %></td>
  			  				<td><%= commande.getEtat()  %></td>
  			  				<td><%= commande.getDate_debut_prepa() %></td>
  			  				<td><%= commande.getDate_fin_prepa()  %></td>	
  			  			</tr>
  					<% 
  				} 
			}else {
				%>
		  			<tr>
		  				<td colspan="6" class="text-center">Aucune commande</td>	
		  			</tr>
				<% 
			}
			%>
  		</tbody>
	</table>
	</body>
</html>