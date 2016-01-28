<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link class="row"  href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row"  href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">


	<script type="text/javascript"
		src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>


	<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

	<script type="text/javascript"
		src="<c:url value='/static/js/jquery.validate.js' />"></script>

	<script type="text/javascript"
		src="<c:url value='/static/js/bootstrap.js' />"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Rejestracja Najemcy</title>
</head>
<body>
	<c:url var="rejestrujNajemca" value="/Admin/Tenant/tenantSave.html" />
	<div class="container">

		<div class="row">
			<h1>Rejestracja Najemcy</h1>
			<hr>
			<a href="../home.html">Strona Główna</a>
		</div>



		<form:form class="myForm" id="rejestrujNajemca"
			modelAttribute="tenant" method="post" action="${rejestrujNajemca}">
			<div class="row">
				<p align="center" class="error">${model.error}</p>
			</div>


			<div class="row">
				<form:label path="firstName">Imię</form:label>
				<form:input path="firstName" name="firstName" />
				<label for="firstname" generated="true" class="error"></label>
				<form:errors path="firstName" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="lastName">Nazwisko</form:label>
				<form:input path="lastName" />
				<label for="lastName" generated="true" class="error"></label>
				<form:errors path="lastName" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="email">Email</form:label>
				<form:input path="email" />
				<label for="email" generated="true" class="error"></label>
				<form:errors path="email" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="phone">Telefon</form:label>
				<form:input path="phone" />
				<label for="phone" generated="true" class="error"></label>
				<form:errors path="phone" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="apartment">Mieszkanie</form:label>
				<form:select path="apartment" items="${model.apartment}"
					itemValue="id" itemLabel="description" />
					<label for="apartment" generated="true" class="error"></label>
				<form:errors path="apartment" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="movementDate">Data Wprowadzenia</form:label>
				<form:input path="movementDate" type="date" />
				<label for="movementDate" generated="true" class="error"></label>
				<form:errors path="movementDate" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="role">Prawa</form:label>
				<form:select path="role" items="${model.role}" />
				<form:errors path="role" cssClass="error" />
				<label for="role" generated="true" class="error"></label>
			</div>

			<div class="row">
				<form:label path="status">Status</form:label>
				<form:select path="status" items="${model.status}" />
				<label for="status" generated="true" class="error"></label>
				<form:errors path="status" cssClass="error" />
			</div>

			<div class="row">
				<form:label path="password" name="password">Hasło</form:label>
				<form:input path="password" />
				<label for="password" generated="true" class="error"></label>
				<form:errors path="password" cssClass="error" />
			</div>
			<div class="row">
				<button class="btn btn-lg btn-default" type="submit">Zapisz</button>
			</div>

			<div class="row">
				<button class="btn btn-lg btn-default" type="reset">Resetuj</button>
			</div>


		</form:form>
	</div>


</body>
</html>