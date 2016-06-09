<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>



<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liczniki Wody</title>
</head>
<body>
	<mytags:navbarAdmin/>
	

	<div class="container">
		<div class='row'>
			<div class='jumbotron'>
				<h1>Liczniki Wody</h1>
				<a href="meterWaterRegister.html">Dodaj Licznik Wody</a>

			</div>
		</div>
		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>


		<c:if test="${!empty meter}">
			<table class='table table-stripped table-hover' width="100%">
				<tr>
					<th>Id</th>
					<th>Numer Seryjny</th>
					<th>Opis</th>
					<th>Jednostka</th>
					<th>Mieszkanie</th>
					<th>Ciepła</th>
					<th>Edytuj</th>
					<th>Usuń</th>

				</tr>
				<c:forEach items="${meter}" var="meter">
					<tr>
						<td><c:out value="${meter.id}" /></td>
						<td><c:out value="${meter.serialNumber}" /></td>
						<td><c:out value="${meter.description}" /></td>
						<td><c:out value="${meter.unit}" /></td>
						<td><c:out value="${meter.apartment.description}" /></td>
						<td><c:out value="${meter.isWarmWater}" /></td>
						<td><a
							href='<c:url value="/Admin/Meter/meterWaterEdit.html?id=${meter.id}"/>'
							role="button" class="btn-xs btn-warning">Edytuj</a></td>
						<td><a
							href='<c:url value="/Admin/Meter/meterWaterDelete.html?id=${meter.id}"  />'
							onclick="return confirm('Na pewno usunąć?')" role="button"
							class="btn-xs btn-danger">Usuń</a></td>

					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>