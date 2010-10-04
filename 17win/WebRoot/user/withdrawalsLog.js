$(document).ready(function() {
			$("#withdrawalsType").change(function() {
						if ($(this).val() == "1") {
							$("#shopType").show();
						} else {
							$("#shopType").hide();
						}
					});
			numberText("startMoney");
			numberText("endMoney");
			$("#myTable").tablesorter({
						widthFixed : true,
						sortList : [[0, 0]],
						headers : {
							2 : {
								sorter : false
							},
							4 : {
								sorter : false
							},
							6 : {
								sorter : false
							},
							7 : {
								sorter : false
							}
						}
					});
		});

function validateForm() {
	var startMoeny = $("input[name='withdrawalsVO.startMoney']").val();
	var endMoney = $("input[name='withdrawalsVO.endMoney']").val();
	var startDate = $("input[name='withdrawalsVO.startDate']").val();
	var endDate = $("input[name='withdrawalsVO.endDate']").val();
	if (parseFloat(startMoeny) > parseFloat(endMoney)) {
		alert("【结束金额】必须大于等于【开始金额】！");
		return false;
	}
	if (Validater.compareDate(startDate, endDate)) {
		alert("【结束时间】必须大于等于【开始时间】！");
		return false;
	}
	return true;
}