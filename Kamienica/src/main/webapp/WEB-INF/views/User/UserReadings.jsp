<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<title>Odczyty</title>
</head>
<body>
	<div id="mainWrapper">

		<h1>Odczyty: ${model.media}</h1>
		<hr>
		<c:if test="${!empty model.readings}">
			<div class="content">
				<a href="userHome.html">Strona Główna</a> <br>
				<br>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Data</th>
						<th>Wartość Odczytu</th>
						<th>Jednostka</th>
						<th>Licznik</th>
					</tr>
					<c:forEach items="${model.readings}" var="reading">
						<tr>
							<td><c:out value="${reading.id}" /></td>
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