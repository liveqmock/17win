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
	if (confirm("是否要取消任务重填任务！")) {
		window.location.href = "taskManager/task!toFirstTask.php?taskId=" + id
				+ "&platformType=" + platformType;
	}
}

// 加时
function addTime(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!addTime.php?taskId=" + id
			+ "&platformType=" + platformType;
}

$(document).ready(function() {
			$("input[class='goItemButton']").click(function() {
						var val = $(this).siblings("input").val();
							window.open(val,"_blank");

					});
		});
