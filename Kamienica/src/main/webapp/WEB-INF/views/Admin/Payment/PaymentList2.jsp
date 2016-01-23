<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
 <meta charset="utf-8">
    <title>Payment List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional Bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Lista Mieszkań</div>
		<br> <br>
		<c:if test="${!empty payment}">
			<table border="1" bgcolor="black" width="600px">
				<tr
					style="background-color: teal; color: white; text-align: center;"
					height="40px">
					<td>Id</td>
					<td>Wartość Opłaty</td>
					<td>Data Rachunku</td>
					<td>Data Odczytu</td>
					<td>Najemca</td>
					<td>Nr Faktury</td>
				</tr>
				<c:forEach items="${payment}" var="payment">
					<tr
						style="background-color: white; color: black; text-align: center;"
						height="30px">
						<td><c:out value="${payment.id}" /></td>
						<td><c:out value="${payment.paymentAmount}" /></td>
						<td><c:out value="${payment.paymentDate}" /></td>
						<td><c:out value="${payment.readingDate}" /></td>
						<td><c:out value="${payment.tenant.firstName}" /> <c:out
								value="${payment.tenant.lastName}" /></td>
						<td><c:out value="${payment.invoice.serialNumber}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br> <br> <a href="../home.html">Strona Główna</a> <a
			href="paymentList.html">Sprawdź pozostałe media</a>
	</center>
</body>
</html>