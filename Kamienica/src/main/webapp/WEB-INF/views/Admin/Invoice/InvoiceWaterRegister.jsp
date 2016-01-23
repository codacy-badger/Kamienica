<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
<title>Rejestracja Nowej Faktury Wody</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Rejestracja Nowej
			Faktury Wody</div>
		<br> <br>

		<c:url var="rejestrujFakture" value="/Admin/Invoice/invoiceWaterSave.html" />
		<form:form id="rejestrujFakture" modelAttribute="invoice"
			method="post" action="${rejestrujFakture}">
			<table width="400px" height="150px">

				<tr>
					<td><form:label path="serialNumber">Numer Faktury</form:label></td>
					<td><form:input path="serialNumber" required="true" /></td>
					<td><form:errors path="serialNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="description">Opis Faktury</form:label></td>
					<td><form:input path="description"
							value="${model.description }" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="date">Data Faktury</form:label></td>
					<td><form:input type="date" path="date" value="${model.date }" /></td>
					<td><form:errors path="date" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="totalAmount">Wartosc Faktury</form:label></td>
					<td><form:input path="totalAmount" min="0" required="true" /></td>
					<td><form:errors path="totalAmount" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Zapisz" /></td>
				</tr>
			</table>

		</form:form>
		<br> <br> <a href="../home.html">Strona Główna</a>

	</center>
</body>
</html>