String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
}

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
}
// 赋值到剪切板
function copyToClipboard(txt, msg) {
	txt=txt.replace(/(^\s*)|(\s*$)/g,""); 
	if (window.clipboardData) {
		window.clipboardData.clearData();
		window.clipboardData.setData("Text", txt);
	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager
					.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");

		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1']
				.createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1']
				.createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"]
				.createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode", str, copytext.length * 2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans, null, clipid.kGlobalClipboard);
	}
	msg = (typeof(msg) == "undefined") ? '复制成功' : msg;
	alert(msg + '!');
}

/**
 * 只能输入整数的文本框
 * 
 * @param {}
 *            copyText
 */
function intText(textID) {
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isInt(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});
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
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isFloat(value, "+")) {
					$(this).val(value.substring(0, value.length - 1));
				}
			});
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
	$("#" + textID).bind("keydown", function() {
				var value = $(this).val();
				if (!Validater.isNumber(value, "+")) {
					var dot = value.substring(value.length - 2, value.length
									- 1);
					if (dot == ".") {
						$(this)
								.val(value.substring(0, value.length - 1)
										+ ".0");
					}
					$(this).val(value.substring(0, value.length - 1));
				}
			});
	$("#" + textID).bind("keyup", function() {
				var value = $(this).val();
				if (!Validater.isNumber(value, "+")) {
					var dot = value.substring(value.length - 2, value.length
									- 1);
					if (dot == ".") {
						$(this)
								.val(value.substring(0, value.length - 1)
										+ ".0");
					}
					$(this).val(value.substring(0, value.length - 1));
				}
			});

}

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
 * type 0 自动获取,1 淘宝 2拍拍
 * 
 * @param {}
 *            url
 * @param {}
 *            type
 * @param {}
 *            textID
 */
function getSeller(url, type, textID) {
	VhostAop.divAOP.ajax("ajaxManager/ajax!obtainSellerByShop.php", {
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

// 动态参数
function dynamicMsg(str) {
	var index = arguments.length - 1;
	for (var i = 0; i < index; i++) {
		var reCat = new RegExp("#\\{" + i + "\\}", "g");
		str = str.replace(reCat, arguments[i + 1]);
	}
	return str;
}

// 全选/反选
function selectAll(obj, name) {
	$("[" + name + "]").attr("checked", $(obj).attr("checked"));
}

// 获取用户信息
function getUserLogin() {
	$.getJSON("userManager/base!getLoginUser.php?time=" + new Date().getTime(),
			function(data) {
				var user = data.loginInfo;
				var tdNo = "<span class='yell_font'>您还没登录！</span> "
						+ "	<a target='_top' href='user/login.html'>登陆</a> | <a target='_top' href='userManager/base!initRegister.php'>注册</a> | "
						+ " <a  href='javascript:window.external.addFavorite(\"http://www.17win.net\",\"17win(一起赢)互刷平台\");'	title='添加到收藏夹'>[收藏本站]</a> ";
				if (user == null) {
					$("#userLoginId").html(tdNo);
					return;
				}
				var tdYes = "<span class='yell_font'>欢迎您！</span> "
						+ "			<font color='red'><b>"
						+ user.username
						+ " </b> </font> 回来 |  "
						+ "				<a target='_self' href='userManager/base!loginOut.php'> "
						+ "				[安全退出] </a> "
						+ "			|    "
						+ "			<a target='_self' href='userInfoManager/info!init.php'> "
						+ "				[个人中心] </a>|  "
						+ "		<a  href='javascript:window.external.addFavorite(\"http://www.17win.net\",\"淘宝,拍拍完全免费互刷信誉\");'	title='添加到收藏夹'>[收藏本站]</a>  ";

				$("#userLoginId").html(tdYes);
			});
}
function waitPage() {
	document.body.innerHTML = '<p>&nbsp;</p>'
			+ '<table width="200" height="200" border="0" align="center" valign="middle" cellpadding="0" cellspacing="0">'
			+ '<tr>'
			+ '<td align="center" valign="middle"><img src="../images/loading.gif" width="136" height="50s"></td>'
			+ '</tr>' + '</table>';
}

function showOrhideLeftInfo(obj) {
	$(obj).parent().children("tbody").toggle();
}
/**
 * 当鼠标移出某层时，则隐藏该层
 */
function moveOutCloseDisplay(evt, divName) {
	var obj = document.all ? evt.toElement : evt.relatedTarget;
	while (obj != null && obj.id != divName) {
		obj = document.all ? obj.parentElement : obj.parentNode;
	}
	if (obj == null) {
		document.getElementById(divName).style.display = "none";
	}
}

function forbidBackSpace() {
	if (((event.keyCode == 8) && // BackSpace
	((event.srcElement.type != "text" && event.srcElement.type != "textarea" && event.srcElement.type != "password") || event.srcElement.readOnly == true))
			|| ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)))
			|| // CtrlN,CtrlR
			(event.keyCode == 116)) { // F5
		event.keyCode = 0;
		event.returnValue = false;
	}
}

function refuseF5() {
	if ((window.event.keyCode == 116) || // 屏蔽 F5
			(window.event.keyCode == 122) || // 屏蔽 F11
			(window.event.shiftKey && window.event.keyCode == 121)) {
		event.keyCode = 0;
		event.cancelBubble = true;
		return false;
		refreshPage();
	}
}
// 首页显示
function Mea(obj) {
	$("#showTitle").find("A").removeClass();
	$("#showTitle").find("A").addClass("li0");
	$(obj).removeClass();
	$(obj).addClass("li1 white");
}

// 验证码
function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	$(obj).attr("src", "verify/verificationCode.php?time=" + timenow);
}

