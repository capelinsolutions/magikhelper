angular.module('starter.controllers', [])
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {
                $scope.dataLoading = true;
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
    .controller('SignUpCtrl',['$scope','$location', 'SignUpService', function ($scope, $location,SignUpService) {
        $scope.createUser = function () {

            if (SignUpService.isPasswordSame($scope.password, $scope.confirmPassword)) {
                var object = {email: $scope.email, firstName: $scope.contactName, password: $scope.password};

                SignUpService.Save(object , function (response){
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

    .controller('ChatsCtrl', function ($scope, Chats) {
        $scope.chats = Chats.all();
        $scope.remove = function (chat) {
            Chats.remove(chat);
        }
    })

    .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    })

    .controller('AccountCtrl', function ($scope) {
        $scope.settings = {
            enableFriends: true
        };
    });
