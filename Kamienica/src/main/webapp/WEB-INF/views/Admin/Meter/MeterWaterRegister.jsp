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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Licznika Wody</title>
</head>
<body>
	<mytags:navbarAdmin/>
	
	<div class='container'>

		<div class='jumbotron'>
			<h1>Rejestracja Nowego Licznika Wody</h1>
		</div>


		<div class='row'>
			<c:if test="${!empty model.error}">
				<p class='alert alert-danger'>${model.error}</p>
			</c:if>
		</div>

		<div class='row'>
			<c:if test="${empty model.error}">
				<c:url var="rejestrujLicznikWoda"
					value="/Admin/Meter/meterWaterSave.html" />
				<form:form id="rejestrujLicznikWoda" modelAttribute="meter"
					method="post" action="${rejestrujLicznikWoda}">


					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="description">Opis</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="description" name="description" />
							<form:errors path="description" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="serialNumber">Nr. seryjny</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="serialNumber" name="serialNumber" />
							<form:errors path="serialNumber" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="unit">Jednostka</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:input path="unit" name="unit" />
							<form:errors path="unit" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="apartment">Mieszkanie</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:select path="apartment" items="${model.apartment}"
								itemValue="id" itemLabel="description" />
							<form:errors path="apartment" class="error" />
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 myLabel ">
							<form:label path="isWarmWater">Woda Ciepła</form:label>
						</div>
						<div class="col-md-6 inputAndError">
							<form:radiobuttons path="isWarmWater" items="${model.tf}" />
							<form:errors path="isWarmWater" class="error" />
						</div>
					</div>



					<div class="row">
						<div class='col-md-12'>
							<input type="submit" class='btn btn-primary' value="Zapisz"
								${ !empty model.error ? 'disabled="disabled"' : ''} />

						</div>
					</div>



				</form:form>
			</c:if>
		</div>
	</div>
</body>
</html>