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
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/updatePW.js" type=text/javascript></SCRIPT>
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
		<s:form action="userInfoManager/info!updatePassword.php"
			theme="simple" onsubmit="return validateForm()">
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
												<td height="5">
													#re
												</td>
											</tr>
										</tbody>
									</table>
									<div class="pp9">
										<div style="padding-bottom: 15px; width: 97%;">
											<div class="pp7">
												您现在的位置是：个人中心 &gt;&gt; 密码/操作密码 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>修改资料</strong>
											</div>



											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td height="10" colspan="3">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td width="146" height="40" align="right" class="font12h">
															当前操作密码：
														</td>
														<td width="230">
															<s:password id="oldPpertationCode"
																name="userVO.operationCode" size="30"
																cssStyle="width:210px"></s:password>
															<br />
															修改密码需要提供当前操作密码

														</td>
														<td width="524">

														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															登录密码：
														</td>
														<td>
															<s:password id="password"
																name="userVO.userEntity.loginPassword" size="30"
																cssStyle="width:210px"></s:password>
															<br />
															留空则不修改
														</td>

														<td>

														</td>
													</tr>

													<tr>
														<td height="40" align="right" class="font12h">
															确认登录密码：
														</td>
														<td>
															<input type="password" id="rePassword" size="30"
																style="width: 210px">
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
													</tr>


													<tr>
														<td height="40" align="right" class="font12h">
															操作密码：
														</td>
														<td align="left" class="red-bcolor">
															<s:password id="opertationCode"
																name="userVO.userEntity.opertationCode" size="30"
																cssStyle="width:210px"></s:password>
															<br />
															留空则不修改
														</td>
														<td>

														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															确认操作密码：
														</td>
														<td align="left" class="red-bcolor">
															<input type="password" id="reOperationCode" size="30"
																style="width: 210px" />
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td height="40" align="left" class="font12h" colspan="3">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td align="center" class="red-bcolor">
															<input type="submit" value="修  改" id="button"
																name="button">
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
												</tbody>
											</table>
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
		</s:form>
		<s:property value="#request.msg" escape="false" />
	</BODY>
</HTML>
