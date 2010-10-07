$(document).ready(function() {
			if (!Validater.isBlank($("#toUsername").val())) {
				$("#toUsername").attr("readonly", true);
				$("#title").focus();
			} else {
				$("#toUsername").focus();
			}

			$("#showTip").text($("#content").val().length + "/" + 200);

			$("#content").keydown(function() {
						var value = $(this).val()+1;
						$("#showTip").text(value.length + "/" + 200);
						if (value.length > 200) {
							alert(value.substring(0, 200));
							$(this).val(value.substring(0, 200));
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

	return true;
}
