<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Najemcy - REST</title>
</head>
<body ng-app="myApp">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="TenantController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Najemcy - REST</h1>
					<button id='mySwitch' ng-click="toggle = !toggle"
						class="btn btn-default btn-sm">{{text}}</button>
				</div>
			</div>
			<div id='form' class="fadein fadeout showpanel panel row"
				ng-show="!toggle">

				<!-- http://www.w3schools.com/angular/tryit.asp?filename=try_ng_validate_show -->

				<p>pustka</p>
			</div>


			<div id='list' class="row fadein fadeout showpanel panel"
				ng-show="toggle">
				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th>Imię</th>
								<th>Nazzwisko</th>
								
								<th>Edytuj/Usuń</th>

							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="a in ctrl.tenants">
								<td><span ng-bind="a.firstName"></span></td>
								<td><span ng-bind="a.lastName"></span></td>
								
								<td>
									<button type="button" ng-click="ctrl.edit(a.id)"
										class="btn-xs btn-warning">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
									</button>
									<button type="button" ng-click="ctrl.remove(a.id)"
										class="btn-xs btn-danger ">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<script src="<c:url value='/static/js/angular.js' />"></script>
	<script src="<c:url value='/static/js/angular-resource.js' />"></script>
	<script src="<c:url value='/static/angular/app.js' />"></script>
	<script
		src="<c:url value='/static/angular/tenant/tenant_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/tenant/tenant_controller.js' />"></script>
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