<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:url var="rejestrujMieszkanie"
					value="/Admin/Apartment/apartmentSave.html" />
				<form class="form-horizontal" role="form" modelAttribute="apartment"
					method="post" action="${rejestrujMieszkanie}">


					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Domofon</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="intercom"
								placeholder="Pole wymagane">
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
								placeholder="Pole wymagane" name="apartmentNumber">
							<p class="help-block">
								<form:errors path="apartmentNumber" class="error" />
							</p>
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">Opis</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="inputEmail3"
								placeholder="Pole wymagane" name="description">
							<p class="help-block">
								<form:errors path="description" class="error" />
							</p>
						</div>
					</div>

					<button type="submit" class="btn btn-default">Zapisz</button>
					<button class="btn btn-default" type="reset">Resetuj</button>
				</form>

</body>
</html>