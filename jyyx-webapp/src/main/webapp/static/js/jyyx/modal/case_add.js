app.controller('caseModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.res = {
			  "caseName":null,
			  "caseDesc":null,
			  "casePrice":null,
			  "caseTips":null,
			  "caseCategorys":[
			      {
			    	  "categoryId":0,
			    	  "orderCode":0
			      }
			  ]
		}
    
    $scope.casecategorys = [];    
    $scope.loadCategory = function() {
    	var searchParam = {
			  "id":null, 
			  "categoryName":null,
			  "parentId":null
		}
    	httpService.httpPost('/api/case/category/get', searchParam ,function(result){
    		var dataList = result.data;
    		for (var i = 0; i < dataList.length; i++) {
    			var parentName = "[" + dataList[i].categoryName + "]";
    			var childList = dataList[i].childList;
    			for (var j=0; j < childList.length; j++) {
    				var child = childList[j];
    				child.categoryName = parentName + "-" + "[" + child.categoryName + "]";
    				$scope.casecategorys.push(child);
    			}
    		}
    		$scope.res.caseCategorys[0].categoryId = $scope.casecategorys[0].id;
    		$scope.initData();
    	});
    }
    $scope.loadCategory();
    
    $scope.initData = function() {
	    if (0 == resId) {
	    	$scope.modifyType = '新增';
	    } else {
	    	$scope.modifyType = '修改';    	
	    	httpService.httpGet('/api/case/get/' + $scope.resId, function(result){
	    		var productData = result.data;
	    		var categorys = [];
	    		for (var i=0; i < productData.caseCategorys.length; i++) {
	    			var childList = productData.caseCategorys[i].childList;
	    			for (var j=0; j < childList.length; j++) {
	    				categorys.push({
	    					"categoryId":childList[j].id,
	  			    	  	"orderCode":0
	    				});
	    			}
	    		}
	    		delete productData.createTime;
	    		delete productData.updatedTime;
	    		delete productData.pics;
	    		productData.caseCategorys = categorys;
	    		$scope.res = productData;
	    	});
	    }
    }

    $scope.addCategoryLine = function() {
    	if ($scope.res.caseCategorys.length === 8) {
    		toaster.pop('warning','','最多添加8个输入框');
    		return;
    	}
    	$scope.res.caseCategorys.push({
	    	  "categoryId":$scope.casecategorys[0].id,
	    	  "orderCode":0
	      });
    }
    
    $scope.removeCategoryLine = function(caseCategory) {
    	var index = -1;
    	if ($scope.res.caseCategorys.length == 1) {
    		toaster.pop('warning', '', '请至少保留一个类型选择框')
    		return;
    	}
    	var index = $scope.res.caseCategorys.indexOf(caseCategory);
    	if (index != -1) {
    		$scope.res.caseCategorys.splice(index, 1);
    	}
    }
    
    $scope.ok = function () {
    	var url = '/api/case/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/case/modify/' + $scope.resId;
    	}
    	
    	httpService.httpPost(url, $scope.res, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

  }])
  ; 
  app.controller('caseModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'caseModalCtrlIns',
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