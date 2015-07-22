<jsp:include page="../theme.jsp" />

<script type="text/javascript" src='lib/bootstrap/selectpicker/js/bootstrap-select.min.js'></script>
<script type="text/javascript" src='lib/js/expeditor.js'></script>
<link href='lib/bootstrap/selectpicker/css/bootstrap-select.min.js' rel="stylesheet">

<%@page import="com.expeditor.bo.*"%>
<%@page import="java.util.ArrayList" %>

<% ArrayList<Employe> listeEmploye = (ArrayList<Employe>) request.getAttribute("listeEmploye"); %>

		<div class="">
		Liste des employés
			<select class="selectpicker show-menu-arrow">
				<option>Sélectionner un employé</option>
				<% for(Employe employe : listeEmploye) { %>
				    <option value="<% employe.getId(); %>"><% employe.getNom(); %> - <% employe.getPrenom(); %>
				<% } %>
			</select>
		</div>


	</body>
</html>