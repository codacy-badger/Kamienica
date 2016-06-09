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
<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>
<mytags:navbarAdmin/>

	<div id="container">

		<div class='row'>
			<div class='jumbotron'>
				<h1>Rejestracja nowego mieszkania</h1>
				<a href="../home.html">Strona Główna</a>
			</div>
		</div>


		<div class='row'>
			<c:url var="rejestrujMieszkanie"
				value="/Admin/Apartment/apartmentSave.html" />
			<form:form id="rejestrujMieszkanie" modelAttribute="apartment"
				method="post" class="form-horizontal"
				action="${rejestrujMieszkanie}">

				<div class="form-group">
					<label class="col-xs-6 control-label">Domofon</label>
					<div class="col-xs-3">
						<input class='form-control' name="intercom" />
					</div>
					<div class="col-xs-3 messageContainer">
						<form:errors path="intercom" class="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-6 control-label">Numer Mieszkania</label>
					<div class="col-xs-3">
						<input class='form-control' name="apartmentNumber" />
					</div>
					<div class="col-xs-3 messageContainer">
						<form:errors path="apartmentNumber" class="error" />
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-6 control-label">Opis Mieszkania</label>
					<div class="col-xs-3">
						<input class='form-control' name="description" />
					</div>
					<div class="col-xs-3 messageContainer">
						<form:errors path="description" class="error" />
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
	</div>


</body>
</html>