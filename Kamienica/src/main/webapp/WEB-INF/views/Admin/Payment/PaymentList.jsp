<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Payment List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/sb-admin-2.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">

<!-- MetisMenu CSS -->
<link href="<c:url value='/static/css/metisMenu.min.css' />"
	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Lista Opłat: ${media}</h1>
				</div>
			</div>

			<c:if test="${!empty payment}">
				<table class='table table-stripped table-hover' >
					<thead>
						<tr>
							<th>Data Rachunku</th>
							<th>Wartość Opłaty</th>
							<th>Najemca</th>
							<th>Data Faktury</th>
							<th>Numer Faktury</th>
						</tr>
					</thead>
					<c:forEach items="${payment}" var="p">
						<tr>
							<td><c:out value="${p.paymentDate}" /></td>
							<td><c:out value="${p.paymentAmount}" /></td>
							<td><c:out value="${p.tenant.firstName}" /> <c:out
									value="${p.tenant.lastName}" /></td>
							<td><c:out value="${p.invoice.date}" /></td>
							<td><c:out value="${p.invoice.serialNumber}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>
</body>
</html>