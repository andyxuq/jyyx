app.controller('ProductCategory', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.search = {
			'categoryName':'',
			'parentId':'0'
	};
	
	//加载数据
	var loadData = function () {
		httpService.httpPost('/api/product/category/get', $scope.search, function(result){
			var data = result.data;
			$scope.productCategoryList = data;
		});
	}
	loadData();

	//查询产品分类
	$scope.searchProductCategory = function() {
		loadData();
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
    		for (var index in $scope.productCategoryList) {
    			if ($scope.selectIds.indexOf($scope.productCategoryList[index].id) == -1) {
    				$scope.selectIds.push($scope.productCategoryList[index].id);
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
    		for (var index in $scope.productCategoryList) {
    			if ($scope.selectIds.indexOf($scope.productCategoryList[index].id) != -1) {
    				var id = $scope.productCategoryList[index].id;
    				var orderCode = $scope.productCategoryList[index].orderCode;
    				
    				postData[id] = orderCode;
    			}
    		}
    		console.log(postData);
    		httpService.httpPost('/api/product/category/modifyOrder', postData, function(result){
    			toaster.pop('info', '', '排序号修改成功');
    			loadData();
    		});
    	}
    }
    
	$scope.deleteCategory = function() {
		console.log($scope.selectIds);
	}
	
	
}]);