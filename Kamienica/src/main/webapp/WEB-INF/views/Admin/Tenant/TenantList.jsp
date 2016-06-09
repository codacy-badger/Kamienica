<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
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
<title>Lista Najemców</title>
</head>
<body>
<mytags:navbarAdmin />

	<div id="container">
		<div class='row'>
			<h1>Lista Najemców</h1>
		</div>
		<div class='row'>
			<a href="tenantRegister.html">Dodaj najemcę</a>
		</div>
		<div class=row>
			<c:if test="${!empty tenant}">
				<div class="col-md-12">
					<table class='table table-stripped table-hover' width="100%">
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
							<th>Edytuj</th>
							<th>Usuń</th>
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
									href='<c:url value="/Admin/Tenant/tenantEdit.html?id=${tenant.id}" />'
									role="button" class="btn-xs btn-warning">Edytuj</a></td>
								<td><a
									href='<c:url value="/Admin/Tenant/tenantDelete.html?id=${tenant.id}"   />'
									onclick="return confirm('Na pewno usunąć?')" role="button" class="btn-xs btn-danger">Usuń</a></td>

							</tr>
						</c:forEach>
						
					</table>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>