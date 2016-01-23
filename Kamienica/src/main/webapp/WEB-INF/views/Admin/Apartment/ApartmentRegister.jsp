<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>
	<div id="mainWrapper">
		<div class="content">
			<h1>Rejestracja Nowego Mieszkania</h1>
			<hr>
			<a href="../home.html">Strona Glowna</a>

			<c:url var="rejestrujMieszkanie"
				value="/Admin/Apartment/apartmentSave.html" />
			<form:form id="rejestrujMieszkanie" class="myForm"
				modelAttribute="apartment" method="post"
				action="${rejestrujMieszkanie}">

				<ul>

					<li><form:label path="intercom">Domofon</form:label> <form:input
							path="intercom" /> <form:errors path="intercom" cssClass="error" /></li>

					<li><form:label path="apartmentNumber">Numer Mieszkania</form:label>
						<form:input path="apartmentNumber" /> <form:errors
							path="apartmentNumber" cssClass="error" /></li>

					<li><form:label path="description">Opis Mieszkania</form:label>
						<form:input path="description" /> <form:errors path="description"
							cssClass="error" /></li>


					<li><button class="submit" type="submit">Zapisz</button></li>
					<li><button class="submit" type="reset">Resetuj</button></li>

				</ul>

			</form:form>
		</div>
		<br> <br>
	</div>
</body>
</html>