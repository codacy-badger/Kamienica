"use strict";

App.factory("ReadingForm", ["$resource", function ($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";

    };
    //http://stackoverflow.com/questions/28457503/passing-an-argument-to-a-service-for-use-with-http-get
    return $resource(
        //"/api/v1/readings.json?media=:media&residence_id=:id"
        path + "/api/v1/readings.json", {
            id: "@id",
            media: "@media"
        }, {
           
            save: {
                method: "POST"
            }
        }
    );
}]);
