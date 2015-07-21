<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>

<div class="col-sm-12 text-center">
	<h2>Expeditor</h2>
</div>

<%  
	if (request.getAttribute("error") != null){
		%><div class="form-group alert alert-danger" role="alert"><%= request.getAttribute("error") %></div><%
	}
%>

<form class="form-horizontal vcenter" method="POST" action="<%=request.getContextPath()%>/ConnexionServlet">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Identifiant</label>
    <div class="col-sm-8">
      <input type="text" class="form-control" id="login" placeholder="Login" name="login">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Mot de passe</label>
    <div class="col-sm-8">
      <input type="password" class="form-control" id="password" placeholder="Password" name="password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-8">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Se souvenir de moi
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-8">
      <button type="submit" class="btn btn-default">Se connecter</button>
    </div>
  </div>
</form>