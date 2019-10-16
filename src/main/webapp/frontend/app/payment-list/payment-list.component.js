'use strict';

// Register `paymentList` component, along with its associated controller and template
angular.
  module('paymentList').
  component('paymentList', {
    templateUrl: 'frontend/app/payment-list/payment-list.template.html',
    controller:['$http', function PaymentListController($http) {
      var self= this;
      self.orderProp = 'studentId';
      $http.get(
        'api/payments'
        ).then(function (response) {
        self.payments=response.data.data;

      },function (reason) {

      });
    }]
  });
