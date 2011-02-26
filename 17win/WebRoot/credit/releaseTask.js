var submitFlag = true;
$(document).ready(function() {
	// 初始化仓库
	initTaskRep();

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
	// 仓库
	$("#respository").bind("click", function() {
				if ($(this).attr("checked")) {
					$("#respositoryName").val("");
					$("#respositoryName").show();
				} else {
					$("#respositoryName").hide();
				}
			});
	// 自己想
	$("#respository").bind("click", function() {
				if ($(this).attr("checked")) {
					$("#respositoryName").val("");
					$("#respositoryName").show();
				} else {
					$("#respositoryName").hide();
				}
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
function selectAssignUser() {
	queryAssignUser();

}
// 查询选择人
function queryAssignUser() {
	// 获取用户地址
	VhostAop.divAOP.ajax("taskManager/task!obtainLinkMan.php", {}, function(
			data) {
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
					+ "</b>"
					+ "	</td>" + "</tr>"
			$("#assignUserTable").append(tr);
		}
		var div = $("#selectUserDiv");
		var position = $("#assignUserID").position();
		div.css("top", position.top + 22);
		div.css("left", position.left);
		div.show();
	}, "JSON");
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
	$("#bgDIV").show();
	$("#rtpLoadingDiv").show();
	$("#submitBtnId").attr("disabled", true);
	return true;
}

/**
 * 任务仓库发送的时候
 */
// 初始化ITEM地址
function initItemUrl(itemUrl) {
	var itemUrls = itemUrl.split(",");
	var itemInput = $("input[name='itemUrls']");
	var td = itemInput.parents(".itemClass");
	itemInput.val(itemUrls[0]);
	for (var i = 1; i < itemUrls.length; i++) {
		var span = itemInput.parent("span").clone();
		span.children("input").val(itemUrls[i]);
		td.append("<br>");
		td.append(span);
	}
}
// 初始化是否修改价格
function initUpdatePrice(updatePrice) {
	$("input[name='creditTaskVO.updatePrice'][value='" + updatePrice + "']")
			.attr("checked", true);
}
// 初始化任务类型和好评要求
function initTaskTypeAndGrade(taskType, grade, intervalHour) {
	grade = grade.replace(/&#(\d+);/g, function($0, $1) {
				return String.fromCharCode($1);
			});
	$("input[name='creditTaskVO.taskType'][value='" + taskType + "']").attr(
			"checked", true);
	hideTaskType($("input[name='creditTaskVO.taskType']:checked").get(0));
	if ("1" == taskType) {
		$("tr[class='xnTaskType']").find("input[value='" + grade + "']").attr(
				"checked", true);
	} else if ("2" == taskType) {
		$("tr[class='stTaskType']").find("input[value='" + grade + "']").attr(
				"checked", true);
		if (grade == "自定义时间好评") {
			$("tr[class='stTaskType']")
					.find("input[name='creditTaskVO.intervalHour']")
					.val(intervalHour);
			$("tr[class='stTaskType']")
					.find("input[name='creditTaskVO.intervalHour']").attr(
							"disabled", false);

		}
	} else if ("3" == taskType) {
		$("tr[class='tcTaskType']").find("input[value='" + grade + "']").attr(
				"checked", true);
		if (grade == "自定义时间好评") {
			$("tr[class='tcTaskType']")
					.find("input[name='creditTaskVO.intervalHour']")
					.val(intervalHour);
			$("tr[class='tcTaskType']")
					.find("input[name='creditTaskVO.intervalHour']").attr(
							"disabled", false);
		}
	}
}
// 自己想 评语
function initCommentThinkBySelf(comment) {
	if (comment == null) {
		return;
	}
	comment = comment.replace(/&#(\d+);/g, function($0, $1) {
				return String.fromCharCode($1);
			});
	if (comment == "-1") {
		$("#commentByJSID").attr("checked", true);
		$("#addtaskForm_creditTaskVO_comment").val("");
	} else {
		$("#addtaskForm_creditTaskVO_comment").val(comment);
	}
}
