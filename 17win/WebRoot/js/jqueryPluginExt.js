//TABLE 排序
$.tablesorter.addParser({
			id : "dateFormat",
			is : function(s) {
				return true;
			},
			format : function(s) {
				return s.toLowerCase().replace(/[\-:\s]/g,"");
			},
			type : "numeric"
		});