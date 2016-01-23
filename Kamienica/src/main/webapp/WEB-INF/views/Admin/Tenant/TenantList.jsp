<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Najemców</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Lista Najemców</h1>
		<hr>
		<a href="../home.html">Strona Główna</a><br> <a
			href="tenantRegister.html">Dodaj najemcę</a> <br> <br>
		<c:if test="${!empty tenant}">
			<table width="100%">
				<tr>
					<th>Id</th>
					<th>Imie</th>
					<th>Nazwisko</th>
					<th>E-mail</th>
					<th>Telefon</th>
					<th>Mieszkanie</th>
					<th>Data Wprowadzenia</th>
					<th>Prawa</th>
					<th>Status</th>
					<th>Hasło</th>
					<th>Usuń</th>
					<th>Edytuj</th>
				</tr>
				<c:forEach items="${tenant}" var="tenant">
					<tr>
						<td><c:out value="${tenant.id}" /></td>
						<td><c:out value="${tenant.firstName}" /></td>
						<td><c:out value="${tenant.lastName}" /></td>
						<td><c:out value="${tenant.email}" /></td>
						<td><c:out value="${tenant.phone}" /></td>
						<td><c:out value="${tenant.apartment.description}" /></td>
						<td><c:out value="${tenant.movementDate}" /></td>
						<td><c:out value="${tenant.role}" /></td>
						<td><c:out value="${tenant.status}" /></td>
						<td><c:out value="${tenant.password}" /></td>
						<td><a
							href="<c:url value="/Admin/Tenant/tenantDelete.html?id=${tenant.id}"  />"
							onclick="return confirm('Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="/Admin/Tenant/tenantEdit.html?id=${tenant.id}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>