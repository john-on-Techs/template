'use strict';

angular.
module('app').
config(['$routeProvider',
    function config($routeProvider) {
        $routeProvider.
        when('/payments', {
            template: '<payment-list></payment-list>'
        }).
        when('/payments/:paymentId', {
            template: '<payment-detail></payment-detail>'
        }).
        otherwise('/payments');
    }
]);