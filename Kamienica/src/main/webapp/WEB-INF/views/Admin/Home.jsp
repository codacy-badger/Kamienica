<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Strona Admina</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Kamienica - Strona Admina</h1>
		<hr>
		<div class="col_two">
			<h2>Wprowadź nowe</h2>
			<a href=" Payment/paymentRegister.html">
				<div class="button-link">OPŁATA</div>
			</a> <a href=" Reading/readingEnergyRegister.html">
				<div class="button-link">ODCZYT - ENERGIA</div>
			</a> <a href=" Reading/readingWaterRegister.html">
				<div class="button-link">ODCZYT - WODA</div>
			</a> <a href=" Reading/readingGasRegister.html">
				<div class="button-link">ODCZYT - GAZ</div>
			</a> <a href=" Invoice/registerInvoice.html">
				<div class="button-link">FAKTURY</div>
			</a><a href=" Apartment/apartmentRegister.html">
				<div class="button-link">MIESZKANIA</div>
			</a> <a href=" Tenant/tenantRegister.html">
				<div class="button-link">NAJEMCY</div>
			</a> <a href=" Meter/meterWaterRegister.html">
				<div class="button-link">LICZNIKI - WODA</div>
			</a> <a href=" Meter/meterEnergyRegister.html">
				<div class="button-link">LICZNIKI - ENERGIA</div>
			</a> <a href=" Meter/meterGasRegister.html">
				<div class="button-link">LICZNIKI - GAZ</div>
			</a><a href=" Division/divisionRegister.html">
				<div class="button-link">PODZIAŁ</div>
			</a>
		</div>

		<div class="col_two">
			<h2>Sprawdź/Edytuj</h2>
			<a href=" Payment/paymentList.html">
				<div class="button-link">OPŁATA</div>
			</a> <a href=" Reading/readingEnergyList.html">
				<div class="button-link">ODCZYT - ENERGIA</div>
			</a> <a href=" Reading/readingWaterList.html">
				<div class="button-link">ODCZYT - WODA</div>
			</a> <a href=" Reading/readingGasList.html">
				<div class="button-link">ODCZYT - GAZ</div>
			</a> <a href=" Invoice/invoiceList.html">
				<div class="button-link">FAKTURY</div>
			</a> <a href=" Apartment/apartmentList.html">
				<div class="button-link">MIESZKANIA</div>
			</a> <a href=" Tenant/tenantList.html">
				<div class="button-link">NAJEMCY</div>
			</a> <a href=" Meter/meterWaterList.html">
				<div class="button-link">LICZNIKI - WODA</div>
			</a> <a href="Meter/meterEnergyList.html">
				<div class="button-link">LICZNIKI - ENERGIA</div>
			</a> <a href="Meter/meterGasList.html">
				<div class="button-link">LICZNIKI - GAZ</div>
			</a><a href="Division/divisionList.html">
				<div class="button-link">PODZIAŁ</div>
			</a>
		</div>
		<br clear="all"> <a href="../index.html">Powrót do strony
			powitalnej</a>
	</div>
</body>
</html>