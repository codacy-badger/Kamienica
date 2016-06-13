<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/sb-admin-2.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">
<!-- MetisMenu CSS -->
<link href="<c:url value='/static/css/metisMenu.min.css' />"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rejestracja Odczytów Gazu</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Nowe Odczyty Gazu</h1>
					</div>
				</div>
			</div>


			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>

			<div class='row'>
				<c:if test="${!empty readingForm.currentReadings}">
					<c:url var="odczyty" value="/Admin/Reading/readingGasSave.html" />
					<form:form modelAttribute="readingForm" method="post"
						action="${odczyty}">
						<div class="row">
							<div class="col-md-6 myLabel ">
								<label>Data</label>
							</div>
							<div class="col-md-6 inputAndError">
								<input name="date" id="date" class='ignore' type="date" required
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
									<form:label path="currentReadings[${i.index}].value">${reading.meter.description}</form:label>
								</div>
								<div class="col-md-6 inputAndError">
									<input name="currentReadings[${i.index}].meter"
										value="${reading.meter.id}" type="hidden" /> <input
										type="number" step="any" min="${reading.value}"
										name="currentReadings[${i.index}].value"
										value="${reading.value}" />
								</div>
							</div>
						</c:forEach>


						<div class='row'>
							<input type="submit" class='btn btn-default' value="Zapisz" />
						</div>
					</form:form>
				</c:if>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>


	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>
</body>
</html>