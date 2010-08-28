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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 修改资料 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>修改资料</strong>
										</div>


										<form method="post" onsubmit="return save_onclick()" id="form"
											name="form">
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
															<input type="text" disabled="disabled" value="xgj1988"
																maxlength="12" size="30" id="UserID" name="UserID">
														</td>
														<td width="524"></td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															Q&nbsp;Q:
														</td>
														<td>
															<input type="text" disabled="disabled" value="30756500"
																size="30" id="QQ" name="QQ">
														</td>
														<td>
															<span class="red-bcolor">*</span> QQ不能修改！
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															电子邮箱：
														</td>
														<td>
															<input type="text" value="30756500@qq.com" size="30"
																id="Email" name="Email">
														</td>
														<td>
															<span class="red-bcolor">*</span>取回密码使用，注册后不能更改
														</td>
													</tr>

													<tr>
														<td height="40" align="right" class="font12h">
															密码问题：
														</td>
														<td>
															<span class="font12h"> <input type="text" value=""
																	size="30" id="weiti" name="weiti"> </span>
														</td>
														<td>
															忘记密码的提示问题
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															问题答案：
														</td>
														<td>
															<span class="font12h"> <input type="text" value=""
																	size="30" id="daai" name="daai"> </span>
														</td>
														<td>
															忘记密码的问题答案
														</td>
													</tr>


													<tr>
														<td height="40" align="right" class="font12h">
															手机号码：
														</td>
														<td align="left" class="red-bcolor">
															<input type="text" value="15112355607" size="30"
																id="phone" name="phone">
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
																		<td height="40" align="right" class="font12h">
																			真实姓名：
																		</td>
																		<td width="80%">
																			<input type="text" value="" size="30" id="rname"
																				name="rname">
																		</td>
																		<td width="4%">
																			&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h">
																			店铺地址：
																		</td>
																		<td>
																			<input type="text" value="" size="30" id="HomePage"
																				name="HomePage">
																		</td>
																		<td>
																			&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h">
																			店铺描述：
																		</td>
																		<td>
																			<input type="text" value=", " size="30" id="shopnote"
																				name="shopnote">
																		</td>
																		<td>
																			&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td height="30" align="right">
																			<span class="font12h">性别：</span>
																		</td>
																		<td>
																			<input type="radio" checked="checked" value="1"
																				id="radio" name="sex">
																			男士
																			<input type="radio" value="2" id="radio2" name="sex">
																			女士
																		</td>
																		<td>
																			&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td height="45" align="right" class="font12h">
																			联系地址：
																		</td>
																		<td>
																			<input type="text" value="" size="30" id="address"
																				name="address">
																		</td>
																		<td>
																			&nbsp;
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
																name="button">
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
