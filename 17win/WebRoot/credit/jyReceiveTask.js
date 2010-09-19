// 好评
function buyerEvaluate(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!buyerEvaluate.php?taskId=" + id
			+ "&platformType=" + platformType;
}
// 撤销付款
function rollbackPay(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!rollbackPay.php?taskId=" + id
			+ "&platformType=" + platformType;
}
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