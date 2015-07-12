angular.module('starter.config', [])
    .constant('configuration', {
        APP_NAME: 'MagikHelp',
        BASE_URL: 'http://magikheper-ws.elasticbeanstalk.com/services',
        //BASE_URL: 'http://localhost:8080/magikhelper-ws/services',
        //BASE_URL: 'http://localhost:8080/magikhelper-ws/services',
        APP_VERSION : '0.1'
    });

angular.module('starter.messages', [])
    .constant('messages', {
        AVAILABILITY_SERVICE_NOT_FOUND: 'No Available Service found for that Zip Code!'
    });

angular.module('starter.codeTypes', [])
    .constant('codeTypes', {
        CREATED : 'Created',
        ASSIGNED_TO_VENDOR : 'Assigned To Vendor',
        IN_PROGRESS: 'In Progress',
        COMPLETED: 'Completed',
        CANCELLED: 'Cancelled',
        VENDOR_ROLE_ID : '3',
        USER_ROLE_ID : '2'

    });


angular.module('starter.demo', [])
    .constant('demoVendorInfo', {
        VENDOR_ID : '4',
        BOOKING_ID : '4',
        VENDOR_EMAIL : 'vendor1@hotmail.com'
    });

