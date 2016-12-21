<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Zmiana hasła</title>
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
<!-- Timeline CSS -->
<link href="<c:url value='/static/css/timeline.css' />" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<c:url value='/static/css/morris.css' />" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body class='userBody'>

	<mytags:userNavbar />

	<div class="container myContainer">
		<div class='row'>
			<h1>Zmiana hasła</h1>
			<hr>
		</div>


		<c:url var="changePassw" value="/User/userUpdatePassword.html" />
		<form:form id="changePassw" class="form-horizontal" method="post"
			action="${changePassw}">

			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Login</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="email"
						placeholder="Podaj swój login" />
					<p class="help-block">
						<form:errors path="email" class="error" />
					</p>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Stare
					Hasło</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" name="oldPassword"
						placeholder="Stare hasło" />
					<p class="help-block">
						<form:errors path="oldPassword" class="error" />
					</p>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Nowe Hasło</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" name="newPassword"
						placeholder="Nowe hasło" />
					<p class="help-block">
						<form:errors path="newPassword" class="error" />
					</p>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Potwórz
					Hasło</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" name="newPassword2"
						placeholder="Powtórz hasło" />
					<p class="help-block">
						<form:errors path="newPassword2" class="error" />
					</p>
				</div>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-default">Zapisz</button>
				<button class="btn btn-default" type="reset">Resetuj</button>
			</div>
			<div class="form-group">
				<c:if test="${!empty model.error}">
					<p align="center" class="alert alert-danger">${model.error}</p>
				</c:if>

			</div>
		</form:form>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</html>