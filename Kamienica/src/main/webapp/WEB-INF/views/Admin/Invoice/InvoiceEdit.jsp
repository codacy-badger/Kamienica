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
						<h1 class="page-header well">Formudsfsfsdlarz Faktur: ${model.media }</h1>
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

<form:select path="baseReading" items="${model.readings}"
				itemValue="id" itemLabel="readingDate" hidden="true" class='ignore' />


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




<c:if test="${empty model.error}">
	<div class='row'>
		<c:url var="fakturaEdytuj" value="invoiceEnergyOverwrite.html" />
		<form:form id="fakturaEdytuj" modelAttribute="invoice" method="post"
			action="${fakturaEdytuj}">

			<form:select path="baseReading" items="${model.readings}"
				itemValue="id" itemLabel="readingDate" hidden="true" class='ignore' />

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="id">ID</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="id" readonly="true" />
					<form:errors path="id" cssClass="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="serialNumber">Numer Faktury</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="serialNumber" name="serialNumber" />
					<form:errors path="serialNumber" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="description">Opis Faktury</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="description" name="description"
						value="${model.description }" />
					<form:errors path="description" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="date">Data Faktury</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input type='date' path="date" name="date"
						value="${model.date }" />
					<form:errors path="date" class="error" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 myLabel ">
					<form:label path="totalAmount">Wartosc Faktury</form:label>
				</div>
				<div class="col-md-6 inputAndError">
					<form:input path="totalAmount" name="totalAmount" />
					<form:errors path="totalAmount" class="error" />
				</div>
			</div>


			<div class='row'>
				<input type="submit" class='btn btn-default' value="Zapisz" />
			</div>
		</form:form>
	</div>
</c:if>