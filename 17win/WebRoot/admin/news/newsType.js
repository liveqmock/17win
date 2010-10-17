$(document).ready(function() {
			$("#myTable").tablesorter();
			$("#updateDIV").dialog({
						autoOpen : false,
						draggable : true,
						hide : 'slide',
						modal : true,
						resizable : false,
						show : 'slide',
						width : 400,
						buttons : {
							"保存" : function() {
								if (Validater.isBlank($("#newsName").val())) {
									alert("名字不能为空！");
								} else {
									$("#moneyForm").submit();
								}
							}
						}
					});
		});

// 删除

function deleteNewsType(id) {
	if (confirm("确认是否删除？")) {
		window.location.href = "adminNewsManager/adminNews!deleteNewsType.php?newsTypeVO.id="
				+ id;
	}
}

// 修改
function updateNewsType(id, name) {
	$("form").attr("action", "adminNewsManager/adminNews!updateNewsType.php");
	$("#updateDIV").attr("title", "修改");
	$("#currID").val(id);
	$("#newsName").val(name);
	$("#updateDIV").dialog("open");
}
// 新增
function addNewsType() {
	$("form").attr("action", "adminNewsManager/adminNews!addNewsType.php");
	$("#updateDIV").attr("title", "新增");
	$("#currID").val("");
	$("#newsName").val("");
	$("#updateDIV").dialog("open");
}