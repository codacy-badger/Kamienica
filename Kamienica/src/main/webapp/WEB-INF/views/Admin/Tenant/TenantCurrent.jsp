<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${!empty najemca}">
		<table border="1" bgcolor="black" width="600px">
			<tr style="background-color: teal; color: white; text-align: center;"
				height="40px">
				<td>Id</td>
				<td>Imie</td>
				<td>Nazwisko</td>
				<td>E-mail</td>
				<td>Telefon</td>
				<td>Mieszkanie</td>
				<td>Data Wprowadzenia</td>
				<td>Prawa Admina</td>
				<td>Usuń</td>
				<td>Edytuj</td>
			</tr>
			<c:forEach items="${najemca}" var="najemca">
				<tr
					style="background-color: white; color: black; text-align: center;"
					height="30px">
					<td><c:out value="${najemca.id}" /></td>
					<td><c:out value="${najemca.imie}" /></td>
					<td><c:out value="${najemca.nazwisko}" /></td>
					<td><c:out value="${najemca.email}" /></td>
					<td><c:out value="${najemca.telefon}" /></td>
					<td><c:out value="${najemca.mieszkanie.opisMieszkania}" /></td>
					<td><c:out value="${najemca.dataWprowadzenia}" /></td>
					<td><c:out value="${najemca.admin}" /></td>
					<td><a
						href="<c:url value="usunNajemca.html?id=${najemca.id}"  />"
						onclick="return confirm('Na pewno usunąć?')">Usuń</a></td>
					<td><a
						href="<c:url value="najemcaEdytuj.html?id=${najemca.id}" />">Edytuj</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>