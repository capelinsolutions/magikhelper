angular.module('starter.controllers', [])
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {
                $scope.dataLoading = true;

                $location.path('/availability');
                /*
                AuthenticationService.Login($scope.username, $scope.password, function (response) {
                    if (response.success) {
                        AuthenticationService.SetCredentials($scope.username, $scope.password);
                        $location.path('/availability');
                    } else {
                        alert(response.message);
                        $scope.error = response.message;
                        $scope.dataLoading = false;
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

    .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    });
