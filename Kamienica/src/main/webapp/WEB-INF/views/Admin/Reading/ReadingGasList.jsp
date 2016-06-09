<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
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
<title>Odczyty Gazu</title>
</head>
<body>

<mytags:navbarAdmin />

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Odczyty Gazu</h1>
				<a href="readingGasRegister.html">Dodaj Odczyty Gazu</a><br>
			</div>
		</div>

		<div class='row'>
			<c:if test="${!empty reading}">
				<table  class='table table-stripped table-hover' width= "100%">
					<tr>
						<th>Id</th>
						<th>Data</th>
						<th>Wartość Odczytu</th>
						<th>Jednostka</th>
						<th>Licznik</th>
						<th>Edytuj</th>
						<th>Usuń</th>
						
					</tr>
					<c:forEach items="${reading}" var="reading">
						<tr>
							<td><c:out value="${reading.id}" /></td>
							<td><c:out value="${reading.readingDate}" /></td>
							<td><c:out value="${reading.value}" /></td>
							<td><c:out value="${reading.unit}" /></td>
							<td><c:out value="${reading.meter.description}" /></td>
							<td><a
								href='<c:url value="/Admin/Reading/readingGasEdit.html?date=${reading.readingDate}" />' role="button"
								class="btn-xs btn-warning">Edytuj</a></td>
							<td><a
								href='<c:url value="/Admin/Reading/readingGasDelete.html?date=${reading.readingDate}"  />' role="button"
								class="btn-xs btn-danger"
								onclick="return confirm('UWAGA! Usunięte zostaną wszystkie odczyty z tą datą!\n Na pewno usunąć?')">Usuń</a></td>
							
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>