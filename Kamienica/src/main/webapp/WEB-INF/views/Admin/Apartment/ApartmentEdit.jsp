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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edycja Mieszkania</title>
</head>
<body>
	<mytags:navbarAdmin/>

	<div id="container">
		<div class='row'>
			<div class="jumbotron">
				<h1>Edycja Mieszkania</h1>
				<a href="../home.html">Strona Główna</a>
			</div>
		</div>


		<div class='row'>
			<c:url var="mieszkanieEdytuj"
				value="/Admin/Apartment/apartmentOverwrite.html" />
			<form:form id="mieszkanieEdytuj" modelAttribute="apartment"
				method="post" class="form-horizontal" action="${mieszkanieEdytuj}">

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
						<form:label path="apartmentNumber">Numer Mieszkania</form:label>
					</div>
					<div class="col-md-6 inputAndError">
						<form:input path="apartmentNumber" />
						<form:errors path="apartmentNumber" cssClass="error" />
					</div>
				</div>


				<div class="row">
					<div class="col-md-6 myLabel ">
						<form:label path="intercom">Domofon</form:label>
					</div>
					<div class="col-md-6 inputAndError">
						<form:input path="intercom" />
						<form:errors path="intercom" cssClass="error" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 myLabel ">
						<form:label path="description">Opis Mieszkania</form:label>
					</div>
					<div class="col-md-6 inputAndError">
						<form:input path="description" />
						<form:errors path="description" cssClass="error" />
					</div>
				</div>


				<div class="row">
					<div class="col-md-12  ">
						<button class="btn btn-default" type="submit">Zapisz</button>
						<button class="btn btn-default" type="reset">Resetuj</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>