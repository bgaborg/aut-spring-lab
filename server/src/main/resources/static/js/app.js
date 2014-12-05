define(["jquery", "angular", 'angularjs-nvd3-directives'],
    function ($, angular) {
        var self = this;
        var phrobeApp = angular.module('phrobe', ['nvd3ChartDirectives', 'ngRoute']);

        phrobeApp.controller('serverInfo', function ($http) {
            var sI = this;
            sI.info = {};
            $http.get('/serverInfo').success(function (data) {
                sI.info = data;
            });
        });

        phrobeApp.controller('menuController', function ($location) {
            var mC = this;
            mC.getClass = function (path) {
                if ($location.path().substr(0, path.length) == path) {
                    return "active"
                } else {
                    return ""
                }
            }
        });

        phrobeApp.controller('phoneListController', function ($http, $interval, $location) {
            var pC = this;
            pC.phones = [];

            pC.refreshPhoneList = function () {
                $http.get('/phones/all').success(function (data) {
                    pC.phones = data;
                    console.log(data);
                });
            }

            var refreshPhoneListInterval = $interval(pC.refreshPhoneList, 5000);
            pC.refreshPhoneList();
        });

        phrobeApp.controller('phoneDataController', function ($http, $scope, $routeParams, $interval) {
            $scope.phone = null;
            $http.get('/phones/' + $routeParams.phoneId).success(function (data) {
                $scope.phone = data;
            });

            var cC = this;
            cC.message = "Ready";
            cC.chartData = [ ];
            cC.displayDebug = false;

            cC.serverIp = "http://192.168.1.103:8080";

            cC.sendMsg = function () {
                $http({
                    url: '/phones/notifyPhone',
                    method: "POST",
                    params: {apiKey: $scope.phone.api_key, msg: cC.message, serverIp: cC.serverIp}
                }).success(function (data) {
                    cC.message = 'Update sent';
                });
            };

            cC.refreshChartData = function () {
                console.log("Update data...")
                $http.get('/phones/getMeasurements/' + $routeParams.phoneId).success(function (data) {
                    // must have transformation - for d3. this transformation is easier than in java side
                    for (var i = 0; i < data.stats.length; i++) {
                        // we should reverse, because the data will be in descending order by date
                        //data.stats[i].values.reverse();
                        for (var j = 0; j < data.stats[i].values.length; j++) {
                            if (data.stats[i].dates === undefined || j > data.stats[i].dates.length) {
                                data.stats[i].values[j] = [j, data.stats[i].values[j]];
                            } else {
                                data.stats[i].values[j] = [new Date(data.stats[i].dates[j]), data.stats[i].values[j]];
                            }
                        }
                    }
                    cC.chartData = data.stats;
                    console.log("Data updated.");
                });
            };

            var refreshChartDataInterval = $interval(cC.refreshChartData, 5000);
            cC.refreshChartData();

            $scope.$on('$destroy', function () {
                if (angular.isDefined(refreshChartDataInterval)) {
                    $interval.cancel(refreshChartDataInterval);
                    refreshChartDataInterval = undefined;
                }
            });
        });


        phrobeApp.config(['$routeProvider',
            function ($routeProvider) {

                $routeProvider.
                    when('/main', {
                        templateUrl: 'js/partials/main.html'
                    }).
                    when('/details/:phoneId', {
                        templateUrl: 'js/partials/phoneData.html',
                        controller: 'phoneDataController'
                    }).
                    otherwise({
                        redirectTo: '/main'
                    });
            }]);


        return phrobeApp;
    });