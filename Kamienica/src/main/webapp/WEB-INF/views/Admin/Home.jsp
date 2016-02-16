<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">


<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>


<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Strona Admina</title>
</head>
<body>

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
			<a class="navbar-brand" href="../Admin/home">Strona Główna</a>
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
						<li><a href="../Admin/Invoice/invoiceGasRegister">Gaz</a></li>
						<li><a href="../Admin/Invoice/invoiceEnergyRegister">Energia</a></li>
						<li><a href="../Admin/Invoice/invoiceWaterRegister">Woda</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../Admin/Invoice/invoiceGasList">Gaz</a></li>
						<li><a href="../Admin/Invoice/invoiceEnergyList">Energia</a></li>
						<li><a href="../Admin/Invoice/invoiceWaterList">Woda</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Odczyty <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li class="dropdown-header">Wprowadź nowe</li>
						<li><a href="../Admin/Reading/readingGasRegister">Gaz</a></li>
						<li><a href="../Admin/Reading/readingEnergyRegister">Energia</a></li>
						<li><a href="../Admin/Reading/readingWaterRegister">Woda</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../Admin/Reading/readingGasList">Gaz</a></li>
						<li><a href="../Admin/Reading/readingEnergyList">Energia</a></li>
						<li><a href="../Admin/Reading/readingWaterList">Woda</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Opłaty <span class="caret"></span></a>

					<ul class="dropdown-menu">

						<li><a href="../Admin/Payment/paymentRegister">Wprowadź
								Nowe</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../Admin/Payment/paymentGasList">Gaz</a></li>
						<li><a href="../Admin/Payment/paymentEnergyList">Energia</a></li>
						<li><a href="../Admin/Payment/paymentWaterList">Woda</a></li>

					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Nawiguj <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="../index">Strona Powitalna</a></li>
						<li><a href="../User/userHome">Strona Użytkownika</a></li>
					</ul></li>

				<li><a href="../logout.html">Wyloguj</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div class="container" role="main">
		<div class='row'>
			<div class="jumbotron">
				<h1>Kamienica - Strona Admina</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="list-group">
					<p class="list-group-item active myListGroup">Wprowadź</p>
					<a href="Payment/paymentRegister.html" class="list-group-item">OPŁATA</a>
					<a href="Reading/readingEnergyRegister.html"
						class="list-group-item">ODCZYT - ENERGIA</a> <a
						href="Reading/readingWaterRegister.html" class="list-group-item">ODCZYT
						- WODA</a> <a href="Reading/readingGasRegister.html"
						class="list-group-item">ODCZYT - GAZ</a> <a
						href="Invoice/registerInvoice.html" class="list-group-item">FAKTURY</a>
					<a href="Apartment/apartmentRegister.html" class="list-group-item">MIESZKANIA</a>
					<a href="Tenant/tenantRegister.html" class="list-group-item">NAJEMCY</a>
					<a href="Meter/meterWaterRegister.html" class="list-group-item">LICZNIKI
						- WODA</a> <a href="Meter/meterEnergyRegister.html"
						class="list-group-item">LICZNIKI - ENERGIA</a> <a
						href="Meter/meterGasRegister.html" class="list-group-item">LICZNIKI
						- GAZ</a> <a href="Division/divisionRegister.html"
						class="list-group-item">PODZIAŁ</a>
				</div>
			</div>



			<div class="col-md-6">
				<div class="list-group">
					<p class="list-group-item active myListGroup">Sprawdź/Edytuj</p>
					<a href="Payment/paymentList.html" class="list-group-item">OPŁATA</a>
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
						- GAZ</a> <a href="Division/divisionList.html" class="list-group-item">PODZIAŁ</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>