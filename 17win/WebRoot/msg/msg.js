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

function validateForm() {
	var telphone = $("#telehpneID").val();
	var content = $("#contentID").val();
	if (!Validater.isTelphone(telphone)) {
		alert("号码格式不正确！");
		return false;
	}
	if ($("#contentID").val().length > 70) {
		alert("内容必须在70个字以内！当前是：" + $("#content").val().length + "多个字！");
		return false;
	}
 	$("#submitBTN").attr("disabled",true);
	return true;
}
