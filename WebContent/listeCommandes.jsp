<jsp:include page="theme.jsp" />

<script type="text/javascript"
	src='lib/bootstrap/datepicker/js/bootstrap-datepicker.js'></script>

<script type="text/javascript"
	src='lib/bootstrap/datepicker/locales/bootstrap-datepicker.fr.min.js'></script>

<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker.min.css'
	rel="stylesheet">
<link
	href='lib/bootstrap/datepicker/css/bootstrap-datepicker.standalone.min.css'
	rel="stylesheet">
<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker3.min.css'
	rel="stylesheet">
<link
	href='lib/bootstrap/datepicker/css/bootstrap-datepicker3.standalone.min.css'
	rel="stylesheet">



<%@ page import="java.util.ArrayList"%>
<%@ page import="com.expeditor.bo.Commande"%>


	<h2>Liste des commandes</h2>
	
	
<div class="col-md-12">
	<div class="col-sm-5"><label for="debut">D&eacute;but</label> : <input type="text" name="debut" id="debut" class="form-control"/></div>
</div>
<div class="col-md-12">
	<div class="col-sm-5"><label for="fin">Fin</label> : <input type="text" name="fin" id="fin" class="form-control"/></div>
</div>

	<div class="col-md-10">
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
				int i;
				for (i=0;i==listeCommandes.size()-1;i++) { %>
					<tr>
						<td><%=listeCommandes.get(i).getId_commande()%></td>
						<td><%=listeCommandes.get(i).getDate_commande()%></td>
						<td><%=listeCommandes.get(i).getEtat()%></td>
						<td><%=listeCommandes.get(i).getClient()%></td>
						<td><%=listeCommandes.get(i).getAdresse()%></td>
						<td><%=listeCommandes.get(i).getEmploye()%></td>
						<td><%=listeCommandes.get(i).getDate_debut_prepa()%></td>
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
</script>

</body>
</html>
