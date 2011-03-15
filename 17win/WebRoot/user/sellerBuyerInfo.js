var submitFlag = true;
$(document).ready(function() {
			$(".classButton").button();
			$("#addBtn").bind("click", function() {
						$("#addtableDIV input[type='text']").val("");
						$("#addtableDIV input[type='text']")
								.removeClass("errorText");
						$("input[name='type'][value='1']")
								.attr("checked", true);
						$("#addtableDIV").dialog("open");

						$('.sellerClass').show();
						$('.sellerClass input select').attr("disabled", true);
						$('.buyerClass').hide();
						$('.buyerClass input select').attr("disabled", false);

					});
			intText("y1");
			intText("y2");

			$("#updateDIV").dialog({
						autoOpen : false,
						draggable : true,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 500

					});
			$("#addtableDIV").dialog({
						autoOpen : false,
						draggable : false,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 500

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
						}
					});
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
// 验证
function validateForm() {
	submitFlag = true;
	if ($("[name='type']:checked").val() == "1") {
		$(".sellerClass input[type='text']").blur();
		if (!submitFlag || Validater.isBlank($("#sellerName").val())) {
			alert("填写的资料不正确！");
			return false;
		}
	} else {
		$(".buyerClass input[type='text']").blur();
		if (!submitFlag) {
			alert("填写的资料不正确！");
			return false;
		}
	}
	return true;
}
// 获取焦点前
function beforeBlur(obj) {
	$(obj).data("nowUrl", $(obj).val());
}
// 卖家失去焦点
function obtainBuyer(obj) {
	var type = $("#platformType").val();
	if (Validater.isBlank($(obj).val())) {
		changeStyle(obj, '0', '不能为空！');
		submitFlag = false;
	} else {
		if ($("input[platformType='" + type + "'][buyerName='" + $(obj).val()
				+ "']").size() > 0) {
			changeStyle(obj, '0', '买号已经存在！');
			alert("买号已经存在！");
			return false;
		}
		changeStyle(obj, '1', '');
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

// 弹出修改
function showUpdateDIV(sellerId) {
	$("#upadteSellerId").val(sellerId);
	$("#updateDIV").dialog("open");
}