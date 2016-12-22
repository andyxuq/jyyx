app.controller('ccModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.ccRes = {
			'categoryName':'',
			'parentId':0,
			'orderCode':0
	}
    if (0 == resId) {
    	$scope.modifyType = '新增';
    } else {
    	$scope.modifyType = '修改';    	
    	httpService.httpGet('/api/case/category/get/' + $scope.resId, function(result){
    		$scope.ccRes = result.data;
    	});
    }

	
    $scope.ok = function () {
    	var url = '/api/case/category/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/case/category/modify/' + $scope.resId;
    	}
    	delete $scope.ccRes.childList;
    	httpService.httpPost(url, $scope.ccRes, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('ccModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'jyyxModalContent.html',
        controller: 'ccModalCtrlIns',
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