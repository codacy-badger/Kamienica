<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Kamienica - Strona główna</title>

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

<!-- Timeline CSS -->
<link href="<c:url value='/static/css/timeline.css' />" rel="stylesheet">


<!-- Morris Charts CSS -->
<link href="<c:url value='/static/css/morris.css' />" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Strona Główna</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>Prace Serwisowe</strong> <span
									class="pull-right text-muted"> <em>2016-06-10</em>
								</span>
							</div>
							<div>27 czerwca nastąpią prace serwisowe. Dostęp do
								aplikacji może być ograniczony</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					
				</ul> <!-- /.dropdown-messages --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<li><a href="#">
							<div>
								<p>
									<strong>Task 1</strong> <span class="pull-right text-muted">40%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: 40%">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 2</strong> <span class="pull-right text-muted">20%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-info" role="progressbar"
										aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
										style="width: 20%">
										<span class="sr-only">20% Complete</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 3</strong> <span class="pull-right text-muted">60%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%">
										<span class="sr-only">60% Complete (warning)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 4</strong> <span class="pull-right text-muted">80%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="80" aria-valuemin="0"
										aria-valuemax="100" style="width: 80%">
										<span class="sr-only">80% Complete (danger)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-tasks --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-alerts --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">


					<li><a href="#"><i class="fa fa-home" aria-hidden="true"></i>
							Mieszkania<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/Kamienica/Admin/Apartment/apartmentRegister.html">Dodaj</a></li>
							<li><a href="/Kamienica/Admin/Apartment/apartmentList.html">Lista</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-users" aria-hidden="true"></i>
							Najemcy<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="flot.html">Dodaj</a></li>
							<li><a href="morris.html">Lista</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
							Konfiguracja<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="flot.html">Algorytm Podziału</a></li>
							<li><a href="morris.html">Ustawienia Początkowe</a></li>
						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-bar-chart"
							aria-hidden="true"></i> </i>Odczyty<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="panels-wells.html">Gaz<span
									class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="buttons.html">Woda<span class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="notifications.html">Energia<span
									class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>

						</ul> <!-- /.nav-second-level --></li>
					<li><a href="#"><i class="fa fa-money" aria-hidden="true"></i>
							</i>Faktury<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="panels-wells.html"> Gaz<span
									class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="buttons.html">Woda<span class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="notifications.html">Energia<span
									class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>

						</ul>
					<li><a href="#"><i class="fa fa-bars"></i> Liczniki<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="panels-wells.html"> Gaz<span
									class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="buttons.html">Woda<span class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>
							<li><a href="notifications.html">Energia<span
									class="fa arrow"></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Nowe</a></li>
									<li><a href="#">Lista</a></li>
								</ul></li>

						</ul> <!-- /.nav-second-level --></li>
					<!-- /.nav-second-level -->
					</li>

				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Kamienica - Strona Główna Admina</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div>
										<h2>26</h2>
									</div>
									<div>Konfiguracja??</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa  fa-calendar-o fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div>
										<h2>Gaz: 10</h2>
									</div>
									<div>Najstarsza opłata</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Dodaj nową opłatę</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-calendar-o fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">
										<h2>Energia: 30</h2>
									</div>
									<div>Najstarszy odczyt</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Dodaj nowe odczyty</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-home fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div>
										<h2>13</h2>
									</div>
									<div>Liczba Pustych Mieszkań</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Sprawdź Puste Mieszkania</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-8">

					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Zużycie mediów
							<div class="pull-right">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										Actions <span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right" role="menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-4">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th>#</th>
													<th>Date</th>
													<th>Time</th>
													<th>Amount</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>3326</td>
													<td>10/21/2013</td>
													<td>3:29 PM</td>
													<td>$321.33</td>
												</tr>
												<tr>
													<td>3325</td>
													<td>10/21/2013</td>
													<td>3:20 PM</td>
													<td>$234.34</td>
												</tr>
												<tr>
													<td>3324</td>
													<td>10/21/2013</td>
													<td>3:03 PM</td>
													<td>$724.17</td>
												</tr>
												<tr>
													<td>3323</td>
													<td>10/21/2013</td>
													<td>3:00 PM</td>
													<td>$23.71</td>
												</tr>
												<tr>
													<td>3322</td>
													<td>10/21/2013</td>
													<td>2:49 PM</td>
													<td>$8345.23</td>
												</tr>
												<tr>
													<td>3321</td>
													<td>10/21/2013</td>
													<td>2:23 PM</td>
													<td>$245.12</td>
												</tr>
												<tr>
													<td>3320</td>
													<td>10/21/2013</td>
													<td>2:15 PM</td>
													<td>$5663.54</td>
												</tr>
												<tr>
													<td>3319</td>
													<td>10/21/2013</td>
													<td>2:13 PM</td>
													<td>$943.45</td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.col-lg-4 (nested) -->
								<div class="col-lg-8">
									<div id="morris-bar-chart"></div>
								</div>
								<!-- /.col-lg-8 (nested) -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->

				</div>
				<!-- /.col-lg-8 -->
				<div class="col-lg-4">

					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Wielkośc opłat wg.
							mieszkań
						</div>
						<div class="panel-body">
							<div id="morris-donut-chart"></div>
							<a href="#" class="btn btn-default btn-block">View Details</a>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->


				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<c:url value='/static/js/metisMenu.min.js' />"></script>

	<!-- Morris Charts JavaScript -->
	<script src="<c:url value='/static/js/raphael-min.js' />"></script>
	<script src="<c:url value='/static/js/morris.min.js' />"></script>
	<script src="<c:url value='/static/js/morris-data.js' />"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/static/js/sb-admin-2.js' />"></script>

</body>

</html>
