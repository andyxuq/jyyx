app.controller('ClusterMonitorCtrl', ['$scope', '$modal', '$log', '$http', '$state','toaster','functionService','httpService',
	function($scope, $modal, $log, $http, $state, toaster, functionService, httpService) {
	
	$scope.selectServiceDef;
	var date = new Date();
	date.setHours(date.getHours() - 1)
	$scope.qpsStartTime = functionService.dateFormat1(new Date(date));
	$scope.qpsEndTime = functionService.dateFormat1(new Date());
	
	$scope.tp99QpsStartTime = functionService.dateFormatDay(new Date());
	date.setDate(date.getDate() + 1);
	$scope.tp99QpsEndTime = functionService.dateFormatDay(new Date(date));
	$scope.is_primary_cluster;
	
	$("#qpsStartTime").datetimepicker({
		language:  'zh-CN',
        format: "yyyy/mm/dd hh:ii",
        autoclose: 1
    });
    
    $("#qpsEndTime").datetimepicker({
    	language:  'zh-CN',
        format: "yyyy/mm/dd hh:ii",
        autoclose: 1
    });
    
    $("#tp99QpsStartTime").datetimepicker({
		language:  'zh-CN',
        format: "yyyy/mm/dd",
        autoclose: 1,
        startView: 2,
		minView: 2,
		forceParse: 0
    });
    
    $("#tp99QpsEndTime").datetimepicker({
    	language:  'zh-CN',
        format: "yyyy/mm/dd",
        autoclose: 1,
        startView: 2,
		minView: 2,
		forceParse: 0
    });
	/*$('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });*/
	
	//获取所有的redis集群
	var serviceMap = {};
	httpService.httpGet('/cluster/getAll', function(result){
		$scope.serviceDefs = result.data;
		
		for (var i = 0; i<$scope.serviceDefs.length; i++) {
			serviceMap[$scope.serviceDefs[i].id] = $scope.serviceDefs[i];
		}
	});
	
	//获取所有的机器
	$scope.hostMap = {};
	httpService.httpGet('/deviceMonitor/getSimpleHostInfos', function(result){
		var hosts = result.data;
		for (var i = 0; i<hosts.length; i++) {
			$scope.hostMap[hosts[i].id] = hosts[i];
		}
	});
	
	
	//获取指定集群的QPS
	$scope.getQPS = function(){
		console.log(new Date($scope.qpsStartTime));
		var url = '/cluster/getQPS/'+$scope.selectServiceDef.id;
		var param = {
						'startDate' : new Date($scope.qpsStartTime),
						'endDate' : new Date($scope.qpsEndTime),
						'isPrimary':$scope.is_primary_cluster==undefined?"":$scope.is_primary_cluster
			};
			
		httpService.http('POST', url, param, function(respondData){
			var title = "QPS"
			var legends = [];
			var dataMap = {};
			
			var data = respondData.data;
			for(var i=0; i< data.length; i++){
				if(data[i].serviceNodeExtend != null){
					var lineName = $scope.hostMap[data[i].serviceNodeExtend.nodeHostId].name+":"+data[i].serviceNodeExtend.nodePort;
					if(dataMap[lineName] == undefined){
						legends.push(lineName);
						dataMap[lineName] = [];
					}
					
					var d = [];
					d.push(new Date(data[i].createTime));
					d.push(data[i].qps);
					dataMap[lineName].push(d);
				}
			}
			
			var option = ToEchar(title, legends, dataMap);
			var myChart = echarts.init(document.getElementById("qps"));
			myChart.setOption(option);
		});
	}
	
	//获取指定集群的Tp99QPS
	$scope.getTp99QPS = function(){
		var url = '/cluster/getTp99QPS/'+$scope.selectServiceDef.id;
		var param = {
						'startDate' : new Date($scope.tp99QpsStartTime),
						'endDate' : new Date($scope.tp99QpsEndTime)
			};
			
		httpService.http('POST', url, param, function(respondData){
			var data = respondData.data;
			var title = "Tp99QPS"
			var legends = [];
			var dataMap = {};
			legends.push(serviceMap[$scope.selectServiceDef.id].name);
			dataMap[serviceMap[$scope.selectServiceDef.id].name] = [];
			for(var i=0; i< data.length; i++){
				
				var d = [];
				d.push(new Date(data[i].createTime));
				d.push(data[i].tp99Qps);
				dataMap[serviceMap[$scope.selectServiceDef.id].name].push(d);
			}
			
			var option = ToEchar(title, legends, dataMap);
			var myChart = echarts.init(document.getElementById("tp99qps"));
			myChart.setOption(option);
		});
	}
	
function ToEchar(title, legend, seriesMap) {
	var option = {
		title: {
			text: title
		},
		tooltip: {
			trigger: 'item',
			formatter: function(params) {
				var date = new Date(params.value[0]);
				data = date.getFullYear() + '-'
                   + (date.getMonth() + 1) + '-'
                   + date.getDate() + ' '
                   + date.getHours() + ':'
                   + date.getMinutes();
                var rTime = 0;
                if(params.value[1] >0){
               		rTime = (1000/params.value[1]).toFixed(3);
                }
				return data + '<br/> QPS:' + params.value[1]+
						'<br/>响应时间:'+rTime+"ms";
			}
		},
		toolbox: {
			show: true,
			feature: {
				saveAsImage: {
					show: true
				}
			}
		},
		dataZoom: {
			show: true,
			start: 70
		},
		legend: {
			data: legend
		},
		grid: {
			y2: 80
		},
		xAxis: [{
			type: 'time',
			splitNumber: 10
		}],
		yAxis: [{
			type: 'value'
		}],
		series: []
	};
	
	for (var key in seriesMap) {
		var serie = {
			name: key,
			type: 'line',
			showAllSymbol: true,
			data: seriesMap[key]
		}
		
		option.series.push(serie);
	}
	return option;
}    
	
}]);



