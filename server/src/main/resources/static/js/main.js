requirejs.config({
    paths: {
        jquery: "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min",
        bootstrap: "lib/bootstrap.min",
        angular: 'lib/angular.min',
        text: 'lib/text',
        d3: 'lib/d3.min',
        'nvd3': 'lib/nv.d3.min',
        'angularjs-nvd3-directives': 'lib/angularjs-nvd3-directives.min',
        'angularRoute': 'lib/angular-route.min',
        'angular-google-maps': 'lib/angular-google-maps.min',
        'lodash': 'lib/lodash.min',
        'async': 'lib/require-plugins/async',
        'goog': 'lib/require-plugins/goog',
        'propertyParser': 'lib/require-plugins/propertyParser'
    },

    shim: {
        'angular': {'exports': 'angular'},
        'chart': {'exports': 'Chart'},
        'nvd3': {
            deps: ['d3', 'css!../css/nv.d3.min']
        },
        'angularjs-nvd3-directives': {
            deps: ['nvd3']
        },
        'angularRoute': ['angular'],
        bootstrap: {
            deps: ["less!../css/dashboard.less", "css!../css/bootstrap.min"]
        },
        'angular-google-maps': {
            deps: ["lodash", "angular", "goog!maps,3,other_params:sensor=false"]
        }
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

require([
    'angular',
    'app',
    'angularRoute',
    'bootstrap'
], function (angular, App) {
    var $html = angular.element(document.getElementsByTagName('html')[0]);

    angular.element().ready(function () {
        angular.resumeBootstrap([App['name']]);
    });
});