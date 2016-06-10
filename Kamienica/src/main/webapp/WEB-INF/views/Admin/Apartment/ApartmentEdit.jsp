<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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