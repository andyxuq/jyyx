app.controller('CacheDetailCtrl', ['$scope', '$modal', '$log', '$http', '$stateParams',
					'toaster','$state', 'httpService', 'functionService',
	function($scope, $modal, $log, $http, $stateParams, toaster, $state, httpService, functionService) {
		$scope.serviceDef = {};
		$scope.serviceDef.id = $stateParams.clustrId;
		$scope.master = {};

		//应用
		$scope.apps = [];
		//模块
		$scope.modules = [];
		$scope.selectedAppId;
		$scope.selectedModule;

		getCluster($scope.serviceDef);

		$scope.update = function() {
			$scope.modal = {};
			$scope.modal.title = "调整集群";
			$scope.modal.message = "确认调整该redis集群？";
			$modal.open({
				templateUrl: 'myModalContent.html',
				controller: 'ModalInstanceCtrl',
				resolve: {
					items: function() {
						return $scope.modal;
					}
				}
			}).result.then(function() {
				getClusterStatus($scope.serviceDef.define.id);
			   }, function() {
				
			   });
				
		};
		
		//判断集群是否已经处于扩容或缩容状态
		function getClusterStatus(clusterId){
			httpService.httpGet('/redisCluster/getClustersStatus/' + clusterId, function(result){
				if(result.data.status== "CHANGEING"){
					$state.go('app.updateMemory',{clusterId:clusterId});
				 	return false;
				}else{
					//分配redis集群
					$scope.serviceDef.ownerAppId = $scope.selectedAppId;
					$scope.serviceDef.ownerModuleId = $scope.selectedModule;
					var postDefine = {};
					angular.copy($scope.serviceDef.define, postDefine);
					postDefine.dataCapacity = postDefine.dataCapacity*1024*1024;
					//修改集群
					httpService.httpPost('/redisCluster/update', postDefine, function(){
						$state.go('app.updateMemory',{clusterId:$scope.serviceDef.define.id});
					});
				}
			});
		}
		
		//删除集群
		$scope.deleteCluster = function() {
			$scope.modal = {};
			$scope.modal.title = "删除集群";
			$scope.modal.message = "确认删除该redis集群？删除后数据不可恢复！";
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
				$scope.serviceDef.ownerAppId = $scope.selectedAppId;
				$scope.serviceDef.ownerModuleId = $scope.selectedModule;
				var postDefine = {};
				angular.copy($scope.serviceDef.define, postDefine);
				postDefine.dataCapacity = postDefine.dataCapacity*1024*1024;
				//修改集群
				httpService.httpPost('/redisCluster/delete', postDefine, function(){
					$state.go('app.cacheMonitor');
				});
		   }, function() {
			
		   });
				
		};

		//加载所有的应用
		httpService.httpGet('/app/getAppList',function(result){
			$scope.apps = result.data;
		});

		//选中应用事件
		$scope.selectApp = function() {
			httpService.httpGet('/app/getAppModule/'+ $scope.selectedAppId,function(result){
				$scope.modules = result.data;
				$scope.selectedModule = $scope.modules[0];
			});
		}

		//获取指定redis集群信息
		function getCluster(serviceDefine) {
			httpService.httpPost('/redisCluster/get/true', serviceDefine, function(result){
				if(result.data.define != null){
					$scope.serviceDef = result.data;
					//$scope.partitions = result.data.partitions;
					$scope.rowPartitions = functionService.arrayToRow(result.data.partitions, 3);
					$scope.serviceDef.define.dataCapacity = $scope.serviceDef.define.dataCapacity/1024/1024;
					$scope.preCapacity = $scope.serviceDef.define.dataCapacity/$scope.serviceDef.define.numberOfPartitions;
					$scope.selectedAppId = $scope.serviceDef.define.ownerAppId;
					getModules($scope.selectedAppId);
				}
			});
			
			//获取备集群节点
			httpService.httpPost('/redisCluster/get/false', serviceDefine, function(result){
				if(result.data.define != null){
					$scope.serviceDef = result.data;
					//$scope.partitions = result.data.partitions;
					$scope.slaveRowPartitions = functionService.arrayToRow(result.data.partitions, 3);
					$scope.serviceDef.define.dataCapacity = $scope.serviceDef.define.dataCapacity/1024/1024;
				}
			});
		}
		
		$scope.slaveOpen = false;
		$scope.slaveTip = "展开"
		$scope.openSlave = function(){
			if($scope.slaveOpen){
				$scope.slaveOpen = false;
				$scope.slaveTip = "展开";
			}else{
				$scope.slaveOpen = true;
				$scope.slaveTip = "收起";
			}
		}
		
		//自动更改内存大小
		$scope.autoUpdateMemory = function(){
			$scope.serviceDef.define.dataCapacity = $scope.preCapacity*$scope.serviceDef.define.numberOfPartitions;
		}
		
		$scope.getSumSlots = function(ownedSlots){
			var sum = 0;
			for(var i=0; i<ownedSlots.length; i++){
				sum += ownedSlots[i].sum;
			}
			return sum;
		}
		
		//根据应用id获取模块
		function getModules(appId){
			httpService.httpGet('/app/getAppModule/'+ $scope.selectedAppId, function(result){
				$scope.modules = result.data;
				$scope.selectedModule = $scope.serviceDef.define.ownerModuleId;
			});
			
		}
	}
]);