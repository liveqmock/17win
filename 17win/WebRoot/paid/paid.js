$(document).ready(function() {
			$("#money").focus();
			intText("money");
		});

function validateForm() {
	var money = $("#money").val();
	var opertationCodeId = $("#opertationCodeId").val();
	var verificationCodeID = $("#verificationCodeID").val();
	if (Validater.isBlank(money) || Validater.isBlank(opertationCodeId)
			|| Validater.isBlank(verificationCodeID)) {
		alert("内容不能为空！");
		return false;
	}
	$("#submitadd").attr("disabled",true);
	return true;
}
