$(document).ready(function() {
	$(".qqConnection").tooltip();

	intText("autoreFresh");
	$("#autoreFresh").focus(function() {
				$(this).data("oldValue", $(this).val());
				$(this).val("");
			});
	$("#autoreFresh").blur(function() {
		if (!Validater.isBlank($(this).val())) {
			var value = parseInt($(this).val());
			if (value < 5) {
				alert("刷新时间必须大于5秒");
				return;
			}
			var queryType = parseInt($("#queryType").val());
			var platformType = $("#platformType").val();
			window.location.href = "taskManager/task!initReleasedTast.php"
					+ "?platformType=" + platformType + "&queryType="
					+ queryType + "&page=1" + "&autoRefresh=" + $(this).val();
		} else {
			if (!Validater.isBlank($(this).data("oldValue"))) {
				var queryType = parseInt($("#queryType").val());
				var platformType = $("#platformType").val();
				window.location.href = "taskManager/task!initReleasedTast.php"
						+ "?platformType=" + platformType + "&queryType="
						+ queryType + "&page=1";
			}
		}
	});

	// /////
	$("input[class='goItemButton']").click(function() {
				var val = $(this).siblings("input").val();
				window.open(val, "_blank");

			});

	$("#autoreFresh").focus(function() {

			});

	$("#contentID").keyup(function() {
				var value = $(this).val();
				if (value.length > 200) {
					$(this).val(value.substring(0, 200));
				} else {
					$("#showTip").text(value.length);
				}
			});

	$("#addressDIV").dialog({
				autoOpen : false,
				draggable : false,
				hide : 'slide',
				modal : true,
				resizable : false,
				show : 'slide',
				width : 400
			});
});

// 弹出发送手机短信层
function openTelephoneDiv(telphone, username) {
	$("#telphoneID").val(telphone);
	$("#contentID").text("这里是来自www.17win.net【" + username + "】用户的消息，你接手的任务");
	$("#showTip").text($("#contentID").text().length);
	$("#sendSmsDIV").dialog({
		autoOpen : false,
		draggable : true,
		hide : 'slide',
		modal : true,
		resizable : false,
		show : 'slide',
		width : 400, 
		title:'发送手机短信',
		buttons : {
			"发送" : function() {
				if (Validater.isBlank($("#contentID").text())
						&& $("#contentID").text().length > 200) {
					alert("内容不能为空,并且长度不能大于200！");
					return;
				}
				$("#sendSmsDIV").dialog("close");
				$("#sendSmsForm").submit();
			}
		},
		close : function(event, ui) {
			$(this).dialog("destroy");
		}
	});
	$("#sendSmsDIV").dialog("open");
}

// 回复
function reply(fromUsername) {
	window.open("smsManager/sms!initSendSms.php?toUser=" + fromUsername
					+ "&timeFlag=" + new Date().getTime(), "_blank");
}
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
	window.location.href = "taskManager/task!toFirstTask.php?taskId=" + id
			+ "&platformType=" + platformType;
}

// 加时
function addTime(id) {
	var platformType = $("#platformType").val();
	window.location.href = "taskManager/task!addTime.php?taskId=" + id
			+ "&platformType=" + platformType;
}

// 旺旺
function callWW(url) {
	if (confirm("为了您和他人的安全，使用旺旺联系对方时请不要发送关于任何刷信誉的消息！")) {
		window.open(url, "_blank");
	}
}
// ///分页
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
	var page = parseInt($("#toPageSelect").val());
	query(page);
}
//复制地址
function showItemUrl(itemUrl,grade,comment) {
	$("#gradeCommon").text(comment+"【"+grade+"】");
	$("#itemContent").empty();
	var itemUrls = itemUrl.split(",");
	for (var i = 0; i < itemUrls.length; i++) {
		var tr = $("<tr>" + "<td>" + "地址" + (i + 1) + "：" +"	</td>" + "	<td>"
				+ "	<input  type='text' readonly='readonly'  value="
				+ itemUrls[i] + " style='width: 200px'/>" + "		</td>" + "	<td>"
				+ "	<a  href=\"javascript:copyToClipboard(\'" + itemUrls[i]
				+ "\')\"" + "		style='cursor: pointer;''> <img border='0'"
				+ "		src='images/renwu-3.png''> </a>" + "</td>" + "</tr>");
		$("#itemContent").append(tr);
	}
	$("#addressDIV").dialog("open");
}
//直接跳转地址
function openItemUrl(itemUrl,grade,comment) {
	var itemUrls = itemUrl.split(",");
	if(itemUrls.length==1){
		window.open(itemUrls[0],"_blank");
	}else{
		showItemUrl(itemUrl,grade,comment);
	}
}
// 条件查询
function sort(sortValue) {
	$("select[name='creditTaskVO.status']").val(sortValue);
	$("#mainForm").submit();
}
// 查询
function query(page) {
	var pageCount = parseInt($("#pageCount").val());
	var queryType = parseInt($("#queryType").val());
	var platformType = $("#platformType").val();
	if (page < 1 || page > pageCount) {
		alert("页数必须在1-" + pageCount + "之间！");
		return;
	}
	$("#mainForm").submit();
}