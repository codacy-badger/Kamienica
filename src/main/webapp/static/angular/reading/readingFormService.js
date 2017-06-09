"use strict";

App.factory("ReadingForm", ["$resource", function ($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";

    };
    return $resource(
        path + "/api/v1/readings.json", {
            id: "@id",
            media: "@media"
        }, {
        	 update: {
                 method: "PUT"
             },
            save: {
                method: "POST"
            },
            delete: {
                method: "DELETE",
                hasBody: true,
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }
        }
    );
}]);
