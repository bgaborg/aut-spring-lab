define(["jquery", "angular", "less!../css/dashboard.less", 'angularjs-nvd3-directives'],
    function ($, angular) {
        var self = this;
        var phrobeApp = angular.module('phrobe', ['nvd3ChartDirectives']);

        phrobeApp.controller('serverInfo', function ($http) {
            var sI = this;
            sI.info = {};
            $http.get('/serverInfo').success(function (data) {
                sI.info = data;
            });
        });

        phrobeApp.controller('chartController', function ($http, $scope, $interval) {
            var cC = this;
            cC.message = "Ready";
            cC.chartData = [ ];
            cC.displayDebug = false;

            cC.refreshChartData = function () {
                console.log("Update data...")
                $http.get('/phones/getMeasurements').success(function (data) {
                    var reportData = [
                        {
                            "key": "signalStrength",
                            "values": [
                            ]
                        },
                        {
                            "key": "gpsAccuracy",
                            "values": [
                            ]
                        }
                    ];

                    for (var i = 0; i < data.length; i++) {
                        reportData[0].values.push([data[i].id, data[i].signalStrength]);
                        reportData[1].values.push([data[i].id, data[i].gpsAccuracy]);
                    }

                    cC.chartData = reportData;
                    console.log("Data updated.")
                });
            };

            var refreshChartDataInterval = $interval(cC.refreshChartData, 5000);

            cC.refreshChartData();

            $scope.$on('$destroy', function () {
                // Make sure that the interval is destroyed too
                if (angular.isDefined(refreshChartDataInterval)) {
                    $interval.cancel(refreshChartDataInterval);
                    refreshChartDataInterval = undefined;
                }
            });
        });


        phrobeApp.controller('phoneListController', function ($http, $interval) {
            var pC = this;
            pC.message = "This text will be sent to the mobile.";
            pC.phones = [];
            pC.status = "Ready";

            pC.sendMsg = function (api_key) {
                $http({
                    url: '/phones/notifyPhone',
                    method: "GET",
                    params: {apiKey: api_key, msg: pC.message}
                }).success(function (data) {
                    pC.status = data.status;
                });
            };

            pC.refreshPhoneList = function () {
                $http.get('/phones/all').success(function (data) {
                    pC.phones = data;
                    console.log(data);
                });
            }

            var refreshPhoneListInterval = $interval(pC.refreshPhoneList, 5000);
            pC.refreshPhoneList();
        });


        return phrobeApp;
    });