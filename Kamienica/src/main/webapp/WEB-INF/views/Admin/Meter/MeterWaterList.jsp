<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liczniki Wody</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Liczniki Gazu</h1>
				</div>
			</div>
			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>


			<c:if test="${!empty meter}">
				<table class='table table-stripped table-hover' width="100%">
					<thead>
						<tr>
							<th>Numer Seryjny</th>
							<th>Opis</th>
							<th>Jednostka</th>
							<th>Mieszkanie</th>
							<th>Ciepła</th>
							<th>Edytuj</th>
							<th>Usuń</th>

						</tr>
					</thead>
					<c:forEach items="${meter}" var="meter">
						<tr>
							<td><c:out value="${meter.serialNumber}" /></td>
							<td><c:out value="${meter.description}" /></td>
							<td><c:out value="${meter.unit}" /></td>
							<td><c:out value="${meter.apartment.description}" /></td>
							<td><c:out value="${meter.isWarmWater}" /></td>
							<td><a
								href='<c:url value="/Admin/Meter/meterWaterEdit.html?id=${meter.id}"/>'
								role="button" class="btn-xs btn-warning"><i
									class="fa fa-pencil-square-o" aria-hidden="true"></a></a></td>
							<td><a
								href='<c:url value="/Admin/Meter/meterWaterDelete.html?id=${meter.id}"  />'
								onclick="return confirm('Na pewno usunąć?')" role="button"
								class="btn-xs btn-danger"><i class="fa fa-times"
									aria-hidden="true"></i></a></td>

						</tr>
					</c:forEach>
				</table>
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