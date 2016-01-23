<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
</head>
<body>
	<div id="mainWrapperWide">
		<h1>Lista Opłat</h1>
		<hr>
		<a href="userHome.html">Strona Główna</a> <br>
		<div class="col_three">
			<table style="width: 100%">
				<tr>
					<th colspan="3">Energia</th>
				</tr>
				<tr>
					<th>Wartość Opłaty</th>
					<th>Data Rachunku</th>
					<th>Nr Faktury</th>
				</tr>
				<c:forEach items="${model.energy}" var="energy">
					<tr>
						<td><c:out value="${energy.paymentAmount}" /></td>
						<td><c:out value="${energy.paymentDate}" /></td>
						<td><c:out value="${energy.invoice.serialNumber}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col_three">
			<table style="width: 100%">
				<tr>
					<th colspan="3">Woda</th>
				</tr>
				<tr>
					<th>Wartość Opłaty</th>
					<th>Data Rachunku</th>
					<th>Nr Faktury</th>
				</tr>
				<c:forEach items="${model.water}" var="water">
					<tr>
						<td><c:out value="${water.paymentAmount}" /></td>
						<td><c:out value="${water.paymentDate}" /></td>
						<td><c:out value="${water.invoice.serialNumber}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col_three">
			<table style="width: 100%">
				<tr>
					<th colspan="3">Gaz</th>
				</tr>
				<tr>
					<th>Wartość Opłaty</th>
					<th>Data Rachunku</th>
					<th>Nr Faktury</th>
				</tr>
				<c:forEach items="${model.water}" var="water">
					<tr>
						<td><c:out value="${water.paymentAmount}"/></td>
						<td><c:out value="${water.paymentDate}"/></td>
						<td><c:out value="${water.invoice.serialNumber}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>