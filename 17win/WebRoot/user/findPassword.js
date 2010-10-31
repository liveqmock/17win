var submitFlag = true;
$(document).ready(function() {
			$("#password").focus();

		});
function validateForm() {
	var password = $("#password").val();
	var rePassword = $("#rePassword").val();
	if (Validater.isPassword(password)) {
		if (password == rePassword) {
			return true;
		} else {
			alert("两次密码不相等！");
			return false;
		}

	} else {
		alert("输入的密码不正确！");
		return false;
	}

}
