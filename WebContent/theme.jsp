<jsp:include page="header.jsp" />

<%@ page import="com.expeditor.dal.*" %>
<%@ page import="com.expeditor.bo.*" %>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <%	
		Employe employe = (Employe)request.getSession().getAttribute("login");
      %>
      
      <a class="navbar-brand" href="#">
      	<%= employe.getNom() %> <%= employe.getPrenom() %>
      </a>
    </div>
		<%	
    	if(employe.getIsManager() == true){
    		%><jsp:include page="menuManager.jsp" /><%	
    	}else{
    		%><jsp:include page="menuUser.jsp" /><%	
    	}
		%>
  	
	  	<ul class="nav navbar-nav navbar-right">
			<li><a href="#"></a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Compte<span
				class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="<%=request.getContextPath()%>/DeconnexionServlet">Deconnexion</a></li>
				</ul>
			</li>
		</ul>
  	</div>
  </div><!-- /.container-fluid -->
</nav>
