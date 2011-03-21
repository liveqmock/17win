var ckeditorObj = null;
$(document).ready(function() {
	$("#myTable").tablesorter({
				headers : {
					0 : {
						sorter : false
					}
				}
			});
	$("#updateMoneyDIV").dialog({
		autoOpen : false,
		draggable : true,
		hide : 'slide',
		modal : true,
		resizable : false,
		show : 'slide',
		width : 400,
		buttons : {
			"保存" : function() {

				if (Validater.isBlank($("#taobaoBuyerID").val())) {
					alert("淘宝买号不能为空！");
					return;
				}
				if (Validater.isBlank($("#moneyDescID").val())) {
					alert("描述不能为空！");
					return;
				}
				if (Validater.isBlank($("#moneyId").val())
						|| isNaN($("#moneyId").val())) {
					alert("金额格式不对");
				} else {
					$("#moneyForm").submit();
				}
			}
		}
	});

	$("#updateStatusDIV").dialog({
				autoOpen : false,
				draggable : true,
				hide : 'slide',
				modal : true,
				resizable : false,
				show : 'slide',
				width : 400,
				buttons : {
					"保存" : function() {
						if (Validater.isBlank($("#statusDescID").val())) {
							alert("描述不能为空！");
						} else {
							$("#statusForm").submit();
						}
					}
				}
			});

	ckeditorObj = CKEDITOR.replace("mailContent", {
				toolbar : 'Full',
				uiColor : '#9AB8F3'
			});

	$("#sendMailDIV").dialog({
				autoOpen : false,
				draggable : true,
				hide : 'slide',
				modal : true,
				resizable : true,
				show : 'slide',
				width : 800,
				buttons : {
					"保存" : function() {
						if ($("[selectUserName]:checked").size() <= 0) {
							alert("必须选择人员！");
							return;
						}
						var userIds = "";
						$("[selectUserName]:checked").each(function(i) {
									userIds += $(this).val() + ",";
								});
						$("#userIdsesId").val(userIds);

						if (Validater.isBlank($("#userIdsesId").val())) {
							alert("必须选择人员！");
							return;
						}
						if (Validater.isBlank($("#mailSubjctID").val())) {
							alert("主题不能为空！");
							return;
						}
						var value = ckeditorObj.document.getBody().getHtml();
						if (Validater.isBlank(value)) {
							alert("内容不能为空！");
							return;
						}
						$("#sendMailForm").submit();
					}
				}
			});

	$("#sendSmsDIV").dialog({
		autoOpen : false,
		draggable : true,
		hide : 'slide',
		modal : true,
		resizable : true,
		show : 'slide',
		width : 400,
		buttons : {
			"保存" : function() {
				if ($("[selectUserName]:checked").size() <= 0) {
					alert("必须选择人员！");
					return;
				}
				var userIds = "";
				$("[selectUserName]:checked").each(function(i) {
							userIds += $(this).val() + ",";
						});
				$("#userIdsesSmsId").val(userIds);

				if (Validater.isBlank($("#userIdsesSmsId").val())) {
					alert("必须选择人员！");
					return;
				}
				if (Validater.isBlank($("#smsTitleId").val())) {
					alert("标题不能为空！");
					return;
				}
				if (Validater.isBlank($("#smsContentID").text())
						&& $("#smsContentID").text().length > 200) {
					alert("内容不能为空,并且长度不能大于200！");
					return;
				}
				$("#sendSmsForm").submit();
			}
		}
	});
});

// 删除
function deleteUser() {
	if (!confirm("确认是否删除！")) {
		return;
	}
	if ($("[selectUserName]:checked").size() > 0) {
		$("#queryForm").attr("action",
				"adminUserManager/adminUser!deleteUser.php");
		$("#queryForm").submit();
	} else {
		$("#queryForm").attr("action",
				"adminUserManager/adminUser!queryUser.php");
		alert("选择要删除的人员!");
	}
}
// 打开邮件kuang
function openMsgDiv() {
	if ($("[selectUserName]:checked").size() <= 0) {
		alert("必须选择人员！");
		return;
	}
	$("#sendMailDIV").dialog("open");
}
// 打开站内信框
function openSmsDiv() {
	if ($("[selectUserName]:checked").size() <= 0) {
		alert("必须选择人员！");
		return;
	}
	$("#sendSmsDIV").dialog("open");
}
// 修改状态
function updateStatus(id, status) {
	if (confirm("确认修改状态？")) {
		$("#useForUpdateStatusId").val(id);
		$("#statusID").val(status);
		$("#updateStatusDIV").dialog("open");
	}
}

// 充值金额
function addMoney(id) {
	$("#moneyId").val("");
	$("#moneyDescID").val("充值成功");
	if (confirm("确认是否充值？")) {
		$("#moneyUserIdId").val(id);
		$("#updateMoneyDIV").dialog("open");
	}
}
// 删除
function deleteSms(id) {
	if (confirm("您确认要删除此短信！")) {
		window.location.href = "smsManager/sms!deleteSms.php?smsVO.id=" + id;
	}
}
// 浏览
function brower(id, read) {
	if (!read) {
		$.post("smsManager/sms!updateSms.php?smsVO.id=" + id);
	}
	$("#title").val($("#a_" + id).attr("title"));
	$("#content").text($("#a_" + id).attr("content"));
	$("#browerSms").dialog("open");

}
// 回复
function reply(fromUsername) {
	window.open("smsManager/sms!initSendSms.php?toUser=" + fromUsername,
			"_blank");
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

// ///分页
function firstPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(1);
	if (validateForm()) {
		$("#queryForm").submit();
	}
}
function prevPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(nowPage - 1);
	if (validateForm()) {
		$("#queryForm").submit();
	}
}
function nextPage() {
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	$("#nowPage").val(nowPage + 1);
	if (validateForm()) {
		$("#queryForm").submit();
	}
}
function lastPage() {
	var page = parseInt($("#pageCount").val());
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	$("#nowPage").val(page);
	if (validateForm()) {
		$("#queryForm").submit();
	}
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	$("#nowPage").val(page);
	if (validateForm()) {
		$("#queryForm").submit();
	}
}
