function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
}

$(document).ready(function() {
			$("#passwordID").focus();
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