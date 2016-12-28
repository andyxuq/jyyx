app.controller('PicResourceCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService','FileUploader',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService, FileUploader) {
	
	$scope.search = {
		'picCode':'',
		'referId':null
	};
	//应用
	$scope.picCodeTypes = [];
	httpService.httpGet('/api/pic/get/picTypes', function(result){
		var typeData = result.data;
		$scope.picCodeTypes = typeData;
	});
	
	$scope.loadData = function() {
		var url = "/api/pic/get?pageRow=" + $scope.pageRow;
		if ($scope.pageData != undefined) {
			url = url + "&page=" + $scope.pageData.page;
		} else {
			url = url + "&page=1";
		}
		
		var requestParam = {};
		angular.copy($scope.search, requestParam);
		
		if (requestParam.picCode === "") {
			requestParam.picCode = null;
		}
		httpService.httpPost(url, requestParam, function(result){
			var data = result.data;
			$scope.pageData = data;
			
			$scope.initPage();
		});
	}
	$scope.loadData();
	
	$scope.searchRes = function() {
		$scope.loadData();
	}
	
	//batch checked
	$scope.selectIds = [];
	$scope.isChecked = function(id){  
        return $scope.selectIds.indexOf(id) >= 0 ;  
    };  
      
    $scope.updateSelection = function(id){  
    	if ($scope.selectIds.indexOf(id) != -1) {
    		var idx = $scope.selectIds.indexOf(id);
    		$scope.selectIds.splice(idx, 1);
    	} else {
    		$scope.selectIds.push(id) ;
    	}
    } ; 
	
    $scope.batchCheck = function($event) {
    	var checkbox = $event.target;
    	var checked = checkbox.checked;
    	
    	if (checked) {
    		for (var index in $scope.pageData.pageData) {
    			if ($scope.selectIds.indexOf($scope.pageData.pageData[index].id) == -1) {
    				$scope.selectIds.push($scope.pageData.pageData[index].id);
    			}
    		}
    	} else {
    		$scope.selectIds = [];
    	}
    		
    }
    
    //big pic
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
    
    //批量修改排序号
    $scope.updateOrderBatch = function() {
    	if ($scope.selectIds.length == 0) {
    		toaster.pop('error', '', '未选择任何资源');
    	} else {
    		var postData = {};
    		for (var index in $scope.pageData.pageData) {
    			if ($scope.selectIds.indexOf($scope.pageData.pageData[index].id) != -1) {
    				var id = $scope.pageData.pageData[index].id;
    				var orderCode = $scope.pageData.pageData[index].orderCode;
    				
    				postData[id] = orderCode;
    			}
    		}
    		console.log(postData);
    		httpService.httpPost('/api/pic/modifyOrder', postData, function(result){
    			toaster.pop('info', '', '排序号修改成功');
    			$scope.loadData();
    		});
    	}
    }
	
    $scope.deleteRes = function(pic) {
		$scope.modal = {};
		$scope.modal.title = "操作确认";
		$scope.modal.message = "是否要删除图片[" + pic.id + "]";
		$modal.open({
			templateUrl: 'messageModal.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			httpService.httpPost('/api/pic/delete/' + pic.id, null, function(result){
				toaster.pop('info', '', '删除图片' + pic.id + "成功");
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
		$scope.loadData();
		
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