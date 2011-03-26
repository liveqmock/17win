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
							$("#mainForm").submit();
						} else {
							if (!Validater.isBlank($(this).data("oldValue"))) {
								$("#mainForm").submit();
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
						modal : true,
						resizable : false,
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
		modal : true,
		resizable : false,
		width : 400,
		title : '发送手机短信',
		buttons : {
			"发送" : function() {
				if (Validater.isBlank($("#contentID").text())
						&& $("#contentID").text().length > 200) {
					alert("内容不能为空,并且长度不能大于200！");
					return;
				}
				$.post("taskManager/task!sendMsg.php", {
							telphone : $("#telphoneID").val(),
							content : $("#contentID").text()
						}, function(data) {
							$("#sendSmsDIV").dialog("close");
							alert(data.creditTaskVO.message);
						}, "json");
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
// 清理买家
function clearReceiver(id) {
	if (confirm("是否清除对方！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!clearReceiver.php");
		$("#mainForm").submit();
	}
}
// 审核卖家
function audiReceiver(id) {
	if (confirm("是否确认审核对方！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!audiReceiver.php");
		$("#mainForm").submit();
	}
}
// 好评
function sellerEvaluate(id) {
	if (confirm("是否确认好评！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!sellerEvaluate.php");
		$("#mainForm").submit();
	}
}
// 发货
function dispatch(id) {
	if (confirm("是否确认发货！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!dispatch.php");
		$("#mainForm").submit();
	}
}
// 取消重填
function cancelTask(id) {
	if (confirm("是否要取消任务！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!cancelTask.php");
		$("#mainForm").submit();
	}
}
// 刷新排前
function toFirstTask(id) {
	if (confirm("是否要刷新任务！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!toFirstTask.php");
		$("#mainForm").submit();
	}
}

// 加时
function addTime(id) {
	if (confirm("是否要为对方加时！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!addTime.php");
		$("#mainForm").submit();
	}
}

// 旺旺
function callWW(url) {
	if (confirm("为了您和他人的安全，使用旺旺联系对方时请不要发送关于任何刷信誉的消息！")) {
		window.open(url, "_blank");
	}
}
// 刷新当前页
function refreshPage() {
	$("#mainForm").submit();
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
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	query(pageCount);
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	query(page);
}
// 复制地址
function showItemUrl(itemUrl, updatePrice, grade, comment, address, waybill,
		intervalHour) {
	$("#updatePrice").text(updatePrice == "true" ? "需修改价" : "不需改价");
	$("#grade").text(grade == "自定义时间好评"
			? grade + "(" + intervalHour + "小时后好评)"
			: grade);
	$("#comment").text((comment == null || comment == "")
			? "无"
			: (comment == "-1") ? "接手人自己想" : comment);
	$("#address").text((address == null || address == "") ? "无" : address);
	$("#waybill").text(waybill);
	$("#waybill").unbind("click");
	$("#waybill").click(function() {
				copyToClipboard($(this).text().split(" ")[1]);
			});
	$("#comment").unbind("click");
	$("#comment").click(function() {
		if (comment != null && comment != "" && comment != "-1"
				&& comment != "无") {
			copyToClipboard($(this).text());
		}
	});
	$("#address").unbind("click");
	$("#address").click(function() {
				if (address != null && address != "" && address != "无") {
					copyToClipboard($(this).text());
				}
			});

	$("#itemContent").empty();
	var itemUrls = itemUrl.split(",");
	for (var i = 0; i < itemUrls.length; i++) {
		var tr = $("<tr>"
				+ "<td>"
				+ "地址"
				+ (i + 1)
				+ "："
				+ "	</td>"
				+ "	<td>"
				+ "	<input  type='text' readonly='readonly' name='itemUrlInput'  value="
				+ itemUrls[i] + " style='width: 200px'/>" + "		</td>"
				+ "	<td  valign='middle'>"
				+ "	<a  href=\"javascript:copyToClipboard(\'" + itemUrls[i]
				+ "\')\"" + "		style='cursor: pointer;''> <img border='0'"
				+ "		src='images/renwu-3.png''> </a>" + "<a href='"
				+ itemUrls[i] + "' target='_blank' xalt_txt='点此直接打开商品地址'>"
				+ "<img border='0' src='images/open.gif'>" + "</a>" + "</td>"
				+ "</tr>");
		$("#itemContent").append(tr);
	}
	$("#addressDIV").dialog("open");
}
// 直接跳转地址
function openItemUrl(itemUrl, updatePrice, grade, comment, address, waybill,
		intervalHour) {
	var itemUrls = itemUrl.split(",");
	if (itemUrls.length == 1) {
		window.open(itemUrls[0], "_blank");
	} else {
		showItemUrl(itemUrl, updatePrice, grade, comment, address, waybill,
				intervalHour);
	}
}
// 条件查询
function sort(sortValue) {
	$("select[name='creditTaskVO.status']").val(sortValue);
	$("#mainForm").submit();
}
// 查询
function query(page) {
	$("#nowPage").val(page);
	$("#mainForm").submit();
}
// 刷新页面
function refreshPage() {
	$("#mainForm").submit();
}
// 打开全部商品地址
function openAllItemUrl() {
	$("input[name='itemUrlInput']").each(function(i) {
				window.open($(this).val(), "_blank");
			});
}
