
angular.module('connector.controllers')

    .controller('CtrlMicroServices',
    function ($scope, $log, $spinner, $constants, $window) {

        var logMsgPrefix = "CtrlMicroServices -> ";
        $log.debug(logMsgPrefix + ' created');

        $scope.event = {};
        $scope.spinner = $spinner;

        var url = $window.location.protocol + "//" +  $window.location.host;

        $scope.microservices = [
            {
                name: 'OAuth2',
                url: url + '/swagger/oauth2.json'
            },
            {
                name: 'Payment',
                url: url + '/swagger/payment.json'
            },
            {
                name: 'Catalogue',
                url: url + '/swagger/catalogue.json'
            },
            {
                name: 'Correspondence',
                url: url + '/swagger/correspondence.json'
            },
            {
                name: 'Shipping',
                url: url + '/swagger/shipping.json'
            },
            {
                name: 'Tax',
                url: url + '/swagger/tax.json'
            },
            {
                name: 'Loyalty',
                url: url + '/swagger/loyalty.json'
            }
        ];


        $scope.errorHandler = function(data, status){
            $log.debug(logMsgPrefix + ' failed to load swagger: '+status);
        };
    });

