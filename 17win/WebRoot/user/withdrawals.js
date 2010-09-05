$(document).ready(function() {
			$("#tabs").tabs();
			$(".buttonFlag").button();

			// 验证淘宝
			$("#realIdentity_1").bind("blur", function() {
						obtainSellerByShop($(this).val(), "realname_1");
					});
		});

function validateForm(flag) {
	var money = parseFloat($("#money").val());
	var moneyTO = parseFloat($("#money_" + flag).val());
	var operationCode = $("#operationCode_" + flag).val();
	var realIdentity = $("#realIdentity_" + flag).val();
	var realName = $("#realName_" + flag).val();
	if (!Validater.isNumber(moneyTO, '+')) {
		alert("金额必须为大于0的数值！");
		return false;
	}
	if (moneyTO < 100) {
		alert("您输入的金额不能小于100,如果想提现小于100的金额，请以任务的方式发布！");
		return false;
	}
	if (moneyTO > money) {
		alert("您输入的金额大于你拥有的金额,你只拥有" + money + "元！");
		return false;
	}
	if (Validater.isBlank(operationCode)) {
		alert("操作码不能为空！");
		return false;
	}
	if ("1" == flag) {
		if (Validater.isBlank(realIdentity)) {
			alert("商品地址不能为空");
			return false;
		}
		if (Validater.isBlank(realName)) {
			alert("掌柜不能为空");
			return false;
		}
	} else if ("2" == flag) {
		if (Validater.isBlank(realIdentity)) {
			alert("支付宝账号不能为空");
			return false;
		}
		if (Validater.isBlank(realName)) {
			alert("真实名字不能为空");
			return false;
		}
	} else if ("3" == flag) {
		if (Validater.isBlank(realIdentity)) {
			alert("财付通账号不能为空");
			return false;
		}
		if (Validater.isBlank(realName)) {
			alert("真实名字不能为空");
			return false;
		}
	}
	return true;
}