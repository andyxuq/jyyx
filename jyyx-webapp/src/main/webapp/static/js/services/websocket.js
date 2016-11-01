//自定义webSocket模块
var zwebsocket = angular.module("zwebsocket",[]);

zwebsocket.service('webSocketService', function($interval){
	var socket;
	
	//host为请求的url window.location.host + '/websocket/dashboard'
	this.getSocket = function(host){
		var url;
		if (window.location.protocol == 'http:') {
		  	url = 'ws://' + host;
		} else {
		  	url = 'wss://' + host;
		}
		
		if ('WebSocket' in window) {
           socket = new WebSocket(url);
        } else if ('MozWebSocket' in window) {
           socket = new MozWebSocket(url);
        } else {
            //使用公共提示组件替换
            return;
        }
        
        socket.onopen = function () {
        };

    	socket.onclose = function () {
        };
        
        //根据需求自定义socket.onmessage函数
        return socket;
	}
	
	//发送心跳数据
	this.interval = function(data) {
		$interval(function() {
			if(socket!=undefined && socket!= null && socket.readyState == 1){
				socket.send(data || "");
			}
		}, 20000);
	}
	
	//发送消息前判断socket是否是连接状态
	this.send = function(socket, message){
		if(socket!=undefined && socket!= null && socket.readyState == 1){
			socket.send(message);
			return true;
		}else{
			console.error('please connect to the server first !!!');
			return false;
		}
	}
});
