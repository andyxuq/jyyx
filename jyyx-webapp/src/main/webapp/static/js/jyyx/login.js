app.controller('LoginCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.user = {
			'loginName':null,
			'loginPwd':null
	}
	
	$scope.registerTrick = function() {
		toaster.pop('info', '', '点我也不能创建账号');
	}
	
	$scope.login = function() {
		httpService.httpPost('/api/user/login', $scope.user, function(result){
			$scope.getUserInfo();
			$state.go('app.pic');
	    });
	}
}]);