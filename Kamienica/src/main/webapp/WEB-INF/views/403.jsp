<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<title>403</title>
</head>
<body>
	<br>
	<br>
	<div id="mainWrapper">
		<h1>403 - Nie masz uprawnień do tej części programu.</h1>
		<br> <a href="<c:url value="/" />">Przejdź do strony głównej</a><br>
		<a href="<c:url value="/logout" />">Wyloguj</a>
	</div>
</body>
</html>