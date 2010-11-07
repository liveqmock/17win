

$(document).ready(function() {
			// numberText("releaseDot_1");
			// //numberText("releaseDot_2");
			// numberText("releaseDot_3");
			$("form input").bind("focus", function() {
						if ($(this).attr("type") != "submit") {
							$(this).val("");
						}
					});

			$("form input").each(function() {
						if ($(this).attr("type") != "submit") {
							$(this).val("");
						}
					});
					
			intText("releaseDot_1");
			intText("releaseDot_2");
			intText("releaseDot_3");
		});

function validateForm(flag) {
	var dot = parseFloat($("#dot").val());
	var score = parseInt($("#score").val());

	if ("1" == flag) {
		var releaseDot = parseFloat($("#releaseDot_1").val());
		var operationCode = $("#operationCode_1").val();
		if (!Validater.isNumber(releaseDot)) {
			alert("发布点必须为数值！");
			return false;
		}
		if (releaseDot > dot) {
			alert("您输入的发布点数不能大于你所拥有的发布点数！");
			return false;
		}
		if (releaseDot < 10) {
			alert("发布点数量不能小于10！");
			return false;
		}
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	}

	if ("2" == flag) {
		var releaseDot = parseFloat($("#releaseDot_2").val());
		var username = $("#username").val();
		var operationCode = $("#operationCode_2").val();
		var loginUsername = $("#loginUsername").val();
		if (!Validater.isNumber(releaseDot)) {
			alert("发布点必须为数值！");
			return false;
		}
		if (releaseDot > dot) {
			alert("您输入的发布点数不能大于你所拥有的发布点数！");
			return false;
		}
		if (loginUsername == $.trim(username)) {
			alert("不能输入自己的用户名！");
			return false;
		}
		if (Validater.isBlank(username)) {
			alert("用户名不能为空！");
			return false;
		}
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	}

	if ("3" == flag) {
		var releaseDot = parseFloat($("#releaseDot_3").val());
		var operationCode = $("#operationCode_3").val();
		if (!Validater.isNumber(releaseDot)) {
			alert("发布点必须为数值！");
			return false;
		}
		if (releaseDot * 200 > score) {
			alert("您的积分不够兑换" + releaseDot + "个发布点,只能兑换"+score/200+"个发布点！");
			return false;
		}
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;

	}
}
