<%@ page import="com.expeditor.bo.*" %>
<%
	Commande commandeEnCours = (Commande)request.getSession().getAttribute("commandeEnCours");
	Boolean impression = (Boolean)request.getSession().getAttribute("impression");
	impression = impression != null;
%>
<style type="text/css">
a.disabled {
  pointer-events: none;
  cursor: default;
}
</style>
	
	<ul class="nav navbar-nav">
		<%
			if (commandeEnCours == null) {
		%>
		
		<li><a href="<%= request.getContextPath() %>/ProchaineCommande">Prochaine commande</a></li>
		
		<% 	
			}
			else if (!impression) {
								
		%>
		
		<li id="btnImpression" class="disabled"><a href="<%= request.getContextPath() %>/Impression" >Impression du bon de livraison</a></li>
		<% 	
			
			}
			else {
		%>
			<li id="btnTerminer"><a href="<%= request.getContextPath() %>/TerminerCommande">Terminer la commande</a></li>
		<%
			}
		%>
       
    </ul>
   

    

<script type="text/javascript">
	function Impression(){
		javascript:window.print();
		
		setTimeout('RedirectionJavascript()', 5000);
	}
	function RedirectionJavascript(){
		document.location.href="<%= request.getContextPath() %>/Impression";		
	}
	
 	$('a.disabled').click(function(){
		if(<%=impression%>==true){
			document.location.href="<%= request.getContextPath() %>/Impression";			
		}else{
			return false;
		}
	}); 
	
</script>
     
