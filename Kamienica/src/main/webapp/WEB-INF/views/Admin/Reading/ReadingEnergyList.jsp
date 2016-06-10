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
<title>Odczyty Energii</title>
</head>
<body>

<mytags:navbarAdmin />

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Odczyty Energii</h1>
				<a href="readingEnergyRegister.html">Dodaj Odczyty Energii</a><br>
			</div>
		</div>
		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>
		<div class='row'>
			<c:if test="${!empty reading}">
				<table class='table table-stripped table-hover' width="100%">
					<tr>
						<th>Id</th>
						<th>Data</th>
						<th>Wartość Odczytu</th>
						<th>Jednostka</th>
						<th>Licznik</th>
						<th>Edytuj</th>
						<th>Usuń</th>

					</tr>
					<c:forEach items="${reading}" var="readingEnergy">
						<tr>
							<td><c:out value="${readingEnergy.id}" /></td>
							<td><c:out value="${readingEnergy.readingDate}" /></td>
							<td><c:out value="${readingEnergy.value}" /></td>
							<td><c:out value="${readingEnergy.unit}" /></td>
							<td><c:out value="${readingEnergy.meter.description}" /></td>
							<td><a
								href="<c:url value="readingEnergyEdit.html?date=${readingEnergy.readingDate}" />"
								role="button" class="btn-xs btn-warning">Edytuj</a></td>
							<td><a
								href="<c:url value="readingEnergyDelete.html?date=${readingEnergy.readingDate}"  />"
								onclick="return confirm('UWAGA!!! Usunięte zostaną wszystkie odczyty z tą datą!\n Na pewno usunąć?')"
								role="button" class="btn-xs btn-danger">Usuń</a></td>

						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>