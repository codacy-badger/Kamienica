<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Licznika Energii</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Rejestracja Nowego
			Licznika Energii</div>
		<br> <br>

		<c:if test="${!empty model.error}">
			<p align="center" style="color: red">${model.error}</p>
			<br>
			<br>
		</c:if>

		<c:url var="rejestrujLicznikEnergia"
			value="/Admin/Meter/meterEnergySave.html" />
		<form:form id="rejestrujLicznikEnergia" modelAttribute="meter"
			method="post" action="${rejestrujLicznikEnergia}">
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="description">opisLicznika</form:label></td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="serialNumber">nrSeryjnyLicznika</form:label></td>
					<td><form:input path="serialNumber" /></td>
					<td><form:errors path="serialNumber" cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="unit">jendostka</form:label></td>
					<td><form:input path="unit" /></td>
					<td><form:errors path="unit" cssClass="error" /></td>
				</tr>
				<tr>

					<td><form:label path="apartment">mieszkanie</form:label></td>
					<td><form:select path="apartment" items="${model.apartment}"
							itemValue="id" itemLabel="description" /></td>
					<td><form:errors path="apartment" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Zapisz"
						${ !empty model.error ? 'disabled="disabled"' : ''} /></td>
				</tr>
			</table>
		</form:form>
		<br> <br> <a href="../home.html">Strona Główna</a>
	</center>
</body>
</html>