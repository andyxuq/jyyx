//自定义webSocket模块
var zwebsocket = angular.module("functionServiceModule", []);

zwebsocket.service('functionService', function($interval) {

	//对机器进行排序,转化成二维数组
	this.arrayToRow = function(hostInfos, perRowNum) {
		var hostInfoRow = [];
		for (var i = 0; i < hostInfos.length; i++) {
			if (i % perRowNum == 0) {
				var row = [];
				hostInfoRow.push(row);
			}
			hostInfoRow[hostInfoRow.length - 1].push(hostInfos[i]);
		}
		return hostInfoRow;
	}

	/**
	 * 生成echar折线图
	 * legends 折线名
	 * xAxisData x轴数组
	 * yAxisFormatter y轴单位 字符
	 * dataMap 包含每条折线名的数据 key:折线名 value:该折线的所有值（数组）
	 * */
	this.ToEchar = function(title, legends, xAxisData, yAxisFormatter, dataMap) {
		var optionChar = {
			title: {
				text: title,
				subtext: title + '实时数据'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: legends
			},
			toolbox: {
				show: true,
				feature: {

					magicType: {
						show: true,
						type: ['line', 'bar']
					},
					saveAsImage: {
						show: true
					}
				}
			},
			calculable: true,
			xAxis: [{
				type: 'category',
				boundaryGap: false,
				axisLabel: {
					rotate: 30,
				},
				data: xAxisData
			}],
			yAxis: [{
				type: 'value',
				scale: true,
				axisLabel: {
					formatter: '{value} ' + yAxisFormatter
				}
			}],
			series: []

		};

		for (var i = 0; i < legends.length; i++) {
			var seriesObject = {};
			seriesObject.name = legends[i];
			seriesObject.type = "line";
			seriesObject.data = dataMap[legends[i]];
			optionChar.series.push(seriesObject);
		}

		return optionChar;
	}

	//将时间秒格式化
	this.dateFormat = function(date) {
		var date = new Date(date * 1000);
		Y = date.getFullYear() + '-';
		M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
		D = date.getDate() < 10 ? '0' + date.getDate() + " " : date.getDate() + " ";
		h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
		m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
		s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
		return M + D + h + m + s;
	}
	
	/*
	 * 将时间秒格式化成 yyyy/mm/dd HH:ss
	 *
	 * */
	this.dateFormat1 = function(date) {
		Y = date.getFullYear() + '/';
		M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
		D = date.getDate() < 10 ? '0' + date.getDate() + " " : date.getDate() + " ";
		h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
		m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes();
		return Y + M + D + h + m;
	}
	
	/*
	 * 将时间秒格式化成 yyyy/mm/dd
	 *
	 * */
	this.dateFormatDay = function(date) {
		Y = date.getFullYear() + '/';
		M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
		D = date.getDate() < 10 ? '0' + date.getDate() + " " : date.getDate();
		
		return Y + M + D;
	}

});