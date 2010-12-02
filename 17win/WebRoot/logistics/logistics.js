var submitFlag = true;
$(document).ready(function() {
	// 选择省的事件
	$("#provinceID").bind("change", function() {
		if ($(this).val() == "") {
			$("#cityID").empty();
			$("#cityID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#cityID").val("");
			$("#areaID").empty();
			$("#areaID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#areaID").val("");
			return;
		}
		getCities($(this).val(), "cityID", "areaID");
	});
	// 选择市的事件
	$("#cityID").bind("change", function() {
		if ($(this).val() == "") {
			$("#areaID").empty();
			$("#areaID")
					.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
			$("#areaID").val("");
			return;
		}
		getAreas($(this).val(), "areaID");
	});
});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	if (submitFlag) {
		return true;
	} else {
		alert("\u586b\u5199\u7684\u8d44\u6599\u4e0d\u6b63\u786e\uff01");
		return false;
	}
}

// 验证成功调用的方法
function validateSuccess(obj) {
	var span = $("<span></span>", {
				css : {
					colour : "red"
				}
			});
	var img = $("<img></img>", {
				src : "images/icon_ok.gif",
				title : "\u9a8c\u8bc1\u6210\u529f"
			});
	var td = $(obj).parent().next();
	span.append(img);
	td.empty();
	td.append(span);
}

// 验证失败调用的方法
function validateError(obj, msg) {
	var span = $("<span></span>", {
				css : {
					colour : "red"
				}
			});
	var img = $("<img></img>", {
				src : "images/icon_error.gif",
				title : msg
			});
	var td = $(obj).parent().next();
	span.append(img);
	span.append(msg);
	td.empty();
	td.append(span);
}
