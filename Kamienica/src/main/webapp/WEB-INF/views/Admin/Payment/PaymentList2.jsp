<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Payment List</title>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">


<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>


<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<mytags:navbarAdmin />

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Lista Opłat: ${media}</h1>
			</div>
		</div>

		<c:if test="${!empty payment}">
			<table class='table table-stripped table-hover' width="600px">
				<tr>
					<th>Id</th>
					<th>Wartość Opłaty</th>
					<th>Data Rachunku</th>
					<th>Najemca</th>
					<th>Data Faktury</th>
					<th>Numer Faktury</th>
				</tr>
				<c:forEach items="${payment}" var="p">
					<tr>
						<td><c:out value="${p.id}" /></td>
						<td><c:out value="${p.paymentAmount}" /></td>
						<td><c:out value="${p.paymentDate}" /></td>
						<td><c:out value="${p.tenant.firstName}" /> <c:out
								value="${p.tenant.lastName}" /></td>
						<td><c:out value="${p.invoice.date}" /></td>
						<td><c:out value="${p.invoice.serialNumber}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>