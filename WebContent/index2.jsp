<%@page import="java.util.Date"%>
<%@page import="com.expeditor.bo.Commande"%>
<jsp:include page="header.jsp" />

<%@ page import="com.expeditor.dal.*" %>

<%
CommandeDB cmd = new CommandeDB();
Commande c = new Commande(0,new Date(),"ROBERT GUENOUNE","1 rue des navigateur",1,new Date(),new Date(),"EC");
cmd.insert_commande(c);
%>

 
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>
	
	<!-- TODO : afficher le bon menu selon la connexion  menuUser ou menuManager -->
  	<jsp:include page="menuManager.jsp" />
  	
  </div><!-- /.container-fluid -->
</nav>


</body>
</html>