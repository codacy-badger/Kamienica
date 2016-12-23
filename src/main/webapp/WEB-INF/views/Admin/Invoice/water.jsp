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
<title>Faktury - Woda</title>
</head>
<body ng-app="myApp">
	<div id="wrapper">
		<mytags:navbarAdmin />

		<div id="page-wrapper" ng-controller="InvoiceController as ctrl">
			<div class='row'>
				<div class="col-lg-12">
					<h1 class="page-header well">Faktury za Wodę</h1>

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

				<form class="form-horizontal" ng-submit="ctrl.submit()"
					name="myForm">

					<input path="id" readonly="true" type='hidden' /> <input
						type="hidden" ng-model="ctrl.invoice.id" />

					<div class="form-group">
						<label for="serialNumber" class="col-sm-3 control-label">Numer
							Faktury</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.invoice.serialNumber"
								class="form-control" path='serialNumber'
								placeholder="Pole wymagane" ng-required='true'
								name="serialNumber" />
							<p class="help-block">
								<span class='error' ng-show="myForm.serialNumber.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.serialNumber}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-3 control-label">Data
							Wystawienia</label>
						<div class="col-sm-9">
							<input type="text" datetime="yyyy-MM-dd"
								ng-model="ctrl.invoice.date" class="form-control" path='date'
								placeholder="YYYY/MM/DD" name="date" ng-required='true' />
							<p class="help-block">
								<span class='error' ng-show="myForm.date.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.date}}</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="totalAmount" class="col-sm-3 control-label">Wartość
							Faktury</label>
						<div class="col-sm-9">
							<input type="text" ng-model="ctrl.invoice.totalAmount"
								class="form-control" path='totalAmount' name="totalAmount"
								ng-pattern="/^[0-9]{1,12}$/" ng-min=0 ng-required='true' />
							<p class="help-block">
								<span class='error' ng-show="myForm.totalAmount.$invalid">Tylko
									liczby</span> <span class='error'>{{errors.totalAmount}}</span>
							</p>
						</div>

					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Odczyty</label>
						<div class="col-sm-9">
							<select name='apartment' ng-model="ctrl.invoice.baseReading"
								ng-required='true' class="form-control"
								ng-options="a.readingDate for a in ctrl.readings"><option>{{ctrl.inovice.readings}}</option>
							</select>
							<p class="help-block">
								<span class='error' ng-show="myForm.baseReading.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.baseReading}}</span>
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


			<div id='list' class="row fadein fadeout showpanel panel"
				ng-show="toggle">
				<div>
					<table class='table table-stripped table-hover'>
						<thead>
							<tr>
								<th>Nr Faktury</th>
								<th>Data</th>
								<th>Wartość</th>
								<!-- th>Edytuj</th -->
								<th>Usuń</th>

							</tr>
						</thead>
						<tbody>

							<tr ng-repeat="a in ctrl.invoices">
								<td><span ng-bind="a.serialNumber"></span></td>
								<td><span ng-bind="a.date"></span></td>
								<td><span ng-bind="a.totalAmount"></span></td>
								<td>

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
		src="<c:url value='/static/angular/invoice/water/baseReading_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/invoice/water/invoice_service.js' />"></script>
	<script
		src="<c:url value='/static/angular/invoice/water/invoice_controller.js' />"></script>
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