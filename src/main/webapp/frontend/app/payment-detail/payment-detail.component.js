'use strict';

// Register `paymentDetail` component, along with its associated controller and template
angular.module('paymentDetail').component('paymentDetail', {
    templateUrl: 'frontend/app/payment-detail/payment-detail.template.html',
    controller: ['$http', '$routeParams',
        function PaymentDetailController($http, $routeParams) {
            var self = this;
            $http.get('api/payments/' + $routeParams.paymentId)
                .then(function (response) {
                    self.payment = response.data.data;
                });
        }
    ]
});