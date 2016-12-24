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

		<!-- tu wkleic kawalekKoduNabar -->

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

                <li><a href="${path}/Admin/Residence/residence.html"><i class="fa fa-building" aria-hidden="true"></i> Nieruchomości</a></li>
				<li><a href="${path}/Admin/Apartment/apartment.html"><i class="fa fa-home" aria-hidden="true"></i> Mieszkania</a></li>
				<li><a href="${path}/Admin/Tenant/tenant.html"><i class="fa fa-users" aria-hidden="true"></i> Najemcy</a>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
						Konfiguracja<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href=#>Algorytm Podziału<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Division/divisionRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a href="${path}/Admin/Division/divisionList.html"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
							</ul></li>
						<!-- <li><a href=#>Konfiguracja<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="${path}/Admin/Settings/edit.html"><i
										class="fa fa-plus" aria-hidden="true"></i>Zmień</a></li>
								<li><a href="${path}/Admin/Settings/list.html"><i
										class="fa fa-list" aria-hidden="true"></i>Sprawdź</a></li>
							</ul></li> -->
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
							<!-- 	<li><a href="${path}/Admin/Reading/gasRest.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Rest</a></li> -->
							</ul></li>
						<li><a href="buttons.html">Woda<span class="fa arrow"></a>
							<ul class="nav nav-third-level">
								<li><a
									href="${path}/Admin/Reading/readingWaterRegister.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Nowe</a></li>
								<li><a
									href="${path}/Admin/Reading/readingList.html?media=WATER"><i
										class="fa fa-list" aria-hidden="true"></i> Lista</a></li>
								<!-- <li><a href="${path}/Admin/Reading/waterRest.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Rest</a></li> -->
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
								<!-- <li><a href="${path}/Admin/Reading/energyRest.html"><i
										class="fa fa-plus" aria-hidden="true"></i> Rest</a></li> -->
							</ul></li>

					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-newspaper-o"
						aria-hidden="true"></i>Faktury<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${path}/Admin/Invoice/energy.html"><i
								aria-hidden="true"></i>Energia</a></li>
						<li><a href="${path}/Admin/Invoice/gas.html"><i
								aria-hidden="true"></i>Gaz</a></li>
						<li><a href="${path}/Admin/Invoice/water.html"><i
								aria-hidden="true"></i>Woda</a></li>

					</ul>
				<li><a href="#"><i class="fa fa-money" aria-hidden="true"></i>
						Opłaty<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${path}/Admin/Payment/payment?media=ENERGY">Energia</a></li>
						<li><a href="${path}/Admin/Payment/payment?media=GAS">Woda</a></li>
						<li><a href="${path}/Admin/Payment/payment?media=WATER">Gaz</a></li>
					</ul> <!-- /.nav-second-level --></li>

				<li><a href="#"><i class="fa fa-bars"></i> Liczniki<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${path}/Admin/Meter/energy.html">Energia</a></li>
						<li><a href="${path}/Admin/Meter/gas.html">Gaz</a></li>
						<li><a href="${path}/Admin/Meter/water.html">Woda</a></li>


					</ul> <!-- /.nav-second-level --></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
