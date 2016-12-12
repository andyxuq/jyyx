//分页服务
var pageService = angular.module("pageServiceModule", []);

pageService.service('pageService', function($interval, $http, toaster) {

	var pageNum = 5;
	
	//获取当前该显示的页码
	this.pageNumArray = function(currentPage, totalPage) {
		if (totalPage <= pageNum) {
			return [1,2,3,4,5]
		}
		
		var startPage = currentPage;
		if ((totalPage - startPage + 1) < pageNum) {
			startPage = totalPage - pageNum + 1;
		}
		
		var endPage = startPage + pageNum - 1;
		var pageNumArray = [];
		for (var i = startPage; i <= endPage; i++) {
			pageNumArray.push(i);
		}
		return pageNumArray;
	}	
	
	this.getPageNum = function() {
		return pageNum;
	}
	
	this.needEllipsis = function(currentPage, totalPage) {
		if (totalPage - currentPage >= pageNum) {
			return true;
		} else {
			return false;
		}
	}
});