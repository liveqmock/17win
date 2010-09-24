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
			window.location.href = "taskManager/task!initTask.php"
					+ "?platformType=" + platformType + "&queryType="
					+ queryType + "&page=1" + "&autoRefresh=" + $(this).val();
		} else {
			if (!Validater.isBlank($(this).data("oldValue"))) {
				var queryType = parseInt($("#queryType").val());
				var platformType = $("#platformType").val();
				window.location.href = "taskManager/task!initTask.php"
						+ "?platformType=" + platformType + "&queryType="
						+ queryType + "&page=1";
			}
		}
	});
	// 选择买号
	$("#buyerDIV").dialog({
		autoOpen : false,
		draggable : false,
		hide : 'slide',
		modal : true,
		resizable : false,
		show : 'slide',
		buttons : {
			"确定" : function() {
				var taskId = $("#currTaskId").val();;
				var platformType = $("#platformType").val();
				var buyerId = $("input[name='buyerName']:checked").val();
				window.location.href = "taskManager/task!receiveTask.php?taskId="
						+ taskId
						+ "&platformType="
						+ platformType
						+ "&buyerId=" + buyerId;
			}
		}
	});

});
// 接手任务
function receiveTask(id) {
	$("#currTaskId").val(id);
	$("#buyerDIV").dialog("open");
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
function allQuery() {
	$("#queryType").val("1");
	query(1);
}

function moneyAsc() {
	$("#queryType").val("2");
	query(1);
}

function moneyDesc() {
	$("#queryType").val("3");
	query(1);
}
function money1_40() {
	$("#queryType").val("4");
	query(1);
}
function money40_100() {
	$("#queryType").val("5");
	query(1);
}

function money100_200() {
	$("#queryType").val("6");
	query(1);
}

function money200_500() {
	$("#queryType").val("7");
	query(1);
}

function money500() {
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
	window.location.href = "taskManager/task!initTask.php" + "?platformType="
			+ platformType + "&queryType=" + queryType + "&page=" + page;
}