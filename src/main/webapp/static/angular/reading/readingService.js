"use strict";

App.factory("Reading", ["$resource", function ($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";

    };
    //http://stackoverflow.com/questions/28457503/passing-an-argument-to-a-service-for-use-with-http-get
    return $resource(
        //"/api/v1/readings.json?media=:media&residence_id=:id"
        path + "/api/v1/readings.json?media=:media&residence_id=:id", {
            id: "@id",
            media: "@media"
        }, {
            query: {
                method: "GET",
                isArray: true
            },
            update: {
                method: "PUT"
            },
            save: {
                method: "POST",
                url: path + "/api/v1/readings.json"
            },
            delete: {
                method: "DELETE",
                url: path + "/api/v1/readings.json",
                headers: {
                    'Content-Type': "application/json"
                },
                data:  readingDetails

            }
        }
    );
}]);
