$(document).ready(function() {
	$("#code").focus();
});

function validateForm() {
	// 淘宝卖家是否有账号
	if (Validater.isPassword($("#code").val())) {
		return true;
	} else {
		alert("您输入的操作码格式不正确！");
		return false;
	}
}
