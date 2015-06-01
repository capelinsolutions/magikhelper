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
                            BookingService.setClient(response)
                            AuthenticationService.SetCredentials(user);
                            $location.path('/sidemenu/availability');
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


                var address = UtilityServices.validateAddress($scope.client.address);
                address.formatted_address = $scope.client.address.formatted_address;
                $scope.client.address = address;

                if ($scope.client.address.valid) {

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

    .controller('AvailableServicesCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', function ($scope, $rootScope, $location, AvailableServices) {

        $scope.availableServices = AvailableServices.getFetchedServices();

    }])

    .controller('ContactPersonCtrl', ['$scope', '$rootScope', '$location', 'BookingService', function ($scope, $rootScope, $location, BookingService) {

        var client = BookingService.getBookingObject().client;
        $scope.contact = {};

        $scope.contact.firstName = client.firstName;
        $scope.contact.lastName = client.lastName;
        $scope.contact.phone = client.mobilePhone;
        $scope.contact.email = client.email;


    }])

    .controller('VendorJobListCtrl', ['$scope', '$rootScope', '$location', 'VendorServices', function ($scope, $rootScope, $location, VendorServices) {
        $scope.listAllAssignedServices= VendorServices.getAllAssignedService();
        $scope.listAllCompletedServices= VendorServices.getAllCompletedService();

        $scope.getJobDetails = function (jobId) {
            $location.path('/sidemenu/job/'+jobId);
        }

    }])
    .controller('VendorJobDetailCtrl', function ($scope, $stateParams, VendorServices) {
        VendorServices.findById($stateParams.jobId).then(function(job) {
            $scope.job = job;
        });
    })
    .controller('MenuCtrl', function ($scope, $stateParams, BookingService) {
        $scope.isLoggedIn = false;
    })
    .controller('MainNavigationCtrl', function($scope, $ionicNavBarDelegate, $ionicSideMenuDelegate) {
        $scope.getPreviousTitle = function() { return $ionicNavBarDelegate.$getByHandle('mainNavBar').getPreviousTitle() };
        $scope.toggleRight = function () {  return $ionicSideMenuDelegate.$getByHandle('mainSideMenu').toggleLeft()} ;
    })

    .controller('RightMenuCtrl', function($scope, $state, BookingService) {
        //var isLoggedIn  = BookingService.isLoggedIn;
        var loginMenu = {stateName : 'sidemenu.login', labelName: 'Login' };
        var signUpMenu = {stateName : 'sidemenu.signup', labelName: 'Sign Up' };
        var bookingMenu = {stateName : 'sidemenu.availability', labelName: 'Create a job' };
        var userProfileMenu = {stateName : 'sidemenu.userprofile', labelName: 'User Profile' };
        var aboutMenu = {stateName : 'sidemenu.about', labelName: 'About' };
        var vendorAssignedMenu = {stateName : 'sidemenu.vendorJoblist', labelName: 'Job List' };


        if (BookingService.isLoggedIn() &&  BookingService.isVendor()) {
            $scope.subMenus = [vendorAssignedMenu,aboutMenu];
        } else if (BookingService.isLoggedIn()) {
            $scope.subMenus = [userProfileMenu,bookingMenu,aboutMenu];
        } else {
            $scope.subMenus = [loginMenu,signUpMenu,aboutMenu];
        }

        $scope.activeSubMenuStateName = 'sidemenu.login';
        $scope.setActiveSubMenu = function(subMenuStateName) {
            //$scope.activeSubMenuStateName=subMenuStateName;
            return $state.go(subMenuStateName);
        };
    })

    .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    })

    .controller('MapCtrl', function ($scope) {
        $scope.latitude = 32.942700;
        $scope.longitude = -96.81539;
        $scope.zoom = 15;


        $scope.refresh = function () {


            $scope.latitude = 32.941858;
            $scope.longitude = -96.818282;

            $location.path('/sidemenu/location');

        };
    })

;

