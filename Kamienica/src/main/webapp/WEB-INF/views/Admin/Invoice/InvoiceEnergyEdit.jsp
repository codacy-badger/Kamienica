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
<title>Edycja Faktur</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Edycja Faktury Energii</div>
		<br> <br>
		<c:url var="fakturaEdytuj" value="invoiceEnergyOverwrite.html" />
		<form:form id="fakturaEdytuj" modelAttribute="invoice" method="post"
			action="${fakturaEdytuj}">
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="id">ID</form:label></td>
					<td><form:input path="id" readonly="true" /></td>
					<td><form:errors path="id" cssClass="error" /></td>
				<tr>
					<td><form:label path="serialNumber">Numer Faktury</form:label></td>
					<td><form:input path="serialNumber" /></td>
					<td><form:errors path="serialNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="description">Opis Faktury</form:label></td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="date">Data Faktury</form:label></td>
					<td><form:input path="date" type="date" /></td>
					<td><form:errors path="date" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="totalAmount">Wartość Faktury</form:label></td>
					<td><form:input path="totalAmount" /></td>
					<td><form:errors path="totalAmount" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Zapisz Zmiany" /></td>
				</tr>
			</table>

		</form:form>
		<br> <br> <a href="../home.html">Strona Główna</a>
	</center>
</body>
</html>