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
<title>Liczniki Wody</title>
</head>
<body ng-app="myApp">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="MeterWaterController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Liczniki Wody</h1>
					
					<div class="alert alert-danger" ng-show="errorField">
						<strong>BŁĄD: </strong> {{errorMsg}}
					</div>
					<button id='mySwitch' ng-click="ctrl.switchForm()"
						class="btn btn-default btn-sm">{{text}}</button>

				</div>
			</div>
			<div id='form' class="fadein fadeout showpanel panel row"
				ng-show="!toggle">

				<form class="form-horizontal" ng-submit="ctrl.submit()"
					name="myForm">
					<input path="id" readonly="true" type='hidden' /> <input
						type="hidden" ng-model="ctrl.meterWater.id" />


					<div class="form-group">
						<label class="col-sm-3 control-label">Opis</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.meterWater.description"
								class="form-control" name="description" ng-required='true'
								ng-minlength="4" />
							<p class="help-block">
								<span class='error' ng-show="myForm.description.$invalid">Pole
									wymagane</span><span class='error'>{{errors.firstName}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Nr. seryjny</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.meterWater.serialNumber"
								class="form-control" name="serialNumber" path='serialNumber'
								ng-required='true' ng-minlength="1" />
							<p class="help-block">
								<span class='error' ng-show="myForm.serialNumber.$invalid">Pole
									wymagane</span><span class='error'>{{errors.lastName}}</span>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Jednostka</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.meterWater.unit"
								class="form-control" name="unit" path='unit' ng-required='true' />
							<p class="help-block">
								<span class='error' ng-show="myForm.unit.$invalid">Niepoprawny
									Format</span><span class='error'>{{errors.unit}}</span>
							</p>
						</div>
					</div>


					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Mieszkanie</label>
						<div class="col-sm-9">
							<select name='apartment' ng-model="ctrl.meterWater.apartment"
								 class="form-control"
								ng-options="a.description for a in ctrl.apartments"><option>{{ctrl.tenant.apartment}}</option>
							</select>
							<p class="help-block">
								<span class='error' ng-show="myForm.meterWater.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.tenant_apartment}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="cwu" class="col-sm-3 control-label">Ciepła woda</label>
						<div class="col-sm-9">
							<label><input type="radio" ng-model="ctrl.meterWater.isWarmWater"
								value="true"> TAK </label> <label> <input type="radio"
								ng-model="ctrl.meterWater.isWarmWater" ng-value="false">NIE
							</label>
							<p class="help-block">
								<form:errors path="isWarmWater" class="error" />
							</p>
						</div>
					</div>


					<div class="form-group ">
						<div class="col-lg-12">
							<input type="submit"
								value="{{!ctrl.meterEnergry.id ? 'Dodaj' : 'Nadpisz'}}"
								class="btn btn-default " ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-default " ng-disabled="myForm.$pristine">Reset
							</button>
						</div>
					</div>
				</form>
				
			</div>


			<div id='list' class="row fadein fadeout showpanel panel"
				ng-show="toggle">
				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th>Numer Seryjny</th>
								<th>Opis</th>
								<th>Jednostka</th>
								<th>Mieszkanie</th>
								<th>CWU</th>
								<th>Edytuj/Usuń</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="a in ctrl.meterWaters">
								<td><span ng-bind="a.serialNumber"></span></td>
								<td><span ng-bind="a.description"></span></td>
								<td><span ng-bind="a.unit"></span></td>
								<td><span ng-bind="a.apartment.description"></span></td>
								<td><span ng-bind="a.isWarmWater"></span></td>
								<td>
									<button type="button" ng-click="ctrl.edit(a.id, $index)"
										class="btn-xs btn-warning">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
									</button>
									<button type="button" ng-click="ctrl.remove(a.id, $index)"
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
		src="<c:url value='/static/angular/apartment/apartment_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/apartment/apartment_controller.js' />"></script>
	<script
		src="<c:url value='/static/angular/meterWater/meterWater_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/meterWater/meterWater_controller.js' />"></script>
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