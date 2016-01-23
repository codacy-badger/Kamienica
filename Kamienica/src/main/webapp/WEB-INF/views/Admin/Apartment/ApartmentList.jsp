<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Mieszkań</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Lista Mieszkań</h1>
		<hr>  <a href="../home.html">Strona Główna</a><br> <a
			href="apartmentRegister.html">Dodaj Mieszkanie</a><br>
		<c:if test="${!empty apartment}">
			<div class="content">
				<table width="70%">
				<tr>
					<th>Id</th>
					<th>Nr Mieszkania</th>
					<th>Domofon</th>
					<th>Opis</th>
					<th>Usuń</th>
					<th>Edytuj</th>
				</tr>
				<c:forEach items="${apartment}" var="apartment">
					<tr>
						<td><c:out value="${apartment.id}" /></td> 
						<td><c:out value="${apartment.apartmentNumber}" /></td>
						<td><c:out value="${apartment.intercom}" /></td>
						<td><c:out value="${apartment.description}" /></td>
						<td><a
							href="<c:url value="/Admin/Apartment/apartmentDelete.html?id=${apartment.id}"  />"
							onclick="return confirm('Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="/Admin/Apartment/apartmentEdit.html?id=${apartment.id}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</c:if>
		<br> 
	</div>
</body>
</html>