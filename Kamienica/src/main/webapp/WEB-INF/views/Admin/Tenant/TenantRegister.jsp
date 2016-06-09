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
<title>Rejestracja Najemcy</title>
</head>
<body>
	<mytags:navbarAdmin />

	<div class="container" role="main">

		<div class="jumbotron">
			<h1>Najemca</h1>
			<p>Wprowadź dane by zarejestrować nowego najemcę</p>
		</div>

		<c:url var="rejestrujNajemca" value="${model.url }" />
		<form:form class="myForm" id="rejestrujNajemca"
			modelAttribute="tenant" method="post" action="${rejestrujNajemca}">

			<div class="row">
				<p align="center" class="error">${model.error}</p>
			</div>


			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="firstName">Imię</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="firstName" name="firstName" />

					<form:errors path="firstName" class="error" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 myLabel">
					<form:label path="lastName">Nazwisko</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="lastName" />
					<form:errors path="lastName" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="email">Email</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="email" />
					<label for="email" generated="true" class="error"></label>
					<form:errors path="email" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="phone">Telefon</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="phone" />
					<label for="phone" generated="true" class="error"></label>
					<form:errors path="phone" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="apartment">Mieszkanie</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:select path="apartment" items="${model.apartment}"
						itemValue="id" itemLabel="description" />
					<label for="apartment" generated="true" class="error"></label>
					<form:errors path="apartment" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="movementDate">Data Wprowadzenia</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="movementDate" type="date" />
					<label for="movementDate" generated="true" class="error"></label>
					<form:errors path="movementDate" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="role">Prawa</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:select path="role" items="${model.role}" />
					<form:errors path="role" class="error" />
					<label for="role" generated="true" class="error"></label>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="status">Status</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:select path="status" items="${model.status}" />
					<label for="status" generated="true" class="error"></label>
					<form:errors path="status" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="password" name="password">Hasło</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="password" />
					<label for="password" generated="true" class="error"></label>
					<form:errors path="password" class="error" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12  ">
					<button class="btn btn-primary" type="submit">Zapisz</button>
					<button class="btn btn-primary" type="reset">Resetuj</button>
				</div>
			</div>

		</form:form>
	</div>


</body>
</html>