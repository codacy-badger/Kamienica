<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/sb-admin-2.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">

<!-- MetisMenu CSS -->
<link href="<c:url value='/static/css/metisMenu.min.css' />"
	rel="stylesheet">
<!-- Timeline CSS -->
<link href="<c:url value='/static/css/timeline.css' />" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<c:url value='/static/css/morris.css' />" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Strona Admina</title>
</head>
<body>
	<!-- Navigation -->
	<div id="wrapper">
		<mytags:oldNavbar/>

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
					<li><a href="index.html"><i class="fa fa-dashboard fa-fw"></i>
							Dashboard</a></li>
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
					<li><a href="#"><i class="fa fa-files-o fa-fw"></i> Sample
							Pages<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="blank.html">Blank Page</a></li>
							<li><a href="login.html">Login Page</a></li>
						</ul> <!-- /.nav-second-level --></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
		<div id="page-wrapper">
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Kamienica - Strona główna</h1>
						<a href="index.html">link do nowego menu</a>
					</div>
					<!-- /.col-lg-12 -->
				</div>


				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-comments fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">26</div>
										<div>Konfiguracja??</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa  fa-calendar-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div>
											<h2>Gaz: 10</h2>
										</div>
										<div>Najstarsza opłata</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">Dodaj nową opłatę</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-calendar-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">
											<h2>Energia: 30</h2>
										</div>
										<div>Najstarszy odczyt</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">Dodaj nowe odczyty</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-home fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div>
											<h2>13</h2>
										</div>
										<div>Liczba Pustych Mieszkań</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">Sprawdź Puste Mieszkania</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="list-group">
							<p class="list-group-item active myListGroup">Wprowadź</p>
							<a href="Reading/readingEnergyRegister.html"
								class="list-group-item">ODCZYT - ENERGIA</a> <a
								href="Reading/readingWaterRegister.html" class="list-group-item">ODCZYT
								- WODA</a> <a href="Reading/readingGasRegister.html"
								class="list-group-item">ODCZYT - GAZ</a> <a
								href="Invoice/registerInvoice.html" class="list-group-item">FAKTURY</a>
							<a href="Apartment/apartmentRegister.html"
								class="list-group-item">MIESZKANIA</a> <a
								href="Tenant/tenantRegister.html" class="list-group-item">NAJEMCY</a>
							<a href="Meter/meterWaterRegister.html" class="list-group-item">LICZNIKI
								- WODA</a> <a href="Meter/meterEnergyRegister.html"
								class="list-group-item">LICZNIKI - ENERGIA</a> <a
								href="Meter/meterGasRegister.html" class="list-group-item">LICZNIKI
								- GAZ</a> <a href="Division/divisionRegister.html"
								class="list-group-item">PODZIAŁ</a>
						</div>
					</div>



					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="list-group">
							<p class="list-group-item active myListGroup">Sprawdź/Edytuj</p>
							<a href="Reading/readingEnergyList.html" class="list-group-item">ODCZYT
								- ENERGIA</a> <a href="Reading/readingWaterList.html"
								class="list-group-item">ODCZYT - WODA</a> <a
								href="Reading/readingGasList.html" class="list-group-item">ODCZYT
								- GAZ</a> <a href="Invoice/invoiceList.html" class="list-group-item">FAKTURY</a>
							<a href="Apartment/apartmentList.html" class="list-group-item">MIESZKANIA</a>
							<a href="Tenant/tenantList.html" class="list-group-item">NAJEMCY</a>
							<a href="Meter/meterWaterList.html" class="list-group-item">LICZNIKI
								- WODA</a> <a href="Meter/meterEnergyList.html"
								class="list-group-item">LICZNIKI - ENERGIA</a> <a
								href="Meter/meterGasList.html" class="list-group-item">LICZNIKI
								- GAZ</a> <a href="Division/divisionList.html"
								class="list-group-item">PODZIAŁ</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>

	<!-- Morris Charts JavaScript -->
	<script src="<c:url value='/static/js/raphael-min.js' />"></script>
	<script src="<c:url value='/static/js/morris.min.js' />"></script>
	<script src="<c:url value='/static/js/morris-data.js' />"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>


</body>
</html>