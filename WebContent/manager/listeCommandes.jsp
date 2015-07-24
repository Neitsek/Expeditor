<jsp:include page="../theme.jsp" />
<jsp:include page="../datepicker.jsp" />


<%@ page import="java.util.ArrayList"%>
<%@ page import="com.expeditor.bo.Commande"%>
<%@ page import="com.expeditor.bll.EmployeManager"%>

<div class="marge_gauche">
	<h2>Liste des commandes</h2>
	<br>
	<form action="<%=request.getContextPath()%>/ListeCommandeServlet" method="post">
		<input type="hidden" value="OK" name="actu">
		<div class="row">
			<!-- Dates -->
			<div class="col-xs-6 col-md-3">
				<div class="form-group">
					<label for="debut">D&eacute;but</label> : <input type="text"
						name="debut" id="debut" class="form-control" value="<%=request.getAttribute("dDebut")%>"/>						
				</div>
				<div class="form-group">
					<label for="fin">Fin</label> : <input type="text" name="fin"
						id="fin" class="form-control" value="<%=request.getAttribute("dFin")%>"/>
				</div>
			</div>
			<!-- Etat -->
			<%
			ArrayList<String> listeEtat = new ArrayList<String>();
			listeEtat = (ArrayList<String>) request.getAttribute("listeEtat");			
						
			%>
			
			<div class="col-xs-6 col-md-3">
				<div class="form-group">
					<label for="checkbox">Etat</label>
					<div class="checkbox">
						<label><input type="checkbox" <%if(listeEtat.contains("ATT")){%> checked="checked" <%}%>
							name="etat" id="ATT" value="ATT" onClick="maFonction();">En attente</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" <%if(listeEtat.contains("EC")){%> checked="checked" <%}%>
							name="etat" id="EC" value="EC" onClick="maFonction();">En cours</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" <%if(listeEtat.contains("TER")){%> checked="checked" <%}%>
							name="etat" id="TER" value="TER" onClick="maFonction();">Terminer</label>
					</div>
				</div>
			</div>
			<!-- Actualisation-->
			<div class="col-xs-6 col-md-3">
				<div class="form-group" id="div_actu">
					<button type="submit" name="actu" id="actu" class="btn btn-default">Actualiser</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="col-md-9">
		<div id="table-commandes">
			<table class="table">
				<thead>
					<th>N°</th>
					<th>Date commande</th>
					<th>Etat</th>
					<th>Client</th>
					<th>Adresse</th>
					<th>Employe</th>
					<th>Date début prépa.</th>
				</thead>
				<tbody>
				<% // On recupère la liste des commandes 
				ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
				listeCommandes = (ArrayList<Commande>) request.getAttribute("listeCommande");
				if(listeCommandes.size()>0){
				for (Commande commande : listeCommandes) {%>
					<tr>
						<td><%=commande.getId_commande()%></td>
						<td><%=commande.getDate_commande()%></td>
						<td><%=commande.getEtat()%></td>
						<td><%=commande.getClient()%></td>
						<td><%=commande.getAdresse()%></td>
						<td><% if(commande.getEmploye()>0){%> <%=EmployeManager.nomPrenom(commande.getEmploye())%><%} %></td>
						<td><%=commande.getDate_debut_prepa()%></td>
					</tr>
				<%}
				}else{%>
					<tr>
		  				<td colspan="7" class="text-center">Aucune commande</td>	
		  			</tr>					
				<%}%>					
				
				</tbody>
			</table>
		</div>
	</div>


<!-- import date picker -->

<script type="text/javascript">
	$(document).ready(function() {
		$('#debut').datepicker({
		    format: "dd/mm/yyyy",
		    clearBtn: true,
		    language: "fr",
		    orientation: "top left",
		    autoclose: true
		});
		$('#fin').datepicker({
		    format: "dd/mm/yyyy",
		    clearBtn: true,
		    language: "fr",
		    orientation: "top left",
		    autoclose: true
		});
	});
	
	function maFonction(){
		if((document.getElementById("ATT").checked == false) && (document.getElementById("EC").checked == false)&& (document.getElementById("TER").checked == false)){
	        document.getElementById('actu').disabled = 'disabled';
	    }else{
	    	document.getElementById('actu').disabled = '';
	    }
	}
	
</script>

</body>
</html>
