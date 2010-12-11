$(document).ready(function() {
			if (!Validater.isBlank($("#toUsername").val())) {
				$("#toUsername").attr("readonly", true);
				$("#title").focus();
			} else {
				$("#toUsername").focus();
			}

			$("#showTip").text($("#content").val().length + "/" + 200);

			$("#content").keyup(function() {
						var value = $(this).val();
						if (value.length > 200) {
							$(this).val(value.substring(0, 200));
						} else {
							$("#showTip").text(value.length + "/" + 200);
						}
					});

		});

function validateForm() {
	var toUsername = $("#toUsername").val();
	var title = $("#title").val();
	var content = $("#content").val();
	if (Validater.isBlank(toUsername) || Validater.isBlank(title)
			|| Validater.isBlank(content)) {
		alert("内容不能为空！");
		return false;
	}
	if ($("#content").val().length > 200) {
		alert("内容必须在200个字以内！当前是：" + $("#content").val().length + "多个字！");
		return false;
	}
	$("#submitBTN").attr("disabled", true);
	return true;
}
