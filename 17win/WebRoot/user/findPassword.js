var submitFlag = true;
$(document).ready(function() {
			$("#password").focus();

		});
function validateForm() {
	var opertationCode = $("#opertationCode").val();
	var reOpertationCode = $("#reOpertationCode").val();
	var password = $("#password").val();
	var rePassword = $("#rePassword").val();
	if (Validater.isPassword(opertationCode)) {
		if (opertationCode != reOpertationCode) {
			alert("两次操作密码不相等！");
			return false;
		}
	} else {
		alert("输入的登录密码不正确！");
		return false;
	}

	if (Validater.isPassword(password)) {
		if (password != rePassword) {
			alert("两次登录密码不相等！");
			return false;
		}

	} else {
		alert("输入的登录密码不正确！");
		return false;
	}

	if (opertationCode == password) {
		alert("操作密码和登录密码不能相同！");
		return false;
	}
	return true;

}
