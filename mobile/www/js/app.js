// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'ui.router'])

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

            .state('page12', {
                url: '/services',
                templateUrl: 'templates/services.html'
            })

            .state('page10', {
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
        ;

        // if none of the above states are matched, use this as the fallback

        $urlRouterProvider.otherwise('/menu');


    });
