$(document).ready(function() {
			$("#opertationCodeId").focus();
			$("#verificationCodeID").click();
		});

function validateForm() {
	var customMoney = $("#customMoneyId").val();
	if ($("select[name='payVO.money']").val() == "-1"
			&& !Validater.isInt(customMoney, "0+")) {
		alert("金额格式不对！");
		return false;
	}
	var buyername = $("#buyernameId").val();
	var opertationCodeId = $("#opertationCodeId").val();
	var verificationCodeID = $("#verificationCodeID").val();
	if (Validater.isBlank(opertationCodeId)
			|| Validater.isBlank(verificationCodeID)
			|| Validater.isBlank(buyername)) {
		alert("内容不能为空！");
		return false;
	}

	$("#submitadd").attr("disabled", true);
	return true;
}

function changeCustomMoney(obj) {
	if ($(obj).val() == "-1") {
		$("#customMoneyId").attr("disabled", false);
		$("#customMoneyId").show();
	} else {
		$("#customMoneyId").hide();
		$("#customMoneyId").attr("disabled", true);
	}
}