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
 * 只能输入数字的文本框
 * 
 * @param {}
 *            copyText
 */
function intText(textID) {
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validate.isInt(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}
