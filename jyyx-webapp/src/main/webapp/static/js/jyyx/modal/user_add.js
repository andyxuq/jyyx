app.controller('userModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.res = {
			  "userName":null,
			  "loginName":null,
			  "loginPwd":null, 
			  "userPhone":null,
			  "userEmail":null
		}
    
    $scope.initData = function() {
	    if (0 == resId) {
	    	$scope.modifyType = '新增';
	    } else {
	    	$scope.modifyType = '修改';    	
	    	httpService.httpGet('/api/user/get/' + $scope.resId, function(result){
	    		$scope.res = result.data;
	    	});
	    }
    }
    $scope.initData();
   
    $scope.ok = function () {
    	var url = '/api/user/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/user/modify/' + $scope.resId;
    	}
    	
    	httpService.httpPost(url, $scope.res, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

  }])
  ; 
  app.controller('userModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'userModalCtrlIns',
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