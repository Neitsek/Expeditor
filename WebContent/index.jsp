<jsp:include page="header.jsp" />

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