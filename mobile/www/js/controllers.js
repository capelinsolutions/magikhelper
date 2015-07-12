angular.module('starter.controllers', ['starter.messages'])

    .controller('LoginCtrl', ['$scope', '$rootScope', 'codeTypes', '$location', 'AuthenticationService', 'BookingService',
        function ($scope, $rootScope, codeTypes, $location, AuthenticationService, BookingService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {

                $scope.dataLoading = true;

                var user = {email: $scope.username, password: $scope.password};

                AuthenticationService.Login(user, function (response) {
                    if (response.login) {
                        var client = {}

                        client.userId = response.userId;
                        client.email = response.email;
                        client.firstName = response.contact.firstName;
                        client.lastName = response.contact.lastName;
                        client.mobilePhone = response.contact.mobilePhone;
                        client.street = response.contact.street;
                        client.additional = response.contact.additional;
                        client.city = response.contact.city;
                        client.zip = response.contact.zip;
                        client.state = response.contact.state;
                        client.country = response.contact.country;

                        for (var i = 0; i < response.roles.length; i++) {
                            var role = response.roles[i];

                            if (role.roleId == codeTypes.VENDOR_ROLE_ID) {
                                client.isVendor = true;
                                client.isClient = false;
                            } else if (role.roleId == codeTypes.USER_ROLE_ID) {
                                client.isClient = true;
                                client.isVendor = false;
                            }
                        }

                        $rootScope.user = client;
                        BookingService.setClient(client)
                        AuthenticationService.SetCredentials(response);
                        if (client.isVendor) {
                            $location.path('/sidemenu/ven_joblist');
                        } else if (client.isClient) {
                            $location.path('/sidemenu/user_joblist');
                        }

                    } else {
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                        alert(response.message);
                    }
                });

            };
        }])
    .controller('SignUpCtrl', ['$scope', '$location', 'SignUpService', 'UtilityServices', function ($scope, $location, SignUpService, UtilityServices) {
        $scope.createUser = function () {

            if (SignUpService.isPasswordSame($scope.client.password, $scope.client.confirmPassword)) {

                $scope.client.address = UtilityServices.validateAddress($scope.client.address);

                if ($scope.client.address.valid) {

                    $scope.client.contact.street = $scope.client.address.street;
                    $scope.client.contact.city = $scope.client.address.city;
                    $scope.client.contact.zip = $scope.client.address.zip;
                    $scope.client.contact.state = $scope.client.address.state;
                    $scope.client.contact.country = $scope.client.address.country;


                    SignUpService.Save($scope.client, function (response) {
                        if (response) {
                            $location.path('/sidemenu/login');
                        } else {
                            alert(response.message);
                        }
                    });
                } else {
                    alert("Address is not Valid !");
                }

            } else {
                alert("Passwords don't match");
            }
        };

    }])

    .controller('AvailabilityCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', 'BookingService', 'UtilityServices',
        function ($scope, $rootScope, $location, AvailableServices, BookingService, UtilityServices) {

            $scope.availabilityData = {};


            $scope.checkAvailability = function () {

                var address = UtilityServices.validateAddress($scope.availabilityData.details);

                if (address.valid) {

                    AvailableServices.getAllServices(address.zip, function (response) {
                        if (response.length == 0) {
                            alert("No Available Service found for that Zip Code!");
                        } else {
                            BookingService.setAddress(address);
                            $location.path('/sidemenu/services');
                        }

                    });
                } else {
                    alert("Address is not Complete!");
                }
            }
        }])

    .controller('AvailableServicesCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', 'BookingService', function ($scope, $rootScope, $location, AvailableServices, BookingService) {
        $scope.availableServices = AvailableServices.getFetchedServices();
        $scope.setSelectedService = function (serviceObj) {
            BookingService.setSelectedService(serviceObj);
            $location.path('/sidemenu/booking');
        }

    }])

    .controller('ContactPersonCtrl', ['$scope', '$rootScope', '$location', 'BookingService', function ($scope, $rootScope, $location, BookingService) {

        var client = BookingService.getBookingObject().client;

        $scope.contact = {};

        $scope.contact.firstName = client.firstName;
        $scope.contact.lastName = client.lastName;
        $scope.contact.mobilePhone = client.mobilePhone;
        $scope.contact.email = client.email;

        $scope.setContactDetails = function () {
            BookingService.setContactPerson($scope.contact);
            $location.path('/sidemenu/confirmation');
        }


    }])

    .controller('ConfirmationCtrl', ['$scope', '$rootScope', '$filter', '$location', 'BookingService', function ($scope, $rootScope, $filter, $location, BookingService) {

        $scope.booking = BookingService.getBookingObject();
        $scope.booking.booked = false;


        $scope.setConfirmBooking = function () {

            BookingService.setContactPerson($scope.contact);
            $location.path('/sidemenu/confirmation');
        }

        $scope.done = function () {
            $scope.booking = {};
            $scope.bookingPojo = {};
            $scope.listAllUserPastJobs = BookingService.getAllBookingForCurrentUser();

            $location.path('/sidemenu/user_joblist');
        }

        $scope.makeBooking = function () {
            $scope.bookingPojo = {};


            $scope.bookingPojo.clientId = $scope.booking.client.userId;
            $scope.bookingPojo.serviceId = $scope.booking.service.serviceId;
            $scope.bookingPojo.bookedDate = $filter('date')($scope.booking.bookingDate, 'MM/dd/yyyy');
            $scope.bookingPojo.bookedTime = $scope.booking.bookedTime;
            $scope.bookingPojo.duration = $scope.booking.duration;

            var bookingContact = {};
            bookingContact.firstName = $scope.booking.firstName;
            bookingContact.lastName = $scope.booking.lastName;
            bookingContact.mobilePhone = $scope.booking.phone;
            bookingContact.street = $scope.booking.address.street;
            bookingContact.city = $scope.booking.address.city;
            bookingContact.zip = $scope.booking.address.zip;
            bookingContact.state = $scope.booking.address.state;
            bookingContact.country = $scope.booking.address.country;

            $scope.bookingPojo.bookingContact = bookingContact;

            BookingService.makeBooking($scope.bookingPojo, function (response) {
                $scope.booking.confirmation = 1234;
                $scope.booking.booked = true;
            });
            $location.path('/sidemenu/confirmation');
        }


    }])

    .controller('BookingCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', 'BookingService', function ($scope, $rootScope, $location, AvailableServices, BookingService) {
        $scope.availableServices = AvailableServices.getFetchedServices();
        $scope.bookingDetails = {};

        $scope.initialize = function () {
            $scope.bookingDetails.hours = "4";

            $scope.timeSlots = [
                {code: "08:00", name: "8:00 AM - 10:00 AM"},
                {code: "10:00", name: "10:00 AM - 12:00 PM"},
                {code: "12:00", name: "12:00 PM - 02:00 PM"},
                {code: "14:00", name: "02:00 PM - 04:00 PM"},
                {code: "16:00", name: "04:00 PM - 06:00 PM"}
            ];


        }


        $scope.setBookingDetails = function () {
            BookingService.setBookingDetails($scope.bookingDetails);
            $location.path('/sidemenu/contact_person');
        }

        $scope.makeBooking = function () {
            $scope.bookingDetails.confirmation = "12345";

            $location.path('/sidemenu/confirmation');
        }

        $scope.initialize();

    }])
    .controller('JobListCtrl', ['$scope', '$rootScope', '$q', '$location', 'BookingService', 'LocationServices', 'demoVendorInfo', function ($scope, $rootScope, $q, $location, BookingService, LocationServices, demoVendorInfo) {
        $scope.listAllUserPastJobs = [];

        $scope.getListAllUserPastJobs = function () {
            return $scope.listAllUserPastJobs;
        }

        $scope.doRefresh = function () {

            var bookingPromise = BookingService.getAllBookings();

            bookingPromise.then(function (data) {
                $scope.listAllUserPastJobs = data;
            }, function (error) {
                alert('No data found');
            });

            $scope.$broadcast('scroll.refreshComplete');
        }

        var bookingPromise = BookingService.getAllBookings();

        bookingPromise.then(function (data) {
            $scope.listAllUserPastJobs = data;
        }, function (error) {
            alert('No data found');
        });

        $scope.locateVendor = function (bookingId) {
            //$rootScope.globals.currentUser.userId
            //TODO: remove hardcoding vendor Id
            var locationPromise = LocationServices.getVendorLocation(demoVendorInfo.BOOKING_ID);

            locationPromise.then(function (data) {
                LocationServices.setVendorCurrentLocation(data);
                $location.path('/sidemenu/location/' + bookingId);
            }, function (error) {
                alert('No data found');
            });

        }
    }])

    .controller('VendorJobListCtrl', ['$scope', '$rootScope', 'codeTypes', '$location', 'VendorServices', 'LocationServices', 'BookingService', function ($scope, $rootScope, codeTypes, $location, VendorServices, LocationServices, BookingService) {
        $scope.listAllAssignedServices = {};
        $scope.listAllCompletedServices = {};

        //TODO: It has to be refactored once we have service returning booking for a vendor
        var bookingAssignedPromise = BookingService.getAllBookingsForCurrentVendor();
        bookingAssignedPromise.then(function (bookings) {
            var data = [];
            angular.forEach(bookings, function (item) {
                if (item.status == codeTypes.ASSIGNED_TO_VENDOR && item.vendorId == $rootScope.user.userId) {
                    data.push(item);
                }
            });
            $scope.listAllAssignedServices = data;
        }, function (error) {
        });

        //TODO: It has to be refactored once we have service returning booking for a vendor
        var bookingCompletePromise = BookingService.getAllBookingsForCurrentVendor();
        bookingCompletePromise.then(function (bookings) {
            var data = [];
            angular.forEach(bookings, function (item) {
                if (item.status == codeTypes.COMPLETED && item.vendorId == $rootScope.user.userId) {
                    data.push(item);

                }
            });
            $scope.listAllCompletedServices = data;
        }, function (error) {
        });



        var allOpenBookingsPromise = BookingService.getAllOpenBookings();

        allOpenBookingsPromise.then(function (data) {
            $scope.listAllOpenServices = data;
        }, function (error) {
            alert('No data found');
        });

        $scope.getJobDetails = function (jobId, status) {
            // alert('/sidemenu/job?jobId=' + jobId+ '&status=' + status);
            $location.url('/sidemenu/job?jobId=' + jobId + '&status=' + status);
        }

    }])

    .controller('VendorJobDetailCtrl', function ($scope, $filter, $rootScope, $location, $stateParams, VendorServices, $cordovaGeolocation, LocationServices, codeTypes, BookingService) {
        var locObj = {};

        // if ($stateParams.status == codeTypes.CREATED) {
            BookingService.findById($stateParams.jobId)
                .then(function (job) {
                    $scope.job = job;
                }
            );
        //} else {
        //    VendorServices.findById($stateParams.jobId)
        //        .then(function (job) {
        //            $scope.job = job;
        //        }
        //    );

        //}

        $scope.isInProgress = codeTypes.IN_PROGRESS;
        $scope.isAssignedToVendor = codeTypes.ASSIGNED_TO_VENDOR;
        $scope.isCreated = codeTypes.CREATED;


        $scope.showInRoute = function () {
            var currentDate = new Date();
            var currentDateStr = $filter('date')(currentDate, "MM/dd/yyyy");
            var newCurrentDate = new Date(currentDateStr);
            return (new Date($scope.job.bookedDate).getTime() == newCurrentDate.getTime()
            && $scope.job.status == codeTypes.ASSIGNED_TO_VENDOR);
        }

        function getCurrentPosition(jobId, vendorId) {
            var posOptions = {timeout: 10000, enableHighAccuracy: false};
            $cordovaGeolocation
                .getCurrentPosition(posOptions)
                .then(function (position) {
                    var coordinates = {};
                    var lat = position.coords.latitude;
                    var long = position.coords.longitude;

                    //Send request to service
                    locObj.vendorId = vendorId;
                    locObj.bookingId = jobId;
                    locObj.latitude = lat;
                    locObj.longitude = long;

                    LocationServices.setCordinates(locObj);

                    setWatch(jobId, vendorId);

                    return coordinates;
                }, function (err) {
                    alert('Make sure your location service is turned on.');
                });
        }

        function setWatch(jobId, vendorId) {
            var watchOptions = {
                frequency: 1000,
                timeout: 3000,
                enableHighAccuracy: false // may cause errors if true
            };

            var watch = $cordovaGeolocation.watchPosition(watchOptions);
            watch.then(
                null,
                function (err) {
                    // error
                },
                function (position) {
                    var lat = position.coords.latitude;
                    var long = position.coords.longitude;

                    //Send request to service
                    locObj.vendorId = vendorId;
                    locObj.bookingId = jobId;
                    locObj.latitude = lat;
                    locObj.longitude = long;

                    LocationServices.setCordinates(locObj);
                });
        }


        function clearWatch() {
            $cordovaGeolocation.clearWatch(watch)
                .then(function (result) {
                    locObj = {};
                    // success
                }, function (error) {
                    // error
                });
        }

        $scope.startRouting = function (jobId, vendorId) {
            getCurrentPosition();
        }

        $scope.acceptJob = function (jobId, vendorId) {
            var obj = {};

            obj.bookingId = jobId;
            if (vendorId) {
                obj.vendorId = vendorId;
            } else {
                //TODO: remove hardcoding
                //obj.vendorId = $rootScope.user.userId;
                obj.vendorId = '4';
            }

            var assignPromise = BookingService.assignBooking(obj);

            assignPromise.then(function (data) {
                alert('You are assigned the job');
                $location.path('/sidemenu//ven_joblist/');
            }, function (error) {
                alert('Job cannot be assigned');
            });
        }
    })

    .controller('LogoutCtrl', function ($scope, $rootScope, $location, BookingService) {
        $rootScope.user = {};
        BookingService.clearBooking();
        $location.path('/sidemenu/login');
    })

    .controller('MenuCtrl', function ($scope, $stateParams, BookingService) {
        $scope.isLoggedIn = false;
    })
    .controller('MainNavigationCtrl', function ($scope, $ionicNavBarDelegate, $ionicSideMenuDelegate) {
        $scope.getPreviousTitle = function () {
            return $ionicNavBarDelegate.$getByHandle('mainNavBar').getPreviousTitle()
        };
        $scope.toggleRight = function () {
            return $ionicSideMenuDelegate.$getByHandle('mainSideMenu').toggleLeft()
        };
    })

    .controller('RightMenuCtrl', function ($scope, $rootScope, $state, BookingService) {
        //var isLoggedIn  = BookingService.isLoggedIn;
        var loginMenu = {stateName: 'sidemenu.login', labelName: 'Login'};
        var signUpMenu = {stateName: 'sidemenu.signup', labelName: 'Sign Up'};
        var bookingMenu = {stateName: 'sidemenu.availability', labelName: 'Create a job'};
        var userProfileMenu = {stateName: 'sidemenu.userprofile', labelName: 'User Profile'};
        var aboutMenu = {stateName: 'sidemenu.about', labelName: 'About'};
        var vendorAssignedMenu = {stateName: 'sidemenu.vendorJoblist', labelName: 'Job List'};
        var vendorProfileMenu = {stateName: 'sidemenu.vendorProfile', labelName: 'Profile'};
        var logoutMenu = {stateName: 'sidemenu.logout', labelName: 'Logout'};

        $rootScope.$watch('user', function () {
            if (BookingService.isLoggedIn() && BookingService.isVendor()) {
                $scope.subMenus = [vendorAssignedMenu, vendorProfileMenu, aboutMenu, logoutMenu];
            } else if (BookingService.isLoggedIn()) {
                $scope.subMenus = [userProfileMenu, bookingMenu, aboutMenu, logoutMenu];
            } else {
                $scope.subMenus = [loginMenu, signUpMenu, aboutMenu];
            }
        });

        if (BookingService.isLoggedIn() && BookingService.isVendor()) {
            $scope.subMenus = [vendorAssignedMenu, vendorProfileMenu, aboutMenu, logoutMenu];
        } else if (BookingService.isLoggedIn()) {
            $scope.subMenus = [userProfileMenu, bookingMenu, aboutMenu, logoutMenu];
        } else {
            $scope.subMenus = [loginMenu, signUpMenu, aboutMenu];
        }

        $scope.activeSubMenuStateName = 'sidemenu.login';
        $scope.setActiveSubMenu = function (subMenuStateName) {
            //$scope.activeSubMenuStateName=subMenuStateName;
            return $state.go(subMenuStateName);
        };
    })

    .controller('GeoCtrl', function ($cordovaGeolocation) {

        var posOptions = {timeout: 10000, enableHighAccuracy: false};
        $cordovaGeolocation
            .getCurrentPosition(posOptions)
            .then(function (position) {
                var lat = position.coords.latitude
                var long = position.coords.longitude
            }, function (err) {
                // error
            });


        var watchOptions = {
            frequency: 1000,
            timeout: 3000,
            enableHighAccuracy: false // may cause errors if true
        };

        var watch = $cordovaGeolocation.watchPosition(watchOptions);
        watch.then(
            null,
            function (err) {
                // error
            },
            function (position) {
                var lat = position.coords.latitude
                var long = position.coords.longitude
            });


        watch.clearWatch();
        // OR
        $cordovaGeolocation.clearWatch(watch)
            .then(function (result) {
                // success
            }, function (error) {
                // error
            });
    })
    .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    })

    .controller('MapCtrl', function ($scope, $stateParams, $location, LocationServices, demoVendorInfo) {

        var locObj = LocationServices.getVendorCurrentLocation();
        $scope.latitude = locObj.latitude;
        $scope.longitude = locObj.longitude;
        $scope.zoom = 15;

        $scope.refresh = function () {
            //TODO: remove hardcoding vendor Id
            var locationPromise = LocationServices.getVendorLocation(demoVendorInfo.BOOKING_ID);

            locationPromise.then(function (data) {
                //$scope.latitude = data.latitude;
                //$scope.longitude = data.longitude;
                LocationServices.setVendorCurrentLocation(data);
            }, function (error) {
                alert('No data found');
            });

            //$scope.latitude = 32.941858;
            //$scope.longitude = -96.818282;
            //TODO: remove hardcoding vendor Id
            $location.path('/sidemenu/location/' + demoVendorInfo.BOOKING_ID);

        };
    })

;

