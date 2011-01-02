$(document).ready(function() {
	CKEDITOR.replace("newsVO.content", {
				toolbar : 'Full',
				uiColor : '#9AB8F3'
			});

	$("#typeID").change(function() {
				// 获取用户地址
				VhostAop.divAOP.ajax(
						"adminNewsManager/adminNews!ajaxObtainSort.php", {
							"newsVO.typeId" : $(this).val()
						}, function(data) {
							$("#sort").val(data.sort);
						}, "json");
			});
});
