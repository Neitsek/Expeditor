<jsp:include page="../theme.jsp" />
<%@ page import="com.expeditor.bo.*" %>
<%
	Commande enCours = (Commande)request.getSession().getAttribute("commandeEnCours");
	if (enCours != null) {
%>

<div class="marginTop content">
	<div class="panel panel-default">
		<div class="panel-heading"><h2>Commande en cours</h2></div>
		<div class="panel-body">
			<ul class="list-unstyled">
				<li><label class="col-sm-4 strong">Date de la commande</label>
					<mark class="col-sm-8">MAINTENANT</mark></li>
				<li><strong class="col-sm-4">Client</strong> <mark
						class="col-sm-8">Toshiba</mark></li>
				<li><strong class="col-sm-4">Adresse</strong> <mark
						class="col-sm-8">Toshiba</mark></li>
			</ul>
			<table class="table col-sm-12">
				<thead>
					<th>Article</th>
					<th>Poids unitaire</th>
					<th>Quantité</th>
					<th class="col-sm-1">Quantité mise en carton</th>
					<th>Poids total</th>
				</thead>
				<tbody>
					<tr class="danger" id="article1">
						<td>Sandwich</td>
						<td>200g</td>
						<td id="qteVoulue1">2</td>
						<td><input id="qteEntree1" type="number" class="form-control"/></td>	
						<td>400g</td>
					</tr>
					<tr class="danger" id="article2">
						<td>Sandwich</td>
						<td>200g</td>
						<td id="qteVoulue2">2</td>
						<td><input id="qteEntree2" type="number" class="form-control"/></td>
						<td>400g</td>
					</tr>
					<tr class="danger" id="article3">
						<td>Sandwich</td>
						<td>200g</td>
						<td id="qteVoulue3">2</td>
						<td><input id="qteEntree3" type="number" class="form-control"/></td>
						<td>400g</td>
					</tr> 
					<tr class="danger">
						<td>Sandwich</td>
						<td>200g</td>
						<td>2</td>
						<td><input type="text" class="form-control"/></td>
						<td>400g</td>
					</tr>
					<tr>
						<td>Sandwich</td>
						<td>200g</td>
						<td>2</td>
						<td><input type="text" class="form-control"/></td>
						<td>400g</td>
					</tr>
					<tr>
						<td>Sandwich</td>
						<td>200g</td>
						<td>2</td>
						<td><input type="text" class="form-control"/></td>
						<td>400g</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function() {
		$('#qteEntree1').change(function() {
			if ($('#qteEntree1').prop('value') == $('#qteVoulue1').html()) {
				if ($('#article1').hasClass('danger')) {
					$('#article1').addClass('success').removeClass('danger');
				}
			} else if ($('#qteEntree1').prop('value') != $('#qteVoulue1').html()) {
				if ($('#article1').hasClass('success')) { 
					$('#article1').addClass('danger').removeClass('success')
				}
			}
		});
	});
</script>
<% } %>
</body>

</html>