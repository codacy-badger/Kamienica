<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.ddiv">
<html>
<head>

<title>Kamienica - Strona główna (USER)</title>
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
<!-- <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700">-->
</head>
<body>

	<mytags:userNavbar />


	<div class="container myContainer">
		<div class="row"></div>
		<div class="row">
			<div class="well">
				<h1>Kamienica - Strona użytkownika</h1>
				<p>Użytkownik: ${model.user.name }</p>
				<p>Mieszkanie nr. ${model.user.apartment.apartmentNumber}
					"${model.user.apartment.description}"</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">
				<div class="list-group">
					<p class="list-group-item active">Sprawdź Dane</p>
					<a href="userPayment.html" class="list-group-item">OPŁATA</a> <a
						href="userReadings.html?media=energy" class="list-group-item">ODCZYT
						- ENERGIA</a> <a href="userReadings.html?media=water"
						class="list-group-item">ODCZYT - WODA</a> <a
						href="userReadings.html?media=gas" class="list-group-item">ODCZYT
						- GAZ</a>
				</div>
			</div>


		</div>
	</div>
</body>
</body>
</html>