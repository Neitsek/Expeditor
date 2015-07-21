<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<s:a class="navbar-brand" action="Accueil" namespace="/formateur" theme="simple">Accueil</s:a>
			
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
		</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="#">Liste des commandes</a></li>
        	<li><a href="#">Suivi de commandes par salarié</a></li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"></a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Compte<span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><s:a action="deconnection" namespace="/">Deconnexion</s:a></li>
				</ul></li>
			<li><a href="#"></a></li>
		</ul>
	</div>
	</div>
</nav>