$(document).ready(function() {
			intText("monthCount");

			$("#renewalVipButton").click(function() {
						if (validateForm()) {
							$("form").attr("action",
									"vipManager/vip!renewalVip.php");
							$("form").submit();
						}
					});

			$("#buyVipButton").click(function() {
						if (validateForm()) {
							$("form").attr("action",
									"vipManager/vip!buyVip.php");
							$("form").submit();
						}
					});
		});

function validateForm() {
	var month = $("#monthCount").val();
	var code = $("#operationCodeId").val();
	if (Validater.isBlank(month) || Validater.isBlank(code)) {
		alert("内容不能为空！");
		return false;
	}
	if(!Validater.isInt(month,"0+")){
		$("#monthCount").val("");
		alert("月份必须为数字！");
		return false;
	}
	return true;
}
