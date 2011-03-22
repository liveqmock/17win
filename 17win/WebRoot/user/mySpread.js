

$(document).ready(function() {
			$("#myTable").tablesorter({
						headers : {
							4 : {
								sorter : "dateFormat"
							}
						}
					});
		});