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
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
		<SCRIPT src="user/updateInfo.js" type=text/javascript></SCRIPT>
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
		<s:form action="userInfoManager/info!updateInfo.php" theme="simple"
			onsubmit="return validateForm()">
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
												您现在的位置是：个人中心 &gt;&gt; 修改资料 &gt;&gt;
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
															用户名：
														</td>
														<td width="230">
															<s:textfield name="userVO.userEntity.username" size="30"
																disabled="true" cssStyle="width:210px" id="username"
																maxlength="12"></s:textfield>
														</td>
														<td width="524"></td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															Q&nbsp;Q:
														</td>
														<td>
															<s:textfield name="userVO.userEntity.qq" id="qq"
																size="30" disabled="true" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															电子邮箱：
														</td>
														<td>
															<s:textfield id="email" name="userVO.userEntity.email"
																disabled="true" size="30" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															手机号码：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield id="telephone"
																name="userVO.userEntity.telephone" size="30"
																disabled="true" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="left" class="font12h" colspan="3">
															<table width="100%" cellspacing="0" cellpadding="0"
																border="0" align="left" id="showinfo_c1">
																<tbody>
																	<tr>
																		<td width="16%" height="40" align="right"
																			class="font14b2">
																			选填项目：
																		</td>
																		<td colspan="2">
																			<hr style="color: rgb(255, 153, 51);">
																		</td>
																	</tr>
																	<tr>

																		<td height="40" align="right" class="font12h"
																			valign="top">
																			旺旺：
																		</td>

																		<td>
																			<s:textfield id="ww" name="userVO.userEntity.ww"
																				size="30" cssStyle="width:210px"></s:textfield>
																			<br />
																			<font color="#FF0000">注意：非必须，不要用旺旺发送任何关于刷信誉的内容</font>
																		</td>

																		<td valign="top">

																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td align="center" class="red-bcolor">
															<input type="submit" value="修  改" id="button"
																style="cursor: pointer;" name="button">
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
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<s:include value="../common/footDuan.jsp"></s:include>
		</s:form>
	</BODY>
</HTML>
