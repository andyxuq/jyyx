app.controller('CaseCategory', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.search = {
			'categoryName':'',
			'parentId':0
	};
	
	//加载数据
	$scope.loadData = function () {
		httpService.httpPost('/api/case/category/get', $scope.search, function(result){
			var data = result.data;
			$scope.caseCategoryList = data;
		});
	}
	$scope.loadData();

	//查询产品分类
	$scope.searchCaseCategory = function() {
		$scope.selectIds = [];
		$scope.loadData();
	}
	
	//删除产品分类
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
    		for (var index in $scope.caseCategoryList) {
    			if ($scope.selectIds.indexOf($scope.caseCategoryList[index].id) == -1) {
    				$scope.selectIds.push($scope.caseCategoryList[index].id);
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
    		for (var index in $scope.caseCategoryList) {
    			if ($scope.selectIds.indexOf($scope.caseCategoryList[index].id) != -1) {
    				var id = $scope.caseCategoryList[index].id;
    				var orderCode = $scope.caseCategoryList[index].orderCode;
    				
    				postData[id] = orderCode;
    			}
    		}
    		console.log(postData);
    		httpService.httpPost('/api/case/category/modifyOrder', postData, function(result){
    			toaster.pop('info', '', '排序号修改成功');
    			$scope.loadData();
    		});
    	}
    }
    
	$scope.deleteCategory = function(resourceId) {
		$scope.modal = {};
		$scope.modal.title = "操作确认";
		$scope.modal.message = "是否要删除分类";
		$modal.open({
			templateUrl: 'messageModal.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			httpService.httpPost('/api/case/category/delete/' + resourceId, null, function(result){
				toaster.pop('info', '', '删除分类' + resourceId + "成功");
				$scope.loadData();
			});
		}, function() {
			
		});
	}
	
	
}]);