var submitFlag = true;
$(document).ready(function() {
	// 密码
	$("#password").bind("blur", function() {
		if (Validater.isBlank($(this).val())) {
			return;
		}
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
	// 以前的操作码
	$("#oldPpertationCode").bind("blur", function() {
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});
	// 操作码
	$("#opertationCode").bind("blur", function() {
		if (Validater.isBlank($(this).val())) {
			return;
		}
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
