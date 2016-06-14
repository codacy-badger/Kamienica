<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Odczyty</title>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="userHome">Strona Główna</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Nawiguj <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="../index">Strona Powitalna</a></li>
						<li><a href="../Admin/home">Admin</a></li>
					</ul></li>
				<li><a href="userPassword.html">Zmien Hasło</a></li>
				<li><a href="../logout.html">Wyloguj</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div id="container">

		<h1>Odczyt: ${model.media}</h1>
		<br>

		<c:if test="${!empty model.readings}">
			<div class="row">
				<div class="col-md-3 myLabel "></div>
				<div class="col-md-6 myLabel ">
					<table class="table table-stripped table-hover" style="width: 100%">
						<tr>
							<th>Id</th>
							<th>Data</th>
							<th>Wartość Odczytu</th>
							<th>Jednostka</th>
							<th>Licznik</th>
						</tr>
						<c:forEach items="${model.readings}" var="reading">
							<tr>
								<td><c:out value="${reading.id}" /></td>
								<td><c:out value="${reading.readingDate}" /></td>
								<td><c:out value="${reading.value}" /></td>
								<td><c:out value="${reading.unit}" /></td>
								<td><c:out value="${reading.meter.description}" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-md-3 myLabel "></div>
			</div>
		</c:if>

	</div>
</body>
</html>