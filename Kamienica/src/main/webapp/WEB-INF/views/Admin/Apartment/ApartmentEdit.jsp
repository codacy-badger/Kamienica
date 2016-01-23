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
<title>Edycja Mieszkania</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Edycja Mieszkania</div>
		<br> <br>
		<c:url var="mieszkanieEdytuj"
			value="/Admin/Apartment/apartmentOverwrite.html" />
		<form:form id="mieszkanieEdytuj" modelAttribute="apartment"
			method="post" action="${mieszkanieEdytuj}">
			<table width="400px" height="150px">
				<tr>
					<td><a title="to jest ID"><form:label path="id">ID</form:label></a></td>
					<td><form:input path="id" readonly="true" /></td>
					<td><form:errors path="id" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="apartmentNumber">Numer Mieszkania</form:label></td>
					<td><form:input path="apartmentNumber" /></td>
					<td><form:errors path="apartmentNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="intercom">Domofon</form:label></td>
					<td><form:input path="intercom" /></td>
					<td><form:errors path="intercom" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="description">Opis Mieszkania</form:label></td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
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