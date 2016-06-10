<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
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

<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

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