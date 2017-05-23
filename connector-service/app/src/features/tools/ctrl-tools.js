
angular.module('connector.controllers')

    .controller('CtrlTools',
    function ($scope, $log, $api) {

        var logMsgPrefix = "CtrlTools -> ";
        $log.debug(logMsgPrefix + ' created');

        $scope.data = $api.data.tools;

    });

