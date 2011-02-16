$(document).ready(function() {
			var $tabs = $("#tabs").tabs();
			if ($("[name='smsVO.queryTypeFlag']").val() == "sjx") {
				$tabs.tabs('select', 0);
			} else {
				$tabs.tabs('select', 1);
			}
			$tabs.tabs("option", "event", 'dblclick');
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
// 删除收件箱
function deleteSjx() {
	if ($("[name='smsVO.sjSmsIDs']:checked").size() == 0) {
		alert("请选择要删除的站内信");
		return false;
	}
	$("#form2").attr("action", "smsManager/sms!deleteSjxSms.php");
	$("#form2").submit();
}
// 删除发件箱
function deleteFjx() {
	if ($("[name='smsVO.fjSmsIDs']:checked").size() == 0) {
		alert("请选择要删除的站内信");
		return false;
	}
	$("#form2").attr("action", "smsManager/sms!deleteFjxSms.php");
	$("#form2").submit();
}
// 浏览
function brower(obj, id, user, title, content, read, browerFlag) {
	$("#td_" + id).text($("#td_" + id).attr("fromUserName"));
	if (!read) {
		$.post("smsManager/sms!updateSms.php?smsVO.id=" + id + "&timeFlag="
				+ new Date().getTime());
		if (browerFlag == "sjx") {
			$(obj).text(title);
		}
	}
	if (browerFlag == "sjx") {
		$("#userLable").text("发件人：");
	} else {
		$("#userLable").text("收件人：");
	}
	$("#user").val(user);
	$("#title").val(title);
	$("#content").text(content);
	$("#browerSms").dialog("open");

}
function changeQuery(obj) {
	if ($(obj).attr("queryFlag") == "sjx") {
		$("#form2").attr("action", "smsManager/sms!querySJXSms.php");
	} else {
		$("#form2").attr("action", "smsManager/sms!queryFJXSms.php");
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
