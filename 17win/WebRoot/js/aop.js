function VhostAop() {

}
VhostAop.divAOP = new VhostAop();
VhostAop.AjaxDivHandler = function(url, arg, callback) {
	var aop = VhostAop.divAOP;
	if (arguments.length == 2) {
		aop.ajaxParam2.apply(aop, arguments);
	} else {
		aop.ajaxParam3.apply(aop, arguments);
	}
};

VhostAop.divAOP.init = function() {
	try {
		var loading_Div = $("<div id='rtpLoadingDiv' style='position:absolute;top:50%;left:50%;margin:0px;z-index:1000;width:200px;height:30px;'><img src='images/loading.gif'/></div>");
		var bg_Div = $("<div>", {
					id : "rtpBgDiv",
					css : {
						width : "100%",
						// height : "100%",
						left : "0px",
						top : "0px",
						position : "absolute",
						background : "#FFFFFF",
						zIndex : "999",
						opacity : "0.5",
						filter : "alpha(opacity=50)",
						mozOpacity : "0.5",
						display : "none"
					}
				});

		this.loadingDiv = loading_Div;
		this.bgDiv = bg_Div;
	} catch (e) {
		alert(e.message);
	}

};
VhostAop.divAOP.before = function() {
	try {
		$(this.bgDiv).css("height", document.body.clientHeight);
		$("body").append(this.bgDiv);
		$("body").append(this.loadingDiv);
		this.bgDiv.show();
		this.loadingDiv.show();
	} catch (e) {
		alert(e.message);
	}

};
VhostAop.divAOP.after = function() {

	this.loadingDiv.hide();
	this.bgDiv.hide();
	$("body").remove("#rtpLoadingDiv");
	$("body").remove("#rtpBgDiv");

};
VhostAop.divAOP.ajaxParam2 = function(url, callback) {
	this.ajax(url, {}, callback);
};
VhostAop.divAOP.ajaxParam3 = function(url, data, callback) {

	this.ajax(url, data, callback);
};
VhostAop.divAOP.ajax = function(url, data, callback) {
	var aop = VhostAop.divAOP;
	aop.before();
	if (url.indexOf("?") != -1) {
		url = url + "&time=" + new Date().getTime();
	} else {
		url = url + "?time=" + new Date().getTime();
	}
	$.post(url, data, function(data) {
				callback(data);
				aop.after();
			});
};
VhostAop.divAOP.init();
