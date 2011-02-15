$(document).ready(function() {
			var $tabs = $("#tabs").tabs();
			if ($("[name='smsVO.queryTypeFlag']").val() == "sjx") {
				$tabs.tabs('select', 0);
			} else {
				$tabs.tabs('select', 1);
			}
			$tabs.tabs( "option", "event", 'dblclick' );
			$("#browerSms").dialog({
						autoOpen : false,
						draggable : false,
						hide : 'highlight',
						modal : true,
						resizable : false,
						width : 500,
						show : 'highlight'
					});
			$("#deleteBtn1").button();
			$("#deleteBtn2").button();

		});
// 删除
function deleteSms(id) {
	if (confirm("您确认要删除此短信！")) {
		window.location.href = "smsManager/sms!deleteSms.php?smsVO.id=" + id;
	}
}
// 浏览
function brower(id, read) {
	$("#td_" + id).text($("#td_" + id).attr("fromUserName"));
	if (!read) {
		$.post("smsManager/sms!updateSms.php?smsVO.id=" + id + "&timeFlag="
				+ new Date().getTime());
	}
	$("#title").val($("#a_" + id).attr("title"));
	$("#content").text($("#a_" + id).attr("content"));
	$("#browerSms").dialog("open");

}
function changeQuery(obj) {
	if ($(obj).attr("queryFlag") == "sjx") {
		$("form").attr("action", "smsManager/sms!querySJXSms.php");
	} else {
		$("form").attr("action", "smsManager/sms!queryFJXSms.php");
	}
	$("form").submit();
}
function selectSJAll(obj) {
	selectAll(obj, "sjSmsID");
}
function selectFJAll(obj) {
	selectAll(obj, "fjSmsID");
}
// 回复
function reply(fromUsername) {
	window.open("smsManager/sms!initSendSms.php?toUser=" + fromUsername
					+ "&timeFlag=" + new Date().getTime(), "_blank");
}
function validateForm() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (Validater.compareDate(startDate, endDate)) {
		alert("【结束时间】必须大于等于【开始时间】！");
		return false;
	}
	return true;
}
