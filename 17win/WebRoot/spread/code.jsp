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
			$("input[id*=copyBtn]").bind("click",function(){ 
				 var index=$(this).attr("id").split("_")[1];
						copyToClipboard($("#copyTxt"+"_"+index).val(),"复制成功，请粘贴到你的QQ/QQ空间/MSN上推荐给你的好友");
				
			});
		</script>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="960" border="0" align="center">
			<tr>
				<td nowrap="nowrap">
					快速获得推广链接，直接进行推广：
					<input id="copyTxt_0" type="text" style="width: 500px"
						value="<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>" />
				</td>
				<td align="left">
					<input type="button" id="copyBtn_0" style="cursor: pointer;"
						value="复&nbsp;&nbsp;制" />
				</td>
				<td align="left" width="200px">
				</td>

			</tr>
		</table>
		<table width="960" height="140" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<img src="images/tui.jpg" width="960" height="176" />
				</td>
			</tr>
		</table>
		<table width="960" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<th width="157">
						<table width="303" height="31" border="0" cellpadding="0"
							cellspacing="1" bgcolor="#D0DBE3">

							<tr>
								<td bgcolor="#F0F7FB">
									<div align="center">
										文字推广
									</div>
								</td>
							</tr>
						</table>
					</th>
				</tr>

			</tbody>
		</table>
		<table width="960" border="0" align="center" cellpadding="10"
			cellspacing="0" bgcolor="#F0F7FB" class="border_hui margin-bottom">
			<tbody>
				<tr>
					<th width="157">
						<img height="22" alt="" src="images/union_01.gif" width="25"
							align="absmiddle" />
						<a>文字代码&nbsp;<img src="images/3jiaoxia.gif" border="0" /> </a>
					</th>
					<td width="595">
						适合发给QQ/MSN好友、个人坛签名或个人博客
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<table cellpadding="10" class="getCode" id="type_a">
							<tbody>
								<tr>
									<td class="em" width="31">
										<em>1</em>
									</td>
									<td width="453">
										样式一
									</td>
									<td width="252">
										发给你的QQ/MSN好友
									</td>

								</tr>
								<tr>
									<td class="em"></td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="Input" id="copyTxt_1" style="width: 400px"
											value="<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>"
											size="46" />
									</td>
									<td>
										<input type="button" id="copyBtn_1" style="cursor: pointer;"
											value="复&nbsp;&nbsp;制" />
									</td>
								</tr>
								<tr>

									<td colspan="3">
										<hr />
									</td>
								</tr>
								<tr>
									<td class="em">
										<em>2</em>
									</td>
									<td>
										样式二
									</td>
									<td>
										适合空间/博客友情链接 或论坛签名
									</td>

								</tr>
								<tr>
									<td class="em"></td>
									<td>
										HTML:
										<input name="Input" id="copyTxt_2" style="width: 400px"
											value="&lt;a href='<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>'&lt;/a&gt;"
											size="46" />
										<br />
										<br />
										UBB:&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="Input" id="copyTxt_3" style="width: 400px"
											value="[url=<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>[/url]"
											size="46" />

										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td valign="top">
										<input type="button" id="copyBtn_2" style="cursor: pointer;"
											value="复&nbsp;&nbsp;制" />
										<br />
										<br />
										<input type="button" id="copyBtn_3" style="cursor: pointer;"
											value="复&nbsp;&nbsp;制" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<hr />
									</td>

								</tr>
								<tr>
									<td class="em">
										<em>3</em>
									</td>
									<td>
										样式三
									</td>
									<td>
										适合空间/博客友情链接 或论坛签名
									</td>
								</tr>
								<tr>

									<td class="em"></td>
									<td>
										HTML:
										<input name="Input" id="copyTxt_4" style="width: 400px"
											value="&lt;a href='http://www.17win.net/register.asp?promotion=xgj1988' target='_blank'&gt;xgj1988'&lt;/a&gt;"
											size="46" />
										<br />
										<br />
										UBB:&nbsp;&nbsp;&nbsp;&nbsp
										<input name="Input" id="copyTxt_5" style="width: 400px"
											value="[url=http://www.17win.net/register.asp?promotion=xgj1988]xgj1988[/url]"
											size="46" />
										<br />
										&nbsp;&nbsp;&nbsp;
									</td>

									<td valign="top">
										<input type="button" id="copyBtn_4" style="cursor: pointer;"
											value="复&nbsp;&nbsp;制" />
										<br />
										<br />
										<input type="button" id="copyBtn_5" style="cursor: pointer;"
											value="复&nbsp;&nbsp;制" />
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" height="100" cellspacing="0" cellpadding="0"
			border="0" background="images/tg_bg.jpg">
			<tbody>
				<tr>
					<td valign="top" height="100" class="wenzi">
						<div align="center">
							<a href="/help/about.html">关于我们</a> |
							<a href="mailto:service@17win.net">联系我们</a> |
							<a href="mailto:service@17win.net">建议留言</a> |
							<a href="/help/index.html">帮助中心</a>
						</div>
					</td>
				</tr>
			</tbody>
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
							src="http://wpa.qq.com/pa?p=1:1442418675:17" />	新手帮助</a>
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
					132533972
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
