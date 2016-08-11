<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>
	<mytags:navbarAdmin />


	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Rejestracja Opłat</h1>
			</div>
		</div>
		<div class='row'>
			<c:if test="${!empty model.error}">
				<div class="alert alert-info">${model.error}</div>
			</c:if>
		</div>
		<c:if test="${empty model.error}">
			<div class='row'>
				<c:url var="paymentSave" value="/Admin/Payment/paymentSave.html" />
				<form:form id="paymentSave" modelAttribute="model" method="post"
					action="${paymentSave}">

					<div class='row'>
						<div class="col-md-4">
							<c:if test="${empty model.energy}">
								<div class="alert alert-info">Brak danych dla energii</div>
							</c:if>
							<c:if test="${!empty model.energy}">
								<table class="table table-hover table-condensed">
									<tr>
										<th colspan="4">Energia</th>
									</tr>
									<tr>
										<th></th>
										<th>Data Faktury</th>
										<th>Wartość</th>
										<th>Data Odczytu</th>
									</tr>
									<c:forEach items="${model.energy }" var="energy">
										<tr>

											<td><input type="radio" name="energy"
												value="${energy.id }" checked="checked"></td>
											<td>${energy.date }</td>
											<td>${energy.totalAmount }</td>
											<td>${energy.baseReading.readingDate}</td>
										<tr>
									</c:forEach>
								</table>
							</c:if>
						</div>

						<div class="col-md-4">
							<c:if test="${empty model.water}">
								<div class="alert alert-info">Brak danych dla wody</div>
							</c:if>
							<c:if test="${!empty model.water}">
								<table class="table table-hover table-condensed">
									<tr>
										<th colspan="4">Woda</th>
									</tr>
									<tr>
										<th></th>
										<th>Data Faktury</th>
										<th>Wartość</th>
										<th>Data Odczytu</th>
									</tr>
									<c:forEach items="${model.water }" var="water">
										<tr>

											<td><input type="radio" name="water"
												value="${water.id }" checked="checked"></td>
											<td>${water.date }</td>
											<td>${water.totalAmount }</td>
											<td>${water.baseReading.readingDate}</td>
										<tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
						<div class="col-md-4">
							<c:if test="${empty model.gas}">
								<div class="alert alert-info">Brak danych dla gazu</div>
							</c:if>
							<c:if test="${!empty model.gas}">
								<table class="table table-hover table-condensed">
									<tr>
										<th colspan="4">Gaz</th>
									</tr>
									<tr>
										<th></th>
										<th>Data Faktury</th>
										<th>Wartość</th>
										<th>Data Odczytu</th>
									</tr>
									<c:forEach items="${model.gas }" var="gas">
										<tr>

											<td><input type="radio" name="gas" value="${gas.id }"
												checked="checked"></td>
											<td>${gas.date }</td>
											<td>${gas.totalAmount }</td>
											<td>${gas.baseReading.readingDate}</td>
										<tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
					</div>



					<input type="submit" value="Wprowadź opłaty">
				</form:form>
			</div>
		</c:if>
	</div>
</body>
</html>