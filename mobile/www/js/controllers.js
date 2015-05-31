angular.module('starter.controllers', [])
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();

            $scope.login = function () {

                $scope.dataLoading = true;

                //testing vendor flow
                //$location.path('/ven_joblist');

                var user = {email: $scope.username, password: $scope.password};

                AuthenticationService.Login(user, function (response) {
                    if (response.login) {
                        AuthenticationService.SetCredentials(user);
                        $location.path('/availability');
                    } else {
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                        alert(response.message);
                    }
                });
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

    .controller('AvailabilityCtrl', ['$scope',  '$rootScope', '$location', 'AvailableServices', 'BookingService', function ($scope, $rootScope, $location, AvailableServices,BookingService) {
        $scope.availabilityData = {};

        $scope.checkAvailability = function () {

            for (index = 0; index < $scope.availabilityData.details.address_components.length; index++) {
                for (subIndex = 0; subIndex < $scope.availabilityData.details.address_components[index].types.length; subIndex++) {
                    var type = $scope.availabilityData.details.address_components[index].types[subIndex];
                    if (type == "street_number") {
                        $scope.availabilityData.address = $scope.availabilityData.details.address_components[index].long_name;
                    } else if (type == "route") {
                        $scope.availabilityData.address += " " + $scope.availabilityData.details.address_components[index].long_name;
                    } else if (type == "locality") {
                        $scope.availabilityData.city = $scope.availabilityData.details.address_components[index].long_name;
                    } else if (type == "administrative_area_level_1") {
                        $scope.availabilityData.state = $scope.availabilityData.details.address_components[index].long_name;
                    } else if (type == "country") {
                        $scope.availabilityData.country = $scope.availabilityData.details.address_components[index].long_name;
                    } else if (type == "postal_code") {
                        $scope.availabilityData.zipCode = $scope.availabilityData.details.address_components[index].long_name;
                    }
                    //Setting Address for Booking
                    BookingService.setAddress($scope.availabilityData);
                }
            }
            AvailableServices.getAllServices($scope.availabilityData.zipCode, function (response) {
                if (response.length == 0) {
                    alert("No Available Service found for that Zip Code!");
                } else {
                    BookingService.setAddress();
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
    })

    .controller('MapCtrl', function ($scope, $ionicLoading, $compile) {
        function initialize() {
            var myLatlng = new google.maps.LatLng(32.942993, -96.815450);

            var mapOptions = {
                center: myLatlng,
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

            //Marker + infowindow + angularjs compiled ng-click
            var contentString = "<div><a ng-click='clickTest()'>Click me!</a></div>";
            var compiled = $compile(contentString)($scope);

            var infowindow = new google.maps.InfoWindow({
                content: compiled[0]
            });

            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title: 'Magik Help'
            });

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });

            $scope.map = map;
        }

        google.maps.event.addDomListener(window, 'load', initialize);

        $scope.centerOnMe = function () {
            if (!$scope.map) {
                return;
            }

            $scope.loading = $ionicLoading.show({
                content: 'Getting current location...',
                showBackdrop: false
            });

            navigator.geolocation.getCurrentPosition(function (pos) {
                $scope.map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
                $scope.loading.hide();
            }, function (error) {
                alert('Unable to get location: ' + error.message);
            });
        };

        $scope.clickTest = function () {
            alert('Example of infowindow with ng-click')
        };

    })

;

