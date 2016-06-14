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

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Licznika Gazu</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Formularz Licznika Gazu</h1>
					</div>
				</div>
			</div>
			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>

			<div class='row'>
				<c:if test="${empty model.error}">
					<c:url var="rejestrujLicznikGaz" value="${model.url }" />
					<form:form class="form-horizontal" id="rejestrujLicznikGaz"
						modelAttribute="meter" method="post"
						action="${rejestrujLicznikGaz}">
						<form:input path="id" hidden="true" />

						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">Opis</label>
							<div class="col-sm-9">
								<form:input type="text" class="form-control" name="description"
									path='description' placeholder="Pole wymagane" />
								<p class="help-block">
									<form:errors path="description" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="serialNumber" class="col-sm-3 control-label">Nr.
								seryjny</label>
							<div class="col-sm-9">
								<form:input type="text" class="form-control" name="serialNumber"
									path='serialNumber' placeholder="Pole wymagane" />
								<p class="help-block">
									<form:errors path="serialNumber" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">Jednostka</label>
							<div class="col-sm-9">
								<form:input type="text" class="form-control" name="unit"
									path='unit' placeholder="Pole wymagane" />
								<p class="help-block">
									<form:errors path="unit" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="apartment" class="col-sm-3 control-label">Mieszkanie</label>
							<div class="col-sm-9">
								<form:select class="form-control" path="apartment"
									items="${model.apartment}" itemValue="id"
									itemLabel="description" />
								<p class="help-block">
									<form:errors path="apartment" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<label for="cwu" class="col-sm-3 control-label">CWU</label>
							<div class="col-sm-9">
								<form:radiobuttons class="radio-inline" path="cwu"
									items="${model.tf}" />
								<p class="help-block">
									<form:errors path="cwu" class="error" />
								</p>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-default"
								${ !empty model.error ? 'disabled="disabled"' : ''}>Zapisz</button>
							<button class="btn btn-default" type="reset">Resetuj</button>
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