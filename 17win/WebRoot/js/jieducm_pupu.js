function Mea(value) {
	$("[testclass]").removeClass();
	$("[testclass]").addClass("li0");
	$("[testclass][id=a" + value + "]").removeClass();
	$("[testclass][id=a" + value + "]").addClass("li1 white");
}
function setBg(value) {
	for (var i = 0; i < 10; i++) {
		if (value == i) {
			document.getElementById("a" + value).className = 'li1';
		} else {
			document.getElementById("a" + i).className = 'li0';
		}
	}
}
