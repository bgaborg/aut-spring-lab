requirejs.config({
    paths: {
        jquery: "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min",
        bootstrap: "lib/bootstrap.min",
        angular: 'lib/angular.min',
        text: 'lib/text',
        chart: 'lib/Chart',
        angular_chart: 'lib/tc-angular-chartjs.min'

    },

    shim: {
        'angular' : {'exports' : 'angular'},
        'chart' : {'exports' : 'Chart'}
    },

    priority: [
        "angular"
    ],
    map: {
        '*': {
            'less': 'lib/require-less/less', // path to less
            'css': 'lib/require-css/css' // path to less
        }
    }
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require( [
    'angular',
    'app'
], function(angular, App) {
    var $html = angular.element(document.getElementsByTagName('html')[0]);

    angular.element().ready(function() {
        angular.resumeBootstrap([App['name']]);
    });
});