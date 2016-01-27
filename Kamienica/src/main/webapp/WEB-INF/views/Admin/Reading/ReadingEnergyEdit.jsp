<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edycja Odczytów Gazu</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Edycja Odczytów
			energii</div>

		<br> <br>

		<c:url var="odczyty"
			value="/Admin/Reading/readingEnergyOverwrite.html" />
		<form:form modelAttribute="readingForm" method="post"
			action="${odczyty}">
			<table width="400px" height="150px">
				<tr>
					<td>Data</td>
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
						<td><input name="currentReadings[${i.index}].id"
							value="${reading.id}" type="hidden" /> <form:label
								path="currentReadings[${i.index}].value">${reading.meter.description}</form:label>
							<input name="currentReadings[${i.index}].meter"
							value="${reading.meter.id}" type="hidden" /></td>
						<td><input type="number" step="any"
							name="currentReadings[${i.index}].value" value="${reading.value}"
							min="${readingForm.previousReadings[i.index].value}" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6" align="center"><input type="submit"
						value="Zapisz" /></td>
				</tr>
			</table>
		</form:form>
		<br> <br> <br> <a href="../home.html">Strona Główna</a>
	</center>
</body>
</html>