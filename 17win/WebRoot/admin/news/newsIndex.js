$(document).ready(function() {
	$("#myTable").tablesorter({
				headers : {
					4 : {
						sorter : "dateFormat"
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
				if (Validater.isBlank($("#moneyId").val())
						|| isNaN($("#moneyId").val())) {
					alert("金额格式不对");
				} else {
					$("#moneyForm").submit();
				}
			}
		}
	});
});

// 删除

function deleteNews(id) {
	if (confirm("确认是否删除？")) {
		window.location.href = "adminNewsManager/adminNews!deleteNews.php?newsVO.id="
				+ id;

	}
}
// 更新
function updateNews(id) {
	window.location.href = "adminNewsManager/adminNews!initUpdateNews.php?newsVO.id="
			+ id;
}

// ///分页
function firstPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(1);
	$("form").submit();
}
function prevPage() {
	var nowPage = parseInt($("#nowPage").val());
	if (nowPage == 1) {
		alert("当前已经是第一页！");
		return;
	}
	$("#nowPage").val(nowPage - 1);
	$("form").submit();
}
function nextPage() {
	var nowPage = parseInt($("#nowPage").val());
	var pageCount = parseInt($("#pageCount").val());
	if (nowPage == pageCount) {
		alert("当前已经是最后一页！");
		return;
	}
	$("#nowPage").val(nowPage + 1);
	$("form").submit();
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
	$("form").submit();
}
function jumpPage() {
	var page = parseInt($("#toPageSelect").val());
	$("#nowPage").val(page);
	$("form").submit();
}
