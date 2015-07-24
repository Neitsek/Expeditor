<jsp:include page="../theme.jsp" />

<script type="text/javascript" src='lib/bootstrap/fileinput/js/fileinput.min.js'></script>
<link href='lib/bootstrap/fileinput/css/fileinput.min.css' rel="stylesheet">
<link href='manager/css/expeditor.css' rel="stylesheet">
<script type="text/javascript" src='manager/js/import.js'></script>

<div><h2>Import de commande</h2></div>

	<div id="import" class="form-group">
		<div class="col-md-12">
			<input name="upload" type="file" id="editUpload" accept="image/*" value="<s:property value='image'/>"/>
			<div id="errorExtension" class="help-block"></div>
		</div>
	</div>

	</body>
</html>