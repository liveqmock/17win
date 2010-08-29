//得到city
function getCities(pid, selectCityId, selectAreaId) {
	$.post("ajaxManager/ajax!address.php", {addressType:"1", addressID:pid}, function (data) {
		var selectCity = $("#" + selectCityId);
		var selectArea = $("#" + selectAreaId);
		selectCity.empty();
		selectArea.empty();
		var option = $("<option value=''>--\u8bf7\u9009\u62e9--</option>");
		selectCity.append(option);
		selectArea.append($("<option value=''>\u8bf7\u9009\u62e9</option>"));
		var cities = data.cityList;
		for (var i = 0; i < cities.length; i++) {
			option = $("<option></option>", {value:cities[i].id, text:cities[i].name});
			selectCity.append(option);
		}
	}, "json");
}
//得到area
function getAreas(cid, selectAreaId) {
	$.post("ajaxManager/ajax!address.php", {addressType:"2", addressID:cid}, function (data) {
		var selectArea = $("#" + selectAreaId);
		selectArea.empty();
		var option = $("<option>--\u8bf7\u9009\u62e9--</option>");
		selectArea.append(option);
		var cities = data.cityList;
		var areas = data.areaList;
		for (var i = 0; i < areas.length; i++) {
			option = $("<option></option>", {value:areas[i].id, text:areas[i].name});
			selectArea.append(option);
		}
	}, "json");
}

