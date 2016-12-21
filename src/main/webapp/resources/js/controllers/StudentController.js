/**
 * Created by mijo on 2016-07-07.
 */
angular.module('myApp')
    .controller('StudentController', ['$scope', '$http', function ($scope, $http) {
        $http.get('spring/students').success(function (data) {
            $scope.students = data;
        });
    }]);