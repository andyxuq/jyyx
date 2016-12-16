app.controller('picAddCtrlIns', ['$scope', '$modalInstance', 'items', 'picResId', 'toaster', 'httpService', 'uploader',
    function($scope, $modalInstance, items, picResId, toaster, httpService, uploader) {
    $scope.items = items;
    $scope.uploader = uploader;
    $scope.selected = {
      item: $scope.items[0]
    };

	$scope.modifyType = '新增';

	$scope.picResId = picResId;
	console.log(picResId);
	$scope.fileSize = 1;
	$scope.picRes = {
			'picCode':'PRODUCT_HEADER',
			'referId':0,
			'picArray':[{
				'codeName':'orderCode_' + $scope.fileSize,
				'codeValue':$scope.fileSize,
				'pathName':'file_' + $scope.fileSize,
				'pathValue':'',
				'nameOrder':$scope.fileSize
			}]
	};
	
	$scope.picCodeTypes = [];
	httpService.httpGet('/api/pic/get/picTypes', function(result){
		var typeData = result.data;
		$scope.picCodeTypes = typeData;
	});
	
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
			'pathValue':'',
			'nameOrder':$scope.fileSize
    	});
    }
    
    $scope.removePicLine = function(nameOrder) {
    	var index = -1;
    	if ($scope.picRes.picArray.length == 1) {
    		toaster.pop('warning', '', '请至少保留一个图片选择框')
    		return;
    	}
    	for (var i = 0; i < $scope.picRes.picArray.length; i++) {
    		if ($scope.picRes.picArray[i].nameOrder === nameOrder) {
    			index = i;
    		}
    	}
    	if (index != -1) {
    		$scope.picRes.picArray.splice(index, 1);
    	}
    }
	
    $scope.ok = function () {
    	$scope.uploader.formData = {
    		
    	};	
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('picAddCtrl', ['$scope', '$modal', '$log', 'FileUploader', function($scope, $modal, $log, $stateParams, FileUploader) {
    $scope.items = ['item1', 'item2', 'item3'];
    
    var uploader = $scope.uploader = new FileUploader({
        url: '/api/pic/add'
    });
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
          },
          uploader: function () {
        	  return uploader
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
        $scope.selected = selectedItem;
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  ; 