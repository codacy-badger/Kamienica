"use strict";

App.controller("ReadingController", [
    "$scope", "$filter",
    "Reading", "Residence", "Meter", "ReadingForm",
    function ($scope, $filter, Reading, Residence, Meter, ReadingForm) {

        $scope.res;
        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };
        $scope.residences = Residence.query();

        var self = this;
        $scope.media = "Energia";
        $scope.latestDate;
        self.entity;
        self.readings = [];
        self.latestReadings = [];
        self.errors = [];
        self.meters = [];
        self.newReadingsForm = new Object();

        var media = "ENERGY";
        var residence;
        var arrayIndex;


        self.switchMedia = function (arg) {
            if (arg === "ENERGY") {
                $scope.media = "Energia";
            } else if (arg === "GAS") {
                $scope.media = "Gaz";
            } else {
                $scope.media = "Woda";
            }
            media = arg;
            self.queryReadings();
            self.prepareFormForNewReadings();
        }

        self.switchResidence = function (arg) {
            residence = arg;
            self.queryReadings();
        }

        self.queryReadings = function () {
            if (residence != undefined) {
                self.readings = Reading.query({
                    media: media,
                    id: residence.id
                });
                self.readings.$promise.then(function (result) {
                   self.prepareLatestReadings(result);
                });
            }

        }

        self.createItem = function () {
            self.reading.$save(function () {}).then(function (ok) {
                $scope.errorField = true;
                $scope.errorMsg = "zapisano do bazy";
                self.queryReadings();
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function (error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });
        };

        self.updateItem = function () {
            self.reading.$update(function () {}).then(function (ok) {
                self.readings.splice(arrayIndex, 1, ok);
            }, function (error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function () {
            var response = Reading.delete({
                media: media,
                id: residence.id
            });$scope.latestDate;
        	
            for(var i = 0; i < self.readings.length; i++) {
            	var scopeDate = $filter('date')($scope.latestDate, "yyyy-MM-dd");
            	var indexDate = $filter('date')(self.readings[i].readingDetails.readingDate, "yyyy-MM-dd");
            	if(scopeDate === indexDate) {
            		self.readings.splice(i,1);
            		i--;
            	}
            }
            self.prepareLatestReadings(self.readings);
            self.reset();
        };

        self.submit = function () {
            var readingForm = new Object();
            readingForm = self.newReadingsForm;
            var tmp = $filter('date')(self.newReadingsForm.readingDetails.readingDate, "yyyy-MM-dd");
            readingForm.readingDetails.readingDate = tmp;
            var response = ReadingForm.save(readingForm);
            console.log("stara lista");
            console.log(self.readings);
            response.$promise.then(function (result) {
                $scope.latestDate = new Date(result.readingDetails.readingDate);
                for(var i =0; i<result.readings.length; i++) {
                	self.readings.unshift(result.readings[i]);
                }
                self.prepareLatestReadings(self.readings);
            });
        	// !!!!!!!!! console.log(new Date(response.readings[i].readingDetails.readingDate));
            console.log("nowa lista");
            console.log(self.readings);
            
           // self.queryReadings();
            
            console.log($scope.latestDate);
            self.switchForm();
        };

        self.edit = function (id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.reading = angular.copy(self.readings[indexOfArray]);
            self.entity = angular.copy(self.readings[indexOfArray]);
            arrayIndex = indexOfArray;
        };

        self.remove = function () {
            self.clearError();
            self.deleteItem();
        };

        self.reset = function () {
            self.reading = new Reading();
            $scope.myForm.$setPristine(); // reset Form

        };

        self.clearError = function () {
            $scope.errorField = false;
            $scope.errorMsg = "";
        }

        $scope.toggleFilter = function () {
            $scope.toggle = $scope.toggle === false ? true : false;
        }
        $scope.$watch("toggle", function () {
            $scope.text = $scope.toggle ? "Dodaj" :
                "Lista";
        })

        self.switchForm = function () {
            if ($scope.text === "Dodaj") {
                $scope.text = "Lista";
                self.reset();
                $scope.toggle = false;
                $scope.errors = "";
                $scope.errorField = false;
                $scope.errorMsg = "";
                self.prepareFormForNewReadings();
            } else {
                $scope.text = "Dodaj";
                $scope.toggle = true;
                $scope.errors = "";
                $scope.errorField = false;
                $scope.errorMsg = "";
            }
        }

        self.prepareFormForNewReadings = function () {
        	console.log("prepareFormForNewReadings");
            if (residence != undefined) {
                self.loadMeters();
                self.newReadingsForm.readingDetails = {
                    resolvement: "UNRESOLVED",
                    media: media,
                    readingDate: new Date($scope.latestDate),
                    residence: residence
                };

                self.meters.$promise.then(function (result) {
                    self.newReadingsForm.readings = self.createReadingsForTheNewForm(result);
                });
            }
        }

        self.loadMeters = function () {
            self.meters = Meter.query({
                media: media,
                id: residence.id
            });

        }
        self.createReadingsForTheNewForm = function (metersArray) {
        	console.log("createReadingsForTheNewForm");
            var result = [];
            var lastIndex =  metersArray.length -1;
            for (var meterIndex = 0; meterIndex < metersArray.length; meterIndex++) {
                var meterHasNoReadingYet = true;
                for (var readingIndex = 0; readingIndex < self.latestReadings.length; readingIndex++) {
                    if (self.latestReadings[readingIndex].meter.id === metersArray[meterIndex].id) {
                        var r = new Object();
                        r.id = null;
                        r.value = self.latestReadings[readingIndex].value;
                        r.meter = self.latestReadings[readingIndex].meter;
                        result.unshift(r);
                        meterHasNoReadingYet = false;
                    }

                }
                if (meterHasNoReadingYet) {
                    console.log("this has not been tested yet");
                    var reading = self.createReadingForNewMeter(metersArray[meterIndex]);
                    result.push(reading);
                }
            }
            return result;
        }

        self.createReadingForNewMeter = function (meter) {
            var r = new Object();
            r.id = null;
            r.value = 0;
            r.residence = residence;
            r.meter = meter;

            return r;
        }
        
        self.prepareLatestReadings = function(result) {
       	 $scope.latestDate = result[0].readingDetails.readingDate;
            self.latestReadings = [];
            for (var i = 0; i < result.length; i++) {
                if (result[i].readingDetails.readingDate == $scope.latestDate) {
                    self.latestReadings.push(result[i]);

                } else {
                    break;
                }
            }
       }
    }
]);
