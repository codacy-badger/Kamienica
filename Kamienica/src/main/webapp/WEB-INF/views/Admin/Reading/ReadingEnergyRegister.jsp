<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Odczytów Energii</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Rejestracja Odczytów Energii</h1>
		<hr>
		<a href="../home.html">Strona Główna</a> <br>
		<c:out value="${model.error}" />

		<c:if test="${!empty readingForm.currentReadings}">
			<c:url var="odczyty" value="/Admin/Reading/readingEnergySave.html" />
			<form:form modelAttribute="readingForm" method="post"
				action="${odczyty}">
				<table width="50%">
					<tr>
						<th>Data</th>
						<td><input name="date" id="date" type="date" required
							value="${model.date}" min="${model.oldDate}" /></td>
					</tr>
					<tr>
						<th>opis licznika</th>
						<th>Wartosc odczytu</th>
					</tr>
					<c:forEach items="${readingForm.currentReadings}" varStatus="i"
						var="reading">
						<tr>
							<td><form:label path="currentReadings[${i.index}].value">${reading.meter.description}</form:label>
								<input name="currentReadings[${i.index}].meter"
								value="${reading.meter.id}" type="hidden" /></td>

							<td><input type="number" step="any" min="${reading.value}"
								name="currentReadings[${i.index}].value"
								value="${reading.value}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" align="center"><input type="submit"
							value="Zapisz" /></td>
				</table>
			</form:form>
		</c:if>
	</div>
</body>
</html>