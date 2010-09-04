

$(document).ready(function() {

			if (!Validater.isNull($("#showDIV").get(0))) {
				$("#showDIV").dialog({
							autoOpen : true,
							draggable : false,
							hide : 'slide',
							modal : true,
							resizable : false,
							show : 'slide'
						});
			}
		});

function validateForm(flag) {

	if ("1" == flag) {
		var dot = parseFloat($("#dot").val());
		if (!confirm('您确定要购买一钻卡吗？')) {
			return false;
		}
		var releaseDot = parseFloat($("#releaseDot").val());
		var operationCode = $("#operationCode_1").val();
		if (!Validater.isInt(releaseDot)) {
			alert("发布点数量必须为整数！");
			return false;
		}
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	} else if ("2" == flag) {
		if (!confirm('您确定要购买皇冠卡吗？')) {
			return false;
		}
		var operationCode = $("#operationCode_2").val();
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	} else if ("3" == flag) {
		if (!confirm('您确定要购买双钻卡吗？')) {
			return false;
		}
		var operationCode = $("#operationCode_3").val();
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	} else if ("4" == flag) {
		if (!confirm('您确定要购买一钻卡吗？')) {
			return false;
		}
		var operationCode = $("#operationCode_4").val();
		if (Validater.isBlank(operationCode)) {
			alert("操作码不能为空！");
			return false;
		}
		return true;
	}
}
