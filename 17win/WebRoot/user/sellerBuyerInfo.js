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
				+ "	<input type='text' name='userVO.sellers[0].shopURL'  onblur=\"obtainSeller('"
				+ type
				+ "',this)\" >		 "
				+ "		</td>  "
				+ "		<td  class='address' nowrap='nowrap' align='center' >"
				+ "	</td> "
				+ "		<td  align='center'  > "
				+ "		 <input type='text' name='userVO.sellers[0].name'>	  "
				+ "			</td>  "
				+ "			<td   align='center'  >    "
				+ "			<a href=\"javascript:void(0)\" onclick=\"deleteSeller(this"
				+ ")\" >删除</a>			</td> " + "		</tr> ";
		var tr = $(trStr);
		var td = tr.children(".address");
		td.append("省：");
		var selectP = $("#tempProvince").clone();
		selectP.show();
		selectP.attr("name", "userVO.sellers[0].province.id");
		selectP.attr("onchange", "selectCity(this)");
		td.append(selectP);
		td
				.append("市：<select onchange='selectArea(this)' name='userVO.sellers[0].city.id'><option>请选择</option></select>");
		td
				.append("县：<select name='userVO.sellers[0].area.id' ><option>请选择</option></select>");
		tbody.append(tr);

	});
	// 买号按钮
	$("input[buyerFlag]").bind("click", function() {
		var type = $(this).attr("buyerFlag");
		var tbody = $("#buyerTable" + type);
		var trStr = "<tr class='buyerTr'> " + "	<td  align='center'  >  "
				+ " <input type='hidden' name='userVO.buyers[0].type' value='"
				+ type + "' /> "
				+ "		 <input type='text'  name='userVO.buyers[0].name' >  "
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
function selectArea(obj) {
	getAreas($(obj).val(), $(obj).next().get(0));
}
// 验证
function validateForm() {
	var inputSellerDom = $("tr .sellerTr").find("input");
	var inputBuyerDom = $("tr .buyerTr").find("input");
	var submitFlag = true;
	inputSellerDom.each(function() {
				if (Validater.isBlank($(this).val())) {
					changeStyle(this, '0');
					submitFlag = false;
				} else {
					changeStyle(this, '1');
				}
			});
	inputBuyerDom.each(function() {
				if (Validater.isBlank($(this).val())) {
					changeStyle(this, '0');
					submitFlag = false;
				} else {
					changeStyle(this, '1');
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
				});
		tr = $("tr .buyerTr");
		tr.each(function() {
					var inputs = $(this).find("input,select");
					inputs.each(function() {
								var name = $(this).attr("name");
								name = name.replace("0", i + "");
								$(this).attr("name", name);
							});
				});
		return true;
	}
	
}
// 根据店铺地址获取到卖号
function obtainSeller(type, obj) {
	var input = $(obj).parent().next().next().children("input");
	// 获取用户地址
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSeller.php", {
				url : $(obj).val(),
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					changeStyle(this, '0');
					alert("您输入的地址不正确！");
				} else {
					changeStyle(this, '1');
					input.val(data.seller);
				}
			}, "json");
}
// 改变样式
function changeStyle(obj, flag) {
	if ("0" == flag) {
		$(obj).css("borderColor", "red");
		$(obj).attr("title", "错误");
	} else {
		$(obj).css("borderColor", "white");
	}
}