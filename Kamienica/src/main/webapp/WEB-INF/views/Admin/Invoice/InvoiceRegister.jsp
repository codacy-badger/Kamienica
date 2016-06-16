<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
<title>Rejestracja Nowej Faktury Energii</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Formularz Faktur: ${model.media }</h1>
					</div>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>

			<c:if test="${empty model.error}">
				<div class='row'>
					<c:url var="register" value="${model.saveUrl }" />
					<form:form class="form-horizontal" id="register"
						modelAttribute="invoice" method="post" action="${register}">

						<form:input path="id" readonly="true" type='hidden' />

						<div class="form-group">
							<label for="serialNumber" class="col-sm-3 control-label">Numer
								Faktury</label>
							<div class="col-sm-9">
								<form:input type="text" class="form-control" name="serialNumber"
									path='serialNumber' placeholder="Pole wymagane" />

								<p class="help-block">
									<form:errors path="serialNumber" class="error" />
								</p>
							</div>
						</div>

						<c:if test="${!empty model.readings}">
							<div class="form-group">
								<label for="baseReading" class="col-sm-3 control-label">Okres
									Rozliczenia</label>
								<div class="col-sm-9">
									<form:select path="baseReading" class="form-control ignore"
										items="${model.readings}" itemValue="id"
										itemLabel="readingDate" />
									<p class="help-block">
										<form:errors path="baseReading" class="error" />
									</p>
								</div>
							</div>
						</c:if>
						<!-- work on that after the decimal bug has been resolved -->
						<c:if test="${empty model.readings}">
							<div class="form-group">
								<label for="baseReading" class="col-sm-3 control-label">Okres
									Rozliczenia</label>
								<div class="col-sm-9">

									<form:input path="baseReading" items="${invoice.baseReading}"
										itemValue="id" itemLabel="${invoice.baseReading.readingDate}"
										readonly="true" class='ignore' />
									<!--<form:input path="baseReading" class="form-control ignore"
										disabled="true" value="${invoice.baseReading.readingDate }" /> -->
									<p class="help-block">
										<form:errors path="baseReading" class="error" />
									</p>
								</div>
							</div>
						</c:if>


						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">Opis
								Faktury</label>
							<div class="col-sm-9">
								<form:input type="text" class="form-control" name="description"
									path='description' value="${model.description }"
									placeholder="Pole wymagane" />
								<p class="help-block">
									<form:errors path="description" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="date" class="col-sm-3 control-label">Data
								Faktury</label>
							<div class="col-sm-9">
								<form:input type="date" class="form-control" name="date"
									path='date' placeholder="Pole wymagane" value="${model.date }" />
								<p class="help-block">
									<form:errors path="date" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="totalAmount" class="col-sm-3 control-label">Wartość
								Faktury</label>
							<div class="col-sm-9">
								<form:input class='form-control bfh-number' type="number"
									step="any" name="totalAmount" path='totalAmount' />
								<p class="help-block">
									<form:errors path="totalAmount" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-default">Zapisz</button>
							<button class="btn btn-default" type="reset">Resetuj</button>
						</div>
					</form:form>
				</div>
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