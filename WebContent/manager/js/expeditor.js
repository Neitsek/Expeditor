$( document ).ready(function() {
	 $('.selectpicker').selectpicker();
	 
	 $('.dateCommande').datepicker({
		    format: "dd/mm/yyyy",
		    todayBtn: "linked",
		    language: "fr",
		    orientation: "top left",
		    daysOfWeekDisabled: "0,6"
		});
	 
	 $( "#filtrage" ).submit(function( event ) {
		 var employe = $('#selectEmploye option:selected').val();
		 var listEtat = $("#selectEtat").val();
		 alert(listEtat);
		 var dateDebut = $('#dateCommande').val();
		 var dateFin = $('#dateFin').val();
		 
		 $.ajax({
			  method: "POST",
			  url: "/ReloadSuiviCommandeAjaxServlet",
			  data: { dateDebut: dateDebut, dateFin: dateFin, idEmploye: employe, listeEtat :listEtat }
			})
			  .done(function( msg ) {
			    alert( "Data Send");
			});
	});
	 

	 $('#tableCommande').DataTable({
		 "dom": '<"top"i>rt<"bottom"flp><"clear">',
		 "oLanguage": {
		        "sEmptyTable":     "My Custom Message On Empty Table"
		  }
	 });
});