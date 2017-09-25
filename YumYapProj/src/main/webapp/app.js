let app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        url: '/',
        templateUrl: 'loginRegisterContent.html'
    })
    .when('loginRegister', {
        url: '/loginRegister',
        templateUrl: 'loginRegisterContent.html'
    })
    .when('app', {
        url: '/app',
        templateUrl: 'appContent.html'
    });
});


/* LoginService */
app.service('LoginService', function($http, $q) {
    let service = this;
    
    service.user = { email: '', password: '' };
    
    service.getUser = function() {
        return service.user;
    }
 
    service.attemptLogin = function() {
        return $http.post('', service.user);
    };
});
/* LoginService */


/* RegisterService */
app.service('RegisterService', function($http, $q) {
    let service = this;
    
    service.user = {
        firstName: '',
        lastName: '',
        email: '',
        password1: '',
        password2: ''
    };
    
    service.getUser = function() {
        return service.user;
    };
    
    service.attemptRegister = function() {
        return $http.post('url', service.user);
    };
});
/* RegisterService */


/* LoginRegisterController */
app.controller('LoginRegisterController', function($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */


/* LoginController*/
app.controller('LoginController', function($scope, LoginService) {
    
    $scope.user = LoginService.getUser();
    
    $scope.attemptLogin = function() {
        console.log($scope.user);
        LoginService.attemptLogin()
        .then(
            function(response) {
                console.log('attemptLogin() success response: ');
                console.log(response);
                
                /* reset all fields upon success */
                $scope.user = { email: '', password: '' };
            },
            function(error) {
                console.log('attemptLogin() error response: ');
                console.log(error);
            }
        );
    };
});
/* LoginController */


/* RegisterController */
app.controller('RegisterController', function($scope, RegisterService) {
    
    $scope.user = RegisterService.getUser();
    
    $scope.attemptRegister = function() {
        console.log($scope.user);
        RegisterService.attemptRegister()
        .then(
            function(response) {
                console.log('attemptRegister() success response: ');
                console.log(response);
                
                /* reset all fields upon success */
                $scope.user = { firstName: '', lastName: '', email: '', password1: '', password2: '' };
            },
            function(error) {
                console.log('attemptRegister() error response: ');
                console.log(error);
            }
        );
    };
});
/* RegisterController */


/* ViewAuthorService */
app.service('ViewAuthorService', function() {
    let service = this;
    
    service.email = '';
    
    service.setEmail = function(email) {
        service.email = email;
    };
    
    service.getEmail = function(email) {
        return service.email;
    }
});
/* ViewAuthorService */


/* AppController */
app.controller('AppController', function($scope, ViewAuthorService) {
    $scope.tab = 'Home';
    
    
    $scope.homeTab = function() {
        $scope.tab = 'Home';
    };
    
    $scope.viewAuthor = function(email) {
        $scope.tab = 'ViewAuthor';
        ViewAuthorService.setEmail(email);
    };
});
/* AppController */


/* HomeTabController */
app.controller('HomeTabController', function($scope) {
    
});
/* HomeTabController */


/* ViewAuthorController */
app.controller('ViewAuthorController', function($scope, $http, $q, ViewAuthorService) {
    let email = ViewAuthorService.getEmail();
    
    $scope.user = null;
    
    $http('getUserInfoController', email)
    .then(
        function(response) {
        },
        function(error) {
            
        });
        
        
    $http('getRecipesByUserIdController', user.id)
    .then(
        function(response) {
        },
        function(error) {
        });
    
});
/* ViewAuthorController */



