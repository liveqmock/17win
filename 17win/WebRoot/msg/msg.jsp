<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
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
											<strong>发送手机短信</strong>
										</div>
										<s:form theme="simple"
											action="smsManager/sms!sendTelphone.php"
											onsubmit="return validateForm()">
											<table>
												<tbody>
													<tr>
														<td height="40" align="right">
															对方ID：
														</td>
														<td width="490">
															<s:textfield maxlength="11" name="telehpne"
																id="telehpneID">
															</s:textfield>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" valign="top">
															内容：
														</td>
														<td>
															<s:textarea name="content" id="contentID" cols="50"
																rows="8">
															</s:textarea>
															<br>
															<font id="showTip" color="red">0/70</font>
														</td>
													</tr>
													<tr>
														<td height="70">
															&nbsp;
														</td>
														<td valign="middle">
															&nbsp;&nbsp;&nbsp;
															<input type="submit" value="提交"  id="submitBTN"  
																style="height: 30px; width: 50px;" id="button"
																name="button">
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
