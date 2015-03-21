angular.module('starter.services', [])
    .factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout',
        function ($http, $rootScope, $timeout) {
            var service = {};

            service.Login = function (username, password, callback) {

                /* Use this for real authentication
                 ----------------------------------------------*/
                $http.post('http://magikheper-ws.elasticbeanstalk.com/services/security/login', {
                    email: username,
                    password: password
                })
                    .success(function (response) {
                        response.success = true;
                        callback(response);
                    })
                    .error(function (data, status, headers, config) {
                        response.success = false;
                        //alert(status);
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

            };

            service.SetCredentials = function (username, password) {
                //var authdata = Base64.encode(username + ':' + password);
                var authdata = username + ':' + password;
                $rootScope.globals = {
                    currentUser: {
                        username: username,
                        authdata: authdata
                    }
                };

                $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
                //$cookieStore.put('globals', $rootScope.globals);
            };

            service.ClearCredentials = function () {
                $rootScope.globals = {};
                //$cookieStore.remove('globals');
                $http.defaults.headers.common.Authorization = 'Basic ';
            };

            return service;
        }])

    .factory('SignUpService', ['$http', function ($http) {
        var service = {};

        service.isPasswordSame = function (password, confirmPassword) {
            return password == confirmPassword;
        };

        service.Save = function (object, callback) {
            $http.post('http://magikheper-ws.elasticbeanstalk.com/services/clients', object)
                .success(function (response) {
                    alert('22');
                    response.success = true;
                    callback(response);
                })
                .error(function (data, status, headers, config) {
                    alert("failure message: " + JSON.stringify({data: data}));
                    alert(status);
                    response.success = false;
                    //alert(status);
                });

        };
        return service;
    }]);

