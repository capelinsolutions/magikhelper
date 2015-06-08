angular.module('starter.controllers', ['starter.messages'])

    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService', 'BookingService',
        function ($scope, $rootScope, $location, AuthenticationService, BookingService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {

                $scope.dataLoading = true;

                //testing vendor flow
                //$location.path('/ven_joblist');

                if ($scope.username == 'vendor@test.com') {
                    $location.path('/sidemenu/ven_joblist');
                } else {
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

                            $rootScope.user = client;
                            BookingService.setClient(client)
                            AuthenticationService.SetCredentials(response);
                            $location.path('/sidemenu/user_joblist');
                        } else {
                            $scope.error = response.message;
                            $scope.dataLoading = false;
                            alert(response.message);
                        }
                    });
                }

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

    .controller('BookingCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices','BookingService', function ($scope, $rootScope, $location, AvailableServices, BookingService) {
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
    .controller('JobListCtrl', ['$scope', '$rootScope', '$q', '$location', 'BookingService', 'LocationServices', function ($scope, $rootScope, $q, $location, BookingService, LocationServices) {
        $scope.listAllUserPastJobs = [];

        $scope.getListAllUserPastJobs = function () {
            return $scope.listAllUserPastJobs;
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
            var locationPromise = LocationServices.getVendorLocation('5');

            locationPromise.then(function (data) {
                LocationServices.setVendorCurrentLocation(data);
                $location.path('/sidemenu/location/' + bookingId);
            }, function (error) {
                alert('No data found');
            });

        }
    }])

    .controller('VendorJobListCtrl', ['$scope', '$rootScope', '$location', 'VendorServices','LocationServices', function ($scope, $rootScope, $location, VendorServices, LocationServices) {
        $scope.listAllAssignedServices = VendorServices.getAllAssignedService();
        $scope.listAllCompletedServices = VendorServices.getAllCompletedService();

        $scope.getJobDetails = function (jobId) {
            $location.path('/sidemenu/job/' + jobId);
        }

    }])

    .controller('VendorJobDetailCtrl', function ($scope,$filter, $stateParams, VendorServices,$cordovaGeolocation, LocationServices) {
        var locObj = {};

        VendorServices.findById($stateParams.jobId)
            .then(function (job) {
                $scope.job = job;
                //getCurrentPosition();
            }
        );

        $scope.showInRoute = function () {
            var currentDate = new Date();
            var currentDateStr = $filter('date')(currentDate,"MM/dd/yyyy");
            var newCurrentDate = new Date(currentDateStr);
            return (new Date($scope.job.dateOfService).getTime() == newCurrentDate.getTime()
                        && $scope.job.status == 'ASSIGNED_TO_VENDOR');
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

        $scope.startRouting = function(jobId, vendorId) {
            getCurrentPosition();
        }

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

    .controller('RightMenuCtrl', function ($scope, $state, BookingService) {
        //var isLoggedIn  = BookingService.isLoggedIn;
        var loginMenu = {stateName: 'sidemenu.login', labelName: 'Login'};
        var signUpMenu = {stateName: 'sidemenu.signup', labelName: 'Sign Up'};
        var bookingMenu = {stateName: 'sidemenu.availability', labelName: 'Create a job'};
        var userProfileMenu = {stateName: 'sidemenu.userprofile', labelName: 'User Profile'};
        var aboutMenu = {stateName: 'sidemenu.about', labelName: 'About'};
        var vendorAssignedMenu = {stateName: 'sidemenu.vendorJoblist', labelName: 'Job List'};
        var vendorProfileMenu = {stateName: 'sidemenu.vendorProfile', labelName: 'Profile'};
        var logoutMenu = {stateName: 'sidemenu.login', labelName: 'Logout'};


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

    .controller('MapCtrl',  function ($scope,$stateParams, $location, LocationServices, demoVendorInfo) {

        var locObj = LocationServices.getVendorCurrentLocation();
        $scope.latitude = locObj.latitude;
        $scope.longitude = locObj.longitude;
        $scope.zoom = 15;

        $scope.refresh = function () {
            //TODO: remove hardcoding vendor Id
            var locationPromise = LocationServices.getVendorLocation('5');

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
            $location.path('/sidemenu/location/5' );

        };
    })

;

