
angular.module('connector.controllers')

    .controller('CtrlTeam',
    function ($scope, $log, $api, $state) {

        var logMsgPrefix = "CtrlTeam -> ";
        $log.debug(logMsgPrefix + ' created');

        $scope.data = $api.data.team;

    });

