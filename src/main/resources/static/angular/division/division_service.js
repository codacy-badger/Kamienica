"use strict";

App.factory("Division", ["$resource", function($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";

    };

    return $resource(
        path + "/api/v1/divisions/:id.json", {
            id: "@id"
        }, {
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