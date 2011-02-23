var submitFlag = true;
$(document).ready(function() {
	// 进入任务仓库
	$("#goTaskRe").click(function() {
		var platformType = $("#platformType").val();
		window.location.href = "taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
				+ platformType;
	});
	// 判断是否取消任务
	var cancelTaskId = $("#cancelFlagTaskIdHidden").val();
	// 自定义时间
	intText("intervalHour1");
	// 自定义时间
	intText("intervalHour2");

	$("[name='creditTaskVO.grade']").bind("change", function() {
		if ($(this).attr("withodCommmon") != null
				&& $(this).attr("withodCommmon") != "underfiend") {
			$("input[name='creditTaskVO.comment']").attr("disabled", true);
			$("#commentByJSID").attr("disabled", true);
		} else {
			$("input[name='creditTaskVO.comment']").attr("disabled", false);
			$("#commentByJSID").attr("disabled", false);
		}
		// 是否自定义时间好评
		if (!$(this).val().startWith("自定义")) {
			$("input[name='creditTaskVO.intervalHour']").attr("disabled", true);
		}
	});
	// tip
	$("#addtaskForm :input").tooltip({

				// place tooltip on the right edge
				position : "center right",

				// a little tweaking of the position
				offset : [-2, 10],

				// use the built-in fadeIn/fadeOut effect
				effect : "fade",

				// custom opacity setting
				opacity : 0.7

			});

});

/**
 * 添加Utem
 */
function addItem(obj) {
	var spans = $("[name='itemUrls']")
	if (spans.size() >= 10) {
		alert("最多添加10个商品!");
		return;
	}
	var td = $(obj).parents(".itemClass");
	var span = $(obj).parent("span").clone();
	span.children("input").val("");
	td.append("<br>");
	td.append(span);
}
/**
 * 删除utem
 * 
 * @param {}
 *            obj
 */
function removeItem(obj) {
	var spans = $("[name='itemUrls']")
	if (spans.size() == 1) {
		alert("至少保留一个商品地址！");
		return;
	}
	var td = $(obj).parents(".itemClass");
	var span = $(obj).parent("span");
	var br = span.prev("br");
	if (br != null) {
		br.detach();
	}
	span.detach();
}

function hideTaskType(obj) {
	var taskType = $(obj).attr("id").split("_")[0];
	$("[class$=TaskType]").hide();
	$("." + taskType).show();
	$("." + taskType).find("input").eq(0).attr("checked", true);
	$("input[name='creditTaskVO.intervalHour']").attr("disabled", true);
}

// 自定义好评时间
function diyCommentTime(id) {
	$("#" + id).attr("disabled", false);
}

// 弹出选择人
function selectAssignUser(obj) {
	var username = $(obj).val();
	queryAssignUser(username);
	var div = $("#selectUserDiv");
	var position = $(obj).position();
	div.css("top", position.top + 22);
	div.css("left", position.left);
	div.show();
}
// 查询选择人
function queryAssignUser(username) {
	// 获取用户地址
	VhostAop.divAOP.ajax("taskManager/task!obtainLinkMan.php", {
				"creditTaskVO.assignUser" : $("#assignUserID").val()
			}, function(data) {
				$("#assignUserTable").empty();
				if (data.linkMans.length == 0) {
					var tr = "<tr>" + "	<td colspan='2'>" + "		<b>没有联系人！</b>"
							+ "	</td>" + "</tr>"
					$("#assignUserTable").append(tr);
					return;
				}
				for (var i = 0; i < data.linkMans.length; i++) {
					var tr = "<tr onmouseover='this.className=\'over\'' style='cursor: pointer;'"
							+ "onclick='selectUser(this);' onmouseout='this.className=\'out\'\>"
							+ "	<td colspan='2'>"
							+ "		<b>"
							+ data.linkMans[i]
							+ "</b>" + "	</td>" + "</tr>"
					$("#assignUserTable").append(tr);
				}
			}, "json");
}
// 改变选择人
function changeUser(obj) {
	queryAssignUser($(obj).val());
}
// 选择人员
function selectUser(obj) {
	var username = $.trim($(obj).find("b").text());
	$("#assignUserID").val(username);
	closeAssignUser();
}
// 关闭选择人
function closeAssignUser() {
	var div = $("#selectUserDiv");
	div.hide();
}

/** *表单验证* */
function validateForm() {
	var platformType = $("#platformType").val();
	var itemUrlObjs = $("input[name='itemUrls']");
	var money = $("#money").val();
	var addtionMoney = $("#addtionMoneyId").val();
	var addtionReleaseDot = $("#addtionReleaseDotId").val();
	var updatePriceObj = $("input[name='creditTaskVO.updatePrice']:checked");
	// 验证地址
	for (var i = 0; i < itemUrlObjs.length; i++) {
		var obj = itemUrlObjs.eq(i);
		obj.val($.trim(obj.val()));
		if (!Validater.isItem(obj.val(), platformType)) {
			alert("输入的地址格式不正确，请核对一下！");
			obj.focus();
			return false;
		}
	}
	// 验证money
	if (!Validater.isNumber(money, "0+")) {
		alert("商品任务价格式不对！");
		$("#money").focus();
		return false;
	}
	// 验证addtionMoney
	if (!Validater.isBlank(addtionMoney) && isNaN(addtionMoney)
			&& parseFloat(addtionMoney) < 0) {
		alert("追加金额格式不对！");
		$("#addtionMoneyId").focus();
		return false;
	}
	// 验证addtionReleaseDot
	if (!Validater.isBlank(addtionReleaseDot) && isNaN(addtionReleaseDot)
			&& parseFloat(addtionMoney) < 0) {
		alert("追加发布点格式不对！");
		$("#addtionReleaseDotId").focus();
		return false;
	}
	if (updatePriceObj == null || updatePriceObj.length == 0
			|| updatePriceObj == "undefined") {
		alert("请选择是否修改价格！");
		$("#addtionReleaseDotId").focus();
		return false;
	}
	return true;
}