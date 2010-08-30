function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
}
var submitFlag = true;
var timer;
$(document).ready(function() {
	timer = setInterval(changeSutmitBtn, 1000);
	// 用户名
	$("#username").bind("blur", function() {
		var obj = this;
		if (Validater.isUsername($(this).val())) {
			VhostAop.divAOP.ajax("ajaxManager/ajax!userExists.php", {
						username : $(obj).val()
					}, function(data) {
						if (data.bool) {
							validateSuccess(obj);
						} else {
							validateError(obj, "用户名已经存在");
						}
					});

		} else {
			validateError(
					this,
					"\u7528\u6237\u540d\u5fc5\u987b\u662f\u53c8\u6570\u5b57\u6216\u5219\u5b57\u7b26\u7ec4\u6210\u76844-12\u4f4d\u5b57\u7b26\u4e32");
			submitFlag = false;
		}
	});
	// 密码
	$("#password").bind("blur", function() {
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});
	// 确认密码密码
	$("#rePassword").bind("blur", function() {
		if ($(this).val() != $("#password").val()) {
			validateError(this, "\u4e24\u6b21\u5bc6\u7801\u4e0d\u76f8\u7b49");
			submitFlag = false;
			return;
		}
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});
	// 操作码
	$("#opertationCode").bind("blur", function() {
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});
	// 确认操作码
	$("#reOperationCode").bind("blur", function() {
		if ($(this).val() != $("#opertationCode").val()) {
			validateError(this,
					"\u4e24\u6b21\u64cd\u4f5c\u7801\u4e0d\u76f8\u7b49");
			submitFlag = false;
			return;
		}
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});

	// QQ
	$("#qq").bind("blur", function() {
		if (Validater.isQQ($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "QQ\u53f7\u7801\u683c\u5f0f\u4e0d\u6b63\u786e");
			submitFlag = false;
		}
	});
	// email
	$("#email").bind("blur", function() {
				if (Validater.isEmail($(this).val())) {
					validateSuccess(this);
				} else {
					validateError(this,
							"\u90ae\u7bb1\u683c\u5f0f\u4e0d\u6b63\u786e");
					submitFlag = false;
				}
			});
	// 手机号码
	$("#telephone").bind("blur", function() {
				if (Validater.isTelphone($(this).val())) {
					validateSuccess(this);
				} else {
					validateError(this,
							"\u624b\u673a\u53f7\u7801\u4e0d\u6b63\u786e");
					submitFlag = false;
				}
			});
	// 验证淘宝
	$("#taobaoShopURL").bind("blur", function() {
				if (Validater.trim($(this).val()) == "") {
					return;
				}
				if (Validater.isTaobaoShop($(this).val())) {
					validateSuccess(this);
					getSeller($(this).val(), "1", "taobaoSeller");
				} else {
					validateError(this, "淘宝地址格式不正确");
					submitFlag = false;
				}
			});
	// 验证码
	$("#verificationCode").bind("blur", function() {
				if ($(this).val().length == 4) {
					validateSuccess(this);
				} else {
					validateError(this, "\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e");
					submitFlag = false;
				}
			});

	// 选择省的事件
	$("#provinceID").bind("change", function() {
		if ($(this).val() == "") {
			$("#cityID").empty();
			$("#cityID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#cityID").val("");
			$("#areaID").empty();
			$("#areaID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#areaID").val("");
			return;
		}
		getCities($(this).val(), "cityID", "areaID");
	});
	// 选择市的事件
	$("#cityID").bind("change", function() {
		if ($(this).val() == "") {
			$("#areaID").empty();
			$("#areaID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#areaID").val("");
			return;
		}
		getAreas($(this).val(), "areaID");
	});
});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	if (submitFlag) {
		return true;
	} else {
		alert("\u586b\u5199\u7684\u8d44\u6599\u4e0d\u6b63\u786e\uff01");
		return false;
	}
}

// 验证成功调用的方法
function validateSuccess(obj) {
	var span = $("<span></span>", {
				css : {
					colour : "red"
				}
			});
	var img = $("<img></img>", {
				src : "images/icon_ok.gif",
				title : "\u9a8c\u8bc1\u6210\u529f"
			});
	var td = $(obj).parent().next();
	span.append(img);
	td.empty();
	td.append(span);
}

// 验证失败调用的方法
function validateError(obj, msg) {
	var span = $("<span></span>", {
				css : {
					colour : "red"
				}
			});
	var img = $("<img></img>", {
				src : "images/icon_error.gif",
				title : msg
			});
	var td = $(obj).parent().next();
	span.append(img);
	span.append(msg);
	td.empty();
	td.append(span);
}
// 提交按钮的改变
function changeSutmitBtn() {
	var time = eval($("#sumbitBtn").attr("timeId")) - 1;
	$("#sumbitBtn").attr("timeId", time);
	$("#sumbitBtn").val("\u540c\u610f\u534f\u8bae(" + time + ")");
	if (time == 0) {
		$("#sumbitBtn").attr("disabled", false);
		clearInterval(timer);
	}
}
