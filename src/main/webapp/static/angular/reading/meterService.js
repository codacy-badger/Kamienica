"use strict";

App.factory("Meter", ["$resource", function ($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";

    };
    return $resource(
        path + "/api/v1/meters.json?media=:media&id=:id", {
            id: "@id",
            media: "@media"

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
