// firefox 复制
function copy_code(copyText) {
	if (window.clipboardData) {
		window.clipboardData.setData("Text", copyText)
	} else {
		var flashcopier = 'flashcopier';
		if (!document.getElementById(flashcopier)) {
			var divholder = document.createElement('div');
			divholder.id = flashcopier;
			document.body.appendChild(divholder);
		}
		document.getElementById(flashcopier).innerHTML = '';
		var divinfo = '<embed src="js/_clipboard.swf" FlashVars="clipboard='
				+ encodeURIComponent(copyText)
				+ '" width="0" height="0" type="application/x-shockwave-flash"></embed>';
		document.getElementById(flashcopier).innerHTML = divinfo;
	}
}

/**
 * 只能输入整数的文本框
 * 
 * @param {}
 *            copyText
 */
function intText(textID) {
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isInt(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}
/**
 * 只能输入浮点数的文本框
 * 
 * @param {}
 *            copyText
 */
function floatText(textID) {
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isFloat(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}

/**
 * 只能输入数值的文本框
 * 
 * @param {}
 *            copyText
 */
function numberText(textID) {
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isNumber(value, "+")) {
					var dot=value.substring(value.length - 2, value.length - 1);
					if(dot=="."){
						$(this).val(value.substring(0, value.length - 1)+".0");
					}
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}