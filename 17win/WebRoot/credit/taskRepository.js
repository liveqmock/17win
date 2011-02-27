$(document).ready(function() {
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

// 继续发布任务
function releaseRepository(id) {
	$("#taskRepCurrId").val(id);
	$("#taskForm").submit();
}

// 删除
function deleteRepository(id) {
	if (confirm("您确定要把该任务从任务仓库中移出？")) {
		var platformType = $("#platformType").val();
		window.location.href = "taskRepositoryManager/taskRepository!deleteRepository.php?taskReId="
				+ id + "&platformType=" + platformType;
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

// 复制地址
function showItemUrl(itemUrl) {
	var itemUrls = itemUrl.split(",");
	$("#itemContent").empty();
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
function openItemUrl(itemUrl) {
	var itemUrls = itemUrl.split(",");
	if (itemUrls.length == 1) {
		window.open(itemUrls[0], "_blank");
	} else {
		showItemUrl(itemUrl);
	}
}
// 直接跳转地址
function openItemUrl(itemUrl, updatePrice, grade, comment, address, status) {
	var itemUrls = itemUrl.split(",");
	if (itemUrls.length == 1 && status != "2" && status != "-2") {
		window.open(itemUrls[0], "_blank");
	} else {
		showItemUrl(itemUrl, updatePrice, grade, comment, address, status);
	}
}

// 查询
function query(page) {
	var pageCount = parseInt($("#pageCount").val());
	var platformType = $("#platformType").val();
	if (page < 1 || page > pageCount) {
		alert("页数必须在1-" + pageCount + "之间！");
		return;
	}
	$("#mainForm").submit();
}

// 打开全部商品地址
function openAllItemUrl() {
	$("input[name='itemUrlInput']").each(function(i) {
				window.open($(this).val(), "_blank");
			});
}
