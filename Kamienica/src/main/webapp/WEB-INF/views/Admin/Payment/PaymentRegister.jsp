<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja Nowego Mieszkania</title>
</head>
<body>
	<center>
		<br> <br> <br> <br> <br> <br>
		<div style="color: teal; font-size: 30px">Rejestracja Opłat</div>
		<br> <br>

		<c:url var="paymentSave" value="/Admin/Payment/paymentSave.html" />
		<form:form id="paymentSave" modelAttribute="model" method="post"
			action="${paymentSave}">

			<c:if test="${!empty model.error}">
				<p>${ model.error}
			</c:if>
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
								<tr style="background-color: grey" onclick="style=background-color: blue">
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
			<input type="submit" value="Wprowadź opłaty"
				${ !empty model.error ? 'disabled="disabled"' : ''}>
		</form:form>
		<br> <br> <a href="../home.html">Strona
			Główna</a>
	</center>
</body>
</html>