app.controller('msgModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.res = {
		  "msgTitle":null, 
		  "msgContent":null, 
		  "orderCode":0, 
		  "categoryId":0
	}
    
    $scope.msgCategorys = [];    
    $scope.loadCategory = function() {
    	var searchParam = {
			  "id":null, 
			  "categoryName":null
		}
    	httpService.httpPost('/api/msg/category/get', searchParam ,function(result){
    		$scope.msgCategorys = result.data;
    		$scope.res.categoryId = $scope.msgCategorys[0].id;
    		$scope.initData();
    	});
    }
    $scope.loadCategory();
    
    $scope.initData = function() {
	    if (0 == resId) {
	    	$scope.modifyType = '新增';
	    } else {
	    	$scope.modifyType = '修改';    	
	    	httpService.httpGet('/api/msg/get/' + $scope.resId, function(result){
	    		$scope.res = result.data;
	    	});
	    }
    }

    $scope.ok = function () {
    	var url = '/api/msg/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/msg/modify/' + $scope.resId;
    	}
    	
    	httpService.httpPost(url, $scope.res, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

  }])
  ; 
  app.controller('msgModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'msgModalCtrlIns',
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