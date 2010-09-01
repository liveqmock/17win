
/**
 * js验证框架 注意，用此框架，要把页面编码设置为utf-8
 */
function Validater() {
}
// 判断对象是否为null
Validater.isNull = function(obj) {
	if (typeof(varObj) == "undefined " || obj == null) {
		return true;
	} else {
		return false;
	}
};

// 判断用户名
Validater.isUsername = function(str) {
	return str.match(/^[\d\w]{4,12}$/);
};
// 判断密码
Validater.isPassword = function(str) {
	return str.match(/^[^\s]{6,20}$/);
};
// 验证QQ
Validater.isQQ = function(str) {
	var filter = /^\s*[.0-9]{5,10}\s*$/;
	return filter.test(str);
};
/**
 * 整数的判定
 * 
 * @param type
 *            为空 任意整数 '0+' 非负整数 '+' 正整数
 * 
 * '-0' 非正整数 '-' 负整数
 * 
 */
Validater.isInt = function(str, type) {
	// var regInt = /^d+^/;
	var regInt;
	if (type == "0+") {
		regInt = /^[1-9]\d*|0$/;
	} else {
		if (type == "+") {
			regInt = /^[1-9]\d*$/;
		} else {
			if (type == "-0") {
				regInt = /^-[1-9]\d*|0$/;
			} else {
				if (type == "-") {
					regInt = /^-[1-9]\d*$/;
				} else {
					regInt = /^-?[1-9]\d*$/;
				}
			}
		}
	}
	return regInt.test(str);
};
// 判定名称，中文，英文字母下划线，数字，范围
Validater.isName = function(str, min, max) {
	var regName = new RegExp("^[\\w\\u4e00-\\u9fa5\\-]{" + min + "," + max
			+ "}$");
	return regName.test(str);
};

// 是否中文
Validater.isChinese = function(str) {
	var regChinese = /^[\u0391-\uFFE5]+$/;
	return regChinese.test(str);
};
// 是否字符
Validater.isWord = function(str) {
	var regWord = /^\w+$/;
	return regWord.test(str);
};
// 是否时间格式yyyy-MM-dd
Validater.isDate = function(str) {
	var reg = /^((((1[6-9]|[2-9]d)d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]d|3[01]))|(((1[6-9]|[2-9]d)d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]d|30))|(((1[6-9]|[2-9]d)d{2})-0?2-(0?[1-9]|1d|2[0-8]))|(((1[6-9]|[2-9]d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
	return reg.test(str);
};
// 比较时间
Validater.compareDate = function(sdate, edate) {
	var sdate = sdate.replace(/-/g, "/");
	var edate = edate.replace(/-/g, "/");
	return Date.parse(sdate) - Date.parse(edate) > 0;
};
// 去除左右空格
Validater.trim = function(str) {
	var m = str.match(/^\s*(\S+(\s+\S+)*)\s*$/);
	return (m == null) ? "" : m[1];
};
// 判断是否为空
Validater.isBlank = function(str) {
	if (str.length == 0) {
		return true;
	} else {
		return (this.trim(str).length == 0) ? true : false;
	}
};
// 判断字符串长度
Validater.fitLen = function(str, minLen, maxLen) {
	return str.length >= minLen && str.length <= maxLen;
};
// 判断字符串最大长度
Validater.fitMaxLen = function(str, minLen, maxLen) {
	return str.length <= maxLen;
};

// 是否email
Validater.isEmail = function(str) {
	var reg = new RegExp("^(?:(?:[\\w-\\.]+)@(?:(?:\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(?:(?:[\\w-]+\\.)+))(?:[a-zA-Z]{2,4}|[0-9]{1,3})(?:\\]?))$");
	return reg.test(str);
};

// 手机
Validater.isTelphone = function(str) {
	var reg = /^(13[0-9]|15[1|0|3|6|7|8|9]|18[8|9])\d{8}$/;
	return reg.test(str);
};
// 验证淘宝店铺地址
Validater.isTaobaoShop = function(str) {
	var regName = new RegExp("^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?$");
	return regName.test(str);
}
// 根据是否是数字返回值 defValue不是数字就返回一个默认值，比如0
Validater.naNValue = function(str, defValue) {
	return isNaN(Validater.trim($(this).text())) ? defValue : Validater
			.trim($(this).text());
}
