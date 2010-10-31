function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
} 
var submitFlag = true;
$(document).ready(function() {
	$("#username").focus();
	$("#verificationCode").val("");
	$("#loginBtn").button();
	$("#findPW").dialog({
				autoOpen : false,
				draggable : false,
				hide : 'slide',
				modal : true,
				resizable : false,
				show : 'slide'
			});

	if ($("#activateDIV") != null) {
		$("#activateDIV").dialog({
					autoOpen : true,
					draggable : false,
					hide : 'slide',
					modal : true,
					resizable : false,
					show : 'slide'
				});
	}
	$("#findPWBtn").bind("click", function() {
				if (Validater.isBlank($("#usernameTelephone").val())) {
					alert("数据不能为空");
					return false;
				}
				$.post("ajaxManager/ajax!findPassword.php", {
							username : $("#usernameTelephone").val(),
							telephone : $("#usernameTelephone").val()
						}, function(data) {
							if (data.bool) {
								alert("邮件已经发送到你的邮箱里面，请查收！");
							} else {
								alert("该用户名或则手机没有被注册过！");
							}
							$("#findPW").dialog("close");
						}, "json");
			});
	// 用户名
	$("#username").bind("blur", function() {
		var obj = this;
		if (Validater.isUsername($(this).val())) {
			validateSuccess(obj);
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
	// 验证码
	$("#verificationCode").bind("blur", function() {
				if ($(this).val().length == 4) {
					validateSuccess(this);
				} else {
					validateError(this, "\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e");
					submitFlag = false;
				}
			});
	// 弹出找回密码层
	$("#findPWA").bind("click", function() {
				$("#findPW").dialog("open");
			});
});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	if (submitFlag) {
		return true;
	} else {
		alert("输入的内容不正确，请检查！");
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
	var td = $(obj).parent();
	td.next().empty();
	td.after(span);
	span.append(img);
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
	var td = $(obj).parent();
	td.next().empty();
	td.after(span);
	span.append(img);
}
