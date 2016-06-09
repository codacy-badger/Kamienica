<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Edycja Faktur</title>
</head>
<body>
		<mytags:navbarAdmin />


	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Edycja Faktury Wody</h1>
				<a href="../home.html">Strona Główna</a>
			</div>
		</div>

		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>

		<c:if test="${empty model.error}">
			<div class='row'>
				<c:url var="fakturaEdytuj" value="invoiceWaterOverwrite.html" />
				<form:form id="fakturaEdytuj" modelAttribute="invoice" method="post"
					action="${fakturaEdytuj}">

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="id">ID</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="id" readonly="true" />
							<form:errors path="id" cssClass="error" />
						</div>
					</div>


					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="serialNumber">Numer Faktury</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="serialNumber" name="serialNumber" />
							<form:errors path="serialNumber" class="error" />
						</div>
					</div>



					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="description">Opis Faktury</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="description" name="description"
								value="${model.description }" />
							<form:errors path="description" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="date">Data Faktury</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input type='date' path="date" name="date"
								value="${model.date }" />
							<form:errors path="date" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="totalAmount">Wartosc Faktury</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="totalAmount" name="totalAmount" />
							<form:errors path="totalAmount" class="error" />
						</div>
					</div>


					<div class='row'>
						<input type="submit" class='btn btn-default' value="Zapisz" />
					</div>

				</form:form>
			</div>
		</c:if>
	</div>
</body>
</html>