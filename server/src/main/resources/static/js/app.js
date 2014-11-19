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
                    // must have transformation.
                    for (var i = 0; i < data.stats.length; i++) {
                        for ( var j = 0; j < data.stats[i].values.length; j++){
                            data.stats[i].values[j] = [j, data.stats[i].values[j]];
                        }
                    }
                    cC.chartData = data.stats;
                    console.log(data.stats);
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
            pC.message = "Send update";
            pC.phones = [];
            pC.status = "Ready";
            pC.serverIp = "http://192.168.1.103:8080";

            pC.sendMsg = function (api_key) {
                $http({
                    url: '/phones/notifyPhone',
                    method: "POST",
                    params: {apiKey: api_key, msg: pC.message, serverIp: pC.serverIp}
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