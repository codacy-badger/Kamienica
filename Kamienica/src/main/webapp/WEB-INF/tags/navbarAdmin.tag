<%@tag pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<div class='navbar'>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/Kamienica/Admin/home">Strona Główna</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">



					<!-- Faktury -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Faktury <span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li class="dropdown-header">Wprowadź nowe</li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceGasRegister">Gaz</a></li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceEnergyRegister">Energia</a></li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceWaterRegister">Woda</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">Sprawdź/Edytuj</li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceGasList">Gaz</a></li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceEnergyList">Energia</a></li>
							<li><a href="/Kamienica/Admin/Invoice/invoiceWaterList">Woda</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Odczyty <span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li class="dropdown-header">Wprowadź nowe</li>
							<li><a href="/Kamienica/Admin/Reading/readingGasRegister">Gaz</a></li>
							<li><a href="/Kamienica/Admin/Reading/readingEnergyRegister">Energia</a></li>
							<li><a href="/Kamienica/Admin/Reading/readingWaterRegister">Woda</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">Sprawdź/Edytuj</li>
							<li><a href="/Kamienica/Admin/Reading/readingGasList">Gaz</a></li>
							<li><a href="/Kamienica/Admin/Reading/readingEnergyList">Energia</a></li>
							<li><a href="/Kamienica/Admin/Reading/readingWaterList">Woda</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Opłaty <span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a href="/Kamienica/Admin/Payment/paymentGasList">Gaz</a></li>
							<li><a href="/Kamienica/Admin/Payment/paymentEnergyList">Energia</a></li>
							<li><a href="/Kamienica/Admin/Payment/paymentWaterList">Woda</a></li>

						</ul></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Nawiguj <span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a href="/Kamienica/index">Strona Powitalna</a></li>
							<li><a href="/Kamienica/User/userHome">Strona Użytkownika</a></li>
						</ul></li>

					<li><a href="/Kamienica/logout.html">Wyloguj</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</div>