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
				+ "	<input type='text' name='userVO.sellers[0].shopURL' >		 "
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
	var inputDom = $("tr .sellerTr").find("input");
	var submitFlag = true;
	inputDom.each(function() {
				if (Validater.isBlank($(this).val())) {
					submitFlag = false;
					return;
				}
			});
	if (!submitFlag) {
		alert("不能为空！");
		return false;
	}
	
}