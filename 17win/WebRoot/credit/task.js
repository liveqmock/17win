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
				var buyerId=$("input[name='buyerName']:checked").val();
				window.location.href = "taskManager/task!receiveTask.php?taskId="
						+ taskId + "&platformType=" + platformType+ "&buyerId=" + buyerId;
			}
		}
	});
});
// 接手任务
function receiveTask(id) {
	$("#currTaskId").val(id);
	$("#buyerDIV").dialog("open");
}
// 刷新排前
function toFirstTask(id) {
	var platformType = $("#platformType").val();
	if (confirm("是否要取消任务重填任务！")) {
		window.location.href = "taskManager/task!toFirstTask.php?taskId=" + id
				+ "&platformType=" + platformType;;
	}
}