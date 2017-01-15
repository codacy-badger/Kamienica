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
<title>Najemcy</title>
</head>
<body ng-app="myApp">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="TenantController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Najemcy</h1>
					<button id='mySwitch' ng-click="toggle = !toggle"
						class="btn btn-default btn-sm">{{text}}</button>

				</div>
			</div>
			<div class="alert alert-danger" ng-show="errorField">
				<strong>BŁĄD: </strong> {{errorMsg}}
			</div>


			<div id='list' class="row fadein fadeout showpanel panel"
				ng-show="toggle">
				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th>Najemca</th>

								<th>E-mail</th>
								<th>Telefon</th>
								<th>Mieszkanie</th>
								<th>Data Wprowadzenia</th>
								<th>Prawa</th>
								<th>Status</th>

								<th>Edytuj/Usuń</th>

							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="a in ctrl.tenants">
								<td><span ng-bind="a.firstName"></span> <span
									ng-bind="a.lastName"></span></td>

								<td><span ng-bind="a.email"></span></td>
								<td><span ng-bind="a.phone"></span></td>
								<td><span ng-bind="a.apartment.description"></span></td>
								<td><span ng-bind="a.movementDate"></span></td>
								<td><span ng-bind="a.role"></span></td>
								<td><span ng-bind="a.status"></span></td>

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
			<div id='form' class="fadein fadeout showpanel panel row"
				ng-show="!toggle">

				<form class="form-horizontal" ng-submit="ctrl.submit()"
					name="myForm">
					<input path="id" readonly="true" type='hidden' /> <input
						type="hidden" ng-model="ctrl.id" />
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Imię</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.tenant.firstName"
								class="form-control" name="firstName" ng-required='true'
								ng-minlength="4" />
							<p class="help-block">
								<span class='error' ng-show="myForm.firstName.$invalid">Pole
									wymagane</span><span class='error'>{{errors.firstName}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Nazwisko</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.tenant.lastName"
								class="form-control" name="lastName" path='lastName'
								ng-required='true' ng-minlength="4" />
							<p class="help-block">
								<span class='error' ng-show="myForm.lastName.$invalid">Pole
									wymagane</span><span class='error'>{{errors.lastName}}</span>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">E-mail</label>
						<div class="col-sm-9">
							<input type="email" ng-model="ctrl.tenant.email"
								class="form-control" name="email" path='email'
								ng-required='true' />
							<p class="help-block">
								<span class='error' ng-show="myForm.email.$invalid">Niepoprawny
									Format</span><span class='error'>{{errors.email}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Telefon</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.tenant.phone"
								class="form-control" path='phone' placeholder="Opcjonalne"
								name="phone" ng-pattern="/^[0-9]{1,12}$/" ng-min=0 />

							<!-- ng-required='true' ng-pattern="/^[0-9]{1,7}$/"  -->
							<p class="help-block">
								<span class='error' ng-show="myForm.phone.$invalid">Tylko
									liczby</span> <span class='error'>{{errors.phone}}</span>
							</p>
						</div>
					</div>
					<!-- https://scotch.io/tutorials/angularjs-form-validation -->

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Mieszkanie</label>
						<div class="col-sm-9">
							<select name='apartment' ng-model="ctrl.tenant.apartment"
								ng-required='true' class="form-control"
								ng-options="a.description for a in ctrl.apartments"><option>{{ctrl.tenant.apartment}}</option>
							</select>



							<!--  select name='apartment' ng-model="ctrl.tenant.apartment"
								ng-required='true' class="form-control">
								<option ng-repeat="item in ctrl.apartments"
									value="{{item}}"
									ng-selected="ctrl.tenant.apartment.description == item.description">
									{{item.description}}</option>
									
							</select-->


							<!--  select name="repeatSelect" id="repeatSelect"
								ng-model="ctrl.apartment" class="form-control" ng-required='true'>
								<option ng-repeat="option in ctrl.apartments"
									value="{{option}}">{{option.description}} test</option>
							</select-->
							<!-- http://stackoverflow.com/questions/37442061/angularjs-ng-options-with-nested-json-based-on-previous-select-option -->


							<p class="help-block">
								<span class='error' ng-show="myForm.tenant_apartment.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.tenant_apartment}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Data
							Wprowadzenia</label>
						<div class="col-sm-9">
							<input type="text" datetime="yyyy-MM-dd"
								ng-model="ctrl.tenant.movementDate" class="form-control"
								path='movementDate' placeholder="YYYY/MM/DD" name="movementDate"
								ng-required='true' />
							<p class="help-block">
								<span class='error' ng-show="myForm.movementDate.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.movementDate}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Prawa</label>
						<div class="col-sm-9">
							<input type="radio" ng-model="ctrl.tenant.role" value="TENANT"
								required name='role' />Najemca <input type="radio"
								ng-model="ctrl.tenant.role" value="OWNER" required name='role' />Właściciel

							<p class="help-block">
								<span class='error' ng-show="myForm.role.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.role}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">Status</label>
						<div class="col-sm-9">
							<input type="radio" ng-model="ctrl.tenant.status" value="ACTIVE"
								required name='status' />Aktywny <input type="radio"
								ng-model="ctrl.tenant.status" value="INACTIVE" required
								name='status' /> Nieaktywny

							<p class="help-block">
								<span class='error' ng-show="myForm.status.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.status}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Hasło</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.tenant.password"
								class="form-control" name="password" ng-required='true'
								ng-minlength="5" value='sdf' />
							<p class="help-block">
								<span class='error' ng-show="myForm.password.$invalid">Pole
									wymagane</span><span class='error'>{{errors.password}}</span>
							</p>
						</div>
					</div>

					<div class="form-group ">
						<div class="col-lg-12">
							<input type="submit"
								value="{{!ctrl.tenant.id ? 'Dodaj' : 'Nadpisz'}}"
								class="btn btn-default " ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-default " ng-disabled="myForm.$pristine">Reset
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="<c:url value='/static/js/angular.js' />"></script>
	<script src="<c:url value='/static/js/angular-resource.js' />"></script>
	<script src="<c:url value='/static/angular/app.js' />"></script>
	<script
		src="<c:url value='/static/angular/apartment/apartment_service.js' />"></script>
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