<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zmiana hasła</title>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>

</head>
<body>

	<div id="mainWrapper">
		<h1>Zmiana hasła</h1>
		<hr>
		<br>

		<c:url var="changePassw" value="/User/userUpdatePassword.html" />
		<form:form id="changePassw" method="post" action="${changePassw}">
			<div class="content">
				<div class="col_label">
					<p>Login</p>
					<p>Stare Hasło</p>
					<p>Nowe Hasło</p>
					<p>Powtórz Nowe Hasło</p>
				</div>
				<div class="col_input">
					<p>
						<input type="text" name="email">
					</p>
					<p>
						<input type="password" name="oldPassword">
					</p>
					<p>
						<input type="password" name="newPassword">
					</p>
					<p>
						<input type="password" name="newPassword2">
					</p>
				</div>
				<div class="col_error"><p>${model.error }</p>
				</div>

				<br clear="all">
			
				<div class='footer'>
					
					<input type="submit" value="Zapisz" class="submit" /><br><br> <a
						href="../userHome.html">Strona Glowna</a>
				</div>
			</div>
		</form:form>
</body>
</html>