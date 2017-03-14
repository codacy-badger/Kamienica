"use strict";

App.factory("Reading", ["$resource", function($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica"

    };

    return $resource(
        path + "/api/v1/readings/WATER.json", {
            id: "@id"
        }, //Handy for update & delete. id will be set with id of instance
        {
            query: {
                method: "GET",
                isArray: true
            },
            update: {
                method: "PUT"
            }
        }
    );
}]);