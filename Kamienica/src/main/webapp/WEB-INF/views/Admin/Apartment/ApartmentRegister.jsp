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
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Formularz Mieszkań</h1>
					</div>
				</div>
			</div>

			<c:url var="rejestrujMieszkanie" value="${url}" />
			<form:form class="form-horizontal" role="form"
				modelAttribute="apartment" method="post"
				action="${rejestrujMieszkanie}">
				<form:input path="id" readonly="true" type='hidden' />

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-3 control-label">Domofon</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" name="intercom"
							path='intercom' placeholder="Pole wymagane" />
						<p class="help-block">
							<form:errors path="intercom" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-3 control-label">Numer
						Mieszkania</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" id="inputEmail3"
							path='apartmentNumber' placeholder="Pole wymagane"
							name="apartmentNumber" />
						<p class="help-block">
							<form:errors path="apartmentNumber" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-3 control-label">Opis</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" id="inputEmail3"
							path='description' placeholder="Pole wymagane" name="description" />
						<p class="help-block">
							<form:errors path="description" class="error" />
						</p>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-default">Zapisz</button>
					<button class="btn btn-default" type="reset">Resetuj</button>
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