$(document).ready(function() {
			$("#withdrawalsType").change(function() {
						if ($(this).val() == "1") {
							$("#shopType").show();
						} else {
							$("#shopType").hide();
						}
					});
			$("#updateTableDIV").dialog({
						autoOpen : false,
						draggable : false,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 500
 
					});
		});

function updateLog(id, flagID) {
	$("#withdrawalsVOID").val(id);
	$("#flagID").val(flagID);
	$("#updateTableDIV").dialog("open");

}

function validateUpdateForm() {
	if (Validater.isBlank($("#statusDesc").val())) {
		alert("状态描述不能为空!");
		return false;
	}
	return true;

}

function validateForm() {
	var startMoeny = $("input[name='withdrawalsVO.startMoney']").val();
	var endMoney = $("input[name='withdrawalsVO.endMoney']").val();
	var startDate = $("input[name='withdrawalsVO.startDate']").val();
	var endDate = $("input[name='withdrawalsVO.endDate']").val();
	if (parseFloat(startMoeny) > parseFloat(endMoney)) {
		alert("【结束金额】必须大于等于【开始金额】！");
		return false;
	}
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
		$("form").submit();
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
		$("form").submit();
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
		$("form").submit();
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
		$("form").submit();
	}
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	$("#nowPage").val(page);
	if (validateForm()) {
		$("form").submit();
	}
}
