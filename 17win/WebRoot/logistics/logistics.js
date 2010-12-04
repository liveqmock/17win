$(document).ready(function() {

		});
function validateForm() {
	var expressCompany = $("#expressCompany").val();
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

	if (Validater.isBlank(expressCompany)) {
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

	$("#releaseInfo").val(fhdz + " " + fhyb + " " + fhlxr + " " + fhdh);
	$("#receieveInfo").val(shdz + " " + shyb + " " + shlxr + " " + shdh);

	return confirm("请认真审核您的信息，如果被人举报虚假物流信息，我们将严惩！");

}
