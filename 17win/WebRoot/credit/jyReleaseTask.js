$(document).ready(function() {
	intText("autoreFresh");
	$("#autoreFresh").focus(function() {
				$(this).data("oldValue", $(this).val());
				$(this).val("");
			});
	$("#autoreFresh").blur(function() {
		if (!Validater.isBlank($(this).val())) {
			var value = parseInt($(this).val());
			if (value < 5) {
				alert("刷新时间必须大于5秒");
				return;
			}
			var queryType = parseInt($("#queryType").val());
			var platformType = $("#platformType").val();
			window.location.href = "taskManager/task!initReleasedTast.php"
					+ "?platformType=" + platformType + "&queryType="
					+ queryType + "&page=1" + "&autoRefresh=" + $(this).val();
		} else {
			if (!Validater.isBlank($(this).data("oldValue"))) {
				var queryType = parseInt($("#queryType").val());
				var platformType = $("#platformType").val();
				window.location.href = "taskManager/task!initReleasedTast.php"
						+ "?platformType=" + platformType + "&queryType="
						+ queryType + "&page=1";
			}
		}
	});

	// /////
	$("input[class='goItemButton']").click(function() {
				var val = $(this).siblings("input").val();
				window.open(val, "_blank");

			});

	$("#autoreFresh").focus(function() {

			});
});

// 回复
function reply(fromUsername) {
	window.open("smsManager/sms!initSendSms.php?toUser=" + fromUsername+"&timeFlag="+new Date().getTime(),
			"_blank");
}
// 审核卖家
function clearReceiver(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!clearReceiver.php?taskId=" + id
			+ "&platformType=" + platformType;
}
// 清理买家
function audiReceiver(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!audiReceiver.php?taskId=" + id
			+ "&platformType=" + platformType;
}
// 好评
function sellerEvaluate(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!sellerEvaluate.php?taskId=" + id
			+ "&platformType=" + platformType;
}
// 发货
function dispatch(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!dispatch.php?taskId=" + id
			+ "&platformType=" + platformType;
}
// 取消重填
function cancelTask(id) {
	var platformType = $("#platformType").val();
	if (confirm("是否要取消任务重填任务！")) {
		window.location.href = "taskManager/task!cancelTask.php?taskId=" + id
				+ "&platformType=" + platformType;
	}
}
// 刷新排前
function toFirstTask(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!toFirstTask.php?taskId=" + id
			+ "&platformType=" + platformType;
}

// 加时
function addTime(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!addTime.php?taskId=" + id
			+ "&platformType=" + platformType;
}

// 旺旺
function callWW(url) {
	if (confirm("为了您和他人的安全，使用旺旺联系对方时请不要发送关于任何刷信誉的消息！")) {
		window.open(url, "_blank");
	}
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
	var page = parseInt($("#pageCount").val());
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	query(page);
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	query(page);
}

// 条件查询
function sort1() {
	$("#queryType").val("1");
	query(1);
}

function sort2() {
	$("#queryType").val("2");
	query(1);
}

function sort3() {
	$("#queryType").val("3");
	query(1);
}
function sort4() {
	$("#queryType").val("4");
	query(1);
}
function sort5() {
	$("#queryType").val("5");
	query(1);
}

function sort6() {
	$("#queryType").val("6");
	query(1);
}
function sort7() {
	$("#queryType").val("7");
	query(1);
}
function sort8() {
	$("#queryType").val("8");
	query(1);
}
// 查询
function query(page) {
	var pageCount = parseInt($("#pageCount").val());
	var queryType = parseInt($("#queryType").val());
	var platformType = $("#platformType").val();
	if (page < 1 || page > pageCount) {
		alert("页数必须在1-" + pageCount + "之间！");
		return;
	}
	window.location.href = "taskManager/task!initReleasedTast.php"
			+ "?platformType=" + platformType + "&queryType=" + queryType
			+ "&page=" + page;
}