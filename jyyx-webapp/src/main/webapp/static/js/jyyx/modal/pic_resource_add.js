app.controller('picAddCtrlIns', ['$scope', '$modalInstance', 'items', 'picResId', 'toaster', 'httpService', 'FileUploader',
    function($scope, $modalInstance, items, picResId, toaster, httpService, FileUploader) {
    $scope.items = items;
    $scope.uploader = new FileUploader({
        url: '/api/pic/add'
    });
    $scope.uploader.onCompleteAll = function() {
		toaster.pop('info', '', '上传完成');
    	$modalInstance.close();
    };
    $scope.uploader.onAfterAddingFile = function(item) {
    	var queueLength = $scope.uploader.queue.length;
    	item.alias = "file_" + queueLength;
    } 
    
    $scope.selected = {
      item: $scope.items[0]
    };
    $scope.picResId = picResId;

    $scope.fileSize = 1;
	$scope.picRes = {
			'picCode':'',
			'referId':0,
			'picPath':''
	};
	
	$scope.picCodeTypes = [];
	httpService.httpGet('/api/pic/get/picTypes', function(result){
		var typeData = result.data;
		$scope.picCodeTypes = typeData;
		
		$scope.picRes.picCode = $scope.picCodeTypes[0].code;
		$scope.initData();
	});
	
	$scope.initData = function() {
	    if (0 == picResId) {
	    	$scope.modifyType = '新增';
	    } else {
	    	$scope.modifyType = '修改';    	
	    	httpService.httpGet('/api/pic/get/' + $scope.picResId, function(result){
	    		$scope.picRes = result.data;
	    	});
	    }
    }
	
    $scope.addPicLine = function() {
    	if ($scope.picRes.picArray.length === 8) {
    		toaster.pop('warning','','最多添加8个输入框');
    		return;
    	}
    	$scope.fileSize += 1;
    	$scope.picRes.picArray.push({
    		'codeName':'orderCode_' + $scope.fileSize,
			'codeValue':$scope.fileSize,
			'pathName':'file_' + $scope.fileSize,
			'pathValue':''
    	});
    }
    
    $scope.removePicLine = function(pic) {
    	var index = -1;
    	if ($scope.picRes.picArray.length == 1) {
    		toaster.pop('warning', '', '请至少保留一个图片选择框')
    		return;
    	}
    	for (var i = 0; i < $scope.picRes.picArray.length; i++) {
    		if ($scope.picRes.picArray.indexOf(pic) != -1) {
    			index = i;
    		}
    	}
    	if (index != -1) {
    		$scope.picRes.picArray.splice(index, 1);
    	}
    }
	
    $scope.ok = function () {
    	if ($scope.picResId == 0) {
	    	$scope.uploader.headers = {
	    		'Content-Type':'application/json'
	    	}
	    	for (var i = 0; i < $scope.uploader.queue.length; i++) {
	    		var queueItem = $scope.uploader.queue[i];
	    		queueItem.formData = [{
	        		'picCode':$scope.picRes.picCode,
	        		'referId':$scope.picRes.referId
	            }];	
	    	}
	        
	    	$scope.uploader.uploadAll();
    	} else {
    		httpService.httpPost('/api/pic/modify/' + $scope.picResId, $scope.picRes ,function(result){
    			toaster.pop('info', '', '修改完成');
    	    	$modalInstance.close();
        	});
    	}
    };
    

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('picAddCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.items = ['item1', 'item2', 'item3'];
    
    $scope.open = function (size, picResId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'picAddCtrlIns',
        size: size,
        resolve: {
          items: function () {
            return $scope.items;
          },
          picResId : function() {
        	  return picResId;
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
    	  $scope.loadData();
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  ; 