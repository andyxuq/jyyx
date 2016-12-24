app.controller('PicResourceCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.search = {};
	$scope.search.picCode = "PRODUCT_HEADER";
	$scope.search.referId = '';
	//应用
	$scope.picCodeTypes = [];
	httpService.httpGet('/api/pic/get/picTypes', function(result){
		var typeData = result.data;
		$scope.picCodeTypes = typeData;
	});
	
	$scope.searchPic = function() {
		console.log($scope.search);
	}
	
	$scope.pageData = {
			'total':21,
			'page':1,
			'pageRow':50
	}
	
	//paging info
	$scope.setPage = function(pageNum) {
		var setPage = pageNum;
		if (pageNum < 1) {
			setPage = 1;
		}
		if (pageNum > $scope.pageData.total) {
			setPage = $scope.pageData.total;
		}		
		$scope.pageData.page = setPage;
		$scope.loadData();
		
		$scope.currentPage = $scope.pageData.page
		$scope.refreshPageStatus();
	}
	
	$scope.refreshPageStatus = function() {
		$scope.pageNumArray = pageService.pageNumArray($scope.currentPage, $scope.pageData.total);
		$scope.needNextEllipsis = pageService.needEllipsis($scope.currentPage, $scope.pageData.total);
	}
	
	$scope.currentPage = $scope.pageData.page;
	$scope.refreshPageStatus(); 
	$scope.showNextPage = function() {
		$scope.currentPage = $scope.currentPage + pageService.getPageNum();
		$scope.refreshPageStatus();	
	}
	
	$scope.pageActive = function(currentPage) {
		if ($scope.pageData.page == currentPage) {
			return 'active';
		} else {
			return '';
		}
	}
}]);