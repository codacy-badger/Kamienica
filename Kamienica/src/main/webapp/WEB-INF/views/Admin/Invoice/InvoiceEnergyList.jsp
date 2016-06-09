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
<title>Lista Faktur</title>
</head>
<body>
	<mytags:navbarAdmin />

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Lista Faktur Energii</h1>
				<a href="invoiceEnergyRegister.html">Dodaj nową fakturę</a>
			</div>
		</div>
		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>
		<div class='row'>

			<c:if test="${!empty invoice}">
				<table class='table table-stripped table-hover' width="100%">
					<tr>
						<th>Id</th>
						<th>Nr Faktury</th>
						<th>Opis</th>
						<th>Data</th>
						<th>Wartość</th>
						<th>Edytuj</th>
						<th>Usuń</th>
					</tr>
					<c:forEach items="${invoice}" var="invoice">
						<tr>
							<td><c:out value="${invoice.id}" /></td>
							<td><c:out value="${invoice.serialNumber}" /></td>
							<td><c:out value="${invoice.description}" /></td>
							<td><c:out value="${invoice.date}" /></td>
							<td><c:out value="${invoice.totalAmount}" /></td>
							<td><a
								href="<c:url value="/Admin/Invoice/invoiceEnergyEdit.html?id=${invoice.id}" />"
								role="button" class="btn-xs btn-warning">Edytuj</a></td>
							<td><a
								href="<c:url value="/Admin/Invoice/invoiceEnergyDelete.html?id=${invoice.id}"  />"
								onclick="return confirm('Na pewno usunąć?')" role="button"
								class="btn-xs btn-danger">Usuń</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>