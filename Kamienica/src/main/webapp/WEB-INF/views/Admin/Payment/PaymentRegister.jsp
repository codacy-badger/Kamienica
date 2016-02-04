<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>



<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="../../Admin/home">Strona Główna</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">



				<!-- Faktury -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Faktury <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li class="dropdown-header">Wprowadź nowe</li>
						<li><a href="../../Admin/Invoice/invoiceGasRegister">Gaz</a></li>
						<li><a href="../../Admin/Invoice/invoiceEnergyRegister">Energia</a></li>
						<li><a href="../../Admin/Invoice/invoiceWaterRegister">Woda</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../../Admin/Invoice/invoiceGasList">Gaz</a></li>
						<li><a href="../../Admin/Invoice/invoiceEnergyList">Energia</a></li>
						<li><a href="../../Admin/Invoice/invoiceWaterList">Woda</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Odczyty <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li class="dropdown-header">Wprowadź nowe</li>
						<li><a href="../../Admin/Reading/readingGasRegister">Gaz</a></li>
						<li><a href="../../Admin/Reading/readingEnergyRegister">Energia</a></li>
						<li><a href="../../Admin/Reading/readingWaterRegister">Woda</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../../Admin/Reading/readingGasList">Gaz</a></li>
						<li><a href="../../Admin/Reading/readingEnergyList">Energia</a></li>
						<li><a href="../../Admin/Reading/readingWaterList">Woda</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Opłaty <span class="caret"></span></a>

					<ul class="dropdown-menu">

						<li><a href="../../Admin/Payment/paymentRegister">Wprowadź
								Nowe</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Sprawdź/Edytuj</li>
						<li><a href="../../Admin/Payment/paymentGasList">Gaz</a></li>
						<li><a href="../../Admin/Payment/paymentEnergyList">Energia</a></li>
						<li><a href="../../Admin/Payment/paymentWaterList">Woda</a></li>

					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Nawiguj <span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="../../index">Strona Powitalna</a></li>
						<li><a href="../../User/userHome">Strona Użytkownika</a></li>
					</ul></li>

				<li><a href="../../logout.html">Wyloguj</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>


	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Rejestracja Opłat</h1>
			</div>
		</div>
		<div class='row'>
			<c:if test="${!empty model.error}">
				<p>${ model.error}
			</c:if>
		</div>
		<c:if test="${empty model.error}">
			<div class='row'>
				<c:url var="paymentSave" value="/Admin/Payment/paymentSave.html" />
				<form:form id="paymentSave" modelAttribute="model" method="post"
					action="${paymentSave}">

					<div class='row'>
						<div class="col-md-4">


							<div class="radio">
								<label><input type="radio" name="energyFirst" checked>${model.energyFirst.date },
									${model.energyFirst.totalAmount },
									${model.energyFirst.baseReading.readingDate}</label>
							</div>


							<c:forEach items="${model.energy }" var="energy">
								<div class="radio">
									<label><input type="radio" name="energyRest" checked>${energy.date },
										${energy.totalAmount }, ${energy.baseReading.readingDate}</label>
								</div>

							</c:forEach>

						</div>
						<div class="col-md-4">


							lorem

						</div>
						<div class="col-md-4">


							lorem

						</div>
					</div>

					<table height="150px" width="70%">
						<tr>
							<c:if test="${!empty model.energyFirstBinder}">
								<td valign="top">
									<table
										style="background-color: black; color: white; text-align: center;"
										width="100%">
										<tr
											style="background-color: teal; color: white; text-align: center;">
											<th colspan="4">Energia</th>
										</tr>
										<tr
											style="background-color: teal; color: white; text-align: center;">
											<th style="background-color: teal"></th>
											<th>Wartość Faktury</th>
											<th>Data Faktury</th>
											<th>Data Odczytu</th>
										</tr>
										<tr style="background-color: grey"
											onclick="style=background-color: blue">
											<td><input type="radio" name="energyFirst"
												value="energy&${model.energyFirstBinder.invoice.id}&${ model.energyFirstBinder.date}"
												checked="checked" readonly="readonly"></td>
											<td>${model.energyFirstBinder.invoice.totalAmount }</td>
											<td>${model.energyFirstBinder.invoice.date }</td>
											<td>${model.energyFirstBinder.date}</td>
										</tr>
										<c:forEach items="${model.energyBinder }" var="binder">
											<tr
												style="background-color: white; color: black; text-align: center;">
												<td><input type="radio" name="energyLast"
													value="energy&${binder.invoice.id}&${binder.date}"
													checked="checked" readonly="readonly"></td>
												<td>${binder.invoice.totalAmount}</td>
												<td>${binder.invoice.date}</td>
												<td>${binder.date}</td>
											</tr>
										</c:forEach>


										<c:if test="${!empty model.invoiceEnergy }">
											<c:forEach items="${model.invoiceEnergy }" var="display">
												<tr
													style="background-color: white; color: black; text-align: center;">
													<td></td>
													<td>${ display.totalAmount}</td>
													<td>${ display.date}</td>
													<td>(BRAK)</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${!empty model.readingDatesEnergy }">
											<c:forEach items="${model.readingDatesEnergy }" var="display">
												<tr
													style="background-color: white; color: black; text-align: center;">
													<td></td>
													<td>(BRAK)</td>
													<td>(BRAK)</td>
													<td>${ display}</td>
												</tr>
											</c:forEach>
										</c:if>
									</table>
								</td>
							</c:if>
							<c:if test="${!empty model.waterFirstBinder}">
								<td valign="top">
									<table border="0" width="100%"
										style="background-color: black; color: white; text-align: center;">
										<tr>
											<th colspan="4"
												style="background-color: teal; color: white; text-align: center;">Woda</th>
										</tr>
										<tr
											style="background-color: teal; color: white; text-align: center;">
											<th></th>
											<th>Wartość Faktury</th>
											<th>Data Faktury</th>
											<th>Data Odczytu</th>
										</tr>
										<tr style="background-color: grey">
											<td><input type="radio" name="waterFirst"
												value="water&${model.waterFirstBinder.invoice.id}&${ model.waterFirstBinder.date}"
												checked="checked" readonly="readonly"></td>
											<td>${model.waterFirstBinder.invoice.totalAmount }</td>
											<td>${model.waterFirstBinder.invoice.date }</td>
											<td>${model.waterFirstBinder.date}</td>
										</tr>
										<c:forEach items="${model.waterBinder }" var="binder">
											<tr
												style="background-color: white; color: black; text-align: center;">
												<td><input type="radio" name="waterLast"
													value="water&${binder.invoice.id}&${binder.date}"
													checked="checked" readonly="readonly"></td>
												<td>${binder.invoice.totalAmount}</td>
												<td>${binder.invoice.date}</td>
												<td>${binder.date}</td>
											</tr>
										</c:forEach>

										<c:if test="${!empty model.invoiceWater }">
											<c:forEach items="${model.invoiceWater }" var="display">
												<tr
													style="background-color: grey; color: black; text-align: center;">
													<td></td>
													<td>${ display.totalAmount}</td>
													<td>${ display.date}</td>
													<td>(BRAK)</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${!empty model.readingDatesWater }">
											<c:forEach items="${model.readingDatesWater }" var="display">
												<tr
													style="background-color: grey; color: black; text-align: center;">
													<td></td>
													<td>(BRAK)</td>
													<td>(BRAK)</td>
													<td>${ display}</td>
												</tr>
											</c:forEach>
										</c:if>

									</table>
								</td>
							</c:if>
							<c:if test="${!empty model.gasFirstBinder}">
								<td valign="top">
									<table border="0" width="100%"
										style="background-color: black; color: white; text-align: center;">
										<tr>
											<th colspan="4"
												style="background-color: teal; color: white; text-align: center;">Gaz</th>
										</tr>
										<tr
											style="background-color: teal; color: white; text-align: center;">
											<th></th>
											<th>Wartość Faktury</th>
											<th>Data Faktury</th>
											<th>Data Odczytu</th>
										</tr>
										<tr style="background-color: grey">
											<td><input type="radio" name="gasFirst"
												value="gas&${model.gasFirstBinder.invoice.id}&${ model.gasFirstBinder.date}"
												checked="checked" readonly="readonly"></td>
											<td>${model.gasFirstBinder.invoice.totalAmount }</td>
											<td>${model.gasFirstBinder.invoice.date }</td>
											<td>${model.gasFirstBinder.date}</td>
										</tr>
										<c:forEach items="${model.gasBinder }" var="binder">
											<tr
												style="background-color: white; color: black; text-align: center;">
												<td><input type="radio" name="gasLast"
													value="gas&${binder.invoice.id}&${binder.date}"
													checked="checked" readonly="readonly"></td>
												<td>${binder.invoice.totalAmount}</td>
												<td>${binder.invoice.date}</td>
												<td>${binder.date}</td>
											</tr>
										</c:forEach>

										<c:if test="${!empty model.invoiceGas }">
											<c:forEach items="${model.invoiceGas }" var="display">
												<tr
													style="background-color: white; color: black; text-align: center;">
													<td></td>
													<td>${ display.totalAmount}</td>
													<td>${ display.date}</td>
													<td>(BRAK)</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${!empty model.readingDatesGas }">
											<c:forEach items="${model.readingDatesGas }" var="display">
												<tr
													style="background-color: white; color: black; text-align: center;">
													<td></td>
													<td>(BRAK)</td>
													<td>(BRAK)</td>
													<td>${ display}</td>
												</tr>
											</c:forEach>
										</c:if>

									</table>
								</td>
							</c:if>
					</table>

					<input type="submit" value="Wprowadź opłaty">
				</form:form>
			</div>
		</c:if>
	</div>
</body>
</html>