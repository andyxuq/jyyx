app.controller('UpdateMemoryCtrl', ['$scope', '$modal', '$log', '$http', 'toaster', '$stateParams',
	'$interval', 'webSocketService', 'functionService', 'httpService', 'radialIndicatorInstance',
	function($scope, $modal, $log, $http, toaster, $stateParams,
		$interval, webSocketService, functionService, httpService, radialIndicatorInstance) {

		$scope.allProcess = {};
		$scope.allProcess.allpercent = 0; //总进度
		$scope.allProcess.type = "info"; //进度条样式
		$scope.allProcess.nowStep = 0; //当前执行的步骤
		$scope.processShow = false; //展示扩容、缩容过程
		$scope.changeButtonDisabled = false; //控制扩容缩容状态时按钮不可用
		$scope.isExpand = "EXPAND"; //判断是扩容或缩容状态，默认扩容

		$scope.process = [{
			"radius": 35,
			"percent": 0,
			"id": "indicator1",
			"message": "",
			"percentage": true,
			"roundCorner": true,
			"barColor": "#87CEEB",
			"initValue": 10
		}, {
			"radius": 35,
			"percent": 0,
			"id": "indicator2",
			"message": "",
			"percentage": true,
			"roundCorner": true,
			"barColor": "#87CEEB",
			"initValue": 20
		}, {
			"radius": 35,
			"percent": 0,
			"id": "indicator3",
			"message": "",
			"percentage": true,
			"roundCorner": true,
			"barColor": "#87CEEB",
			"initValue": 0
		}, {
			"radius": 35,
			"percent": 0,
			"id": "indicator4",
			"message": "",
			"percentage": true,
			"barColor": "#87CEEB",
			"roundCorner": true,
			"initValue": 0
		}];
		$scope.selectedredisCluster = {};
		$scope.redisClusterId;
		var test = {
			barColor: '#87CEEB',
			barWidth: 10,
			initValue: 0,
			roundCorner: true,
			percentage: true
		};

		$scope.indicatorOption = {
			radius: 80,
			percentage: true,
			barColor: "#87CEEB"
		};

		var i = 0;
		$interval(function() {
			i += 1;
			$scope.indicatorValue = i;

		}, 100);

		//连接websocket
		function connWebSocket() {
			processSocket = webSocketService.getSocket(window.location.host + '/websocket/updateMemory');
			processSocket.onmessage = function(message) {
				showProcess(message.data);
			}
			processSocket.onopen = function() {
				processSocket.send($scope.redisClusterId);
			}
			webSocketService.interval("");
		}
		
		function initProcess(){
			$scope.allProcess.allpercent = 0;
			$scope.allProcess.type = "info";
			$scope.stopButton = false;
			for (var i=0; i<4; i++) {
				$scope.process[i].percent = 0;
			}
		}

		//解析process,显示集群更改过程，扩容和缩容顺序不一样
		function showProcess(process) {
			process = eval('(' + process + ')');
			var returnStep = process.step - 1;

			$scope.process[returnStep].percent = process.percent;
			//$scope.process[returnStep].status = process.status;
			$scope.allProcess.nowStep = returnStep;
			$scope.process[returnStep].message = process.message;
			switch(process.step) {
				case 1:
					if($scope.isExpand != process.type) {
						$scope.isExpand = process.type;
					}
					if(process.status == "SUCCESS") {
						$scope.allProcess.allpercent += 25;

					} else if(process.status == "PENDING") {
						$scope.allProcess.allpercent = process.percent / 4;
					} else {
						$scope.allProcess.allpercent -= 25;
					}
					break;
				case 2:
					if($scope.isExpand != process.type) {
						$scope.isExpand = process.type;
					}
					if(process.status == "SUCCESS") {
						$scope.allProcess.allpercent += 25;
					} else {
						$scope.allProcess.allpercent -= 25;
					}
					break;
				case 3:
					if($scope.isExpand != process.type) {
						$scope.isExpand = process.type;
					}
					if(process.status == "SUCCESS") {
						if(process.type == "REDUCE"){
							$scope.allProcess.allpercent = 50;
						}else{
							$scope.allProcess.allpercent = 75;
						}
					} else if(process.status == "PENDING") {
						if(process.type == "REDUCE"){
							$scope.allProcess.allpercent = 25 + process.percent / 4;
						}else{
							$scope.allProcess.allpercent = 50 + process.percent / 4;
						}
					}
					break;
				case 4:
					if($scope.isExpand != process.type) {
						$scope.isExpand = process.type;
					}
					if(process.status == "SUCCESS") {
						$scope.allProcess.allpercent = 100;
						$scope.allProcess.type = "success";
					} else {
						$scope.process[$scope.allProcess.nowStep].message = process.message != null ? process.message : "";
						$scope.allProcess.type = "danger";
						$scope.allProcess.allpercent = 75;
					}
					break;
				default:
					if(process.status != "SUCCESS") {
						toaster.pop("error", "", process.message);
						$scope.process[$scope.allProcess.nowStep].message = process.message != null ? process.message : "";
						$scope.allProcess.type = "error";
					};
			}
			
			if($scope.allProcess.allpercent == 100 || $scope.allProcess.allpercent == 0){
				$scope.changeButtonDisabled = false;
				$scope.stopButton = true;
			}else{
				$scope.changeButtonDisabled = true;
			}
			
		}

		if($stateParams.clusterId != "") {
			$scope.processShow = true;
			$scope.changeButtonDisabled = true;
			$scope.selectedredisCluster.id = $stateParams.clusterId;
			getCluster($scope.selectedredisCluster);
			$scope.redisClusterId = $scope.selectedredisCluster.id;
			connWebSocket();
		}

		//扩容、缩容
		$scope.updateMemory = function() {
			$scope.modal = {};
			$scope.modal.title = "分配内存";
			$scope.modal.message = "确认修改内存？";
			$modal.open({
				templateUrl: 'myModalContent.html',
				controller: 'ModalInstanceCtrl',
				resolve: {
					items: function() {
						return $scope.modal;
					}
				}
			}).result.then(function() {
				//判断是否已经处于扩容状态
				httpService.httpGet('/redisCluster/getClustersStatus/' + $scope.selectedredisCluster.define.id, function(result) {
					if(result.data.status == "CHANGEING") {
						$scope.processShow = true;
						$scope.changeButtonDisabled = true;
						$scope.redisClusterId = $scope.selectedredisCluster.define.id;
						connWebSocket();
						toaster.pop("info", "", "redis集群已处在扩容（缩容）状态");

					} else {
						var postDefine = {};
						angular.copy($scope.selectedredisCluster.define, postDefine);
						postDefine.dataCapacity = postDefine.dataCapacity * 1024 * 1024;
						$scope.redisClusterId = $scope.selectedredisCluster.define.id;
						connWebSocket();
						initProcess();

						httpService.httpPost('/redisCluster/update', postDefine, function(result) {
							$scope.processShow = true;
							$scope.changeButtonDisabled = true;
						});
					}
				});

			}, function() {

			});
		}
		
		//终止
		$scope.stopButton = false;
		$scope.stop = function(){
			$scope.modal = {};
			$scope.modal.title = "终止操作";
			$scope.modal.message = "警告！确认要终止集群更改？";
			$modal.open({
				templateUrl: 'myModalContent.html',
				controller: 'ModalInstanceCtrl',
				resolve: {
					items: function() {
						return $scope.modal;
					}
				}
			}).result.then(function() {
				httpService.httpGet("/redisCluster/stopUpdate/"+$scope.redisClusterId, function(result){
					if(result.code != 0){
						toaster.pop("error", "", result.message);
					}else{
						toaster.pop("info", "", result.message);
						$scope.stopButton = true;
					}
				});
			}, function() {

			});
		}

		$scope.selectCluster = function() {
			$scope.selectedredisCluster = $scope.redisClustersMap[$scope.selectedredisClusterId];
			$scope.dataCapacity = $scope.selectedredisCluster.define.dataCapacity;
			$scope.numberOfPartitions = $scope.selectedredisCluster.define.numberOfPartitions;
			$scope.backupRatio = $scope.selectedredisCluster.define.backupRatio;
		}

		//自动调整内存容量，原来每分片的大小乘以现有的分片数
		$scope.autoUpdateMemory = function() {
			$scope.selectedredisCluster.define.dataCapacity = ($scope.dataCapacity / $scope.numberOfPartitions * $scope.selectedredisCluster.define.numberOfPartitions).toFixed(0);
		}

		$scope.redisClustersMap = {};
		//获取指定redis集群信息
		function getCluster(serviceDefine) {
			httpService.httpPost('/redisCluster/get/true', serviceDefine, function(result) {
				$scope.selectedredisCluster = result.data;
				$scope.selectedredisCluster.define.dataCapacity = $scope.selectedredisCluster.define.dataCapacity / 1024 / 1024;
				$scope.selectedredisClusterId = $scope.selectedredisCluster.define.id;
			});
		}

		$scope.perRowNum = 4; //界面每行只展示4台机器
		//加载所有物理机信息
		httpService.httpGet('/deviceMonitor/getHostInfos', function(result) {
			$scope.hostInfoRows = functionService.arrayToRow(result.data, $scope.perRowNum);
			getHostInfoExtend();
		});

		//加载所有物理机详细信息
		function getHostInfoExtend() {
			httpService.httpGet('/redisCluster/getHostInfoExtend', function(result) {
				$scope.hostInfoRows = functionService.arrayToRow(result.data, $scope.perRowNum);
			});
		}

		//加载所有redis集群
		httpService.httpGet('/redisCluster/getAll', function(result) {
			$scope.redisClusters = result.data;
			for(var i = 0; i < $scope.redisClusters.length; i++) {
				$scope.redisClusters[i].define.dataCapacity = $scope.redisClusters[i].define.dataCapacity / 1024 / 1024;
				$scope.redisClustersMap[$scope.redisClusters[i].define.id] = $scope.redisClusters[i];
			}
		});
	}
]);