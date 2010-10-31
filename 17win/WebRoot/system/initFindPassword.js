$(document).ready(function() {
			$("#username").focus();
		});

function validateForm() {
	// 淘宝卖家是否有账号
	if (!Validater.isBlank($("#username").val())) {
		$("#submitBtn").attr("disabled", true);
		return true;
	} else {
		alert("输入你的内容不能为空！");
		return false;
	}
}
