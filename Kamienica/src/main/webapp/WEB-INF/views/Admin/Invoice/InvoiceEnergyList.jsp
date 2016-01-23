<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Faktur</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Lista Faktur Energii</h1>
		<hr>
		<a href="../home.html">Strona Główna</a> <br> <a
			href="registerInvoice.html">Dodaj fakturę</a><br> <a
			href="invoiceList.html">Sprawdź pozostałe faktury</a>
		<c:if test="${!empty invoice}">
			<table width="100%">
				<tr>
					<th>Id</th>
					<th>Nr Faktury</th>
					<th>Opis</th>
					<th>Data</th>
					<th>Wartość</th>
					<th>Usuń</th>
					<th>Edytuj</th>
				</tr>
				<c:forEach items="${invoice}" var="invoice">
					<tr>
						<td><c:out value="${invoice.id}" /></td>
						<td><c:out value="${invoice.serialNumber}" /></td>
						<td><c:out value="${invoice.description}" /></td>
						<td><c:out value="${invoice.date}" /></td>
						<td><c:out value="${invoice.totalAmount}" /></td>
						<td><a
							href="<c:url value="/Admin/Invoice/invoiceEnergyDelete.html?id=${invoice.id}"  />"
							onclick="return confirm('Na pewno usunąć?')">Usuń</a></td>
						<td><a
							href="<c:url value="/Admin/Invoice/invoiceEnergyEdit.html?id=${invoice.id}" />">Edytuj</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>