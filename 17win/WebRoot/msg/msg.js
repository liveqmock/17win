$(document).ready(function() {
			$("#telehpneID").focus();
			$("#showTip").text($("#contentID").val().length + "/" + 70);

			$("#contentID").keyup(function() {
						var value = $(this).val();
						if (value.length > 70) {
							$(this).val(value.substring(0, 70));
						} else {
							$("#showTip").text(value.length + "/" + 70);
						}
					});

		});

function selectSendType(obj) {
	if ($(obj).val() == "1") {
		$("#showName").text("用户ID：");
	} else {
		$("#showName").text("手机号码：");
	}
	$("#telehpneID").val("");
}

function validateForm() {
	var telphone = $("#telehpneID").val();
	var content = $("#contentID").val();
	var opertaionCode=$("#opertaionCodeID").val();
	if ($("#sendTypeId").val() == "2") {
		if (!Validater.isTelphone(telphone)) {
			alert("号码格式不正确！");
			return false;
		} 
	}
	if (Validater.isBlank(opertaionCode)) {
		alert("操作码不能为空！");
		return false;
	}
	if (Validater.isBlank(content)) {
		alert("内容不能为空！");
		return false;
	}
	if ($("#contentID").val().length > 70) {
		alert("内容必须在70个字以内！当前是：" + $("#content").val().length + "多个字！");
		return false;
	}
	$("#submitBTN").attr("disabled", true);
	return true;
}
