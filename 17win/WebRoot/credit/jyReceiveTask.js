// 付款
function payMoney(id) {
	var platformType = $("#platformType").val();
	if (confirm("确认已经已经付款！")) {
		window.location.href = "taskManager/task!payMoney.php?taskId=" + id
				+ "&platformType=" + platformType;
	}
}
// 退出
function quitTask(id) {
	var platformType = $("#platformType").val();
	if (confirm("是否退出任务！")) {
		window.location.href = "taskManager/task!quitTask.php?taskId=" + id
				+ "&platformType=" + platformType;;
	}
}