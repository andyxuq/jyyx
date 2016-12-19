app.controller('pcModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.pcRes = {
			'categoryName':'',
			'parentId':0,
			'orderCode':0
	}
    if (0 == resId) {
    	$scope.modifyType = '新增';
    } else {
    	$scope.modifyType = '修改';    	
    	httpService.httpGet('/api/product/category/get/' + $scope.resId, function(result){
    		$scope.pcRes = result.data;
    	});
    }

	
    $scope.ok = function () {
    	var url = '/api/product/category/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/product/category/modify/' + $scope.resId;
    	}
    	delete $scope.pcRes.childList;
    	httpService.httpPost(url, $scope.pcRes, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('pcModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'pcModalCtrlIns',
        size: size,
        resolve: {
          resId : function() {
        	  return resId;
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
    	  $scope.loadData();
      }, function (data) {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  ; 