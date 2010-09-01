var sellTarget;
$(document).ready(function() {

	// 设置买家数据
	$("[totalASell]").each(function() {
		sellTarget = $(this);
		$(this).prevAll().each(function(i) {
			var value = parseInt($.trim(sellTarget.text()))
					+ parseInt(Validater.naNValue($(this).text(), "0"));
			sellTarget.text(value);
		});
	});

	// 设置买家数据
	$("td[id*='sellH']").each(function() {
		var obj = $(this);
		var index = $.trim(obj.text()).split("_")[1];
		$("[sellAID=" + index + "]").each(function() {
			var value = parseInt($.trim(obj.text()))
					+ parseInt(Validater.naNValue($(this).text(), "0"));
			obj.text(value);
		});
	});
	// 设置买家数据
	$("#sell_ALL").prevAll().each(function(i) {
		var value = parseInt($.trim($("#sell_ALL").text()))
				+ parseInt(Validater.naNValue($(this).text(), "0"));
		$("#sell_ALL").text(value);
	});

	// /////////////////////

	// 设置卖家数据
	$("[totalABuy]").each(function() {
		sellTarget = $(this);
		$(this).prevAll().each(function(i) {
			var value = parseInt($.trim(sellTarget.text()))
					+ parseInt(Validater.naNValue($(this).text(), "0"));
			sellTarget.text(value);
		});
	});

	// 设置卖家数据
	$("td[id*='buyH']").each(function() {
		var obj = $(this);
		var index = $.trim(obj.text()).split("_")[1];
		$("[buyAID=" + index + "]").each(function() {
			var value = parseInt($.trim(obj.text()))
					+ parseInt(Validater.naNValue($(this).text(), "0"));
			obj.text(value);
		});
	});
	// 设置卖家数据
	$("#buy_ALL").prevAll().each(function(i) {
		var value = parseInt($.trim($("#buy_ALL").text()))
				+ parseInt(Validater.naNValue($(this).text(), "0"));
		$("#buy_ALL").text(value);
	});
});
