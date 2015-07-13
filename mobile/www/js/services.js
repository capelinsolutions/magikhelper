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

    .factory('BookingService', ['$http', '$rootScope', 'configuration', '$q', '$rootScope', function ($http, $rootScope, configuration, $q, $rootScope) {
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
            return ($rootScope.user);
        };

        service.isVendor = function () {
            return $rootScope.user.isVendor;
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

        service.clearBooking = function () {
            bookingObj = {};
            listOfBookings = {};
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

        service.getAllBookingsForCurrentVendor = function () {
            var deferred = $q.defer();

            var req = {
                method: 'get',
                //TODO: Need to change to the right service call, right now no service for vendor is available
                url: configuration.BASE_URL + '/bookings/'
            }

            $http(req).success(function (response) {
                listOfBookings = response;
                deferred.resolve(listOfBookings);
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

    .factory('VendorServices', ['$http', '$rootScope', '$q', function ($http,$rootScope, $q) {
        var service = {};
/*
        service.getAllCompletedService = function () {
            var data = [];
            var bookingPromise = BookingService.getAllBookingsForCurrentVendor();
            bookingPromise.then(function (data) {
                angular.forEach(data, function (item) {
                    if (item.status == codeTypes.COMPLETED && item.vendorId == $rootScope.user.userId) {
                        data.push(item);
                    }
                });
            }, function (error) {
                alert('No data found');
            });
            return data;
        };

        service.getAllAssignedService = function () {

            var data = [];
            var bookingPromise = BookingService.getAllBookingsForCurrentVendor();
            bookingPromise.then(function (data) {
                angular.forEach(data, function (item) {
                    if (item.status == codeTypes.ASSIGNED_TO_VENDOR && item.vendorId == $rootScope.user.userId) {
                        data.push(item);
                    }
                });
            }, function (error) {
                alert('No data found');
            });
            return data;
        };
*/
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

    .factory('UtilityServices', ['$http', '$q', function ($http, $q) {
        var service = {};

        service.validateAddress = function (data) {

            var url = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + data.address +
                '+' + data.zipcode +
                '&key=AIzaSyBqZSX5uLhpIMlUYHpRg0Lb0A2M7KxzXe0';

            var deferred = $q.defer();

            return $http.get(url)
                .then(function (response) {

                    var parsedAddress = {};
                    var validateAddress = 0;


                    if (response.data.results != null && response.data.results[0] != null) {
                        var address = response.data.results[0];

                        if (address.partial_match == null) {

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

                        } else {
                            parsedAddress.valid = false;
                        }
                    }

                    return parsedAddress;

                }, function (response) {
                    return deferred.reject(response);
                });

        };

        /*
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
         */
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

