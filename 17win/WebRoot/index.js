function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
}
var submitFlag = true;
$(document).ready(function() {
	$.messager.lays(300, 200);
	$.messager
			.show(
					'17win公告',
					'300x200的消息<br>脚本之家（JB51.net）提供各类编程源码、书籍教程、JavaScript/CSS特效代码以及常用软件下载等，做有质量的学习型源码下载站。');
	$("#username").focus();
	// // 找回密码
	// $("#findPW").dialog({
	// autoOpen : false,
	// draggable : false,
	// hide : 'slide',
	// modal : true,
	// resizable : false,
	// show : 'slide'
	// });
	// $("#findPW").bind("dialogbeforeclose", function(event, ui) {
	// $("#usernameTelephone").val("");
	// });
	// $("#findPWBtn").button();
	// $("#findPWBtn").bind("click", function() {
	// if (Validater.isBlank($("#usernameTelephone").val())) {
	// alert("数据不能为空");
	// return false;
	// }
	// $.post("ajaxManager/ajax!findPassword.php", {
	// username : $("#usernameTelephone").val(),
	// telephone : $("#usernameTelephone").val()
	// }, function(data) {
	// if (data.bool) {
	// alert("邮件已经发送到你的邮箱里面，请查收！");
	// } else {
	// alert("该用户名或则手机没有被注册过！");
	// }
	// $("#findPW").dialog("close");
	// }, "json");
	// });
	// 弹出找回密码层
	$("#findPWA").bind("click", function() {
				$("#findPW").dialog("open");
			});

	// 用户名
	$("#username").focus();
	$("#username").bind("blur", function() {
		var obj = this;
		if (Validater.isUsername($(this).val())) {
			validateSuccess(obj);
		} else {
			validateError(
					this,
					"\u7528\u6237\u540d\u5fc5\u987b\u662f\u53c8\u6570\u5b57\u6216\u5219\u5b57\u7b26\u7ec4\u6210\u76844-12\u4f4d\u5b57\u7b26\u4e32");
			submitFlag = false;
		}
	});
	// 密码
	$("#password").bind("blur", function() {
		if (Validater.isPassword($(this).val())) {
			validateSuccess(this);
		} else {
			validateError(this, "\u5fc5\u987b\u662f6\u81f320\u4f4d\u5b57\u7b26");
			submitFlag = false;
		}
	});
	// 验证码
	$("#verificationCode").bind("blur", function() {
				if ($(this).val().length == 4) {
					validateSuccess(this);
				} else {
					submitFlag = false;
				}
			});

	// //// ajax

	$.getJSON("userManager/base!getLoginUser.php?time=" + new Date().getTime(),
			function(data) {
				var user = data.loginInfo;
				var tdNo = "<span class='yell_font'>您还没登录！</span> "
						+ "	<a target='_top' href='user/login.html'>登陆</a> | 注册</a> | "
						+ " <a onclick='window.external.addFavorite('http://www.2000w.net','淘宝刷信誉')' title='添加到收藏夹' href='#'>[收藏本站]</a> ";
				var tableNo = "<table width='100%' cellspacing='0' cellpadding='0' border='0' class='LeftNews'>"
						+ "		<tbody><tr> "
						+ "			<td> "
						+ "				用户名： "
						+ "			</td> "
						+ "			<td> "
						+ "				<input type='text' style='width: 120px;' id='username' tabindex='0' value='' maxlength='12' size='30' name='userVO.userEntity.username'> "
						+ "				<span><img src='images/icon_ok.gif' title='验证成功'></span> "
						+ "			</td> "
						+ "		</tr> "
						+ "		<tr> "
						+ "			<td> "
						+ "				密 码： "
						+ "			</td> "
						+ "		<td> "
						+ "				<input type='password' style='width: 120px;' id='password' maxlength='20' size='30' name='userVO.userEntity.loginPassword'> "
						+ "				<span> </span> "
						+ "			</td> "
						+ "		</tr> "
						+ "		<tr> "
						+ "			<td> "
						+ "				验证码： "
						+ "			</td> "
						+ "			<td> "
						+ "				<input type='text' style='width: 60px;' id='verificationCode' value='' maxlength='4' size='30' name='userVO.verificationCode'> "
						+ "				<img style='cursor: pointer;' title='点击图片刷新验证码' onclick='changeValidateCode(this)' src='verify/verificationCode.php'> "
						+ "				<span> </span> "
						+ "			</td> "
						+ "		</tr> "
						+ "	 <tr> "
						+ "	<td nowrap='nowrap' colspan='2'> "
						+ "		<input type='image' border='0' onfocus='this.blur()' src='images/login-q_r4_c3.gif' name='imageField'> "
						+ "		<a href='userManager/base!initRegister.php'><img vspace='0' hspace='5' border='0' src='images/login-q_r4_c31.gif'> </a> "
						+ "		<a border='0' id='findPWA' href='javascript:void(0);'>找回密码</a> "
						+ "	</td> " + "	</tr>  	</tbody></table>";
				if (user == null) {
					$("#userLoginId").html(tdNo);
					$("#tableLoginUserInfoID").html(tableNo);
					$("#username").focus();
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
						+ "		<a onclick='window.external.addFavorite('http://www.2000w.net','淘宝刷信誉')' title='添加到收藏夹' href='#'>[收藏本站]</a>  ";
				var tableYes = "<table width='100%' cellspacing='0' cellpadding='0' border='0' class='LeftNews'>  "
						+ "			<tbody>  "
						+ "			<tr> "
						+ "				<td colspan='2'> "
						+ "					会&nbsp;&nbsp;&nbsp;&nbsp;员： "
						+ "					<font color='#ff0000'>"
						+ user.username
						+ " </font> 您好！ "
						+ "				</td> "
						+ "			</tr> "
						+ "			<tr> "
						+ "				<td colspan='2'> "
						+ "					您拥有： "
						+ "					<font color='#ff0000'>"
						+ user.money
						+ "</font> 元 发布点： "
						+ "					<font color='#ff0000'>"
						+ user.releaseDot
						+ "</font>点 "
						+ "				</td> "
						+ "			</tr> "
						+ "			<tr> "
						+ "				<td width='94'> "
						+ "					<a href='userInfoManager/info!initUpdateInfo.php'><font color='#ff0000'>修改资料</font> </a> "
						+ "				</td> "
						+ "				<td width='102'> "
						+ "					<a href='user/paid.jsp'><font color='#ff0000'>帐号充值</font> "
						+ "					</a> "
						+ "				</td> "
						+ "			</tr> "
						+ "			<tr> "
						+ "				<td> "
						+ "					<a href='taskManager/task!initTask.php?platformType=1'><font color='#ff0000'>免费刷钻&nbsp; </font> </a> "
						+ "				</td> "
						+ "				<td> "
						+ "					<a onclick='return confirm('确定退出操作吗？');' href='userManager/base!loginOut.php'><font color='#ff0000'> 安全退出</font> </a> "
						+ "				</td>  "
						+ "			</tr> "
						+ "		</tbody> "
						+ "	</table>";

				$("#userLoginId").html(tdYes);
				$("#tableLoginUserInfoID").html(tableYes);
				
			});

});
function validateForm() {
	submitFlag = true;
	$("input").blur();
	if (submitFlag) {
		return true;
	} else {
		alert("\u586b\u5199\u7684\u8d44\u6599\u4e0d\u6b63\u786e\uff01");
		return false;
	}
}

// 验证成功调用的方法
function validateSuccess(obj) {
	var img = $("<img></img>", {
				src : "images/icon_ok.gif",
				title : "\u9a8c\u8bc1\u6210\u529f"
			});
	var span = $(obj).next();
	span.empty();
	span.append(img);
}

// 验证失败调用的方法
function validateError(obj, msg) {
	var img = $("<img></img>", {
				src : "images/icon_error.gif",
				title : msg
			});
	var span = $(obj).next();
	span.empty();
	span.append(img);
}
