angular.module('app', []).controller('indexController', function ($scope, $http) {
   const contextPath = 'http://localhost:8081/app';

   $scope.pageNumber = 0;

   $scope.loadProducts = function () {
      $http.get(contextPath + '/products')
          .then(function (response) {
             $scope.ProductList = response.data;
          });
   };

   $scope.lastPage = function () {
       $scope.pageNumber--;
       if ($scope.pageNumber < 0) {
           $scope.pageNumber = 0;
       }
       $scope.loadProductsOnPage($scope.pageNumber);
   }

    $scope.nextPage = function () {
        $scope.pageNumber++;
        $scope.loadProductsOnPage($scope.pageNumber);
    }

   $scope.loadProductsOnPage = function (num) {
       $http({
           url: contextPath + '/products/page',
           method: 'GET',
           params: {
               number: num
           }
       }).then(function (response) {
           $scope.ProductList = response.data;
           if ($scope.ProductList.toString() === '') {
               $scope.lastPage();
           }
       });
   };

   $scope.deleteProduct = function (productId) {
       $http.get(contextPath + '/products/delete/' + productId)
           .then(function (response) {
               $scope.loadProducts();
           });
   };

    // $scope.loadProducts();
    $scope.loadProductsOnPage($scope.pageNumber);


});