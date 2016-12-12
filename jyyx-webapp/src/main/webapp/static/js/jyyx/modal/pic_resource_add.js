  app.controller('picAddCtrlIns', ['$scope', '$modalInstance', 'items', function($scope, $modalInstance, items) {
    $scope.items = items;
    $scope.selected = {
      item: $scope.items[0]
    };

	$scope.modifyType = '新增';
	
	$scope.fileSize = 1;
	$scope.picRes = {
			'picCode':'bbb',
			'referId':0,
			'picArray':[{
				'codeName':'orderCode_' + $scope.fileSize,
				'codeValue':0,
				'pathName':'file_' + $scope.fileSize,
				'pathValue':''
			},{
				'codeName':'orderCode_2',
				'codeValue':0,
				'pathName':'file_2',
				'pathValue':''
			}]
	};
	
	$scope.picCodeTypes = [{
		'code':'aaaa',
		'name':'高大上'
	},{
		'code':'bbb',
		'name':'矮穷挫'
	}];
	
    
    $scope.ok = function () {
      $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('picAddCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
	
    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'picAddCtrlIns',
        size: size,
        resolve: {
          items: function () {
            return $scope.items;
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