<jsp:include page="../theme.jsp" />
<jsp:include page="../datepicker.jsp" />

<script type="text/javascript" src='lib/bootstrap/selectpicker/js/bootstrap-select.min.js'></script>
<link href='lib/bootstrap/selectpicker/css/bootstrap-select.min.css' rel="stylesheet">
<script type="text/javascript" src='manager/js/expeditor.js'></script>
<script type="text/javascript" src='lib/jquery/plugin/jquery-datatable/jquery.dataTables.min.js'></script>

<%@page import="com.expeditor.bo.*"%>
<%@page import="java.util.ArrayList" %>

<% ArrayList<Employe> listeEmploye = (ArrayList<Employe>) request.getAttribute("listeEmploye"); %>

	<form id="filtrage" method="POST" action="" class="col-sm-12">		
		<div class="col-sm-5">
			<div class="form-group">
				<select id="selectEmploye" name="employeSel" class="selectpicker">
					<option value="default">Sélectionner un employé</option>
					<% for(Employe employe : listeEmploye) { %>
					    <option value="<%= employe.getId() %>"><%= employe.getNom() %> - <%= employe.getPrenom() %>
					<% } %>
				</select>
			</div>
			<div class="form-group">
				<select id="selectEtat" class="selectpicker" multiple data-selected-text-format="count>3">
				    <option value="TER" selected="selected">Terminé</option>
				    <option value="ATT">En attente</option>
				    <option value="EC">En cours</option>
				</select>
			</div>
		</div>
		<div class="col-sm-5">
			<div class="form-group">
				<div class="input-group date">
			    	<input type="text" class="form-control dateCommande" id="dateDébut" name="dateDébut"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
			    </div>
		    </div>
		    <div class="form-group">
				<div class="input-group date">
			    	<input type="text" class="form-control dateCommande" id="dateFin" name="dateFin"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
			    </div>
		    </div>
		</div>  
		<div class="form-group">
			<input type="submit" id="filtrer" class="btn btn-default" value="Rechercher"/>
		</div>
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
  		
  			<% ArrayList<Commande> listeCommande = (ArrayList<Commande>) request.getAttribute("listeCommande");
  			if(listeCommande != null){
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
			}
			%>
  		</tbody>
	</table>
	
	
	</body>
</html>