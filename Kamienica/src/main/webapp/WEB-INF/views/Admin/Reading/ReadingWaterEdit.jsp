<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edycja Odczytów Wody</title>
</head>
<body>
	<mytags:navbarAdmin />

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Edycja Odczytów Wody</h1>
				<a href="../home.html">Strona Główna</a>
			</div>
		</div>


		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>

		<div class='row'>
			<c:url var="odczyty"
				value="/Admin/Reading/readingWaterOverwrite.html" />
			<form:form modelAttribute="readingForm" method="post"
				action="${odczyty}">
				<div class="row">
					<div class="col-md-6 myLabel ">
						<label>Data</label>
					</div>
					<div class="col-md-6 inputAndError">
						<input name="date" id="date" type="date" required
							value="${model.date}" min="${model.oldDate}" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 myLabel ">
						<label style="margin-top: 15px; margin-bottom: 15px;">Opis
							Licznika</label>
					</div>
					<div class="col-md-6 inputAndError">
						<label style="margin-top: 15px; margin-bottom: 15px;">Wartość
							Odczytu</label>
					</div>
				</div>

				<c:forEach items="${readingForm.currentReadings}" varStatus="i"
					var="reading">
					<div class="row">
						<div class="col-md-6 myLabel ">
							<input name="currentReadings[${i.index}].id"
								value="${reading.id}" type="hidden" />
							<form:label path="currentReadings[${i.index}].value">${reading.meter.description}</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<input name="currentReadings[${i.index}].id"
								value="${reading.id}" type="hidden" /> <input
								name="currentReadings[${i.index}].meter"
								value="${reading.meter.id}" type="hidden" /> <input
								type="number" step="any"
								name="currentReadings[${i.index}].value"
								value="${reading.value}"
								min="${readingForm.previousReadings[i.index].value}" />
						</div>
					</div>
				</c:forEach>
				<tr>
					<td colspan="6" align="center"><input type="submit"
						value="Zapisz" /></td>
				</tr>
			</form:form>
		</div>
	</div>

</body>
</html>