app.controller('ZookeeperConfigCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService',
    function($scope, $modal, $log, $http, toaster, $state, functionService, httpService) {
        $scope.selectServiceDef = {};
        $scope.serviceDefs = {};
        
        httpService.httpGet('/cluster/getAll', function(result){
            $scope.serviceDefs = result.data;
        });
        
        $scope.getClusterNodes = function() {
            $scope.hasAppKeyConfig = true;
            if ($scope.selectServiceDef.id == undefined) {
                toaster.pop("error", "", '集群选取失败');
                return;
            }
            
            $scope.selectServiceDef.timeWaitMins = $scope.selectServiceDef.timeWait / 60;
            $scope.mainNodes = new Array();
            $scope.backupNodes = new Array();
            
            var serviceDefine = {};
            serviceDefine.id = $scope.selectServiceDef.id;
            serviceDefine.name = $scope.selectServiceDef.name;            
            httpService.httpPost('/redisCluster/get/true', serviceDefine, function(result){
                if(result.data.define != null){
                    var nodeList = result.data.define.nodeList;
                    for (var i in nodeList) {
                        if (nodeList[i].primaryCluster) {
                            $scope.mainNodes.push(nodeList[i]);
                        } else {
                            $scope.backupNodes.push(nodeList[i]);
                        }
                    }
                } else {
                    toaster.pop("error", "", "查询集群节点信息失败");
                }
            });
            
            getZookeeperConfig($scope.selectServiceDef);
        }
        
        
        var getZookeeperConfig = function(serviceDefine) {
            if (serviceDefine.appKey == null) {
                $scope.hasAppKeyConfig = false;
                return;
            }
            
            $scope.zkConfigs = new Array();
            $scope.zkConfigRoot = '';
            httpService.httpGet('/configCenter/getConfig/' + serviceDefine.appKey, function(result){
                if (result.data == null) {
                    $scope.zkConfigRoot = '未获取到zookeeper上的配置信息';
                } else {
                    for (var key in result.data) {
                        var rootPath = key.substr(0, key.lastIndexOf('/'));
                        var path = key.substr(key.lastIndexOf('/'));
                        if ($scope.zkConfigRoot == '') {
                            $scope.zkConfigRoot =  rootPath;
                        }
                        
                        var pathMap = {};
                        pathMap.path = path;
                        pathMap.configValue = result.data[key];
                        pathMap.status = "";
                        if (result.data[key].indexOf('不存在') != -1) {
                            pathMap.status = "color:red";
                        }
                        
                        $scope.zkConfigs.push(pathMap);
                    }
                }
            });
        }
        
        $scope.switchCluster = function(isPrimary) {
            var title = isPrimary?"主":"备";
            if ($scope.selectServiceDef.id == undefined) {
                toaster.pop("error", "", "未选择集群");
                return;
            }
            if ($scope.selectServiceDef.appKey == undefined || $scope.selectServiceDef.appKey == null 
                    || $scope.selectServiceDef.appKey == '') {
                toaster.pop("error", "", "集群的appKey为空");
                return;
            }
            
            $scope.modal = {};
            $scope.modal.title = "切换集群";
            $scope.modal.message = "确认将集群切换到[ " + title + "集群 ]";
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                resolve: {
                    items: function() {
                        return $scope.modal;
                    }
                }
            }).result.then(function() {
                httpService.httpPost('/configCenter/updateConfig/' + $scope.selectServiceDef.id + '/' + isPrimary, null, function(result){
                    toaster.pop("success", "", "集群已切换为:" + result.data);
                    getZookeeperConfig($scope.selectServiceDef);
                });               
            }, function() {});
        }
        
        $scope.changeTimeWait = function() {
            var timeWaitSeconds = $scope.selectServiceDef.timeWaitMins * 60;
            if (timeWaitSeconds == $scope.selectServiceDef.timeWait) {
                toaster.pop("error", "", "主备切换等待时间没有更改");
                return;
            }
            $scope.modal = {};
            $scope.modal.title = "修改主备切换等待时间";
            $scope.modal.message = "确认将集群的主备切换等待时间更改为[ " + $scope.selectServiceDef.timeWaitMins + "分钟 ]";
            $modal.open({
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                resolve: {
                    items: function() {
                        return $scope.modal;
                    }
                }
            }).result.then(function() {                
                httpService.httpPost('/configCenter/updateTimeWait/' + $scope.selectServiceDef.id + '/' + timeWaitSeconds, null, function(result){
                    toaster.pop("success", "", "主备切换等待时间已改为:" + $scope.selectServiceDef.timeWaitMins + "分钟");
                    $scope.selectServiceDef.timeWait = timeWaitSeconds;
                    getZookeeperConfig($scope.selectServiceDef);
                });
            }, function() {});
        }
    }
]);