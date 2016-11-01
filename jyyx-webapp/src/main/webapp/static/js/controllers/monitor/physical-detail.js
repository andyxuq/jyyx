app.controller('PhysicalDetailCtrl', ['$scope', '$modal', '$log', '$stateParams', '$http',
				'$interval', 'toaster','$state','functionService', 'httpService',
	function($scope, $modal, $log, $stateParams, $http, 
		$interval, toaster, $state, functionService, httpService) {
		$scope.test = "asdasd";
		$scope.charData = {};

		$scope.ip = $stateParams.ip;
		
		$scope.hostId = $stateParams.hostId;
		$scope.deletePhysical = function() {
			$scope.modal = {};
			$scope.modal.title = "删除物理机";
			$scope.modal.message = "确认删除物理机？删除物理机时会同时删除本机器下的所有redis集群节点！";
			$modal.open({
				templateUrl: 'myModalContent.html',
				controller: 'ModalInstanceCtrl',
				resolve: {
					items: function() {
						return $scope.modal;
					}
				}
			}).result.then(function() {
				httpService.httpGet('/physicalServer/deleteHost/'+ $scope.ip+".",function(result){
					$state.go('app.physicalMinitor');
				});
			}, function() {

			});
		};
		getCluster($scope.hostId);
		//获取指定redis集群信息
		function getCluster(hostId) {
			httpService.httpGet('/redisCluster/getClusterServiceListByHostId/'+hostId, function(result){
				$scope.clusters = result.data;
			});
		}
		
		httpService.httpGet('/deviceMonitor/getHostInfoExtendById/'+$scope.hostId, function(result){
			$scope.device = result.data;
			$scope.masterNum = 0;
			$scope.slaveNum = 0;
			for (var i=0; i<$scope.device.nodeList.length; i++) {
				var deviceNode = $scope.device.nodeList[i];
				if(deviceNode.nodeType == "MASTER"){
					$scope.masterNum++;
				}else{
					$scope.slaveNum++;
				}
			}
		});
		
		getChar();
		var promise = $interval(function(){
			getChar();
		}, 60000);
		
		//销毁定时任务
		$scope.$on('$destroy',function(){
			$interval.cancel(promise);
		})
		
		
		//获取实时图
		function getChar() {
			httpService.httpGet('/physicalServer/loadPhysicalServerChartData?ip=' + $scope.ip, function(result){
				$scope.charData = result.data;
				for (var charKey in $scope.charData) {

					var legends = [];
					var xAxisData = [];
					var yAxisFormatter;
					var dataMap = {};
					var temp;
					for (var keydata in $scope.charData[charKey]) {
						legends.push(keydata);
						var data = [];
						for (var i = 0; i < $scope.charData[charKey][keydata].length; i++) {
							data.push($scope.charData[charKey][keydata][i].data);
						}
						dataMap[keydata] = data;
						yAxisFormatter = $scope.charData[charKey][keydata][0].unit;
						temp = keydata;
					}

					for (var i = 0; i < $scope.charData[charKey][temp].length; i++) {

						xAxisData.push(functionService.dateFormat($scope.charData[charKey][temp][i].receiveDate));
					}

					var option = functionService.ToEchar(charKey, legends, xAxisData, yAxisFormatter, dataMap);
					var myChart = echarts.init(document.getElementById(charKey));
					myChart.setOption(option);
				}
			});
		}

	}
]);
