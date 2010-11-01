var submitFlag = true;
$(document).ready(function() {
			$("#addBtn").button();
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
						width : 400,
						buttons : {
							"保存" : function() {
								$(this).attr("disabled", true);
								$("#updatewForm").submit();
							}
						}
					});
			$("#addtableDIV").dialog({
						autoOpen : false,
						draggable : false,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 500,
						buttons : {
							"保存" : function() {
								if (validateForm()) {
									$(this).attr("disabled", true);
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
						}
					});

			$("#shopURL").blur(function() {
						obtainSellerByShop($("#platformType").val(), this);

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
// 根据店铺地址获取到卖号
function obtainSellerByShop(type, obj) {
	// 去掉空格
	$(obj).val($.trim($(obj).val()));
	var shopURL = $(obj).val();
	// 获取seller input
	var input = $("#sellerName");
	if (!Validater.isShop(shopURL, type)) {
		submitFlag = false;
		changeStyle(obj, '0', '您输入的格式不地址格式不正确，最好复制在浏览器地址栏里面复制后粘贴,如还有疑问，请联系客户！');
		return;
	}
	if ($(obj).data("nowUrl") == shopURL) {
		return;
	}
	if ($("input[platformType='" + type + "'][shopUrl='" + shopURL + "']")
			.size() > 0) {
		changeStyle(obj, '0', '已经存在该店铺！');
		alert("已经存在该店铺！");
		submitFlag = false;
		return;
	}
	$("#huoquUser").show();
	$("#huoquUser").text("(获取中...)");
	// 获取用户地址
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSellerByShop.php", {
				url : $(obj).val(),
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					submitFlag = false;
					changeStyle(obj, '0', '您输入的地址不正确！');
					$("#huoquUser").text("(失败)");

				} else {
					if ($("input[platformType='" + type + "'][sellerName='"
							+ data.seller + "']").size() > 0) {
						submitFlag = false;
						changeStyle(obj, '0', '改店铺已经存在！');
						alert("已经存在该店铺！");
						$("#huoquUser").text("(失败)");
					}
					changeStyle(obj, '1', '该地址可以使用！');
					$(obj).data("nowUrl", $(obj).val());
					input.val(data.seller);
					$("#huoquUser").text("");
					$("#huoquUser").hide();
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

// 弹出修改
function showUpdateDIV(sellerId) {
	$("#upadteSellerId").val(sellerId);
	$("#updateDIV").dialog("open");
}