$(document).ready(function() {

		});
function validateForm() {
	var username = $("#username").val();
	if (!Validater.isName(username, 4, 12)) {
		alert("用户名必须4-12个字符的字母、汉字、数字、下划线！");
		return false;
	}
	return true;
}
