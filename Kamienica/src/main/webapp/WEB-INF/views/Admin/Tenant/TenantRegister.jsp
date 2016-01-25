<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>



<script type="text/javascript"
	src="<c:url value='/static/javascript/jquery-2.2.0.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/javascript/tenant.js' />">
alert("ggg")
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Najemcy</title>
</head>
<body>
	<div id="mainWrapper">
		<div class="content">
			<h1>Rejestracja Najemcy</h1>
			<hr>
			<a href="../home.html">Strona Główna</a>
			<c:url var="rejestrujNajemca" value="/Admin/Tenant/tenantSave.html" />
			<form:form class="myForm" id="rejestrujNajemca"
				modelAttribute="tenant" method="post" action="${rejestrujNajemca}">
				<p align="center" class="error">${model.error}</p>
				<br>
				<ul>
					<li><form:label path="firstName">Imię</form:label> <form:input
							path="firstName" /> <form:errors path="firstName"
							cssClass="error" /></li>
					<li><form:label path="lastName">Nazwisko</form:label> <form:input
							path="lastName" /> <form:errors path="lastName" cssClass="error" /></li>
					<li><form:label path="email">Email</form:label> <form:input
							path="email" /> <form:errors path="email" cssClass="error" /></li>
					<li><form:label path="phone">Telefon</form:label> <form:input
							path="phone" /> <form:errors path="phone" cssClass="error" /></li>
					<li><form:label path="apartment">Mieszkanie</form:label> <form:select
							path="apartment" items="${model.apartment}" itemValue="id"
							itemLabel="description" /> <form:errors path="apartment"
							cssClass="error" /></li>
					<li><form:label path="movementDate">Data Wprowadzenia</form:label>
						<form:input path="movementDate" type="date" /> <form:errors
							path="movementDate" cssClass="error" /></li>
					<li><form:label path="role">Prawa</form:label> <form:select
							path="role" items="${model.role}" /> <form:errors path="role"
							cssClass="error" /></li>
					<li><form:label path="status">Status</form:label> <form:select
							path="status" items="${model.status}" /> <form:errors
							path="status" cssClass="error" /></li>
					<li><form:label path="password" name="password">Hasło</form:label> <form:input
							path="password" /> <form:errors path="password" cssClass="error" /></li>
					<li><button class="submit" type="submit">Zapisz</button></li>
					<li><button class="submit" type="reset">Resetuj</button></li>
				</ul>

			</form:form>
		</div>
	</div>

</body>
</html>