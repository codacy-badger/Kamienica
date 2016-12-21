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
<title>Lista Mieszkań - REST</title>
</head>
<body ng-app="myApp">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="DivisionController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Lista Mieszkań - REST</h1>

					<div class="alert alert-danger" ng-show="errorField">
						<strong>BŁĄD: </strong> {{errorMsg}}
					</div>
					<button id='mySwitch' ng-click="ctrl.switchForm()"
						class="btn btn-default btn-sm">{{text}}</button>

				</div>
			</div>
			<div id='form' class="fadein fadeout showpanel panel row"
				ng-show="!toggle">

				<!-- http://www.w3schools.com/angular/tryit.asp?filename=try_ng_validate_show -->


			</div>


			<div id='list' class="row fadein fadeout showpanel panel"
				ng-show="toggle">
				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th></th>
								<th ng-repeat="a in ctrl.apartments"><span
									ng-bind="a.description"></span></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="t in ctrl.tenants">
								<th><span ng-bind="t.firstName + ' ' +  t.lastName"></span></th>
								<td ng-repeat="d in ctrl.divisions"><span  ng-if="d.tenant.id = t.id" ng-bind="d.divisionValue"></span> </td>
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
		src="<c:url value='/static/angular/apartment/apartment_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/apartment/apartment_controller.js' />"></script>

	<script
		src="<c:url value='/static/angular/tenant/tenant_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/tenant/tenant_controller.js' />"></script>

	<script
		src="<c:url value='/static/angular/division/division_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/division/division_controller.js' />"></script>
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