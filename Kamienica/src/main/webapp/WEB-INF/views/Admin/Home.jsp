<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
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
<mytags:navbarAdmin/>
	

	<div class="container text-center">
		<div class='row'>
			<div class="jumbotron">
				<h1>Kamienica - Strona Admina</h1>
				<a href="Payment/paymentList.html" class="btn btn-lg btn-default paymentButton">OPŁATY
				</a>
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
						- GAZ</a> <a href="Division/divisionList.html" class="list-group-item">PODZIAŁ</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>