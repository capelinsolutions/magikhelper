angular.module('starter.controllers', [])
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {
                $scope.dataLoading = true;

                //testing vendor flow
                //$location.path('/ven_joblist');
                //testing vendor flow
                if ($scope.username =='vendor') {
                    $location.path('/ven_joblist');
                } else {
                    $location.path('/user_joblist');
                }


                //$location.path('/availability');
                /*
                AuthenticationService.Login($scope.username, $scope.password, function (response) {
                    if (response.success) {
                        AuthenticationService.SetCredentials($scope.username, $scope.password);
                        $location.path('/availability');
                    } else {
                        alert(response.message);
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                        alert(response.message);
                    }
                 });*/
            };
        }])
    .controller('SignUpCtrl', ['$scope', '$location', 'SignUpService', function ($scope, $location, SignUpService) {
        $scope.createUser = function () {

            if (SignUpService.isPasswordSame($scope.password, $scope.confirmPassword)) {
                var object = {email: $scope.email, firstName: $scope.contactName, password: $scope.password};

                SignUpService.Save(object, function (response) {
                    if (response.success) {
                        $location.path('/menu');
                    } else {
                        alert(response.message);
                    }
                });
            } else {
                alert("Passwords don't match");
            }
        };

    }])

    .controller('AvailabilityCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', function ($scope, $rootScope, $location, AvailableServices) {
        $scope.checkAvailability = function () {

            AvailableServices.getAllServices($scope.searchZipCode, function (response) {
                if (response.length == 0) {
                    alert("No Available Service found for that Zip Code!");
                } else {
                    $location.path('/services');
                }

            });

        }
    }])

    .controller('AvailableServicesCtrl', ['$scope', '$rootScope', '$location', 'AvailableServices', function ($scope, $rootScope, $location, AvailableServices) {

        $scope.availableServices = AvailableServices.getFetchedServices();

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
