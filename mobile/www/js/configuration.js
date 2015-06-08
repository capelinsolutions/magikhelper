angular.module('starter.config', [])
    .constant('configuration', {
        APP_NAME: 'MagikHelp',
        BASE_URL: 'http://magikheper-ws.elasticbeanstalk.com/services',
        //BASE_URL: 'http://localhost:8080/magikhelper-ws/services',
        APP_VERSION : '0.1'
    });

angular.module('starter.messages', [])
    .constant('messages', {
        AVAILABILITY_SERVICE_NOT_FOUND: 'No Available Service found for that Zip Code!'
    });

