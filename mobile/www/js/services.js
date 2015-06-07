angular.module('starter.services', ['starter.config'])
    .factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout', 'configuration','$cordovaDevice',
        function ($http, $rootScope, $timeout , configuration, $cordovaDevice) {
            var service = {};

            service.Login = function (user, callback) {

                var req = {
                    method: 'POST',
                    url:  configuration.BASE_URL +'/security/login',
                    headers: {
                        'DEVICE_ID': $cordovaDevice.getUUID()
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

            /* service.ServicesService = function (zipCode, callback) {

             var req = {
             method: 'get',
             url: 'http://magikheper-ws.elasticbeanstalk.com/helperServices/zipcode/'+zipCode
             }

             $http(req).success(function (response) {

             alert(JSON.stringify(response));

             })
             .error(function (data, status, headers, config) {
             response.success = false;
             //alert(status);
             // called asynchronously if an error occurs
             // or server returns response with an error status.
             });
             };*/

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

    .factory('AvailableServices', ['$http','configuration', function ($http, configuration) {
        var service = {};
        var availableServices = null;

        service.getAllServices = function (object, callback) {


            var req = {
                method: 'get',
                url: configuration.BASE_URL +  '/helperServices/zipcode/' + object
            }

            $http(req).success(function (response) {
                availableServices = response;
                callback(response);
            })
                .error(function (data, status, headers, config) {
                    response.success = false;
                    //alert(status);
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });

        };

        service.getFetchedServices = function () {
            return availableServices;
        }

        return service;
    }])

    .factory('BookingService', ['$http','configuration', '$q', function ($http , configuration, $q) {
        var service = {};
        var bookingObj= {};
        var listOfBookings = {};

        service.setSelectedService = function (service) {
            bookingObj.service = service;
        };

        service.setClient = function (client) {
            bookingObj.client = client;
        };

        service.setAddress = function (address) {
            bookingObj.address = address;
        };
        service.isLoggedIn = function () {
            return true;
        };

        service.isVendor = function () {
            return true;
        };

        service.setBookingDetails = function(object) {
            bookingObj.bookingDate = object.bookingDate;
            bookingObj.bookedTime = object.selectedTimeSlot.name;
            bookingObj.duration = object.hours;
            bookingObj.instructions = object.instructions;
        }

        service.setContactPerson = function(object) {
            bookingObj.firstName = object.firstName;
            bookingObj.lastName = object.lastName;
            bookingObj.phone = object.mobilePhone;
            bookingObj.email = object.email;
        }

        service.getBookingObject = function() {
            return bookingObj;
        }


        service.getAllBookings =  function () {
            var deferred = $q.defer();

            var req = {
                method: 'get',
                url: configuration.BASE_URL +  '/clients/bookings/'
            }

            $http(req).success(function (response) {
                listOfBookings = response;
                //callback(listOfBookings);
                deferred.resolve(listOfBookings[0].bookings);
            })
                .error(function (data, status, headers, config) {
                    response.success = false;
                    deferred.reject(data);
                });
            return deferred.promise;
        }

        return service;
    }])

    .factory('VendorServices', ['$http', '$q' , function ($http, $q) {
        var service = {};
        var joblist = [
            {"id": 2, "jobType": "Cleaning", "requestedBy": "John Doe", "dateOfService": "05/15/2015", "requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "3", "serviceAddress": "1 main st. Dallas TX 75200", "status": "COMPLETED", "commentByRequester": "Job Done on Time"},
            {"id": 3, "jobType": "Moving", "requestedBy": "John Doe", "dateOfService": "05/18/2015","requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "2","serviceAddress": "13155 Noel Rd Dallas TX 75240", "status": "ASSIGNED", "commentByRequester": "Do a good job!!"},
            {"id": 5, "jobType": "General Labor", "requestedBy": "Jane Doe", "dateOfService": "05/17/2015","requestedTimeOfService": "12PM - 2PM", "noOfHrsToComplete": "2", "serviceAddress": "14155 Preston Rd Dallas TX 75254", "status": "ASSIGNED", "commentByRequester": "Do a good job"}
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



    .factory('UtilityServices', ['$http', function ($http) {
        var service = {};

        service.validateAddress = function (address) {

            var parsedAddress = {}
            var validateAddress = 0;

            for (index = 0; index < address.address_components.length; index++) {
                for (subIndex = 0; subIndex < address.address_components[index].types.length; subIndex++) {
                    var type = address.address_components[index].types[subIndex];

                    if (type == "street_number") {
                        parsedAddress.street = address.address_components[index].long_name;
                        validateAddress++;
                    } else if (type == "route") {
                        parsedAddress.street += " " + address.address_components[index].long_name;
                        validateAddress++;
                    } else if (type == "locality") {
                        parsedAddress.city = address.address_components[index].long_name;
                        validateAddress++;
                    } else if (type == "administrative_area_level_1") {
                        parsedAddress.state = address.address_components[index].long_name;
                        validateAddress++;
                    } else if (type == "country") {
                        parsedAddress.country = address.address_components[index].long_name;
                        validateAddress++;
                    } else if (type == "postal_code") {
                        parsedAddress.zip = address.address_components[index].long_name;
                        validateAddress++;
                    }
                }
            }

            if (validateAddress == 6) {
                parsedAddress.valid = true;
            } else {
                parsedAddress.valid = false;
            }

            return parsedAddress;
        };

        return service;
    }])

    .factory('SignUpService', ['$http', function ($http) {
        var service = {};

        service.isPasswordSame = function (password, confirmPassword) {
            return password == confirmPassword;
        };

        service.Save = function (object, callback) {
            var status = {};
            alert(JSON.stringify(object));
            $http.post(configuration.BASE_URL +  '/clients', object)
                .success(function (response) {
                    alert("User Created Successfully");
                    status = true;
                    callback(status);
                })
                .error(function (data, status, headers, config) {
                    alert("failure message: " + JSON.stringify({data: data}));
                    callback(response);
                });

        };
        return service;
    }]);

