app.controller('ProductCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.search = {
	  "id":null, 
	  "productName":null, 
	  "productCategorys":[]
	}
	
	$scope.searchRes = function() {
		$scope.loadData();
	}
	
	$scope.loadData = function() {
		var url = "/api/product/get?pageRow=" + $scope.pageRow;
		if ($scope.pageData != undefined) {
			url = url + "&page=" + $scope.pageData.page;
		} else {
			url = url + "&page=1";
		}
		httpService.httpPost(url, $scope.search, function(result){
			var data = result.data;
			$scope.pageData = data;
			
			$scope.initPage();
		});
	}
	
	$scope.loadData();
	$scope.showBigPic = false;
	//big pic
	$scope.showBigPicFun = function($event) {
		var img = $event.target;
		$scope.bigPicSrc = img.currentSrc;
		
		$scope.showBigPic = true;
	}
	
	$scope.hiddenBigPic = function() {
		$scope.showBigPic = false;
		$scope.bigPicSrc = "#";
	}
	
	$scope.deleteRes = function(product) {
		$scope.modal = {};
		$scope.modal.title = "操作确认";
		$scope.modal.message = "是否要删除产品[" + product.productName + "]";
		$modal.open({
			templateUrl: 'messageModal.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			httpService.httpPost('/api/product/delete/' + product.id, null, function(result){
				toaster.pop('info', '', '删除产品' + product.productName + "成功");
				$scope.loadData();
			});
		}, function() {
			
		});
	}
	
	//paging info
	$scope.initPage = function() {
		$scope.currentPage = $scope.pageData.page;
		$scope.refreshPageStatus(); 
	}
	
	$scope.setPage = function(pageNum) {
		var setPage = pageNum;
		if (pageNum < 1) {
			setPage = 1;
		}
		if (pageNum > $scope.pageData.total) {
			setPage = $scope.pageData.total;
		}		
		$scope.pageData.page = setPage;
		$scope.currentPage = $scope.pageData.page
		$scope.refreshPageStatus();
	}
	
	$scope.refreshPageStatus = function() {
		$scope.pageNumArray = pageService.pageNumArray($scope.currentPage, $scope.pageData.total);
		$scope.needNextEllipsis = pageService.needEllipsis($scope.currentPage, $scope.pageData.total);
	}
	
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