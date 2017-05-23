'use strict';

(function () {

    angular.module('connector', ['ngSanitize','swaggerUi', 'ui.bootstrap','ui.router','connector.services', 'connector.directives',
        'connector.controllers', 'connector.filters', 'ngMaterial', 'material.components.expansionPanels', 'ngCookies'])

      .run(function ($log, $window,$q, $api, $rootScope, $urlRouter, $state, $timeout, $http, $cookieStore) {
          var logMsgPrefix = 'app.js -> ';
          $log.debug(logMsgPrefix + ' created');


            // Once the user has logged in, sync the current URL
            // to the router:
            // $urlRouter.sync();

            // Configures $urlRouter's listener *after* your custom listener
            //$urlRouter.listen();

            $timeout(function(){
                $state.go("main",{reload:true});
            }, 500);

            // keep user logged in after page refresh
            // $rootScope.globals = $cookieStore.get('globals') || {};
            // if ($rootScope.globals.currentUser) {
            //    $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
            //}

            $rootScope.$on('$locationChangeStart',
                function(event, next, prev) {
                    $log.debug(logMsgPrefix + " $locationChangeStart");

                    // redirect to login page if not logged in
                    // if (!$rootScope.globals.currentUser) {
                    //    $rootScope.$broadcast("AUTHENTICATION_REQUIRED");
                    // }

            });

            var windowElement = angular.element($window);
            windowElement.on('beforeunload', function (event) {
                $log.debug(logMsgPrefix + ' beforeUnload');
                //After this will prevent reload or navigating away.
                event.preventDefault();
            });

            // Get all entries
            var apis = [];
            apis.push(
                $api.getTools()
                    .then(function(response){
                        $log.debug(logMsgPrefix + " tools compeletion:" + response);
                    })
            );
            apis.push(
                $api.getTeam()
                    .then(function(response){
                        $log.debug(logMsgPrefix + " team compeletion:" + response);
                    })
            );
            $q.all(apis)
                .then(function() {
                   $log.debug(logMsgPrefix + " api calls completed");
                    $api.data.loaded = true;
          });

      })

      .config(function ($stateProvider, $urlRouterProvider, $qProvider) {

            $qProvider.errorOnUnhandledRejections(false);

            // Prevent $urlRouter from automatically intercepting URL changes;
            // this allows you to configure custom behavior in between
            // location changes and route synchronization:
            $urlRouterProvider.deferIntercept();

            $urlRouterProvider.otherwise('/main');
            // use the HTML5 History API
            //$locationProvider.html5Mode(true);

            var logMsgPrefix = "config() ";

            $stateProvider
                .state('main', {
                    url: '/main',
                    templateProvider: function ($templateCache, $http) {
                        var url = 'src/features/main/templates/main.html';
                        return $http.get(url, {cache: $templateCache}).then(function (html) {
                            console.log(logMsgPrefix +  "main.html");
                            return html.data;
                        });
                    },
                    controller: 'CtrlMain',
                    resolve: {

                    }
                })
                .state('microservices', {
                    url: '/microservices/:cache',
                    views: {
                        'microservices': {
                            templateProvider: function ($templateCache, $http) {
                                var url = 'src/features/microservices/templates/microservices.html';
                                return $http.get(url, {cache: $templateCache}).then(function (html) {
                                    console.log(logMsgPrefix +  "microservices.html");
                                    return html.data;
                                });
                            },
                            controller: 'CtrlMicroServices',
                            resolve: {

                            }
                        }
                    }
                })
                .state('sysdigcloud', {
                    url: '/sysdigcloud/:cache',
                    views: {
                        'sysdigcloud': {
                            templateProvider: function ($templateCache, $http) {
                                var url = 'src/features/sysdigcloud/templates/sysdigcloud.html';
                                return $http.get(url, {cache: $templateCache}).then(function (html) {
                                    console.log(logMsgPrefix +  "sysdigcloud.html");
                                    return html.data;
                                });
                            },
                            controller: 'CtrlSysdigcloud',
                            resolve: {

                            }
                        }
                    }

                })
                .state('bdds', {
                    url: '/bdds/:cache',
                    views: {
                        'bdds': {
                            templateProvider: function ($templateCache, $http) {
                                var url = 'src/features/bdds/templates/bdds.html';
                                return $http.get(url, {cache: $templateCache}).then(function (html) {
                                    console.log(logMsgPrefix +  "bdds.html");
                                    return html.data;
                                });
                            },
                            controller: 'CtrlBDDs',
                            resolve: {

                            }
                        }
                    }

                })
                .state('tools', {
                    url: '/tools/:cache',
                    views: {
                        'tools' : {
                            templateProvider: function ($templateCache, $http) {
                                var url = 'src/features/tools/templates/tools.html';
                                return $http.get(url, {cache: $templateCache}).then(function (html) {
                                    console.log(logMsgPrefix +  "tools.html");
                                     return html.data;
                                });
                            },
                            controller: 'CtrlTools',
                            resolve: {

                            }
                        }
                    }

                })
                .state('team', {
                    url: '/team/:cache',
                    views: {
                        'team': {
                            templateProvider: function ($templateCache, $http) {
                                var url = 'src/features/team/templates/team.html';
                                return $http.get(url, {cache: $templateCache}).then(function (html) {
                                    console.log(logMsgPrefix +  "team.html");
                                    return html.data;
                                });
                            },
                            controller: 'CtrlTeam',
                            resolve: {

                            }
                        }

                    }

                });



      });

})();

















