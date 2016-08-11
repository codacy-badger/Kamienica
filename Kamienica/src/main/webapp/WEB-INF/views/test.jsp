<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="app">
<head>

<title>Angular Controllers</title>
</head>

<body>
	<div ng-controller="HelloController as hello">
		<h1>To proste jak {{ 2+2 }}</h1>
		<input type="text" ng-model="hello.exampleVariable">
		<p>{{hello.exampleVariable}}</p>


		<input type="text" ng-model="hello.a">
		<p>{{hello.a}}</p>
	</div>


	<div ng-controller="ClickController as clickControl">
		<input type="text" ng-model="clickControl.textInput">
		<button ng-click="clickControl.changeHeader()">Change Header</button>
		<h1 ng-dblclick="clickControl.headerClick()">{{clickControl.header}}</h1>
	</div>

	<div ng-controller="MouseController as mouseControl">
		<h1 ng-mousedown="mouseControl.mouseDown()">Mouse Down</h1>
		<h1 ng-mouseup="mouseControl.mouseUp()">Mouse Up</h1>
		<h1 ng-mouseenter="mouseControl.mouseEnter()">Mouse Enter</h1>
		<h1 ng-mouseleave="mouseControl.mouseLeave()">Mouse Leave</h1>
		<h1 ng-mousemove="mouseControl.mouseMove()">Mouse Move</h1>
		<h1 ng-mouseover="mouseControl.mouseOver()">Mouse Over</h1>
	</div>


	<div ng-controller="KeyController as keyControl">
		<input type="text" ng-keydown="keyControl.keyDown()"
			ng-keyup="keyControl.keyUp()" ng-keypress="keyControl.keyPress()"
			placeholder="input">
		<h1>Key down: {{keyControl.down}}</h1>
		<h1>Key up: {{keyControl.up}}</h1>
		<h1>Key press: {{keyControl.press}}</h1>
	</div>

	<script type="application/javascript"
		src="<c:url value='/static/js/angular.js' />"></script>
	<script type="application/javascript"
		src="<c:url value='/static/controller/app.js' />"></script>

</body>
</html>