app.controller('PhysicalMonitorCtrl', ['$scope', '$modal', '$log', '$http', '$state','httpService',
	function($scope, $modal, $log, $http, $state, httpService) {
	$scope.groupMasters = [];
	$scope.groupSlaves = [];
	$scope.groupMasterId;
	
	
	httpService.httpGet('/deviceMonitor/getSimpleHostInfoMap',function(result){
		var hostInfoMap = result.data;
		if(hostInfoMap["master"][0] != undefined|| hostInfoMap["master"][0] != null){
			$scope.groupMasterId = hostInfoMap["master"][0].hostGroupId;
		}
		
		for (var key in hostInfoMap) {
			if(key == "master"){
				for (var i = 0; i<hostInfoMap[key].length;i++) {
					$scope.groupMasters.push(hostInfoMap[key][i]);
				}
			}else{
				for (var i = 0; i<hostInfoMap[key].length;i++) {
					$scope.groupSlaves.push(hostInfoMap[key][i]);
				}
			}
		}
		httpService.httpGet('/redisCluster/getHostInfoExtend',function(result){
			$scope.hostInfos = result.data;
			$scope.groupMasters = [];
			$scope.groupSlaves = [];
			for (var i=0; i< $scope.hostInfos.length; i++) {
				hostInfo = $scope.hostInfos[i];
				if($scope.groupMasterId == hostInfo.hostGroupId){
					$scope.groupMasters.push(hostInfo);
					$scope.groupMasterId = hostInfo.hostGroupId;
				}else{
					$scope.groupSlaves.push(hostInfo);
				}
			}
		})
	});
	
	$scope.open = function () {
      var modalInstance = $modal.open({
        templateUrl: 'myModalContent.html',
        controller: 'ModalInstanceCtrl',
        resolve: {
          items: function () {
            return 0;
          }
        }
      });
      modalInstance.result.then(function () {
       
      }, function () {
      });
    };
}]);

/**
 * 填写增加物理机表单
 * */
app.controller('addPhysicalCtrl', ['$scope', '$modal', '$log', '$http', 'toaster', '$state', 'httpService',
	function($scope, $modal, $log, $http, toaster, $state, httpService) {
   
    $scope.hostGroups;
    
    $scope.hostInfo;
    /**
     * 获取所有的HostGroup
     * */
    httpService.httpGet('/physicalServer/getHostGroups', function(result){
    	$scope.hostGroups = result.data;
    });
   
    $scope.addPhysicalServer = function(){
    	httpService.httpPost('/physicalServer/addPhysicalServer', $scope.hostInfo, function(result){
    		toaster.pop("success","","保存成功");
			$state.go('app.physicalMinitor',null,{
				reload:true
			});
    	});
    }
 
}]);