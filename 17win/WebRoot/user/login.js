var submitFlag = true;
$(document).ready(function() {
	$("#username").focus();
	$("#verificationCode").val("");
	$("#verificationCodeID").click();
	$("#loginBtn").button();
	// 用户名
	$("#username").bind("blur", function() {
				var obj = this;
				if (Validater.isName($(this).val(), 4, 12)) {
					validateSuccess(obj);
				} else {
					validateError(this, "用户名必须4-12个字符的字母、汉字、数字、下划线！");
					submitFlag = false;
				}
			});
	// 密码
	$("#password").bind("blur", function() {
				if (Validater.isPassword($(this).val())) {
					validateSuccess(this);
				} else {
					validateError(this, "登录时需要使用密码，可以是6至20位字符 ");
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
