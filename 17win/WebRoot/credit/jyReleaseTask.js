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
				+ "&platformType=" + platformType;;
	}
}

// 加时
function addTime(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!addTime.php?taskId=" + id
			+ "&platformType=" + platformType;;
}