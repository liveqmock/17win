var submitFlag = true;
$(document).ready(function() {
	$("#releaseDIV").dialog({
				autoOpen : true,
				draggable : false,
				hide : 'slide',
				modal : true,
				resizable : false,
				show : 'slide'
			});
	$(".btnClass").button();
	$("#money").focus();

	// 价格
	$("#money").bind("blur", function() {
				var currMoney = parseFloat($("#currMoney").val());
				if (!Validater.isNumber($(this).val(), '+')) {
					changeStyle(this, '0', '不是有效的数值！');
					submitFlag = false;
				} else {
					changeStyle(this, '1', '');
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
	// 选择仓库
	$("#resultTaskRepsId").bind("change", function() {
		var id = $(this).val();
		if (!Validater.isBlank(id)) {
			// 获取用户地址
			VhostAop.divAOP.ajax(
					"taskRepositoryManager/taskRepository!obtainTaskInfo.php",
					{
						"creditTaskRepositoryVO.id" : id
					}, function(data) {
						if (!Validater.isNull(data)
								&& !Validater
										.isNull(data.creditTaskRepositoryVO)) {
							var obj = data.creditTaskRepositoryVO;
							// 价格
							$("#money").val(obj.money);
							// 地址
							$("#itemUrl").val(obj.itemUrl);
							// 掌柜
							$("input[name='creditTaskVO.sellerID'][value='"
									+ obj.sellerID + "']")
									.attr("checked", true);
							// 价格相等
							$("input[name='creditTaskVO.updatePrice'][value='"
									+ obj.updatePrice + "']").attr("checked",
									true);
							// 动态评分
							$("input[name='creditTaskVO.grade'][value='"
									+ obj.grade + "']").attr("checked", true);
							// 收货时间好评
							$("input[name='creditTaskVO.goodTimeType'][value='"
									+ obj.goodTimeType + "']").attr("checked",
									true);
							if (obj.goodTimeType == "5") {
								// 自定义小时
								$("#intervalHour").val(obj.intervalHour);
							}
							// 任务保护
							$("input[name='creditTaskVO.protect']").attr(
									"checked", obj.protect);
							// 收货地址
							$("input[name='creditTaskVO.address']").attr(
									"checked", obj.address);
							// 描述
							$("input[name='creditTaskVO.desc']").val(obj.desc);
							$("input[class='errorText']")
									.removeClass("errorText");
						} else {
							alert("系统错误，请联系管理员！");
							submitFlag = false;
						}
					}, "json");
		}
	});
	// 删除层
	$("#closeDIVBtn").bind("click", function() {
				$("#releaseDIV").hide();
			});
});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	// 验证和第一次加载都为真
	if (submitFlag) {
		var action = $("form").attr("action") + "?platformType="
				+ $("#platformType").val();
		$("form").attr("action", action);
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