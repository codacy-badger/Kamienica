<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Payment List</title>
<link class="row" href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">

<link class="row" href="<c:url value='/static/css/style.css' />"
	rel="stylesheet">


<script type="text/javascript"
	src="<c:url value='/static/js/jquery-2.2.0.js' />"></script>


<script type="text/javascript" src="<c:url value='/static/js/jq.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/jquery.validate.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/static/js/bootstrap.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
				<h1>Lista Opłat: ${media}</h1>
			</div>
		</div>

		<c:if test="${!empty payment}">
			<table class='table table-stripped table-hover' width="600px">
				<tr>
					<th>Id</th>
					<th>Wartość Opłaty</th>
					<th>Data Rachunku</th>
					<th>Najemca</th>
					<th>Data Faktury</th>
					<th>Numer Faktury</th>
				</tr>
				<c:forEach items="${payment}" var="p">
					<tr>
						<td><c:out value="${p.id}" /></td>
						<td><c:out value="${p.paymentAmount}" /></td>
						<td><c:out value="${p.paymentDate}" /></td>
						<td><c:out value="${p.tenant.firstName}" /> <c:out
								value="${p.tenant.lastName}" /></td>
						<td><c:out value="${p.invoice.date}" /></td>
						<td><c:out value="${p.invoice.serialNumber}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>