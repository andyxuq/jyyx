
//http request tool

var getUrl = function(url, callback) {
	$.ajax({
		url:url,
		type:'GET',
		beforeSend: function (request)
        {
            request.setRequestHeader("Content-Type", "application/json");
        },
		dataType: 'json',
		success:function(data) {
			if (data.code != 0) {
				alert('请求数据出错:' + data.msg);
			} else {
				callback(data);
			}
		},
		error: function(status) {
			alert('http请求出错');
		}
	});
}

var postUrl = function(url, params, callback) {
	$.ajax({
		url:url,
		type:'POST',
		beforeSend: function (request)
        {
            request.setRequestHeader("Content-Type", "application/json");
            request.setRequestHeader("Accept", "application/json");
        },
		data:JSON.stringify(params),
		success:function(data) {
			if (data.code != 0) {
				alert('请求数据出错:' + data.msg);
			} else {
				callback(data);
			}
		},
		error: function(status) {
			alert('http请求出错');
		}
	});
}