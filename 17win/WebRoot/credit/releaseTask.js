var submitFlag = false;
$(document).ready(function() {
	$("#money").focus();
	// 价格
	$("#money").bind("blur", function() {
				var currMoney = parseFloat($("#currMoney").val());
				if (!Validater.isNumber($(this).val(), '+')) {
					changeStyle(this, '0', '不是有效的数值！');
					submitFlag = false;
				} else {
					changeStyle(this, '1', '');
					submitFlag = true;
				}
			});

	// 商品地址
	$("#itemUrl").bind("focus", function() {
				$(obj).data("nowUrl", $(obj).val());
			});
	$("#itemUrl").bind("blur", function() {
		var obj = this;
		var platformType = $("#platformType").val();
		// 去掉空格
		$(obj).val($.trim($(obj).val()));
		// 获取seller input
		if (Validater.isBlank($(obj).val())) {
			changeStyle(obj, '0', '您输入的地址不能为空！');
			submitFlag = false;
			return;
		}
		if ($(obj).data("nowUrl") == $(obj).val()) {
			submitFlag = true;
			return;
		}
		// 获取用户地址
		VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSeller.php", {
					url : $(obj).val(),
					type : platformType
				}, function(data) {
					if (data.seller == null || data.seller == "") {
						changeStyle(obj, '0', '您输入的地址不正确！');
					} else {
						var flag = false;
						var selectRadio = null;
						$("input[name='creditTaskVO.sellerID']").each(
								function() {
									if ($.trim($(this).next("label").text()) == data.seller) {
										flag = true;
										selectRadio = $(this);
										return;
									}
								});
						if (flag) {
							changeStyle(obj, '1', '');
							selectRadio.attr("checked", true);
							$(obj).data("nowUrl", $(obj).val());
							submitFlag = true;
						} else {
							changeStyle(obj, '0', '输入的地址和您的卖家账号不相同！');
							submitFlag = false;
						}
					}
				}, "json");
	});
	// 自定义时间单选框
	$("input[name='creditTaskVO.goodTimeType']").bind("click", function() {
				if ($(this).val() == "5") {
					$("#intervalHour").val("");
					$("#intervalHour").attr("disabled", false);
				} else {
					$("#intervalHour").val("");
					$("#intervalHour").attr("disabled", true);
				}
			});
	// 自定义时间
	intText("intervalHour");

	// 仓库
	$("#respository").bind("click", function() {
				if ($(this).attr("checked")) {
					$("#respositoryName").val("");
					$("#respositoryName").show();
				} else {
					$("#respositoryName").hide();
				}
			});
});
function validateForm() {
	// 验证和第一次加载都为真
	if (submitFlag) {
		return true;
	} else {
		alert("您填写的信息不正确，请检查！");
		return false;
	}
}

// 改变样式
function changeStyle(obj, flag, msg) {
	if ("0" == flag) {
		$(obj).addClass("errorText");
	} else {
		$(obj).removeClass("errorText");
	}
	$(obj).attr("title", msg);
}