angular.module('app', []).controller('indexController', function ($scope, $window, $http) {
   const contextPath = 'http://localhost:8081/products';

   $scope.loadProducts = function () {
      $http.get(contextPath + '/show_all')
          .then(function (response) {
             $scope.ProductList = response.data;
          });
   };

   $scope.deleteProduct = function (productId) {
       $http.get(contextPath + '/delete/' + productId)
           .then(function (response) {
               $scope.loadProducts();
           });

   };

    $scope.loadProducts();


   // $scope.changeProductPosition = function (productId, delta) {
   //     $http({
   //         url: contextPath + '/change_position',
   //         method: 'GET',
   //         params: {
   //             id: productId,
   //             delta: delta
   //         }
   //     }).then(function (response) {
   //         $scope.loadProducts();
   //     });
   // };

});