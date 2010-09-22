$(document).ready(function() {
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

/////分页
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
	var page = $("#toPageSelect");
}
function query(page) {
	var pageCount = parseInt($("#pageCount").val());
	var queryFlag = parseInt($("#queryFlag").val());
	var platformType = $("#platformType").val();
	if (page < 1 || page > pageCount) {
		alert("页数必须在1-" + pageCount + "之间！");
		return;
	}
	window.location.href = "taskManager/task!initTask.php" + "?platformType="
			+ platformType + "&queryFlag=" + queryFlag;
}