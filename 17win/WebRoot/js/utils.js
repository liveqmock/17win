// firefox 复制
function copy_code(copyText) {
	if (window.clipboardData) {
		window.clipboardData.setData("Text", copyText)
	} else {
		var flashcopier = 'flashcopier';
		if (!document.getElementById(flashcopier)) {
			var divholder = document.createElement('div');
			divholder.id = flashcopier;
			document.body.appendChild(divholder);
		}
		document.getElementById(flashcopier).innerHTML = '';
		var divinfo = '<embed src="js/_clipboard.swf" FlashVars="clipboard='
				+ encodeURIComponent(copyText)
				+ '" width="0" height="0" type="application/x-shockwave-flash"></embed>';
		document.getElementById(flashcopier).innerHTML = divinfo;
	}
}

/**
 * 只能输入整数的文本框
 * 
 * @param {}
 *            copyText
 */
function intText(textID) {
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isInt(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isInt(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});
}
/**
 * 只能输入浮点数的文本框
 * 
 * @param {}
 *            copyText
 */
function floatText(textID) {
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isFloat(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isFloat(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}

/**
 * 只能输入数值的文本框
 * 
 * @param {}
 *            copyText
 */
function numberText(textID) {
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isNumber(value, "+")) {
					var dot = value.substring(value.length - 2, value.length
									- 1);
					if (dot == ".") {
						$(this)
								.val(value.substring(0, value.length - 1)
										+ ".0");
					}
					$(this).val(value.substring(0, value.length - 1));
				}
			});
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isNumber(value, "+")) {
					var dot = value.substring(value.length - 2, value.length
									- 1);
					if (dot == ".") {
						$(this)
								.val(value.substring(0, value.length - 1)
										+ ".0");
					}
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}

// 得到city
function getCities(pid, cityObj, areaObj) {
	$.post("ajaxManager/ajax!address.php", {
				type : "1",
				id : pid
			}, function(data) {
				var selectCity = $(cityObj);
				var selectArea = $(areaObj);
				selectCity.empty();
				selectArea.empty();
				var option = $("<option value=''>--请选择--</option>");
				selectCity.append(option);
				selectArea.append($("<option value=''>请选择</option>"));
				var cities = data.cityList;
				for (var i = 0; i < cities.length; i++) {
					option = $("<option></option>", {
								value : cities[i].id,
								text : cities[i].name
							});
					selectCity.append(option);
				}
			}, "json");
}
// 得到area
function getAreas(cid, areaObj) {
	$.post("ajaxManager/ajax!address.php", {
				type : "2",
				id : cid
			}, function(data) {
				var selectArea = $(areaObj);
				selectArea.empty();
				var option = $("<option>--请选择--</option>");
				selectArea.append(option);
				var cities = data.cityList;
				var areas = data.areaList;
				for (var i = 0; i < areas.length; i++) {
					option = $("<option></option>", {
								value : areas[i].id,
								text : areas[i].name
							});
					selectArea.append(option);
				}
			}, "json");
}
// 根据店铺得到seller
/**
 * type 0 自动获取,1 淘宝 2拍拍 3有啊
 * 
 * @param {}
 *            url
 * @param {}
 *            type
 * @param {}
 *            textID
 */
function getSeller(url, type, textID) {
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSellerByShop.php", {
				url : url,
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					alert("您输入的地址不正确！");
				} else {
					$("#" + textID).val(data.seller);
				}
			}, "json");
}

// 动态参数
function dynamicMsg(str) {
	var index = arguments.length - 1;
	for (var i = 0; i < index; i++) {
		var reCat = new RegExp("#\\{" + i + "\\}", "g");
		str = str.replace(reCat, arguments[i + 1]);
	}
	return str;
}
