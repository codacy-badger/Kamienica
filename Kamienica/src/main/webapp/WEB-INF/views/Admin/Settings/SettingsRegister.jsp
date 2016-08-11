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
<title>Algorytm Podziału</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">

			<div class='row'>
				<div class='row'>
					<div class="col-lg-12">
						<h1 class="page-header well">Konfiguracja</h1>

						${model.cwu}

					</div>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>

			<c:if test="${empty model.error}">

				<!--<f  orm:options items="${model.labels}" itemLabel="active"
											itelLabel="active"/>  -->
				<div class='row'>
					<c:url var="set" value="/Admin/Settings/save.html" />
					<form:form modelAttribute="settings" method="post" action="${set}">
						<form:input path="id" readonly="true" value='${model.item.id }'
							hidden='true' />



						<div class="form-group">
							<label for="cwu" class="col-sm-3 control-label">Instalacja
								gazowa</label>
							<div class="col-sm-9">

								<form:select path="gas" name="gas"
									itemValue="${model.item.gas }">
									<form:option value="true">Tak</form:option>
									<form:option value="false">Nie</form:option>
								</form:select>

								<p class="help-block">
									<form:errors path="gas" class="error" />
								</p>
							</div>
						</div>



						<form:input type="hidden" class="form-control"
							path='correctDivision' name="correctDivision"
							value='${model.item.correctDivision }' readonly="true" />


						<div class="form-group">
							<label class="col-sm-3 control-label">Internet</label>
							<div class="col-sm-9">

								<form:select path="internet" name="internet"
									itemValue="${model.item.internet }">
									<form:option value="true">Tak</form:option>
									<form:option value="false">Nie</form:option>
								</form:select>


							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label">System ogrzewania wody</label>
							<div class="col-sm-9">

								<form:select path="waterHeatingSystem" name="waterHeatingSystem">
									<form:options items="${model.cwu}" />
								</form:select>


							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label">Śmieci</label>
							<div class="col-sm-9">
								<form:select path="garbage" name="garbage" readonly='true'
									itemValue="${model.item.garbage }">
									<form:option value="true">Tak</form:option>
									<form:option value="false">Nie</form:option>
								</form:select>


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