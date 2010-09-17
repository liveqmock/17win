var submitFlag = true;
$(document).ready(function() {
			$("#addBtn").button();
			$("#addBtn").bind("click", function() {
						$("#addtableDIV").dialog("open");
						$('.sellerClass').show();
						$('.sellerClass input select').attr("disabled", true);
						$('.buyerClass').hide();
						$('.buyerClass input select').attr("disabled", false);

					});
			$("#addtableDIV").dialog({
						autoOpen : false,
						draggable : false,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 400,
						buttons : {
							"保存" : function() {
								if (validateForm()) {
									$("#addForm").submit();
								}
							}
						}
					});
			// 卖家/买号
			$("[name='type']").bind("click", function() {
						if ($(this).val() == "1") {
							$('.sellerClass').show();
							$('.buyerClass').hide();
							$('.sellerClass input select').attr("disabled",
									true);
							$('.buyerClass input select').attr("disabled",
									false);
						} else {
							$('.sellerClass').hide();
							$('.buyerClass').show();
							$('.sellerClass input select').attr("disabled",
									false);
							$('.buyerClass input select')
									.attr("disabled", true);
						}
					});

			$("[name='type']").bind("click", function() {
						if ($(this).val() == "1") {
							$('.sellerClass').show();
							$('.buyerClass').hide();
						} else {
							$('.sellerClass').hide();
							$('.buyerClass').show();
						}
					});

			$("#shopURL").blur(function() {
						obtainSeller($("#platformType").val(), this);

					}

			);

		});

// 删除seller
function deleteSeller(obj) {
	if (confirm("删除这个账号的以前完成的交易记录也会删除掉！")) {
		$(obj).parent("form").submit();
	}
}
// 删除buyer
function deleteBuyer(obj) {
	$(obj).parent().parent().remove();
}
// 选择市
function selectCity(obj) {
	if ($(obj).val() == "") {
		return;
	}
	getCities($(obj).val(), $("#cityId").get(0));
}
// 选择县区
// 验证
function validateForm() {
	if ($("[name='type']:checked").val() == "1") {
		if (Validater.isBlank($("#sellerName").val())) {
			alert("卖号不能为空！");
			return false
		}
	} else {
		if (Validater.isBlank($("#buyerName").val())) {
			alert("买号不能为空！");
			return false
		}
	}
	return true;
}
// 获取焦点前
function beforeBlur(obj) {
	$(obj).data("nowUrl", $(obj).val());
}
// 买家失去焦点
function obtainBuyer(obj) {
	if (Validater.isBlank($(obj).val())) {
		changeStyle(obj, '0', '不能为空！');
		submitFlag = false;
	} else {
		if ($("input[platformType='" + type + "'][buyerName='" + $(obj).val() + "']")
				.size() > 0) {
			changeStyle(obj, '0', '买号已经存在！');
			alert("买号已经存在！");
			return false;
		}
		changeStyle(obj, '1', '');
	}
}
// 根据店铺地址获取到卖号
function obtainSeller(type, obj) {
	// 去掉空格
	$(obj).val($.trim($(obj).val()));
	var shopURL = $(obj).val();
	// 获取seller input
	var input = $("#sellerName");
	if (Validater.isBlank(shopURL)) {
		changeStyle(obj, '0', '您输入的地址不正确！');
		return;
	}
	if ($(obj).data("nowUrl") == shopURL) {
		return;
	}
	if ($("input[platformType='" + type + "'][shopUrl='" + shopURL + "']")
			.size() > 0) {
		changeStyle(obj, '0', '已经存在该店铺！');
		alert("已经存在该店铺！");
		return false;
	}
	// 获取用户地址
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSeller.php", {
				url : $(obj).val(),
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					changeStyle(obj, '0', '您输入的地址不正确！');
					submitFlag = false;
				} else {
					if ($("input[platformType='" + type + "'][sellerName='"
							+ data.seller + "']").size() > 0) {
						changeStyle(obj, '0', '改店铺已经存在！');
						alert("已经存在该店铺！");
						return false;
					}
					changeStyle(obj, '1', '该地址可以使用！');
					$(obj).data("nowUrl", $(obj).val());
					input.val(data.seller);
					changeStyle(input.get(0), '1', '成功！');
				}
			}, "json");
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