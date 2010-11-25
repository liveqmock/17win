$(document).ready(function() {
	$("#myTable").tablesorter();
	$("#updateMoneyDIV").dialog({
		autoOpen : false,
		draggable : true,
		hide : 'slide',
		modal : true,
		resizable : false,
		show : 'slide',
		width : 400,
		buttons : {
			"保存" : function() {
				if (Validater.isBlank($("#moneyId").val())
						|| isNaN($("#moneyId").val())) {
					alert("金额格式不对");
				} else {
					$("#moneyForm").submit();
				}
			}
		}
	});
});

// 删除

function deleteMoney(id) {
	if (confirm("确认是否删除？")) {
		window.location.href = "adminPaidManager/adminPaid!deleteMoney.php?payId="
				+ id;

	}
}

// 充值
function addMoney(id) {
	if (confirm("确认是否充值？")) {
		window.location.href = "adminPaidManager/adminPaid!addMoney.php?payId="
				+ id;
	}
}
// 浏览
function brower(id, read) {
	if (!read) {
		$.post("smsManager/sms!updateSms.php?smsVO.id=" + id);
	}
	$("#title").val($("#a_" + id).attr("title"));
	$("#content").text($("#a_" + id).attr("content"));
	$("#browerSms").dialog("open");

}
// 回复
function reply(fromUsername) {
	window.open("smsManager/sms!initSendSms.php?toUser=" + fromUsername,
			"_blank");
}
function validateForm() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (Validater.compareDate(startDate, endDate)) {
		alert("【结束时间】必须大于等于【开始时间】！");
		return false;
	}
	return true;
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
