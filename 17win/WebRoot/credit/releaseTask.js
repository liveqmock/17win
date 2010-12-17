var submitFlag = true;
$(document).ready(function() {
	intText("addtionMoneyId");
	intText("addtionReleaseDotId");

	// 进入任务仓库
	$("#goTaskRe").click(function() {
		var platformType = $("#platformType").val();
		window.location.href = "taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
				+ platformType;
	});
	// 判断是否取消任务
	var cancelTaskId = $("#cancelFlagTaskIdHidden").val();
	if (!Validater.isBlank(cancelTaskId)) {
		// 评论时间
		var cancelGTT = $("#cancelFlagGTTHidden").val();
		// 是否改价
		var cancelUP = $("#cancelFlagUPHidden").val();
		// 评价类型
		var cancelGradde = $("#cancelFlagGradeHidden").val();

		$("input[name='creditTaskVO.updatePrice'][value='" + cancelUP + "']")
				.attr("checked", true);
		$("input[name='creditTaskVO.goodTimeType'][value='" + cancelGTT + "']")
				.attr("checked", true);
		$("input[name='creditTaskVO.grade'][value='" + cancelGradde + "']")
				.attr("checked", true);
		var hour = $("#cancelFlagHourHidden").val();
		if (!Validater.isBlank(hour)) {
			$("#intervalHour").attr("disabled", false);
			$("#intervalHour").val(hour);
		}

	}

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
				$(obj).data("nowUrl", $.trim($(obj).val()));
			});
	$("#itemUrl").bind("blur", function() {
		if (typeof($(this).data("oldURL")) != "undefined"
				&& $(this).data("oldURL") == $(this).val()) {
			return;
		}
		// 如果有被选中的
		if ($("input[name='creditTaskVO.sellerID']:checked").size() > 0) {
			$("input[name='creditTaskVO.sellerID']").attr("disabled", false);
			return;
		}
		var obj = this;
		var platformType = $("#platformType").val();
		// 去掉空格
		$(obj).val($.trim($(obj).val()));
		$("#itemUrl").data("oldURL", $(obj).val());
		// 获取seller input
		if (!Validater.isItem($(obj).val(), platformType)) {
			submitFlag = false;
			alert("您输入的格式不地址格式不正确，最好复制在浏览器地址栏里面复制后粘贴,如还有疑问，请联系客户！");
			changeStyle(obj, '0',
					'您输入的格式不地址格式不正确，最好复制在浏览器地址栏里面复制后粘贴,如还有疑问，请联系客户！');
			return;
		}
		if ($(obj).data("nowUrl") == $(obj).val()) {
			return;
		}
		// 获取用户地址
		VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSellerByItem.php", {
					url : $(obj).val(),
					type : platformType
				}, function(data) {
					if (data.seller == null || data.seller == "") {
						submitFlag = false;
						alert("您输入的地址不正确！");
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
							$("input[name='creditTaskVO.sellerID']").attr(
									"disabled", false);
							changeStyle(obj, '1', '');
							selectRadio.attr("checked", true);
							$(obj).data("nowUrl", $(obj).val());
						} else {
							alert("输入的地址和您的卖家账号不相同！");
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
								$("#intervalHour").attr("disabled", false);
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

							// 附加时间
							$("#addtionMoney").val(obj.addtionMoney);
							// 附加发布点
							$("#addtionReleaseDotId")
									.val(obj.addtionReleaseDot);

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
				$("#releaseDIV").dialog("close");
			});
});
function validateForm() {
	if ($("input[name='creditTaskVO.sellerID']:checked").size() == 0) {
		alert("掌柜未选中，请检查您的商品地址！");
		return false;
	}
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