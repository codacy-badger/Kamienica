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
				<a class="navbar-brand" href="/Kamienica/Admin/home">Strona
					Główna</a>
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
							<li><a href="/Kamienica/User/userHome">Strona
									Użytkownika</a></li>
						</ul></li>

					<li><a href="/Kamienica/logout.html">Wyloguj</a></li>

				</ul>

				<div class="navbar-inverse sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li class="sidebar-search">
								<div class="input-group custom-search-form">
									<input type="text" class="form-control" placeholder="Search...">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div> <!-- /input-group -->
							</li>
							<li><a href="index.html"><i
									class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
							<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
									Charts<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="flot.html">Flot Charts</a></li>
									<li><a href="morris.html">Morris.js Charts</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a href="tables.html"><i class="fa fa-table fa-fw"></i>
									Tables</a></li>
							<li><a href="forms.html"><i class="fa fa-edit fa-fw"></i>
									Forms</a></li>
							<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI
									Elements<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="panels-wells.html">Panels and Wells</a></li>
									<li><a href="buttons.html">Buttons</a></li>
									<li><a href="notifications.html">Notifications</a></li>
									<li><a href="typography.html">Typography</a></li>
									<li><a href="icons.html"> Icons</a></li>
									<li><a href="grid.html">Grid</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
									Multi-Level Dropdown<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="#">Second Level Item</a></li>
									<li><a href="#">Second Level Item</a></li>
									<li><a href="#">Third Level <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level">
											<li><a href="#">Third Level Item</a></li>
											<li><a href="#">Third Level Item</a></li>
											<li><a href="#">Third Level Item</a></li>
											<li><a href="#">Third Level Item</a></li>
										</ul> <!-- /.nav-third-level --></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
									Sample Pages<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="blank.html">Blank Page</a></li>
									<li><a href="login.html">Login Page</a></li>
								</ul> <!-- /.nav-second-level --></li>
						</ul>
					</div>
					<!-- /.sidebar-collapse -->
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</div>