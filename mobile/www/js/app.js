// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'ngAutocomplete','starter.controllers', 'starter.services', 'ui.router'])

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
    /*
            .state('side-menu2', {
                url: '/menu',
                templateUrl: 'templates/side-menu2.html'
            })

            .state('signup', {
                url: '/signup',
                controller: 'SignUpCtrl',
                templateUrl: 'templates/signup.html'
            })

            .state('login', {
                url: '/login',
                controller: 'LoginCtrl',
                templateUrl: 'templates/login.html'
            })

            .state('page11', {
                url: '/availability',
                templateUrl: 'templates/availability.html'
            })

            .state('vendorJoblist', {
                url: '/ven_joblist',
                controller: 'VendorJobListCtrl',
                templateUrl: 'templates/ven_joblist.html'
            })
            .state('vendorJobDetail', {
                url: '/job/:jobId',
                templateUrl: 'templates/ven_jobDetail.html',
                controller: 'VendorJobDetailCtrl'
            })

            .state('page12', {
                url: '/services',
                templateUrl: 'templates/services.html'
            })

            .state('booking', {
                url: '/booking',
                templateUrl: 'templates/booking.html'
            })

            .state('page14', {
                url: '/info',
                templateUrl: 'templates/info.html'
            })

            .state('page13', {
                url: '/confirm',
                templateUrl: 'templates/confirmation.html'
            })

            .state('home', {
                url: 'index',
                templateUrl: 'index.html'
            })

*/
            .state('side-menu2', {
                url: '/menu',
                templateUrl: 'templates/side-menu2.html'
            })
            .state('signup', {
                url: '/signup',
                controller: 'SignUpCtrl',
                templateUrl: 'templates/signup.html'
            })

            .state('login', {
                url: '/login',
                controller: 'LoginCtrl',
                templateUrl: 'templates/login.html'
            })
            .state('userJobList', {
                url: '/user_joblist',
                controller: 'LoginCtrl',
                templateUrl: 'templates/user_joblist.html'
            })

            .state('vendorJoblist', {
                url: '/ven_joblist',
                controller: 'VendorJobListCtrl',
                templateUrl: 'templates/ven_joblist.html'
            })
            .state('vendorJobDetail', {
                url: '/job/:jobId',
                templateUrl: 'templates/ven_jobDetail.html',
                controller: 'VendorJobDetailCtrl'
            })

            .state('services', {
                url: '/services',
                templateUrl: 'templates/services.html'
            })

            .state('about', {
                url: '/about',
                templateUrl: 'templates/about.html'
            })

            .state('contact_person', {
                url: '/contact_person',
                templateUrl: 'templates/contact_person.html'
            })

            .state('address', {
                url: '/address',
                templateUrl: 'templates/address.html'
            })

            .state('availability', {
                url: '/availability',
                controller:'AvailabilityCtrl',
                templateUrl: 'templates/availability.html'
            })

            .state('booking', {
                url: '/booking',
                templateUrl: 'templates/booking.html'
            })
            .state('confirmation', {
                url: '/confirmation',
                templateUrl: 'templates/confirmation.html'
            })
            .state('home', {
                url: '/index',
                templateUrl: 'index.html'
            })

            .state('location', {
                url: '/location',
                controller: 'MapCtrl',
                templateUrl: 'templates/location.html'
            })
        ;

        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/login');

    });