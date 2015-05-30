angular.module('starter.controllers', [])
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status


            AuthenticationService.ClearCredentials();

            $scope.login = function () {
                $scope.dataLoading = true;


                //testing vendor flow
                //$location.path('/ven_joblist');

                //$location.path('/availability');


                AuthenticationService.Login($scope.username, $scope.password, function (response) {

                    if (response.success) {
                        AuthenticationService.SetCredentials($scope.username, $scope.password);
                        $location.path('/availability');
                    } else {
                        alert(response.message);
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                    }
                });
            };
        }])
    .controller('SignUpCtrl', ['$scope', '$location', 'SignUpService', function ($scope, $location, SignUpService) {
        $scope.createUser = function () {

            if (SignUpService.isPasswordSame($scope.password, $scope.confirmPassword)) {
                var object = {email: $scope.email, firstName: $scope.contactName, password: $scope.password};

                SignUpService.Save(object, function (response) {
                    if (response) {
                        $location.path('/login');
                    } else {
                        alert(response);
                    }
                });
            } else {
                alert("Passwords don't match");
            }
        };

    }])

    .controller('AvailabilityCtrl', ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {
        $scope.checkAvailability = function () {
            $location.path('/services');
        }
    }])

    .controller('AvailableServicesCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', function ($scope, $rootScope, $location, AvailableServices) {
        $scope.listAllServices = function () {
            //TODO: to be implemented
        }
    }])

    .controller('VendorJobListCtrl', ['$scope', '$rootScope', '$location', 'VendorServices', function ($scope, $rootScope, $location, VendorServices) {
        $scope.listAllAssignedServices= VendorServices.getAllAssignedService();
        $scope.listAllCompletedServices= VendorServices.getAllCompletedService();

        $scope.getJobDetails = function (jobId) {
            $location.path('/job/'+jobId);
        }

    }])
    .controller('VendorJobDetailCtrl', function ($scope, $stateParams, VendorServices) {
        VendorServices.findById($stateParams.jobId).then(function(job) {
            $scope.job = job;
        });
    })
    .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    });
