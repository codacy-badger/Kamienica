<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">

<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>




<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<div class="container text-center">
		<div class='row'>
			<div class='jumbotron'>
				<h1>Kamienica</h1>
				<h3>Podaj login i hasło</h3>
			</div>
		</div>
		<div class='row'>

			<div class="login-container">
				<div class="login-card  ">
					<div class="form  ">
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post"
							class="form-horizontal form  ">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Nieprawidłowy login lub hasło.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>Zostałeś wylogowany</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon ingore" for="email"><i
									class="glyphicon glyphicon-user  "></i></label> <input type="text"
									class="  form-control" id="email" name="email"
									placeholder="Wprowadź email" >
							</div>
							<div class="input-group input-sm  ">
								<label class="input-group-addon ingore" for="password"><i
									class="	glyphicon glyphicon-lock ingore"></i></label> <input
									type="password" class="form-control  ignore" id="password"
									name="password" placeholder="Wprowadź hasło" >
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Zaloguj">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>