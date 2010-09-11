$(document).ready(function() {
	// 卖号按钮
	$("input[sellerFlag]").bind("click", function() {
		var type = $(this).attr("sellerFlag");
		var tbody = $("#sellerTable" + type);
		var trStr = "<tr class='sellerTr'>"
				+ "		<td   align='center'  > "
				+ "  <input type='hidden' name='userVO.sellers[0].type' value='"
				+ type
				+ "' />  "
				+ "	<input type='text' name='userVO.sellers[0].shopURL'  onfocus=\"beforeBlur(this)\"     onblur=\"obtainSeller('"
				+ type
				+ "',this)\" >		 "
				+ "		</td>  "
				+ "		<td  class='address'   nowrap='nowrap' align='center' >"
				+ "	</td> "
				+ "		<td  align='center'  > "
				+ "		 <input type='text' name='userVO.sellers[0].name' readonly='readonly'>	  "
				+ "			</td>  "
				+ "			<td   align='center'  >    "
				+ "			<a href=\"javascript:void(0)\" onclick=\"deleteSeller(this"
				+ ")\" >删除</a>			</td> " + "		</tr> ";
		var tr = $(trStr);
		var td = tr.children(".address"); 
		td.append("省： ");
		var selectP = $("#tempProvince").clone();
		selectP.show();
		selectP.attr("name", "userVO.sellers[0].province.id");
		selectP.bind("change", function() {
					selectCity(this)
				});
		td.append(selectP);
		td
				.append(" 市： <select   name='userVO.sellers[0].city.id'><option value=''>请选择</option></select>");
	//	td
	//			.append(" 县： <select name='userVO.sellers[0].area.id' ><option value=''>请选择</option></select>");
		tbody.append(tr);

	});
	// 买号按钮
	$("input[buyerFlag]").bind("click", function() {
		var type = $(this).attr("buyerFlag");
		var tbody = $("#buyerTable" + type);
		var trStr = "<tr class='buyerTr'> "
				+ "	<td  align='center'  >  "
				+ " <input type='hidden' name='userVO.buyers[0].type' value='"
				+ type
				+ "' /> "
				+ "		 <input type='text'  onblur=\"obtainBuyer(this)\" name='userVO.buyers[0].name' >  "
				+ "    </td>  " + "	<td align='center' >  " + "		0 "
				+ "		</td> " + "	<td   align='center' >  "
				+ "		<a href=\"javascript:void(0)\" onclick=\"deleteBuyer(this"
				+ ")\" >删除</a>			</td> " + "		</tr> ";
		var tr = $(trStr);
		tbody.append(trStr);
	});
});
// 删除seller
function deleteSeller(obj) {
	$(obj).parent().parent().remove();
}
// 删除buyer
function deleteBuyer(obj) {
	$(obj).parent().parent().remove();
}
// 选择市
function selectCity(obj) {
	getCities($(obj).val(), $(obj).next().get(0), $(obj).next().next().get(0));
}
// 选择县区
//function selectArea(obj) {
//	getAreas($(obj).val(), $(obj).next().get(0));
//}
// 验证
function validateForm() {
	var inputSellerDom = $("tr .sellerTr").find("input");
	var inputBuyerDom = $("tr .buyerTr").find("input");
	var submitFlag = true;
	inputSellerDom.each(function() {
				if (Validater.isBlank($(this).val())) {
					changeStyle(this, '0', '不能为空！');
					submitFlag = false;
				} else {
					changeStyle(this, '1', '');
				}
			});
	inputBuyerDom.each(function() {
				if (Validater.isBlank($(this).val())) {
					changeStyle(this, '0', '不能为空！');
					submitFlag = false;
				} else {
					changeStyle(this, '1', '');
				}
			});
	if (!submitFlag) {
		alert("数据不能为空，不需要的数据可以删除！");
		return false;
	} else {
		// 重新把数据关系拉上。
		var tr = $("tr .sellerTr");
		var i = 0;
		tr.each(function() {
					var inputs = $(this).find("input,select");
					inputs.each(function() {
								var name = $(this).attr("name");
								name = name.replace("0", i + "");
								$(this).attr("name", name);
							});
					i++;
				});
		tr = $("tr .buyerTr");
		i = 0
		tr.each(function() {
					var inputs = $(this).find("input,select");
					inputs.each(function() {
								var name = $(this).attr("name");
								name = name.replace("0", i + "");
								$(this).attr("name", name);
							});
					i++;
				});

		return true;
	}

}
// 获取焦点前
function beforeBlur(obj) {
	$(obj).data("nowUrl", $(obj).val());
}
// 买家失去焦点
function obtainBuyer(obj) {
	if (Validater.isBlank($(obj).val())) {
		changeStyle(obj, '0', '不能为空！');
	} else {
		changeStyle(obj, '1', '');
	}
}
// 根据店铺地址获取到卖号
function obtainSeller(type, obj) {
	// 去掉空格
	$(obj).val($.trim($(obj).val()));
	// 获取seller input
	var input = $(obj).parent().next().next().children("input");
	if (Validater.isBlank($(obj).val())) {
		changeStyle(obj, '0', '您输入的地址不正确！');
		return;
	}
	if ($(obj).data("nowUrl") == $(obj).val()) {
		return;
	}
	// 获取用户地址
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSeller.php", {
				url : $(obj).val(),
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					changeStyle(obj, '0', '您输入的地址不正确！');
				} else {
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