$(document).ready(function() {
			$("#myTable").tablesorter({
						headers : {
							2 : {
								sorter : "dateFormat"
							},
							3 : {
								sorter : "dateFormat"
							}
						}
					});
		});

function validateForm() {
	var startDate = $("input[name='logisticsVO.startDate']").val();
	var endDate = $("input[name='logisticsVO.endDate']").val();
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
