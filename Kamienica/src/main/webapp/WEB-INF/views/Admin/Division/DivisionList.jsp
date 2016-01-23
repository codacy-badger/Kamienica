<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<title>Algorytm Podzialu</title>
</head>
<body>
	<div id="mainWrapper">
		<h1>Algorytm Podzialu</h1>
		<hr>
		<br>
		<table style="width: 100%">
			<tr>
				<th></th>
				<c:forEach items="${model.apartment}" var="m">
					<th><c:out value="${m.description}" /></th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${model.tenantList}" var="tenantList">
					<tr>
						<th><c:out
								value="${tenantList.firstName} ${tenantList.lastName}" /></th>
						<c:forEach items="${model.divisionForm.divisionList}"
							varStatus="i" var="l">
							<c:if test="${tenantList.id.equals(l.tenant.id)}">
								<td><c:out value="${l.divisionValue}" /></td>
							</c:if>
						</c:forEach>
					</tr>

				</c:forEach>
		</table>
		<br> <a href="divisionRegister.html">Wprowadź
			nowy podział</a><br> <a href="../home.html">Strona
			Główna</a> <br>
	</div>
</body>
</html>