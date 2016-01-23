<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Odczyty Wody</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Odczyty Wody</h1>
		<hr>
		<a href="../home.html">Strona Główna</a> <br> <a
			href="readingWaterRegister.html">Dodaj
			Odczyty Wody</a>
		<c:if test="${!empty reading}">
			<table width="100%">
				<tr>
					<th>Id</th>
					<th>Data</th>
					<th>Wartość Odczytu</th>
					<th>Jednostka</th>
					<th>Licznik</th>
					<th>Usuń</th>
					<th>Edytuj</th>
				</tr>
				<c:forEach items="${reading}" var="reading">
					<tr>
						<td><c:out value="${reading.id}" /></td>
						<td><c:out value="${reading.readingDate}" /></td>
						<td><c:out value="${reading.value}" /></td>
						<td><c:out value="${reading.unit}" /></td>
						<td><c:out value="${reading.meter.description}" /></td>
						<td><a
							href="<c:url value="/Admin/Reading/readingWaterDelete.html?date=${reading.readingDate}"  />"
							onclick="return confirm('UWAGA!!! Usunięte zostaną wszystkie odczyty z tą datą!\n Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="/Admin/Reading/readingWaterEdit.html?date=${reading.readingDate}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br>
	</div>
</body>
</html>