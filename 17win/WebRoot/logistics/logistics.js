$(document).ready(function() {

		});
function validateForm() {

	var expressCompany = $("input[name='otherExpress']").val();
	var waybill = $("#waybill").val();

	var fhdz = $("#fhdz").val();
	var fhyb = $("#fhyb").val();
	var fhlxr = $("#fhlxr").val();
	var fhdh = $("#fhdh").val();

	var shdz = $("#shdz").val();
	var shyb = $("#shyb").val();
	var shlxr = $("#shlxr").val();
	var shdh = $("#shdh").val();

	var startDate = $("#sendDate").val();
	var endDate = $("#arrivalDate").val();

	if ($("select[name='logisticsVO.logistics.expressCompany']").val() == "-1"
			&& Validater.isBlank(expressCompany)) {
		alert("物流公司不能为空");
		return false;
	}
	if (Validater.isBlank(waybill)) {
		alert("运货单号不能为空");
		return false;
	}

	if (Validater.isBlank(startDate)) {
		alert("发货时间不能为空");
		return false;
	}
	if (!Validater.isBlank(endDate)) {
		if (Validater.compareDate(startDate, endDate)) {
			alert("预计到达日期不能早于发货日期！");
			return false;
		}
	}

	// /
	if (Validater.isBlank(fhdz)) {
		alert("发货地址不能为空");
		return false;
	}
	if (Validater.isBlank(fhyb)) {
		alert("发货邮编不能为空");
		return false;
	}
	if (Validater.isBlank(fhlxr)) {
		alert("发货联系人不能为空");
		return false;
	}
	if (Validater.isBlank(fhdh)) {
		alert("发货联系人电话不能为空");
		return false;
	}
	// /

	// /
	if (Validater.isBlank(shdz)) {
		alert("收货地址不能为空");
		return false;
	}
	if (Validater.isBlank(shyb)) {
		alert("收货邮编不能为空");
		return false;
	}
	if (Validater.isBlank(shlxr)) {
		alert("收货联系人不能为空");
		return false;
	}
	if (Validater.isBlank(shdh)) {
		alert("收货电话不能为空");
		return false;
	}
	// /
	var releaseInfo = fhdz + " " + fhyb + " " + fhlxr + " " + fhdh;
	var receieveInfo = shdz + " " + shyb + " " + shlxr + " " + shdh;
	if (releaseInfo.length > 255) {
		alert("发货信息（发货地址+邮编+联系人+电话）长度不能大于255，一般发货信息不会大于这个多，如果确实大于请和客户联系！");
		return false
	}
	if (receieveInfo.length > 255) {
		alert("收货信息（收货地址 +邮编+联系人+电话）长度不能大于255，一般发货信息不会大于这个多，如果确实大于请和客户联系！");
		return false
	}
	$("#releaseInfo").val(releaseInfo);
	$("#receieveInfo").val(receieveInfo);
	return confirm("请认真审核您的信息，如果被人举报虚假物流信息，我们将严惩！");
}

function changeExpressCompany(obj) {
	if ($(obj).val() == "-1") {
		$("input[name='otherExpress']").attr("disabled", false);
		$("input[name='otherExpress']").show();
	} else {
		$("input[name='otherExpress']").hide();
		$("input[name='otherExpress']").attr("disabled", true);
	}
}