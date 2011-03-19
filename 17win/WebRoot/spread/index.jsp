<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<s:include value="../common/header.jsp"></s:include>
		<link href="css/union.css" type="text/css" rel="stylesheet" />
		<link href="css/unionCss.css" type="text/css" rel="stylesheet" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<style type="text/css">
.STYLE5 {
	font-size: 25px
}

.STYLE6 {
	font-size: 14px;
	font-weight: bold;
}
</style>
		<script type="text/javascript">
		$(document).ready(function() {
			$("#copyBtn").bind("click",function(){ 
				copyToClipboard($("#page_url").val(),"复制成功，请粘贴到你的QQ/QQ空间/MSN上推荐给你的好友");
				
				 });
				
			});
		</script>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="960" border="0" align="center">
			<tr>
				<td nowrap="nowrap">
					快速获得推广链接，直接进行推广：
					<input id="page_url" type="text" style="width: 500px"
						value="<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>" />
				</td>
				<td align="left">
					<input type="button" id="copyBtn" style="cursor: pointer;"
						value="复&nbsp;&nbsp;制" />
				</td>
				<td align="left" width="200px">
				</td>
			</tr>
		</table>
		<table width="960" border="0" align="center">
			<tr>
				<td>
					<img src="images/gude58.jpg" width="960" height="204" />
				</td>
			</tr>
		</table>

		<table width="960" height="100" border="0" cellpadding="0"
			cellspacing="0" background="images/tg_bg.jpg">
			<tr>
				<td height="100" valign="top" class="wenzi">
					<div align="center">
						<a href="/help/about.html">关于我们</a> |
						<a href="mailto:service@17win.net">联系我们</a> |
						<a href="mailto:service@17win.net">建议留言</a> |
						<a href="/help/index.html">帮助中心</a>
					</div>
				</td>
			</tr>
		</table>

		<div class="service2  service serviceA " id="divSerWin">
			<div class="service-open" id="divMySer">
				<div onclick="ClickSer();" class="service-button"></div>
				<div class="service-inside">
					<dl>
						<dt class='title'>
							客服工作时间
						</dt>
						<dd class="esp_4">
							周一至周五
						</dd>
						<dd class="esp_4">
							9:00 - 18:30
						</dd>
						<dd class="esp_5">
							<!--<a href="#" target="_blank">自助服务</a>-->
						</dd>
						<dt class='title'>
							客服团队
						</dt>
						<dt class="content">

							<!-- 1348001415 -->
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" /> 新手帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" />客服帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" />充值帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" /> 投诉建议</a>
						</dt>
						<dt class="content">
							<a target="_blank"
								href="http://amos1.taobao.com/msg.ww?v=2&uid=lxq380712448&s=1"><img
									border="0"
									src="http://amos1.taobao.com/online.ww?v=2&uid=lxq380712448&s=1"
									alt="联系旺旺客服" /> </a>
						</dt>
						<dt class='title'>
							QQ交流群
						</dt>
						<dd class="esp_3">
							100103766
						</dd>
						<dt class='title'>
							我们的宗旨
						</dt>
						<dd class="esp_4">
							安全第一
						</dd>
						<dd class="esp_4">
							和谐互刷
						</dd>
						<dd class="esp_4">
							大家互赢
						</dd>
						<dd class="esp_4">
							互相监督
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</BODY>
</HTML>
