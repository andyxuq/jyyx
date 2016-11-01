app.controller('CacheMonitorCtrl', ['$scope', '$modal', '$log', 'toaster', '$http', 'httpService',
	function($scope, $modal, $log, toaster, $http, httpService) {
    
    httpService.httpGet('/redisCluster/getAll',function(result){
    	$scope.cacheServices = result.data;
    });
	
}]);