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
<title>Algorytm Podzialu</title>
</head>
<body>
	<mytags:navbarAdmin/>

	<div class="container">
		<div class='row'>
			<div class='jumbotron'>
				<h1>Algorytm Podzialu</h1>
				<a href="divisionRegister.html">Wprowadź nowy podział</a>
			</div>
		</div>

		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>

		<div class='row'>
			<table class='table table-stripped table-hover' style="width: 100%">
				<tr>
					<th></th>
					<c:forEach items="${model.apartment}" var="m">
						<th><c:out value="${m.description}" /></th>
					</c:forEach>
				</tr>

				<c:forEach items="${model.tenantList}" var="tenantList">
					<tr>
						<th><c:out
								value="${tenantList.firstName} ${tenantList.lastName}" /></th>
						<c:forEach items="${model.divisionForm.divisionList}"
							varStatus="i" var="l">
							<c:if test="${tenantList.id.equals(l.tenant.id)}">
								<td><c:out value="${l.divisionValue}" /></td>
							</c:if>
						</c:forEach>
					</tr>

				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>