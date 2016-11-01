//自定义webSocket模块
var httpServiceM = angular.module("httpServiceModule", []);

httpServiceM.service('httpService', function($interval,$http, toaster) {
//http Get请求
	 this.httpGet = function(url, callback) {
		$http.get(url)
			.success(function(result, status, headers, config) {
				if (result.code == 0) {
					callback(result);
				} else {
					toaster.pop("error", "",  result.message);
				}
			})
			.error(function(result, status, headers, config) {
				toaster.pop("error", "",   result.message!=undefined?result.message:"http请求错误");
			});
	}
	
	/*
	 * http post请求
	 * url : 请求的url
	 * callback 请求成功后的回调函数
	 * */
	this.httpPost = function(url, parma, callback) {
		$http.post(url, parma)
			.success(function(result, status, headers, config) {
				if (result.code == 0) {
					callback(result);
				} else {
					toaster.pop("error", "", result.message);
				}
			})
			.error(function(result, status, headers, config) {
				toaster.pop("error", "", result.message!=undefined?result.message:"http请求错误");
			});
	}
	
	/*
	 * http post请求
	 * url : 请求的url
	 * callback 请求成功后的回调函数
	 * */
	this.http = function(method, url, parma, callback) {
		$http({
			method: method,
			url: url,
			params: parma
		}).success(function(result, status, headers, config) {
			if (result.code == 0) {
				callback(result);
			} else {
				toaster.pop("error", "", result.message);
			}
        }).
        error(function(result, status, headers, config) {
        	toaster.pop("error", "", result.message!=undefined?result.message:"http请求错误");
        });
	}
	
	

});