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
<title>Edycja Najemcy</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Edycja Najemcy</div>
		<br> <br>
		<c:url var="najemcaEdytuj" value="/Admin/Tenant/tenantOverwrite.html" />
		<form:form id="najemcaEdytuj" modelAttribute="tenant" method="post"
			action="${najemcaEdytuj}">
			<table width="500px" height="150px">
				<tr>
					<td><form:label path="id">ID</form:label></td>
					<td><form:input path="id" readonly="true" /></td>
					<td><form:errors path="id" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="firstName">Imię</form:label></td>
					<td><form:input path="firstName" /></td>
					<td><form:errors path="firstName" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Nazwisko</form:label></td>
					<td><form:input path="lastName" /></td>
					<td><form:errors path="lastName" cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="email">Email</form:label></td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="phone">Telefon</form:label></td>
					<td><form:input path="phone" /></td>
					<td><form:errors path="phone" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="role">Prawa</form:label></td>
					<td><form:select path="role" items="${model.role}" /></td>
					<td><form:errors path="role" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="status">Status</form:label></td>
					<td><form:select path="status" items="${model.status}" /></td>
					<td><form:errors path="status" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="apartment">Mieszkanie</form:label></td>
					<td><form:select path="apartment" items="${model.apartment}"
							itemValue="id" itemLabel="description" /></td>
							<td><form:errors path="apartment" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="movementDate">Data Wprowadzenia</form:label></td>
					<td><form:input path="movementDate" type="date" /></td>
					<td><form:errors path="movementDate" cssClass="error" /></td>
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