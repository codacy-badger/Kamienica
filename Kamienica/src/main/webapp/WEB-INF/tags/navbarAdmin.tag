<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${path}/Admin/home">Kamienica</a>
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
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%">
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
				<li><a href="${path}/User/userHome"><i
						class="fa fa-user fa-fw"></i>Strona Najemcy</a></li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i>Ustawienia</a></li>
				<li class="divider"></li>
				<li><a href="${path}/logout"><i
						class="fa fa-sign-out fa-fw"></i>Wyloguj</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">


				<li><a href="${path}/Admin/Apartment/apartment.html"><i
						class="fa fa-home" aria-hidden="true"></i> Mieszkania<span
						class="fa arrow"></span></a>
				<li><a href="${path}/Admin/Tenant/tenant.html"><i
						class="fa fa-users" aria-hidden="true"></i> Najemcy<span
						class="fa arrow"></span></a>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
						Konfiguracja<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href=#>Algorytm Podziału<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Division/divisionRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a href="${path}/Admin/Division/divisionList.html"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
								<li><a href="${path}/Admin/Division/divisionRest.html"><i
										class="fa fa-list" aria-hidden="true"></i> REST</a></li>
							</ul></li>
						<li><a href=#>Konfiguracja<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Settings/edit.html"><i
										class="fa fa-plus" aria-hidden="true"></i>Zmień</a></li>
								<li><a href="${path}/Admin/Settings/list.html"><i
										class="fa fa-list" aria-hidden="true"></i>Sprawdź</a></li>
							</ul></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-bar-chart"
						aria-hidden="true"></i> </i>Odczyty<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="panels-wells.html">Gaz<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Reading/readingGasRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a
									href="${path}/Admin/Reading/readingList.html?media=GAS"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>
						<li><a href="buttons.html">Woda<span class="fa arrow"></a>
							<ul class="nav nav-third-level">
								<li><a
									href="${path}/Admin/Reading/readingWaterRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a
									href="${path}/Admin/Reading/readingList.html?media=WATER"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>
						<li><a href="notifications.html">Energia<span
								class="fa arrow"></a>
							<ul class="nav nav-third-level">
								<li><a
									href="${path}/Admin/Reading/readingEnergyRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a
									href="${path}/Admin/Reading/readingList.html?media=ENERGY"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>

					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-newspaper-o"
						aria-hidden="true"></i> </i>Faktury<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="panels-wells.html"> Gaz<span
								class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Invoice/invoiceGasRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a href="${path}/Admin/Invoice/invoiceGasList.html"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>
						<li><a href="buttons.html">Woda<span class="fa arrow"></a>
							<ul class="nav nav-third-level">
								<li><a
									href="${path}/Admin/Invoice/invoiceWaterRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a href="${path}/Admin/Invoice/invoiceWaterList.html"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>
						<li><a href="notifications.html">Energia<span
								class="fa arrow"></a>
							<ul class="nav nav-third-level">
								<li><a
									href="${path}/Admin/Invoice/invoiceEnergyRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a href="${path}/Admin/Invoice/invoiceEnergyList.html"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>

					</ul>
				<li><a href="#"><i class="fa fa-money" aria-hidden="true"></i>
						Opłaty<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${path}/Admin/Payment/paymentEnergyList"><i
								class="fa fa-list" aria-hidden="true"></i> Energia</a></li>
						<li><a href="${path}/Admin/Payment/paymentWaterList"><i
								class="fa fa-list" aria-hidden="true"></i> Woda</a></li>
						<li><a href="${path}/Admin/Payment/paymentGasList"><i
								class="fa fa-list" aria-hidden="true"></i> Gaz</a></li>
					</ul> <!-- /.nav-second-level --></li>

				<li><a href="#"><i class="fa fa-bars"></i> Liczniki<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${path}/Admin/Meter/energy.html"><i
								class="fa fa-plus" aria-hidden="true"></i> Energia</a></li>
						<li><a href="${path}/Admin/Meter/gas.html"><i
								class="fa fa-plus" aria-hidden="true"></i> Gaz</a></li>
						<li><a href="${path}/Admin/Meter/water.html"><i
								class="fa fa-plus" aria-hidden="true"></i> Woda</a></li>


					</ul> <!-- /.nav-second-level --></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
