$(document).ready(function() {

		});
function validateForm() {
	var expressCompany = $("#expressCompany").val();
	var waybill = $("#waybill").val();
	var releaseInfo = $("#releaseInfo").val();
	var receieveInfo = $("#receieveInfo").val();
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
	if (Validater.isBlank(releaseInfo)) {
		alert("发货信息不能为空");
		return false;
	}
	if (Validater.isBlank(receieveInfo)) {
		alert("收货信息不能为空");
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
	return true;

}
