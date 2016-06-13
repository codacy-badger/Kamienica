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
<title>Odczyty Energii</title>
</head>
<body>
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Odczyty Energii</h1>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty reading}">
					<table class='table table-stripped table-hover'">
						<thead>
							<tr>
								<th>Data</th>
								<th>Wartość Odczytu</th>
								<th>Jednostka</th>
								<th>Licznik</th>
								<th>Edytuj</th>
								<th>Usuń</th>
							</tr>
						</thead>
						<c:forEach items="${reading}" var="readingEnergy">
							<tr>
								<td><c:out value="${readingEnergy.readingDate}" /></td>
								<td><c:out value="${readingEnergy.value}" /></td>
								<td><c:out value="${readingEnergy.unit}" /></td>
								<td><c:out value="${readingEnergy.meter.description}" /></td>
								<td><a
									href="<c:url value="readingEnergyEdit.html?date=${readingEnergy.readingDate}" />"
									role="button" class="btn-xs btn-warning"><i
										class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
								<td><a
									href="<c:url value="readingEnergyDelete.html?date=${readingEnergy.readingDate}"  />"
									onclick="return confirm('UWAGA!!! Usunięte zostaną wszystkie odczyty z tą datą!\n Na pewno usunąć?')"
									role="button" class="btn-xs btn-danger"><i
										class="fa fa-times" aria-hidden="true"></i> </a></td>

							</tr>
						</c:forEach>
					</table>
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