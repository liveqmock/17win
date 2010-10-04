function sum(x, y) {
	var vager = 1000;
	return function(x, y) {
		return x + y + vager;
	};
}
