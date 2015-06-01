// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'ngAutocomplete','starter.controllers', 'starter.services', 'starter.config', 'starter.messages','ngMap', 'ui.router'])

    .run(function ($ionicPlatform) {
        $ionicPlatform.ready(function () {
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins.Keyboard) {
                cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            }
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleDefault();
            }
        });
    })

    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

            .state('sidemenu', {
                url: "/sidemenu",
                abstract: true,
                templateUrl: "templates/menu.html"
            })
            .state('sidemenu.signup', {
                url: '/signup',
                views: {
                    'mainContent': {
                        controller: 'SignUpCtrl',
                        templateUrl: 'templates/signup.html'
                    }
                }
            })

            .state('sidemenu.login', {
                url: '/login',
                views: {
                    'mainContent': {
                        controller: 'LoginCtrl',
                        templateUrl: 'templates/login.html'
                    }
                }
            })
            .state('sidemenu.userJobList', {
                url: '/user_joblist',
                views: {
                    'mainContent': {
                        controller: 'LoginCtrl',
                        templateUrl: 'templates/user_joblist.html'
                    }
                }
            })


            .state('sidemenu.services', {
                url: '/services',
                views: {
                    'mainContent': {
                        controller: 'AvailableServicesCtrl',
                        templateUrl: 'templates/services.html'
                    }
                }
            })

            .state('sidemenu.about', {
                url: '/about',
                views: {
                    'mainContent': {
                        templateUrl: 'templates/about.html'
                    }
                }
            })

            .state('sidemenu.contact_person', {
                url: '/contact_person',
                views: {
                    'mainContent': {
                        controller: 'ContactPersonCtrl',
                        templateUrl: 'templates/contact_person.html'
                    }
                }
            })
            .state('sidemenu.address', {
                url: '/address',
                views: {
                    'mainContent': {
                        templateUrl: 'templates/address.html'
                    }
                }
            })

            .state('sidemenu.availability', {
                url: '/availability',
                views: {
                    'mainContent': {
                        controller: 'AvailabilityCtrl',
                        templateUrl: 'templates/availability.html'
                    }
                }
            })
            .state('sidemenu.userprofile', {
                url: '/userprofile',
                views: {
                    'mainContent': {
                        templateUrl: 'templates/user_profile.html'
                    }
                }
            })
            .state('sidemenu.booking', {
                url: '/booking',
                views: {
                    'mainContent': {
                        controller: 'BookingCtrl',
                        templateUrl: 'templates/booking.html'
                    }
                }
            })
            .state('sidemenu.confirmation', {
                url: '/confirmation',
                views: {
                    'mainContent': {
                        templateUrl: 'templates/confirmation.html'
                    }
                }
            })
            .state('sidemenu.location', {
                url: '/location',
                views: {
                    'mainContent': {
                        controller: 'MapCtrl',
                        templateUrl: 'templates/location.html'
                    }
                }

            })
            .state('sidemenu.vendorJoblist', {
                url: '/ven_joblist',
                views: {
                    'mainContent': {
                        controller: 'VendorJobListCtrl',
                        templateUrl: 'templates/ven_joblist.html'
                    }
                }
            })
            .state('sidemenu.vendorJobDetail', {
                url: '/job/:jobId',
                views: {
                    'mainContent': {
                        templateUrl: 'templates/ven_jobDetail.html',
                        controller: 'VendorJobDetailCtrl'
                    }
                }
            })

            .state('home', {
                url: '/index',
                templateUrl: 'index.html'
            });

        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/sidemenu/login');

    });