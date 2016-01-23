<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Odczyty Energii</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Odczyty Energii</h1>
		<hr>
		<a href="../home.html">Strona Główna</a> <br> <a
			href="readingEnergyRegister.html">Dodaj Odczyty Energii</a><br>
		<br>
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
				<c:forEach items="${reading}" var="readingEnergy">
					<tr>
						<td><c:out value="${readingEnergy.id}" /></td>
						<td><c:out value="${readingEnergy.readingDate}" /></td>
						<td><c:out value="${readingEnergy.value}" /></td>
						<td><c:out value="${readingEnergy.unit}" /></td>
						<td><c:out value="${readingEnergy.meter.description}" /></td>
						<td><a
							href="<c:url value="readingEnergyDelete.html?date=${readingEnergy.readingDate}"  />"
							onclick="return confirm('UWAGA!!! Usunięte zostaną wszystkie odczyty z tą datą!\n Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="readingEnergyEdit.html?date=${readingEnergy.readingDate}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>