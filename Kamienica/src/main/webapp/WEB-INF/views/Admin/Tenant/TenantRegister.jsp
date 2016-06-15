<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
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
<title>Rejestracja Najemcy</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Formularz Najemcy</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<c:if test="${!empty model.error}">
					<p align="center" class="alert alert-danger">${model.error}</p>
				</c:if>

			</div>

			<c:url var="rejestrujNajemca" value="${model.url }" />
			<form:form class="form-horizontal" id="rejestrujNajemca"
				modelAttribute="tenant" method="post" action="${rejestrujNajemca}">

				<form:input path="id" readonly="true" hidden='true' />

				<div class="form-group">
					<label for="firstName" class="col-sm-3 control-label">Imię</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" name="firstName"
							path='firstName' placeholder="Pole wymagane" />
						<p class="help-block">
							<form:errors path="firstName" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="lastName" class="col-sm-3 control-label">Nazwisko</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" name="lastName"
							path='lastName' placeholder="Pole wymagane" />
						<p class="help-block">
							<form:errors path="lastName" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-sm-3 control-label">E-mail</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" name="email"
							path='email' placeholder="Pole wymagane" />
						<p class="help-block">
							<form:errors path="email" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Telefon</label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" name="phone"
							path='phone' placeholder="Pole wymagane" />
						<p class="help-block">
							<form:errors path="phone" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Mieszkanie</label>
					<div class="col-sm-9">
						<form:select path="apartment" items="${model.apartment}"
							itemValue="id" itemLabel="description" />
						<p class="help-block">
							<form:errors path="apartment" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Data
						Wprowadzenia</label>
					<div class="col-sm-9">
						<form:input path="movementDate" type="date" />
						<p class="help-block">
							<form:errors path="movementDate" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Prawa</label>
					<div class="col-sm-9">
						<form:select path="role" items="${model.role}" />
						<p class="help-block">
							<form:errors path="role" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Status</label>
					<div class="col-sm-9">
						<form:select path="status" items="${model.status}" />
						<p class="help-block">
							<form:errors path="status" class="error" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label for="phone" class="col-sm-3 control-label">Status</label>
					<div class="col-sm-9">
						<form:input path="password" />
						<p class="help-block">
							<form:errors path="password" class="error" />
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