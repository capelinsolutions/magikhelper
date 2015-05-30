angular.module('starter.services', [])
    .factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout',
        function ($http, $rootScope, $timeout) {
            var service = {};

            service.Login = function (user, callback) {
                /* Use this for real authentication
                 ----------------------------------------------*/

                //alert(JSON.stringify(user))

                var req = {
                    method: 'POST',
                    url: 'http://magikheper-ws.elasticbeanstalk.com/services/security/login',
                    headers: {
                        'DEVICE_ID': "1234"
                    },
                    data: user
                }

                $http(req).success(function (response) {

                    if (response.code == 1001) {
                        response.login = false;
                        callback(response);
                    } else {
                        response.login = true;
                        callback(response);
                    }

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

    .factory('AvailableServices', ['$http', function ($http) {
        var service = {};

        service.getAllServices = function (object, callback) {
            alert(JSON.stringify(object));
            $http.get('http://magikheper-ws.elasticbeanstalk.com/services/helperServices', object)
                .success(function (response) {
                    response.success = true;
                    callback(response);
                })
                .error(function (data, status, headers, config) {
                    alert("failure message: " + JSON.stringify({data: data}));
                    response.success = false;
                });

        };
        return service;
    }])

    .factory('VendorServices', ['$http', '$q' , function ($http, $q) {
        var service = {};
        var joblist = [
            {"id": 1, "jobType": "Cleaning", "requestedBy": "John Doe", "dateOfService": "05/15/2015", "requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "3", "serviceAddress": "1 main st. Dallas TX 75200", "status": "COMPLETED", "commentByRequester": "Job Done on Time"},
            {"id": 2, "jobType": "Cleaning", "requestedBy": "John Doe", "dateOfService": "05/18/2015","requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "2","serviceAddress": "13155 Noel Rd Dallas TX 75240", "status": "ASSIGNED", "commentByRequester": ""},
            {"id": 3, "jobType": "General Labor", "requestedBy": "Jane Doe", "dateOfService": "05/17/2015","requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "2", "serviceAddress": "14155 Preston Rd Dallas TX 75254", "status": "ASSIGNED", "commentByRequester": ""}
        ];

        service.getAllCompletedService = function () {
            var data=[];
            	  angular.forEach(joblist, function(item){
                   if(item.status == 'COMPLETED'){
                       data.push(item);
                   }});
             return data;
        };
        service.getAllAssignedService = function () {
            var data=[];
            angular.forEach(joblist, function(item){
                if(item.status == 'ASSIGNED'){
                    data.push(item);
                }});
            return data;
        };
        service.findById = function(jobId) {
            var deferred = $q.defer();
            var job ;
            angular.forEach(joblist, function(item){
                if(item.id == parseInt(jobId)){
                    job = item;
                }});
            deferred.resolve(job);
            return deferred.promise;
        };

        return service;
    }])

    .factory('SignUpService', ['$http', function ($http) {
        var service = {};

        service.isPasswordSame = function (password, confirmPassword) {
            return password == confirmPassword;
        };

        service.Save = function (object, callback) {
            alert(JSON.stringify(object));
            $http.post('http://magikheper-ws.elasticbeanstalk.com/services/clients', object)
                .success(function (response) {
                    alert('User successfully created');
                    callback(true);
                })
                .error(function (data, status, headers, config) {
                    alert("failure message: " + JSON.stringify({data: data}));
                    response.success = false;
                });

        };
        return service;
    }]);

