<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liczniki Gazu</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Liczniki Gazu</h1>
		<hr>
		<a href="../home.html">Strona Główna</a> <br> <a
			href="meterGasRegister.html">Dodaj Licznik Energii</a>
			<br>
		<c:if test="${!empty meter}">
			<table width="100%">
				<tr>
					<th>Id</th>
					<th>Numer Seryjny</th>
					<th>Opis</th>
					<th>Jednostka</th>
					<th>Mieszkanie</th>
					<th>CWU</th>
					<th>Usuń</th>
					<th>Edytuj</th>
				</tr>
				<c:forEach items="${meter}" var="meter">
					<tr>
						<td><c:out value="${meter.id}" /></td>
						<td><c:out value="${meter.serialNumber}" /></td>
						<td><c:out value="${meter.description}" /></td>
						<td><c:out value="${meter.unit}" /></td>
						<td><c:out value="${meter.apartment.description}" /></td>
						<td><c:out value="${meter.cwu}" /></td>
						<td><a
							href="<c:url value="/Admin/Meter/meterGasDelete.html?id=${meter.id}"  />"
							onclick="return confirm('Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="/Admin/Meter/meterGasEdit.html?id=${meter.id}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>