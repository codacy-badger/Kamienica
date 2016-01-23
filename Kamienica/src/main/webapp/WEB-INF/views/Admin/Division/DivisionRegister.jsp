<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/MyStyle.css' />" rel="stylesheet"></link>
<title>Algorytm Podziału</title>
</head>
<body>
	<div id="mainWrapperWide">
		<div class="content">
			<h1>Algorytm Podziału</h1>
			<hr>
			<br> <a href="../home.html">Strona Główna</a>
			<c:url var="division" value="/Admin/Division/divisionSave.html" />
			<form:form modelAttribute="divisionForm" method="post"
				action="${division}">
				<div>
					<p class="error">${model.error}</p>
				</div>




				<table>
					<tr>
						<td><form:label path="date">
								<b>Data</b>
							</form:label> <form:input path="date" type="date" /></td>
						<c:forEach items="${model.apartment}" var="m">
							<th><c:out value="${m.description}" /></th>
						</c:forEach>
					<tr>
						<c:forEach items="${model.tenantList}" var="tenantList">
							<tr>
								<th style="height: 12px"><b><c:out
											value="${tenantList.firstName} ${tenantList.lastName}" /> </b></td>
								<c:forEach items="${divisionForm.divisionList}" varStatus="i"
									var="l">


									<c:if test="${tenantList.id.equals(l.tenant.id)}">
										<input name="divisionList[${i.index}].tenant"
											value="${l.tenant.id}" type="hidden" />
										<input name="divisionList[${i.index}].apartment"
											value="${l.apartment.id}" type="hidden" />
										<td><input type="number" step="any" max="1.0" min="0"
											name="divisionList[${i.index}].divisionValue"
											value="${l.divisionValue}"></td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
				</table>
				<br>
				<input type="submit" value="Zapisz" />
			</form:form>
			<br> <br>
		</div>
	</div>
</body>
</html>