$(document).ready(function() {
			$("#myTable").tablesorter();
		});
function validateForm() {
	var startValue = $("#startMoney").val();
	var endValue = $("#endMoney").val();
	var startDate = $("input[name='logisticsVO.startDate']").val();
	var endDate = $("input[name='logisticsVO.endDate']").val();
	if (isNaN(startValue) || isNaN(endValue)) {
		alert("必须输入数值！");
		return false;
	}
	if (parseInt(startValue) > parseInt(endValue)) {
		alert("【结束金额】必须大于等于【开始金额】！");
		return false;
	}
	if (Validater.compareDate(startDate, endDate)) {
		alert("【结束时间】必须大于等于【开始时间】！");
		return false;
	}

	return true;
}

function toUse(id) {
	if (confirm("确认是否使用该物流信息！")) {
		window.location.href = "logisticsManager/logistics!useLogistics.php?logisticsID="+
		id;
	}
}

// ///分页
function firstPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(1);
	if (validateForm()) {
		$("form").submit();
	}
}
function prevPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(nowPage - 1);
	if (validateForm()) {
		$("form").submit();
	}
}
function nextPage() {
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	$("#nowPage").val(nowPage + 1);
	if (validateForm()) {
		$("form").submit();
	}
}
function lastPage() {
	var page = parseInt($("#pageCount").val());
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	$("#nowPage").val(page);
	if (validateForm()) {
		$("form").submit();
	}
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	$("#nowPage").val(page);
	if (validateForm()) {
		$("form").submit();
	}
}
