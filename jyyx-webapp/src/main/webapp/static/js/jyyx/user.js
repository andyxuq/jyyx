app.controller('UserCtrl', ['$scope', '$modal', '$log', '$http','toaster', '$state','functionService','httpService','pageService',
function($scope, $modal, $log, $http, toaster, $state, functionService, httpService, pageService) {

	$scope.userList = [];
	$scope.loadData = function() {
		var url = "/api/user/get"
		httpService.httpPost(url, null, function(result){
			$scope.userList = result.data;
		});
	}
	$scope.loadData();
	
	
	$scope.deleteRes = function(user) {
		$scope.modal = {};
		$scope.modal.title = "操作确认";
		$scope.modal.message = "是否要删除用户[" + user.userName + "]";
		$modal.open({
			templateUrl: 'messageModal.html',
			controller: 'ModalInstanceCtrl',
			resolve: {
				items: function() {
					return $scope.modal;
				}
			}
		}).result.then(function() {
			httpService.httpPost('/api/user/delete/' + user.id, null, function(result){
				toaster.pop('info', '', '删除用户' + user.userName + "成功");
				$scope.loadData();
			});
		}, function() {
			
		});
	}
}]);