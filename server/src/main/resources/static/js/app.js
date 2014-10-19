define(["jquery", "angular", "less!../css/dashboard.less", 'angularjs-nvd3-directives'],
    function ($, angular) {
        var self = this;
        var phrobeApp = angular.module('phrobe', ['nvd3ChartDirectives']);

        phrobeApp.controller('chartController', function ($http, $scope, $interval) {
            var cC = this;
            cC.message = "Ready";

            cC.chartData = [ ];

            cC.displayDebug = false;

            cC.refreshChartData = function () {
                console.log("Update data...")
                $http.get('/phoneData').success(function (data) {
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

            $scope.$on('$destroy', function() {
                // Make sure that the interval is destroyed too
                if (angular.isDefined(refreshChartDataInterval)) {
                    $interval.cancel(refreshChartDataInterval);
                    refreshChartDataInterval = undefined;
                }
            });
        });


        phrobeApp.controller('drawController', function () {
            var dC = this;
        });


        return phrobeApp;
    });