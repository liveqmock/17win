// 得到city
function getCities(pid, cityObj, areaObj) {
	$.post("ajaxManager/ajax!address.php", {
				type : "1",
				id : pid
			}, function(data) {
				var selectCity = $(cityObj);
				var selectArea = $(areaObj);
				selectCity.empty();
				selectArea.empty();
				var option = $("<option value=''>--请选择--</option>");
				selectCity.append(option);
				selectArea.append($("<option value=''>请选择</option>"));
				var cities = data.cityList;
				for (var i = 0; i < cities.length; i++) {
					option = $("<option></option>", {
								value : cities[i].id,
								text : cities[i].name
							});
					selectCity.append(option);
				}
			}, "json");
}
// 得到area
function getAreas(cid, areaObj) {
	$.post("ajaxManager/ajax!address.php", {
				type : "2",
				id : cid
			}, function(data) {
				var selectArea = $(areaObj);
				selectArea.empty();
				var option = $("<option>--请选择--</option>");
				selectArea.append(option);
				var cities = data.cityList;
				var areas = data.areaList;
				for (var i = 0; i < areas.length; i++) {
					option = $("<option></option>", {
								value : areas[i].id,
								text : areas[i].name
							});
					selectArea.append(option);
				}
			}, "json");
}
// 根据店铺得到seller
/**
 * type 0 自动获取,1 淘宝 2拍拍 3有啊
 * 
 * @param {}
 *            url
 * @param {}
 *            type
 * @param {}
 *            textID
 */
function getSeller(url, type, textID) {
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSeller.php", {
				url : url,
				type : type
			}, function(data) {
				if (data.seller == null || data.seller == "") {
					alert("您输入的地址不正确！");
				} else {
					$("#" + textID).val(data.seller);
				}
			}, "json");
}
