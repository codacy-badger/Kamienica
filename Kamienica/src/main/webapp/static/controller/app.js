var app = angular.module('app', []);

app.controller('HelloController', HelloController);

function HelloController() {
	this.exampleVariable = 'Przykładowy tekst';
}

app.controller('ClickController', function() {
	this.header = "Header przed zmianami";
	this.changeHeader = function() {
		this.header = this.textInput;
	}
	this.headerClick = function() {
		this.header = this.header.toUpperCase();
	}
});

app.controller('MouseController', function() {
	this.mouseDown = function() {
		console.log('Mouse down');
	}
	this.mouseUp = function() {
		console.log('Mouse Up');
	}
	this.mouseEnter = function() {
		console.log('Mouse enter');
	}
	this.mouseLeave = function() {
		console.log('Mouse leave');
	}
	this.mouseMove = function() {
		console.log('Mouse move');
	}
	this.mouseOver = function() {
		console.log('Mouse over');
	}
});

app.controller('KeyController', function() {
	this.up = 0;
	this.down = 0;
	this.press = 0;
	this.keyDown = function() {
		this.down++;
	}
	this.keyUp = function() {
		this.up++;
	}
	this.keyPress = function() {
		this.press++;
	}
});