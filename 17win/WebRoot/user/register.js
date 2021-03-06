var submitFlag = true;
var timer;
$(document).ready(function() {
			timer = setInterval(changeSutmitBtn, 1000);
			$("#username").focus();
			$("#verificationCodeID").click();
			intText("qq");
			intText("telephone");
			// 用户名
			$("#username").bind("focus", function() {
						$(this).data("nowData", $(this).val());
					});
			// 用户名
			$("#username").bind("blur", function() {
						var obj = this;
						if (Validater.isName($(this).val(), 4, 12)) {
							// 去掉空格
							$(obj).val($.trim($(obj).val()));
							if ($(obj).data("nowData") == $(obj).val()) {
								return;
							}
							VhostAop.divAOP.ajax(
									"ajaxManager/ajax!userExists.php", {
										username : $(obj).val()
									}, function(data) {
										if (data.bool) {
											validateSuccess(obj);
										} else {
											validateError(obj, "用户名已经存在");
											submitFlag = false;
										}
									});

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
							validateError(this, "密码格式不正确，必须为6至20位字符！");
							submitFlag = false;
						}
					});
			// 确认密码密码
			$("#rePassword").bind("blur", function() {
						if ($(this).val() != $("#password").val()) {
							validateError(this, "两次密码不相等！");
							submitFlag = false;
						} else {
							validateSuccess(this);
						}
					});
			// 操作码
			$("#opertationCode").bind("blur", function() {
						if (Validater.isPassword($(this).val())) {
							if ($(this).val() == $("#rePassword").val()) {
								validateError(this, "操作码不能和密码相同！");
								submitFlag = false;
							} else {
								validateSuccess(this);
							}
						} else {
							validateError(this, "操作码格式不正确，必须为6至20位字符！");
							submitFlag = false;
						}
					});
			// 确认操作码
			$("#reOperationCode").bind("blur", function() {
						if ($(this).val() != $("#opertationCode").val()) {
							validateError(this, "两次操作码不相等！");
							submitFlag = false;
						} else {
							validateSuccess(this);
						}
					});

			// QQ
			$("#qq").bind("blur", function() {
						if (Validater.isQQ($(this).val())) {
							validateSuccess(this);
						} else {
							validateError(this, "QQ号码格式不正确！");
							submitFlag = false;
						}
					});
			// email
			$("#email").bind("focus", function() {
						$(this).data("nowData", $(this).val());
					});
			// email
			$("#email").bind("blur", function() {
						var obj = this;
						if (Validater.isEmail($(this).val())) {
							// 去掉空格
							$(obj).val($.trim($(obj).val()));
							// 获取seller input
							if ($(obj).data("nowData") == $(obj).val()) {
								return;
							}
							VhostAop.divAOP.ajax(
									"ajaxManager/ajax!emailExists.php", {
										email : $(obj).val()
									}, function(data) {
										if (data.bool) {
											validateSuccess(obj);
										} else {
											validateError(obj, "该邮箱已经注册过！");
											submitFlag = false;
										}
									});

						} else {
							validateError(this, "邮箱格式不正确！");
							submitFlag = false;
						}
					});
			// 手机
			$("#telephone").bind("focus", function() {
						$(this).data("nowData", $(this).val());
					});
			// 手机号码
			$("#telephone").bind("blur", function() {
						var obj = this;
						if (Validater.isTelphone($(this).val())) {
							// 去掉空格
							$(obj).val($.trim($(obj).val()));
							if ($(obj).data("nowData") == $(obj).val()) {
								return;
							}
							VhostAop.divAOP.ajax(
									"ajaxManager/ajax!phoneExists.php", {
										telephone : $(obj).val()
									}, function(data) {
										if (data.bool) {
											validateSuccess(obj);
										} else {
											validateError(obj, "该手机号码已经注册过！");
											submitFlag = false;
										}
									});
						} else {
							validateError(this, "手机号码格式不正确！");
							submitFlag = false;
						}
					});
		});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	if (submitFlag) {
		$("#sumbitBtn").attr("disabled", true);
		return true;
	} else {
		alert("您填写的资料不正确！");
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
				title : "验证成功"
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
	$("#sumbitBtn").val("同意协议并注册(" + time + ")");
	if (time == 0) {
		$("#sumbitBtn").attr("disabled", false);
		clearInterval(timer);
	}
}
