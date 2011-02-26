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
	$("#addressDIV").dialog({
				autoOpen : false,
				draggable : false,
				hide : 'slide',
				modal : true,
				resizable : false,
				show : 'slide',
				width : 400,
				beforeClose : function(event, ui) {
					var status = $("#nowAddressTaskStatus").val();
					if (status == "2" || status == "-2") {
						if ($("input[name='alertCheckedBox']:checked").size() == 4) {
							return true;
						} else {
							alert("请查看对方提出的要求后，然后勾选每个条件后面的复选框再关闭！");
							return false;
						}
					}

				}
			});
	$("#contentID").keyup(function() {
				var value = $(this).val();
				if (value.length > 200) {
					$(this).val(value.substring(0, 200));
				} else {
					$("#showTip").text(value.length);
				}
			});

});

// 弹出发送手机短信层
function openTelephoneDiv(telphone, username) {
	$("#telphoneID").val(telphone);
	$("#contentID").text("这里是来自www.17win.net【" + username + "】用户的消息，你发布的任务");
	$("#showTip").text($("#contentID").text().length);
	$("#sendSmsDIV").dialog({
		autoOpen : false,
		draggable : true,
		hide : 'slide',
		modal : true,
		resizable : false,
		show : 'slide',
		width : 400,
		title : '发送手机短信',
		buttons : {
			"保存" : function() {
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
// 好评
function buyerEvaluate(id) {
	if (confirm("是否确认好评！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!buyerEvaluate.php");
		$("#mainForm").submit();
	}
}
// 撤销付款
function rollbackPay(id) {
	if (confirm("是否确认撤销付款！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!rollbackPay.php");
		$("#mainForm").submit();
	}
}
// 付款
function payMoney(id) {
	if (confirm("是否确认已经付款！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!payMoney.php");
		$("#mainForm").submit();
	}
}
// 退出任务
function quitTask(id) {
	if (confirm("是否确认退出任务！")) {
		$("#taskId").val(id);
		$("#mainForm").attr("action", "taskManager/task!quitTask.php");
		$("#mainForm").submit();
	}
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
function showItemUrl(itemUrl, updatePrice, grade, comment, address, status,
		intervalHour) {
	$("#updatePrice").text(updatePrice == "true" ? "需修改价" : "不需改价");
	$("#grade").text(grade == "自定义时间好评"
			? grade + "(" + intervalHour + "小时后好评)"
			: grade);
	$("#comment").text((comment == null || comment == "")
			? "无"
			: (comment == "-1") ? "接手人自己想" : comment);
	$("#address").text((address == null || address == "") ? "无" : address);
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
		var tr = $("<tr>" + "<td>" + "地址" + (i + 1) + "：" + "	</td>" + "	<td>"
				+ "	<input  type='text' readonly='readonly'  value="
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
	$("input[name='alertCheckedBox']").attr("checked", false);
	if (status == "2" || status == "-2") {
		$("span[alertSpan='alertSpan']").show();
	} else {
		$("span[alertSpan='alertSpan']").hide();
	}
	$("#nowAddressTaskStatus").val(status);
	$("#addressDIV").dialog("open");
}
// 直接跳转地址
function openItemUrl(itemUrl, updatePrice, grade, comment, address, status,
		intervalHour) {
	var itemUrls = itemUrl.split(",");
	if (itemUrls.length == 1 && status != "2" && status != "-2") {
		window.open(itemUrls[0], "_blank");
	} else {
		showItemUrl(itemUrl, updatePrice, grade, comment, address, status,
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
