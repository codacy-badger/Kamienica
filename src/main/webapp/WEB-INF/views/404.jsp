<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">
<title>403</title>
<script>
	function goBack() {
		window.history.back();
	}
</script>
<title>404</title>
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="jumbotron">
			<h2>404 - Podana strona nie istnieje.</h2>
			<h3>
				<a onclick="goBack()">Powrót</a>
			</h3>
			<h3>
				<a href="<c:url value="/logout" />">Wyloguj</a>
			</h3>
		</div>
	</div>
</body>
</html>