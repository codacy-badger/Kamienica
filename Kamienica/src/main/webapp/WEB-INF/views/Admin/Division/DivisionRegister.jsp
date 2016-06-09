<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Algorytm Podziału</title>
</head>
<body>
	<mytags:navbarAdmin />

	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1>Algorytm Podziału</h1>
				<p>
			</div>
		</div>

		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>


		<div class='row'>
			<c:url var="division" value="/Admin/Division/divisionSave.html" />
			<form:form modelAttribute="divisionForm" method="post"
				action="${division}">

				<table class='table table-bordered table-condensed'>
					<tr>
						<td><form:label path="date">
								<b>Data</b>
							</form:label> <form:input path="date" type="date" /></td>
						<c:forEach items="${model.apartment}" var="m">
							<th><c:out value="${m.description}" /></th>
						</c:forEach>
					<tr>
						<c:forEach items="${model.tenantList}" var="tenantList">
							<tr>
								<th style="height: 12px"><c:out
										value="${tenantList.firstName} ${tenantList.lastName}" /></th>
								<c:forEach items="${divisionForm.divisionList}" varStatus="i"
									var="l">


									<c:if test="${tenantList.id.equals(l.tenant.id)}">
										<input name="divisionList[${i.index}].tenant"
											value="${l.tenant.id}" type="hidden" />
										<input name="divisionList[${i.index}].apartment"
											value="${l.apartment.id}" type="hidden" />
										<td><input type="number" step="any" max="1.0" min="0"
											name="divisionList[${i.index}].divisionValue"
											value="${l.divisionValue}"></td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
				</table>
				<br>
				<input type="submit" value="Zapisz" />
			</form:form>
		</div>

	</div>

</body>
</html>