<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

                <html>

                <head>
                    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">

                    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
                    <link href="<c:url value='/static/css/sb-admin-2.css' />" rel="stylesheet">
                    <link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">

                    <!-- MetisMenu CSS -->
                    <link href="<c:url value='/static/css/metisMenu.min.css' />" rel="stylesheet">


                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Odczyty- Restowo</title>
                </head>

                <body ng-app="myApp">
                    <div id="wrapper">
                        <mytags:navbarAdmin />

                        <div id="page-wrapper" ng-controller="ReadingController as ctrl">
                            <div class='row'>
                                <div class="col-lg-12">
                                    <h1 class="page-header well">Odczyty - {{media}}</h1>

                                    <div class="alert alert-danger" ng-show="errorField">
                                        <strong>BŁĄD: </strong> {{errorMsg}}
                                    </div>


                                    <div class="btn-group" role="group">
                                        <button type="button" ng-click="ctrl.switchMedia('ENERGY')" class="btn btn-default btn-sm">Energia</button>
                                        <button type="button" ng-click="ctrl.switchMedia('GAS')" class="btn btn-default btn-sm">Gaz</button>
                                        <button type="button" ng-click="ctrl.switchMedia('WATER')" class="btn btn-default btn-sm">Woda</button>
                                        <select ng-model="res" ng-change="ctrl.switchResidence(res)" ng-options="item as (item.street+' '+item.number+'- '+item.city) for item in residences" class="btn btn-default btn-sm dropdown-toggle"></select>
                                        <!--                                        <pre>{{res | json}}</pre>-->
                                    </div>


                                </div>
                            </div>

                            <div class='row'>
                                <div class="col-lg-12">
                                    <button id='mySwitch' ng-click="ctrl.switchForm()" class="btn btn-default btn-sm">{{text}}</button>
                                </div>
                            </div>

                            <div id='list' class="row fadein fadeout showpanel panel" ng-show="toggle">

                                <div ng-switch on="!!res">
                                    <div ng-switch-when="false">
                                        <h2>Wybierz nieruchomość, dla której wyświetlić odczyty</h2>
                                    </div>
                                    <div ng-switch-default>
                                        <div>
                                            <table class='table table-stripped table-hover' ng-if="ctrl.readings.length !=0">
                                                <thead>
                                                    <tr>
                                                        <th>Data</th>
                                                        <th>Wartość Odczytu</th>

                                                        <th>Licznik</th>
                                                        <th>Usuń</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr ng-repeat="r in ctrl.readings">
                                                        <td><span ng-bind="r.readingDetails.readingDate"></span></td>
                                                        <td><span ng-bind="r.value"></span></td>

                                                        <td><span ng-bind="r.meter.description"></span></td>
                                                        <td>
                                                            <button ng-if="latestDate == r.readingDetails.readingDate" type="button" ng-click="ctrl.remove()" class="btn-xs btn-danger ">
										<i class="fa fa-times" aria-hidden="true"></i>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                            <h2 ng-if="ctrl.readings.length ==0">Brak odczytów</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div id='form' class="fadein fadeout showpanel panel row" ng-show="!toggle">

                                <!-- http://www.w3schools.com/angular/tryit.asp?filename=try_ng_validate_show -->
                                <form class="form-horizontal" ng-submit="ctrl.submit()" name="myForm">

                                    <div ng-switch on="!!res">
                                        <div ng-switch-when="false">
                                            <h2>Wybierz nieruchomość, dla której chcesz wprowadzić nowe odczyty</h2>
                                        </div>
                                        <div ng-switch-default>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Data</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="date" class="form-control" name="date" path="date" ng-pattern="/^[0-9]{1,7}$/" placeholder="YYYY-MM-DD" ng-required='true' />-->

                                                    <div class="col-sm-9">
                                                        <input type="date" datetime="yyyy-MM-dd" ng-model="ctrl.newReadingsForm.readingDetails.readingDate" class="form-control" placeholder="YYYY/MM/DD" name="date" ng-required='true' />
                                                        <p class="help-block">
                                                            <span class='error' ng-show="myForm.date.$invalid">Pole
									wymagane</span> <span class='error'>{{errors.contractStart}}</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <!--
                                                <p class="help-block">
                                                    <span class='error' ng-show="myForm.date.$invalid">Pole wymagane</span><span class='error'>{{errors.date}}</span>
                                                </p>
-->
                                            </div>

                                            <div class="row">
                                                <label class="col-sm-3 text-right">Opis Licznika </label><label class="col-sm-3 ">Wartość Odczytu</label>
                                            </div>


                                            <div class="form-group" ng-repeat="reading in ctrl.newReadingsForm.readings">
                                                <label class="col-sm-3 control-label"><span ng-bind="reading.meter.description"></span></label>

                                                <div class="col-sm-9">
                                                    <input type="number" class="form-control" step=".01" ng-model="reading.value" ng-min="reading.value" />
                                                    <!--                                                    <input type="number" ng-model="reading.id" class="form-control" ng-step="any" ng-min="reading.value" ng-init="reading.id = m.value" />-->
                                                    <p class="help-block">
                                                        <span class='error' ng-show="myForm.reading.value.$invalid">Pole
									wymagane</span>
                                                    </p>
                                                </div>
                                            </div>

                                            <div class="form-group ">
                                                <button type="submit" class="btn btn-default">Zapisz</button>
                                                <button class="btn btn-default" type="reset">Resetuj</button>
                                            </div>







                                        </div>
                                    </div>
                                </form>


                            </div>
                        </div>
                    </div>

                    <script src="<c:url value='/static/js/angular.js' />"></script>
                    <script src="<c:url value='/static/js/angular-resource.js' />"></script>
                    <script src="<c:url value='/static/angular/app.js' />"></script>
                    <script src="<c:url value='/static/angular/reading/meterService.js' />"></script>
                    <script src="<c:url value='/static/angular/reading/residenceService.js' />"></script>
                    <script src="<c:url value='/static/angular/reading/readingService.js' />"></script>
                    <script src="<c:url value='/static/angular/reading/readingFormService.js' />"></script>
                    <script src="<c:url value='/static/angular/reading/readingController.js' />"></script>

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
