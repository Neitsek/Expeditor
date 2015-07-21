<jsp:include page="header.jsp" />

<%@ page import="com.expeditor.dal.*" %>
 
 <jsp:include page="theme.jsp" />
 
<!-- Multiple Checkboxes -->
<div class="control-group">
  <label class="control-label" for="chckEtat">Etat</label>
  <div class="controls">
    <label class="checkbox" for="chckEtat-0">
      <input type="checkbox" name="chckEtat" id="chckEtat-0" value="En attente">
      En attente
    </label>
    <label class="checkbox" for="chckEtat-1">
      <input type="checkbox" name="chckEtat" id="chckEtat-1" value="En cours">
      En cours
    </label>
    <label class="checkbox" for="chckEtat-2">
      <input type="checkbox" name="chckEtat" id="chckEtat-2" value="Terminer">
      Terminer
    </label>
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for="btActualiser"></label>
  <div class="controls">
    <button id="btActualiser" name="btActualiser" class="btn btn-primary">Actualiser</button>
  </div>
</div>



</body>
</html>