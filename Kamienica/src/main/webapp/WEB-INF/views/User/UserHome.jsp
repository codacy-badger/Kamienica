<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.ddiv">
<html>
<head>

<title>Kamienica - Strona główna (USER)</title>
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
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body class='userBody'>

	<mytags:userNavbar />


	<div class="container myContainer">

		<div class="row">
			<div class="well">
				<h1>Kamienica - Strona użytkownika</h1>
				<p>
					<strong>Użytkownik:</strong> ${model.user.tenant.getFullName() }
				</p>
				<p>
					<b>Mieszkanie: </b> ${model.user.apartment.apartmentNumber}
					"${model.user.apartment.description}"
				</p>
			</div>
		</div>

		<c:if test="${!empty model.error}">
			<p align="center" class="alert alert-danger">${model.error}</p>
		</c:if>
		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">
				<div class="list-group">
					<p class="list-group-item list-group-header">Sprawdź Dane</p>
					<a href="userPayment.html" class="list-group-item">OPŁATA</a> <a
						href="userReadings.html?media=energy" class="list-group-item">ODCZYT
						- ENERGIA</a> <a href="userReadings.html?media=water"
						class="list-group-item">ODCZYT - WODA</a> <a
						href="userReadings.html?media=gas" class="list-group-item">ODCZYT
						- GAZ</a>
				</div>
			</div>


		</div>
		<div class='row'>
			<div class="row">
				<c:if test="${!empty model.msg}">
					<p align="center" class="alert alert-success">${model.msg}</p>
				</c:if>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</body>
</html>