$(document).ready(function() {
			intText("monthCount");
		});

function validateForm() {
	var month = $("#monthCount").val();
	var code = $("#operationCodeId").val();
	if (Validater.isBlank(month) || Validater.isBlank(code)) {
		alert("内容部能为空！");
		return false;
	}
	return true;
}