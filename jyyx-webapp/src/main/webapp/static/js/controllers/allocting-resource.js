app.controller('AlloctingResourceCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService) {

	//应用
	$scope.apps = [];
	//模块
	$scope.modules = [];
	$scope.selectedApp;
	$scope.selectedModule;
	$scope.serviceDef = {};
	
 	$scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };
    
    function popTip(message){
    	$scope.modal = {};
		$scope.modal.title = "分配成功";
		$scope.modal.message = "分配缓存集群成功！"+message;
		$modal.open({
			templateUrl: 'myModalContent.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			$state.go("app.cacheMonitor");
		}, function() {
			$state.go("app.cacheMonitor");
		});
    }
    
	$scope.postData = function() {
		$scope.modal = {};
		$scope.modal.title = "分配缓存集群";
		$scope.modal.message = "确认分配？";
		$modal.open({
			templateUrl: 'myModalContent.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			//分配redis集群
			$scope.serviceDef.ownerAppId = $scope.selectedApp.id;
			
			$scope.serviceDef.ownerModuleId = $scope.selectedModule.id;
			var postServiceDef = {};
			
			angular.copy($scope.serviceDef, postServiceDef);
			
			//单位转化为Kb
			postServiceDef.dataCapacity = postServiceDef.dataCapacity*1024*1024;
			postServiceDef.timeWait = postServiceDef.timeWait * 60;
			$scope.showProcess = true;
			$http.post('/redisCluster/add/', postServiceDef)
			.success(function(result, status, headers, config) {
				$scope.showProcess = false;
				if (result.code == 0) {
					var message = "集群的appKey为:";
					for(var i=0;i<result.data.length;i++){
						message += result.data[i];
					}
					popTip(message);
				} else {
					$scope.message = result.message;
					toaster.pop("error", "", result.message);
				}
			})
			.error(function(result, status, headers, config) {
				$scope.showProcess = false;
				toaster.pop("error", "", result.message!=undefined?result.message:"http请求错误");
			});
			
		}, function() {});
	};

	//加载所有的应用
	httpService.httpGet('/app/getAppList', function(result){
		$scope.apps = result.data;
	});
		
	$scope.redisClusterNum = 0;
	
	$scope.perRowNum = 4;//界面每行只展示4台机器
	//加载所有物理机信息
	httpService.httpGet('/deviceMonitor/getHostInfos', function(result){
		$scope.hostInfoRows = functionService.arrayToRow(result.data, $scope.perRowNum);
		getHostInfoExtend();
	});
	
	//加载所有物理机详细信息
	function getHostInfoExtend(){
		httpService.httpGet('/redisCluster/getHostInfoExtend', function(result){
			for(var i=0;i<result.data.length;i++){
				if(result.data[i].hostState.ok){
					$scope.redisClusterNum++;
				}
			}
			$scope.hostInfoRows = functionService.arrayToRow(result.data, $scope.perRowNum);
		});
	
	}
	
	//选中应用事件
	$scope.selectApp = function() {
		httpService.httpGet('/app/getAppModule/'+$scope.selectedApp.id, function(result){
			$scope.modules = result.data;
			$scope.selectedModule = $scope.modules[0];
		});
		
	}
	
	//根据分片更改容量
	$scope.autoUpdateMemory = function(){
		$scope.serviceDef.dataCapacity = $scope.serviceDef.numberOfPartitions*10
	}

}]);