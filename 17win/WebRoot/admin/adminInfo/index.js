$(document).ready(function() {
			$("#passwordID").focus();
			$("#verificationCodeID").click();
		});

function validateform() {
	var password = $("#passwordID").val();
	var repassword = $("#repasswordID").val();
	var code = $("#codeID").val();
	if (!Validater.isPassword(password)) {
		alert("密码必须为6-12的字符！");
		return false;
	}
	if (password != repassword) {
		alert("两次密码不正确！");
		return false;
	}
	if (Validater.isBlank(code)) {
		alert("操作码不能为空！");
		return false;
	}
	return true;

}