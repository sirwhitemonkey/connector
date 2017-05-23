
angular.module('connector.controllers')

    .controller('CtrlSysdigcloud',
    function ($scope, $log, $constants, $spinner) {

        var logMsgPrefix = "CtrlSysdigcloud -> ";
        $log.debug(logMsgPrefix + ' created');

        $scope.spinner = $spinner;
        $scope.data = {
            id: $constants.uuid(),
            src:'https://app.sysdigcloud.com',
            sessionId: $constants.uuid(),
            didComplete: false,
            interval: 50
        };
    });
 