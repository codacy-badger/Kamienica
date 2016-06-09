<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>



<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista Mieszkań</title>
</head>
<body>
<mytags:navbarAdmin/>

	<div class="container">
		<div class='row'>
			<div class='jumbotron'>
				<h1>Lista Mieszkań</h1>
				<a href="apartmentRegister.html">Dodaj Mieszkanie</a>
			</div>
		</div>

		<div class='row'>
			<c:if test="${!empty apartment}">
				<div>
					<table class='table table-stripped table-hover' width="100%">
						<tr>
							<th>Id</th>
							<th>Nr Mieszkania</th>
							<th>Domofon</th>
							<th>Opis</th>
							<th>Edytuj</th>
							<th>Usuń</th>

						</tr>
						<c:forEach items="${apartment}" var="apartment">
							<tr>
								<td><c:out value="${apartment.id}" /></td>
								<td><c:out value="${apartment.apartmentNumber}" /></td>
								<td><c:out value="${apartment.intercom}" /></td>
								<td><c:out value="${apartment.description}" /></td>
								<td><a
									href='<c:url value="/Admin/Apartment/apartmentEdit.html?id=${apartment.id}" />'
									role="button" class="btn-xs btn-warning">Edytuj</a></td>
								<td><a
									href='<c:url value="/Admin/Apartment/apartmentDelete.html?id=${apartment.id}"  />'
									onclick="return confirm('Na pewno usunąć?')" role="button"
									class="btn-xs btn-danger">Usuń</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>