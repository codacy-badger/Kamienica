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
<title>Edycja Odczytów Gazu</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Odczyty Energii - Edycja</h1>
					</div>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>

			<c:if test="${!empty readingForm.currentReadings}">
				<c:url var="odczyty"
					value="/Admin/Reading/readingEnergyOverwrite.html" />
				<form:form class="form-horizontal" modelAttribute="readingForm"
					method="post" action="${odczyty}">


					<div class="form-group">
						<label for="date" class="col-sm-3 control-label">Data</label>
						<div class="col-sm-9">
							<input name="date" class="form-control" id="date" type="date"
								class='ignore' value="${model.date}" min="${model.oldDate}"
								readonly="readonly" />
						</div>
					</div>

					<div class="row">
						<label class="col-sm-3 text-right">Opis Licznika </label><label
							class="col-sm-3 ">Wartość Odczytu</label>

					</div>

					<c:forEach items="${readingForm.currentReadings}" varStatus="i"
						var="reading">
						<div class="form-group">
							<input name="currentReadings[${i.index}].id"
								value="${reading.id}" type="hidden" />
							<form:label path="currentReadings[${i.index}].value"
								class="col-sm-3 control-label">${reading.meter.description}</form:label>
							<div class="col-sm-9">
								<input name="currentReadings[${i.index}].id"
									value="${reading.id}" type="hidden" /> <input
									name="currentReadings[${i.index}].meter"
									value="${reading.meter.id}" type="hidden" /> <input
									type="number" step="any" class="form-control"
									name="currentReadings[${i.index}].value"
									value="${reading.value}"
									min= '${ !empty readingForm.previousReadings[i.index].value ? readingForm.previousReadings[i.index].value : 0}'  />
							</div>
						</div>
					</c:forEach>

					<div class="form-group ">
						<button type="submit" class="btn btn-default">Zapisz</button>
						<button class="btn btn-default" type="reset">Resetuj</button>
					</div>
				</form:form>
			</c:if>
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