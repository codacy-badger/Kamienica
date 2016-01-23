<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Opłat</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Rejestracja Opłat</div>
		<br> <br>
		<c:forEach items="${model.message}" var="m">
			<c:out value="${m}" />
			<br>
		</c:forEach>
		<table width="1400px" height="100px">
			<tr>
				<th>Energia</th>
				<th>Woda</th>
				<th>Gaz</th>
			</tr>

			<tr>
				<td><c:out value="${model.energia}" /></td>
				<td><c:out value="${model.woda}" /></td>
				<td><c:out value="${model.gaz}" /></td>
			</tr>
		</table>

		
		<br> <br> <a href="/Kamienica/Admin/home.html">Strona Główna</a>
		<br> <a href="/Kamienica/Admin/paymentList.html">Lista Opłat</a>
	</center>
</body>
</html>