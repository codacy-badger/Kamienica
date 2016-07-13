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
<body ng-app="apartment">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="ApartmentController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Lista Mieszkań - REST</h1>
				</div>
			</div>
			<div class='row'>

				<c:url var="rejestrujMieszkanie" value="${url}" />
				<form class="form-horizontal" 
					modelAttribute="apartment" method="post"
					action="${rejestrujMieszkanie}" ng-submit="ctrl.submit()" name="myForm">
					<input path="id" readonly="true" type='hidden' />

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Domofon</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="intercom"
								path='intercom' placeholder="Pole wymagane" />
							<p class="help-block">
								<form:errors path="intercom" class="error" />
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Numer
							Mieszkania</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="inputEmail3"
								path='apartmentNumber' placeholder="Pole wymagane"
								name="apartmentNumber" />
							<p class="help-block">
								<form:errors path="apartmentNumber" class="error" />
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Opis</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="inputEmail3"
								path='description' placeholder="Pole wymagane"
								name="description" />
							<p class="help-block">
								<form:errors path="description" class="error" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">Zapisz</button>
						<button class="btn btn-default" type="reset">Resetuj</button>
					</div>

				</form>
			</div>
			<div class='row'>

				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th>Nr Mieszkania</th>
								<th>Domofon</th>
								<th>Opis</th>
								<th>Edytuj/Usuń</th>

							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="a in ctrl.apartments">
								<td><span ng-bind="a.apartmentNumber"></span></td>
								<td><span ng-bind="a.intercom"></span></td>
								<td><span ng-bind="a.description"></span></td>
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
	<script src="<c:url value='/static/angular/app.js' />"></script>
	<script
		src="<c:url value='/static/angular/apartment/apartment_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/apartment/apartment_controller.js' />"></script>
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