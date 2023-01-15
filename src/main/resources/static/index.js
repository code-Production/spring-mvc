angular.module('app', []).controller('indexController', function ($scope, $http) {

   const contextPath = 'http://localhost:8081/app/api/v1';

   // $scope.newProduct = {title: null, price: null};
   $scope.pageNumber = 0;

   $scope.prevPage = function () {
       $scope.pageNumber--;
       $scope.filteredProducts();
   }

    $scope.nextPage = function () {
        $scope.pageNumber++;
        $scope.filteredProducts();
    }


   $scope.deleteProduct = function (productId) {
       $http.delete(contextPath + '/products/' + productId)
           .then(function (response) {
               $scope.filteredProducts();
           });
   };

   $scope.addProduct = function () {
       $http.post(contextPath + '/products', $scope.newProduct)
           .then(function (response) {
               $scope.filteredProducts();
           });
   };

   $scope.filteredProducts = function () {
       $http({
           url: contextPath + '/products',
           method: 'GET',
           params: {
               min_price: $scope.filter ? $scope.filter.min_price : null,
               max_price: $scope.filter ? $scope.filter.max_price : null,
               page_num: $scope.pageNumber
           }
       }).then(function (response) {
           $scope.firstPage = response.data.first;
           $scope.lastPage = response.data.last;
           $scope.ProductList = response.data.content;
           if ($scope.ProductList.toString() === '') {
               if ($scope.pageNumber > 0) {
                   $scope.prevPage();
               }
           }
       })
   };

    $scope.filteredProducts();


});