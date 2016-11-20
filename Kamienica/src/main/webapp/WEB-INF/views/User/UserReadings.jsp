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


<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Odczyty</title>
</head>
<body class='userBody'>

	<mytags:userNavbar />

	<div class="container myContainer">
		<div class='row well'>
			<h2>Odczyt: ${model.media}</h2>
		
		</div>


		<c:if test="${!empty model.readings}">
			<div class="row">

				<table class="table table-stripped table-hover">
					<thead>
						<tr>

							<th>Data</th>
							<th>Wartość Odczytu</th>
							<th>Jednostka</th>
							<th>Licznik</th>
						</tr>
					</thead>
					<c:forEach items="${model.readings}" var="reading">
						<tr>

							<td><c:out value="${reading.readingDate}" /></td>
							<td><c:out value="${reading.value}" /></td>
							<td><c:out value="${reading.unit}" /></td>
							<td><c:out value="${reading.meter.description}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>


		</c:if>

	</div>
</body>
</html>