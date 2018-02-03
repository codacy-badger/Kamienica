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
<title>Algorytm Podziału</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Formularz Podziału</h1>
					</div>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>
			
			<c:if test="${empty model.error}">


				<div class='row'>
					<c:url var="division" value="/Admin/Division/divisionSave.html" />
					<form:form modelAttribute="divisionForm" method="post"
						action="${division}">

						<table class='table  table-condensed'>
							<thead>
								<tr>
									<th><form:label path="date">
										Data
									</form:label> <form:input path="date" type="date" /></th>
									<c:forEach items="${divisionForm.apartments}" var="m">
										<th><c:out value="${m.description}" /></th>
									</c:forEach>
								</tr>
							</thead>
							<c:forEach items="${divisionForm.tenants}" var="tenantList">
								<tr>
									<th><c:out
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
						<div class="form-group">
							<button type="submit" class="btn btn-default">Zapisz</button>
							<button class="btn btn-default" type="reset">Resetuj</button>
						</div>
					</form:form>
				</div>

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