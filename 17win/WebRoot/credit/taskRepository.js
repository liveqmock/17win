// 继续发布任务
function releaseTask(id) {
	var platformType = $("#platformType").val();
	wwindow.location.href = "taskRepositoryManager/taskRepository!releaseRepository.php?taskReId="
			+ id + "&platformType=" + platformType;
}

// 删除
function deleteRepository(url) {
	if (confirm("您确定要把该任务从任务仓库中移出？")) {
		var platformType = $("#platformType").val();
		wwindow.location.href = "taskRepositoryManager/taskRepository!deleteRepository.php?taskReId="
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

// 查询
function query(page) {
	var pageCount = parseInt($("#pageCount").val());
	var platformType = $("#platformType").val();
	if (page < 1 || page > pageCount) {
		alert("页数必须在1-" + pageCount + "之间！");
		return;
	}
	window.location.href = "taskRepositoryManager/taskRepository!queryRepositories.php"
			+ "?platformType=" + platformType
}