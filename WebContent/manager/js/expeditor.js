$( document ).ready(function() {
	 $('.selectpicker').selectpicker();
	 
	 $('.dateCommande').datepicker({
		    format: "dd/mm/yyyy",
		    todayBtn: "linked",
		    language: "fr",
		    orientation: "top left",
		    daysOfWeekDisabled: "0,6"
		});
	 
	/* $( "#filtrage" ).submit(function( event ) {
		 var employe = $('#selectEmploye option:selected').val();
		 var listEtat = new Array();
		 listEtat.push($("#selectEtat").val());
		 var dateDebut = $('#dateCommande').val();
		 var dateFin = $('#dateFin').val();
		 
		 $.ajax({
			  method: "POST",
			  url: "${pageContext.request.contextPath}/ReloadSuiviCommandeAjaxServlet",
			  data: { "dateDebut": dateDebut, "dateFin": dateFin, "idEmploye": employe, "listeEtat" :listEtat }
			})
			  .done(function( msg ) {
			    alert( "Data Send");
			});
	});*/
	 
});