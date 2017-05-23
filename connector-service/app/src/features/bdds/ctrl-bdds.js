
angular.module('connector.controllers')

    .controller('CtrlBDDs',
    function ($scope, $log, $spinner, $constants) {

        var logMsgPrefix = "CtrlBDDs -> ";
        $log.debug(logMsgPrefix + ' created');
        $scope.event = {};
        $scope.spinner = $spinner;
        $scope.data = [];
        $scope.data.push({
            src: "TODO",
            name: 'Payment',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false,
            show: false
        });
        $scope.data.push({
            src: "TODO",
            name: 'Catalogue',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false
        });
        $scope.data.push({
            src: "TODO",
            name: 'Correspondence',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false
        });
        $scope.data.push({
            src: "TODO",
            name: 'Shipping',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false
        });
        $scope.data.push({
            src: "TODO",
            name: 'Tax',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false
        });
        $scope.data.push({
            src: "TODO",
            name: 'Loyalty',
            interval: 50,
            id: $constants.uuid(),
            didComplete: false
        });
    });

