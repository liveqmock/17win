var sellTarget;
$(document).ready(function() {
	$("[buyer='buyer']").each(function() {
		var row = parseInt($(this).attr("row")) + 1;
		var col = parseInt($(this).attr("col")) + 1;
		var url = "#";
		if (col == 7) {
			url = "taskManager/task!initReceivedTast.php?platformType=" + row
					+ "&creditTaskVO.status=&creditTaskVO.nowPage=1";
		} else {
			url = "taskManager/task!initReceivedTast.php?platformType=" + row
					+ "&creditTaskVO.status=" + col + "&creditTaskVO.nowPage=1";
		}
		$(this).attr("href", url);
	});

	$("[seller='seller']").each(function() {
		var row = parseInt($(this).attr("row")) + 1;
		var col = parseInt($(this).attr("col")) - 1;
		if (col == 2) {
			col = -2;
		} else if (col > 2) {
			col = col - 1;
		}
		if (col == 7) {
			var url = "taskManager/task!initReleasedTast.php?platformType="
					+ row + "&creditTaskVO.status=&creditTaskVO.nowPage=1";
		} else {
			var url = "taskManager/task!initReleasedTast.php?platformType="
					+ row + "&creditTaskVO.status=" + col
					+ "&creditTaskVO.nowPage=1";
		}
		$(this).attr("href", url);
	});
});
