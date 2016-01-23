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
<title>Edycja Licznika Wody</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Edycja Licznika Wody</div>
		<br> <br>
		<c:url var="licznikWodaEdytuj" value="/Admin/Meter/meterWaterOverwrite.html" />
		<form:form id="licznikWodaEdytuj" modelAttribute="meter" method="post"
			action="${licznikWodaEdytuj}">
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="id">ID</form:label></td>
					<td><form:input path="id" readonly="true" /></td>
					<td><form:errors path="id" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="description">description</form:label></td>
					<td><form:input path="description" /></td>
					<td><form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="serialNumber">serialNumber</form:label></td>
					<td><form:input path="serialNumber" /></td>
					<td><form:errors path="serialNumber" cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="unit">jendostka</form:label></td>
					<td><form:input path="unit" /></td>
					<td><form:errors path="unit" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="apartment">apartment</form:label></td>
					<td><form:select path="apartment" items="${model.apartment}"
							itemValue="id" itemLabel="description" /></td>
					<td><form:errors path="apartment" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="isWarmWater">czyCieplaWoda</form:label></td>
					<td><form:radiobuttons path="isWarmWater"
							items="${model.tf}" /></td>
					<td><form:errors path="isWarmWater" cssClass="error" /></td>
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