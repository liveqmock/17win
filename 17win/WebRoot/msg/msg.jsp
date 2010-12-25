<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">
		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<script src="msg/msg.js" type="text/javascript"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
body {
	
}

img {
	vertical-align: bottom;
	border: 0px;
}

.sec_menu {
	border-left: 1px solid white;
	border-right: 1px solid white;
	border-bottom: 1px solid white;
	overflow: hidden;
}

.menu_title {
	
}

.menu_title span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}

.menu_title2 {
	
}

.menu_title2 span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}
</style>

	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="760" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
					<table width="910" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<!-- xgj   user left menu-->
							<s:include value="../common/user/infoMenu.jsp"></s:include>
							<!-- end xgj -->
							<td width="15">
								&nbsp;
							</td>

							<!-- 要插入的 -->
							<td valign="top">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td height="5"></td>
										</tr>
									</tbody>
								</table>
								<div class="pp9">
									<div style="padding-bottom: 15px; width: 97%;">
										<div class="pp7">
											您现在的位置是：个人中心 &gt;&gt;短信管理 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>发送手机短信<font color="red">(会员功能)</font> </strong>
										</div>
										<s:form theme="simple"
											action="smsManager/sms!sendTelphone.php"
											onsubmit="return validateForm()">
											<table>
												<tbody>
													<s:if test="#request.remainMsgCount!=null">
														<tr>
															<td height="40" align="left" style="color: red"
																colspan="2">
																&nbsp;&nbsp;您当前剩余
																<b><s:property value="#request.remainMsgCount" /> </b>条短信可以发送!
															</td>
														</tr>
													</s:if>
													<s:if test="#request.noMsgCount!=null">
														<tr>
															<td height="40" align="left" style="color: red"
																colspan="2">
																&nbsp;&nbsp;您当前剩余的短信数量为
																<b>0</b>，不能发送。
															</td>
														</tr>
													</s:if>
													<tr>
														<td height="40" align="right">
															发送类型：
														</td>
														<td width="490">
															<select name="sendType" id="sendTypeId"
																onchange="selectSendType(this)">
																<option value="1">
																	站内用户
																</option>
																<option value="2">
																	自定义发送
																</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" id="showName">
															用户ID：
														</td>
														<td width="490">
															<input type="text" maxlength="12" name="telehpne"
																id="telehpneID" />
														</td>
													</tr>
													<tr>
														<td height="40" align="right" valign="top">
															内容：
														</td>
														<td>
															<textarea name="content" id="contentID" cols="50"
																rows="8"></textarea>
															<br>
															<font id="showTip" color="red">0/70</font>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" id="showName">
															操作码：
														</td>
														<td width="490">
															<input type="password" maxlength="20"
																name="opertaionCode" id="opertaionCodeID" />
														</td>
													</tr>
													<tr>
														<td height="70">
															&nbsp;
														</td>
														<td valign="middle">
															&nbsp;&nbsp;&nbsp;
															<input type="submit" value="提交" id="submitBTN"
																<s:if test="#request.notVIP!=null || #request.noMsgCount!=null">
																	disabled="disabled"
																</s:if>
																style="height: 30px; width: 50px;" id="button"
																name="button">
															<input type="hidden" name="win17_token"
																value="<s:property value="#session.win17_token"/>">
														</td>
													</tr>
												</tbody>
											</table>
										</s:form>
									</div>
								</div>
							</td>
							<!-- end -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
