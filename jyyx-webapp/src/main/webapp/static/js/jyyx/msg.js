app.controller('MsgCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.search = {
	  "id":null, 
	  "categoryId":"", 
	  "msgTitle":null
	}
	
	$scope.searchRes = function() {
		$scope.loadData();
	}
	
	$scope.msgCategorys = [];
	$scope.loadCategory = function() {
    	var searchParam = {
			  "id":null, 
			  "categoryName":null
		}
    	httpService.httpPost('/api/msg/category/get', searchParam ,function(result){
    		$scope.msgCategorys = result.data;
    	});
    }
    $scope.loadCategory();
	
	$scope.loadData = function() {
		var url = "/api/msg/get?pageRow=" + $scope.pageRow;
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
    		httpService.httpPost('/api/msg/modifyOrder', postData, function(result){
    			toaster.pop('info', '', '排序号修改成功');
    			$scope.loadData();
    		});
    	}
    }
	
	$scope.deleteRes = function(msg) {
		$scope.modal = {};
		$scope.modal.title = "操作确认";
		$scope.modal.message = "是否要删除资讯[" + msg.msgTitle + "]";
		$modal.open({
			templateUrl: 'messageModal.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			httpService.httpPost('/api/msg/delete/' + msg.id, null, function(result){
				toaster.pop('info', '', '删除案例' + msg.msgTitle + "成功");
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