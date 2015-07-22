<jsp:include page="theme.jsp" />
<script type="text/javascript" src='lib/bootstrap/datepicker/js/bootstrap-datepicker.js'></script>
<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker.min.css' rel="stylesheet">
<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker.standalone.min.css' rel="stylesheet">
<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker3.min.css' rel="stylesheet">
<link href='lib/bootstrap/datepicker/css/bootstrap-datepicker3.standalone.min.css' rel="stylesheet">

<div class="marginTop"></div>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.expeditor.bo.Commande" %>




<br>
<div><h2>Liste des commandes</h2></div>
<br>


	<div class="input-group date col-sm-12" id="groupDate">
	  <input type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
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
     $(document).ready(function () {                      	
    	 $('#groupDate ').datepicker({
     	    language: "fr",
     	   orientation: "top left"
     	});     	 
     });
    	 
</script>

</body>
</html>
