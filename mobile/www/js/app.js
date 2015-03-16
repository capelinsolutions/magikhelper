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
    /*
     .config(['$routeProvider',
     function($routeProvider) {
     $routeProvider.
     when('/menu', {
     templateUrl: 'templates/side-menu2.html'
     })
     .when('/signup', {
     templateUrl: 'templates/signup.html'
     })
     .when('/login', {
     templateUrl: 'templates/login.html',
     controller: 'LoginCtrl'
     })

     .when('/availability', {
     templateUrl: 'templates/availability.html'
     })

     .when('/services', {
     templateUrl: 'templates/services.html'
     })

     .when('/booking', {
     templateUrl: 'templates/booking.html'
     })

     .when('/info', {
     templateUrl: 'templates/info.html'
     })

     .when('/confirm', {
     templateUrl: 'templates/confirmation.html'
     })

     otherwise({
     redirectTo: '/login'
     });
     }]);
     */
//.config(function($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    /*
     $stateProvider

     // setup an abstract state for the tabs directive
     .state('tab', {
     url: "/tab",
     abstract: true,
     templateUrl: "templates/tabs.html"
     })

     // Each tab has its own nav history stack:

     .state('tab.dash', {
     url: '/dash',
     views: {
     'tab-dash': {
     templateUrl: 'templates/tab-dash.html',
     controller: 'DashCtrl'
     }
     }
     })

     .state('tab.chats', {
     url: '/chats',
     views: {
     'tab-chats': {
     templateUrl: 'templates/tab-chats.html',
     controller: 'ChatsCtrl'
     }
     }
     })
     .state('tab.chat-detail', {
     url: '/chats/:chatId',
     views: {
     'tab-chats': {
     templateUrl: 'templates/chat-detail.html',
     controller: 'ChatDetailCtrl'
     }
     }
     })

     .state('tab.account', {
     url: '/account',
     views: {
     'tab-account': {
     templateUrl: 'templates/tab-account.html',
     controller: 'AccountCtrl'
     }
     }
     });

     // if none of the above states are matched, use this as the fallback
     $urlRouterProvider.otherwise('/tab/dash');
     */
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

            .state('side-menu2', {
                url: '/menu',
                templateUrl: 'templates/side-menu2.html'
            })

            .state('page8', {
                url: '/signup',
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
