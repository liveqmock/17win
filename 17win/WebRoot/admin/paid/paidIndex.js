$(document).ready(function() {
	$("#myTable").tablesorter({
				headers : {
					4 : {
						sorter : "dateFormat"
					}
				}
			});
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

// ///分页
function firstPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	query(1);
}
function prevPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	query(nowPage - 1);
}
function nextPage() {
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	query(nowPage + 1);
}
function lastPage() {
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	query(pageCount);
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	query(page);
}

function query(page) {
	$("#nowPage").val(page);
	$("form").submit();
}
