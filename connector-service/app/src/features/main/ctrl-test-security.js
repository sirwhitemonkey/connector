
angular.module('connector.controllers')

    .controller('CtrlTestSecurity',
    function ($scope, $rootScope, $log, $q, $uibModalInstance) {

        var logMsgPrefix = "CtrlTestSecurity -> ";
        $log.debug(logMsgPrefix + ' created');

        $scope.alerts = [];
        $scope.data = {
            password: '',
            encryption: ''
        };

        $scope.event = {};

        $scope.event.encryption = function() {
            $log.debug(logMsgPrefix + "event.encryption()");
            if ($scope.data.password !== undefined && $scope.data.password.trim() !== '') {
                $scope.data.encryption = calcSHA1(calcMD5($scope.data.password));

            } else {
                $log.debug(logMsgPrefix + "event.encryption(): password is required");
                $scope.alerts = [];
                $scope.alerts.push({msg:'Password is required'});
            }
        };

        $scope.event.cancel = function() {
            $uibModalInstance.close(false);
        };

        $scope.event.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };

    });




