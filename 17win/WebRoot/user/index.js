var sellTarget;
$(document).ready(function() {
	$("[buyer='buyer']").each(function() {
		var row = parseInt($(this).attr("row")) + 1;
		var col = parseInt($(this).attr("col")) + 1;
		var url = "#";
		if (col == 6) {
			url = "taskManager/task!initReceivedTast.php?platformType=" + row
					+ "&queryType=1&page=1";
		} else {
			url = "taskManager/task!initReceivedTast.php?platformType=" + row
					+ "&queryType=" + col + "&page=1";
		}
		$(this).attr("href", url);
	});

	$("[seller='seller']").each(function() {
		var row = parseInt($(this).attr("row")) + 1;
		var col = parseInt($(this).attr("col"));
		if (col == 1) {
			// 等待接手
			col = 7;
		} else if (col == 2) {
			col = 8;
		} else if (col == 8) {
			col = 1;
		} else if (col == 7) {
			col = 1;
		} else {
			col = col - 1;
		}
		var url = "taskManager/task!initReleasedTast.php?platformType=" + row
				+ "&queryType=" + col + "&page=1";
		$(this).attr("href", url);
	});
});
