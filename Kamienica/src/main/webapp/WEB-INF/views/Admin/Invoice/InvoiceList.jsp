<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Faktur</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Wybierz Rodziaj Faktury</h1>
		<hr>
		<br>

		<div class="content">

			<div class="button-link">
				<a href="invoiceGasList.html">GAZ</a>
			</div>
			<br>

			<div class="button-link">
				<a href="invoiceWaterList.html">WODA</a>
			</div>

			<br>
			<div class="button-link">
				<a href="invoiceEnergyList.html">ENERGIA</a>
			</div>
			<br>
			<div class="button-link">
				<a href="../home.html">STRONA GŁÓWNA</a>
			</div>

		</div>
	</div>
</body>
</html>