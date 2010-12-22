function changeValidateCode(obj) {
	// 获取当前的时间作为参数，无具体意义
	var timenow = new Date().getTime();
	// 每次请求需要一个不同的参数，否则可能会返回同样的验证码
	// 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	obj.src = "verify/verificationCode.php?time=" + timenow;
}
var submitFlag = true;
$(document).ready(function() {
	$("#newnotice").floatdiv("leftbottom");
	$(window).load(function() {
				$("#newnotice").slideDown("slow");
			})
	$("#tomin").click(function() {
				$("#noticecon", "#newnotice").slideUp();
			});
	$("#tomax").click(function() {
				$("#noticecon", "#newnotice").slideDown();
			});
	$("#toclose").click(function() {
				$("#newnotice").hide();
			});

	// //// ajax
	$.getJSON("userManager/base!getLoginUser.php?time=" + new Date().getTime(),
			function(data) {
				if (data == null || data.loginInfo==null) {
					return;
				}
				var user=data.loginInfo;
				var tdYes = "<span class='yell_font'>欢迎您！</span> "
						+ "			<font color='red'><b>"
						+ user.username
						+ " </b> </font> 回来 |  "
						+ "				<a target='_self' href='userManager/base!loginOut.php'> "
						+ "				[安全退出] </a> "
						+ "			|    "
						+ "			<a target='_self' href='userInfoManager/info!init.php'> "
						+ "				[个人中心] </a>|  "
						+ "		<a  href='javascript:window.external.addFavorite(\"http://www.17win.net\",\"淘宝刷信誉\");'	title='添加到收藏夹'>[收藏本站]</a>  ";
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
