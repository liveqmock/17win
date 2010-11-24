function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
}

$(document).ready(function() {
			$("#money").focus();
			intText("money");
		});

function validateForm() {
	var money = $("#money").val();
	var opertationCodeId = $("#opertationCodeId").val();
	var verificationCodeID = $("#verificationCodeID").val();
	if (money != "50" && money != "100" && money != "200" && money != "300"
			&& money != "500") {
		alert("金额必须为50,100,200,300,500");
		return false; 
	}
	if (Validater.isBlank(money) || Validater.isBlank(opertationCodeId)
			|| Validater.isBlank(verificationCodeID)) {
		alert("内容不能为空！");
		return false;
	}
	$("#submitadd").attr("disabled", true);
	return true;
}
