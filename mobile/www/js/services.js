angular.module('starter.services', ['starter.config'])
    .factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout', 'configuration', '$cordovaDevice',
        function ($http, $rootScope, $timeout, configuration, $cordovaDevice) {
            var service = {};

            service.Login = function (user, callback) {
                /* Use this for real authentication
                 ----------------------------------------------*/

                //alert(JSON.stringify(user))

                var req = {
                    method: 'POST',
                    url: configuration.BASE_URL + '/security/login',
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

            service.SetCredentials = function (user) {
                //var authdata = Base64.encode(username + ':' + password);
                var authdata = user.email + ':' + user.password;
                $rootScope.globals = {
                    currentUser: {
                        username: user.email,
                        authdata: authdata,
                        userId: user.userId
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

    .factory('AvailableServices', ['$http', 'configuration', function ($http, configuration) {
        var service = {};
        var availableServices = null;

        service.getAllServices = function (object, callback) {


            var req = {
                method: 'get',
                url: configuration.BASE_URL + '/helperServices/zipcode/' + object
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

    .factory('BookingService', ['$http', 'configuration', '$q', '$rootScope', function ($http, configuration, $q, $rootScope) {
        var service = {};
        var bookingObj = {};
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

        service.setBookingDetails = function (object) {
            bookingObj.bookingDate = object.bookingDate;
            bookingObj.bookedTime = object.selectedTimeSlot;
            bookingObj.duration = object.hours;
            bookingObj.instructions = object.instructions;
        }

        service.setContactPerson = function (object) {
            bookingObj.firstName = object.firstName;
            bookingObj.lastName = object.lastName;
            bookingObj.phone = object.mobilePhone;
            bookingObj.email = object.email;
        }

        service.getBookingObject = function () {
            return bookingObj;
        }

        service.makeBooking = function (object, callback) {
            var req = {
                method: 'POST',
                url: configuration.BASE_URL + '/bookings',
                headers: {
                    'DEVICE_ID': "1234"
                },
                data: object
            }


            $http(req).success(function (response) {
                response.success = true;
                callback(response);
            })
                .error(function (data, status, headers, config) {
                    response.success = false;
                    callback(response);
                });
        }

        service.assignBooking = function (object) {
            var deferred = $q.defer();
alert(JSON.stringify(object));
            var req = {
                method: 'POST',
                url: configuration.BASE_URL + '/bookings/assign',
                headers: {
                    'DEVICE_ID': "1234"
                },
                data: object
            }

            $http(req).success(function (response) {
                response.success = true;
                deferred.resolve(response);
           })
                .error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
            return deferred.promise;
        }


        service.getAllBookingForCurrentUser = function () {
            var booking = {}

            var req = {
                method: 'get',
                url: configuration.BASE_URL + '/clients/bookings/' + $rootScope.globals.currentUser.userId
            }

            $http(req).success(function (response) {

                booking.success = true;
                //callback(listOfBookings);
                if (response[0] != null) {
                    booking.list = response[0];
                }
            })
                .error(function (data, status, headers, config) {
                    response.success = false;

                });

        }

        service.getAllBookings = function () {
            var deferred = $q.defer();

            var req = {
                method: 'get',
                url: configuration.BASE_URL + '/clients/bookings/' + $rootScope.globals.currentUser.userId
            }

            $http(req).success(function (response) {
                listOfBookings = response;
                //callback(listOfBookings);
                if (listOfBookings[0] != null) {
                    deferred.resolve(listOfBookings[0].bookings);
                }
            })
                .error(function (data, status, headers, config) {
                    response.success = false;
                    deferred.reject(data);
                });
            return deferred.promise;
        }

        service.getAllOpenBookings = function () {
            var deferred = $q.defer();

            var req = {
                method: 'get',
                url: configuration.BASE_URL + '/bookings?status=created'
            }

            $http(req).success(function (response) {
                listOfBookings = response;
                deferred.resolve(response);
            })
                .error(function (data, status, headers, config) {
                    response.success = false;
                    deferred.reject(data);
                });
            return deferred.promise;
        }

        service.findById = function (jobId) {
            var deferred = $q.defer();
            var job;
            angular.forEach(listOfBookings, function (item) {
                if (item.bookingId == parseInt(jobId)) {
                    job = item;
                }
            });
            deferred.resolve(job);
            return deferred.promise;
        };

        return service;

    }])

    .factory('VendorServices', ['$http', '$q','codeTypes',  function ($http, $q, codeTypes) {
        var service = {};
        var joblist = [
            {
                "bookingId": 1,
                "serviceId": 1,
                "bookedDate": "06/06/2015",
                "bookedTime": "16:00:00",
                "duration": 2,
                "startDateTime": null,
                "finishDateTime": null,
                "status": "Assigned To Vendor",
                "statusDesc": "Booking created with created status.",
                "serviceName": "Furniture Assembly",
                "rate": null,
                "bookingComments": "coments sdljflaksdf asdflkasd fsad",
                "clientId": 3,
                "clientEmail": "client1@hotmail.com",
                "bookingContact": {
                    "firstName": "Test",
                    "lastName": "UserVO",
                    "mobilePhone": "Mobile",
                    "street": "Street",
                    "additional": "Additional",
                    "city": "City",
                    "zip": "Zip",
                    "state": "State",
                    "country": "Country"
                }
            },
            {
                "bookingId": 2,
                "serviceId": 1,
                "bookedDate": "06/06/2015",
                "bookedTime": "16:00:00",
                "duration": 2,
                "startDateTime": null,
                "finishDateTime": null,
                "status": "Completed",
                "statusDesc": "Booking created with created status.",
                "serviceName": "Furniture Assembly",
                "rate": null,
                "bookingComments": "coments sdljflaksdf asdflkasd fsad",
                "clientId": 3,
                "clientEmail": "client1@hotmail.com",
                "bookingContact": {
                    "firstName": "Test",
                    "lastName": "UserVO",
                    "mobilePhone": "Mobile",
                    "street": "Street",
                    "additional": "Additional",
                    "city": "City",
                    "zip": "Zip",
                    "state": "State",
                    "country": "Country"
                }
            },
            {
                "bookingId": 10,
                "serviceId": 4,
                "bookedDate": "06/06/2015",
                "bookedTime": "16:00:00",
                "duration": 2,
                "startDateTime": null,
                "finishDateTime": null,
                "status": "Assigned To Vendor",
                "statusDesc": "Booking created with created status.",
                "serviceName": "Gardening",
                "rate": null,
                "bookingComments": "coments sdljflaksdf asdflkasd fsad",
                "clientId": 3,
                "clientEmail": "client1@hotmail.com",
                "bookingContact": {
                    "firstName": "Test",
                    "lastName": "UserVO",
                    "mobilePhone": "Mobile",
                    "street": "Street",
                    "additional": "Additional",
                    "city": "City",
                    "zip": "Zip",
                    "state": "State",
                    "country": "Country"
                }
            }
        ];

        service.getAllCompletedService = function () {
            var data = [];
            angular.forEach(joblist, function (item) {
                if (item.status == codeTypes.COMPLETED) {
                    data.push(item);
                }
            });
            return data;
        };
        service.getAllAssignedService = function () {
            var data = [];
            angular.forEach(joblist, function (item) {
                if (item.status == codeTypes.ASSIGNED_TO_VENDOR) {
                    data.push(item);
                }
            });
            return data;
        };
        service.findById = function (jobId) {
            var deferred = $q.defer();
            var job;
            angular.forEach(joblist, function (item) {
                if (item.bookingId == parseInt(jobId)) {
                    job = item;
                }
            });
            deferred.resolve(job);
            return deferred.promise;
        };

        return service;
    }])

    .factory('LocationServices', ['$http', '$q', 'configuration', '$cordovaDevice', function ($http, $q, configuration, $cordovaDevice) {
        var service = {};
        var vendorCurrentLocation = {};

        service.setVendorCurrentLocation = function (vendorLocationObj) {
            vendorCurrentLocation = vendorLocationObj;
        }

        service.getVendorCurrentLocation = function () {
            return vendorCurrentLocation;
        }

        service.getVendorLocation = function (bookingId) {
            var deferred = $q.defer();

            var req = {
                method: 'get',
                url: configuration.BASE_URL + '/route/' + bookingId
            }

            $http(req).success(function (response) {
                deferred.resolve(response);
            })
                .error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
            return deferred.promise;
        }

        service.setCordinates = function (locationObj) {
            var req = {
                method: 'POST',
                url: configuration.BASE_URL + '/route',
                headers: {
                    //'DEVICE_ID': $cordovaDevice.getUUID()
                    'DEVICE_ID': '33333'
                },
                data: locationObj
            }

            $http(req)
                .success(function (response) {
                    // alert('Customer has been notified that you are in route');
                })
                .error(function (data, status, headers, config) {
                    alert('Some error occurred in determining the location. Please try again later.');
                    response.success = false;
                    //alert(status);
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });
        }
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

    .factory('SignUpService', ['$http', 'configuration', function ($http, configuration) {
        var service = {};

        service.isPasswordSame = function (password, confirmPassword) {
            return password == confirmPassword;
        };

        service.Save = function (object, callback) {
            var status = {};

            $http.post(configuration.BASE_URL + '/clients', object)
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

