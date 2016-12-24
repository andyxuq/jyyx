app.controller('productModalCtrlIns', ['$scope', '$modalInstance', 'resId', 'toaster', 'httpService', 
    function($scope, $modalInstance, resId, toaster, httpService) {
    $scope.resId = resId;
    
    $scope.res = {
			  "productName":null,
			  "productDesc":null,
			  "productLink":null, 
			  "productCategorys":[
			      {
			    	  "categoryId":0,
			    	  "orderCode":0
			      }
			  ]
		}
    
    $scope.procategorys = [];    
    $scope.loadCategory = function() {
    	var searchParam = {
			  "id":null, 
			  "categoryName":null,
			  "parentId":null
		}
    	httpService.httpPost('/api/product/category/get', searchParam ,function(result){
    		var dataList = result.data;
    		for (var i = 0; i < dataList.length; i++) {
    			var parentName = "[" + dataList[i].categoryName + "]";
    			var childList = dataList[i].childList;
    			for (var j=0; j < childList.length; j++) {
    				var child = childList[j];
    				child.categoryName = parentName + "-" + "[" + child.categoryName + "]";
    				$scope.procategorys.push(child);
    			}
    		}
    		$scope.res.productCategorys[0].categoryId = $scope.procategorys[0].id;
    		$scope.initData();
    	});
    }
    $scope.loadCategory();
    
    $scope.initData = function() {
	    if (0 == resId) {
	    	$scope.modifyType = '新增';
	    } else {
	    	$scope.modifyType = '修改';    	
	    	httpService.httpGet('/api/product/get/' + $scope.resId, function(result){
	    		var productData = result.data;
	    		var categorys = [];
	    		for (var i=0; i < productData.productCategorys.length; i++) {
	    			var childList = productData.productCategorys[i].childList;
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
	    		productData.productCategorys = categorys;
	    		$scope.res = productData;
	    	});
	    }
    }

    $scope.addCategoryLine = function() {
    	if ($scope.res.productCategorys.length === 8) {
    		toaster.pop('warning','','最多添加8个输入框');
    		return;
    	}
    	$scope.res.productCategorys.push({
	    	  "categoryId":$scope.procategorys[0].id,
	    	  "orderCode":0
	      });
    }
    
    $scope.removeCategoryLine = function(productCategory) {
    	var index = -1;
    	if ($scope.res.productCategorys.length == 1) {
    		toaster.pop('warning', '', '请至少保留一个类型选择框')
    		return;
    	}
    	var index = $scope.res.productCategorys.indexOf(productCategory);
    	if (index != -1) {
    		$scope.res.productCategorys.splice(index, 1);
    	}
    }
    
    $scope.ok = function () {
    	var url = '/api/product/add';    	
    	if (0 != $scope.resId) {
    		url = '/api/product/modify/' + $scope.resId;
    	}
    	
    	httpService.httpPost(url, $scope.res, function(result){
    		toaster.pop('info', '', $scope.modifyType + "资源成功");
    		$modalInstance.close();
    	});
    };

  }])
  ; 
  app.controller('productModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log, $stateParams) {
    $scope.open = function (size, resId) {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'productModalCtrlIns',
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