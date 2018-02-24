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
	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>



	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Algorytm Podzialu</title>
</head>
<body>

	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Konfiguracja</h1>
				</div>
			</div>

			<div class='row'>
				<c:if test="${!empty model.error}">
					<p class='alert alert-danger'>${model.error}</p>
				</c:if>
			</div>


			<c:if test="${empty model.error}">
				<div class=row>
					<c:if test="${!empty model.settings}">
						<div class="col-md-12">
							<table class='table table-stripped table-hover' width="100%">
								<thead>
									<tr>
										<th>Instalacja gazowa</th>
										<th>Ogrzewanie ciepłej wody</th>
										<th>Poprawny Podzial</th>
										<th>Internet</th>
										<th>Śmieci</th>
										<th>Edytuj</th>

									</tr>
								</thead>

								<tr>
									<td><c:out value="${model.settings.gas}" /></td>
									<td><c:out value="${model.settings.waterHeatingSystem}" /></td>
									<td><c:out value="${model.settings.correctDivision}" /></td>
									<td><c:out value="${model.settings.internet}" /></td>
									<td><c:out value="${model.settings.garbage}" /></td>

									<td><a href='<c:url value="/Admin/Settings/edit.html" />'
										role="button" class="btn-xs btn-warning"><i
											class="fa fa-pencil-square-o"></i></a></td>


								</tr>


							</table>
						</div>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>