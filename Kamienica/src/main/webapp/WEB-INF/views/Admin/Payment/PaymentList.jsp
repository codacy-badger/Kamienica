<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<mytags:navbarAdmin />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista Opłat</title>
</head>
<body>
<mytags:navbarAdmin />
	

	<div class='container'>
		<div class='row'>
			<div class='jumbotron'>
				<h1>Lista Opłat</h1>

				<a href="../home.html">Strona Główna</a>
			</div>
		</div>


		<div class="row">
			<div class="col-md-12">
				<div class="list-group">
					<p class="list-group-item active myListGroup">Wybierz Media</p>
					<a href="paymentGasList.html" class="list-group-item">Gaz</a> <a
						href="paymentWaterList.html" class="list-group-item">Woda</a> <a
						href="paymentEnergyList.html" class="list-group-item">Energia</a>




				</div>
			</div>
		</div>
	</div>

</body>
</html>