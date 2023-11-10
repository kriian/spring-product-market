angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/products'

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.productsList = response.data
            })
    }

    $scope.changeProductPrice = function (id, price) {
        $http({
            url: contextPath + '/change_price',
            method: 'GET',
            params: {
                id: id,
                price: price
            }
        }).then(function () {
            $scope.loadProducts()
        })
    }

    $scope.deleteProduct = function(id) {
        $http.delete(contextPath + "/" + id)
            .then(function () {
                $scope.loadProducts()
            })
    }

    $scope.loadProducts()
})
