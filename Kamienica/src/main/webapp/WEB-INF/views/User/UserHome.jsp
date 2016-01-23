<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.ddiv">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kamienica - Strona główna (USER)</title>

<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<!-- <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700">-->
</head>
<body>

	<div id="mainWrapper">

		<br>
		<div>

			<div class="head">
				<h1>Kamienica - Strona użytkownika</h1>
			</div>
			<hr>

			<div class="list">
				<ul>
					<li>Użytkownik: ${model.user.name }</li>
					<li>Mieszkanie nr. ${model.user.apartment.apartmentNumber}
						"${model.user.apartment.description}"</li>
				</ul>

			</div>

			<br>


			<div>

				<div class="button-link">
					<a href="userPayment.html">OPŁATY</a>
				</div>
				<br>

				<div class="button-link">
					<a href="userReadings.html?media=energy">ODCZYTY ENERGII</a>
				</div>
				<br>

				<div class="button-link">
					<a href="userReadings.html?media=water">ODCZYTY WODY</a>
				</div>


				<br> <a href="userReadings.html?media=gas">
					<div class="button-link">ODCZYTY GAZU</div>
				</a>
			</div>
		</div>
		<p align="right">
			<a href="userPassword.html">Zmień hasło</a><br> <a
				href="../logout.html">Wyloguj</a><br> <a
				href="../index.html">Powrót do Strony głównej</a>
		</p>

	</div>
</body>
</body>
</html>