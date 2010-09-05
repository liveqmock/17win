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
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/exchange.js" type="text/javascript"></SCRIPT>
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
											您现在的位置是：个人中心 &gt;&gt;我要兑换 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>我要兑换</strong>
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<ul>
												<li>
													* 如果你推荐的人越多，将获得更多的积分！
													<br>
													* 兑换成功后，系统自动相应的操作。
													<br>
													* 您的操作记录中也会有相应的记录信息

												</li>
											</ul>
										</div>
										<br>
										<br>
										<table width="95%" cellspacing="0" cellpadding="3" border="0"
											bgcolor="#deeefa" align="center">
											<tbody>
												<tr>
													<td>
														<strong>&nbsp;发布点兑换存款</strong>
														<input type="hidden" id="dot"
															value="<s:property value="#session.userLogin.releaseDot" />" />
														<input type="hidden" id="score"
															value="<s:property value="#session.userLogin.convertScore" />" />
														<input type="hidden" id="loginUsername"
															value="<s:property value="#session.userLogin.username" />" />
													</td>
												</tr>
											</tbody>
										</table>
										<s:form action="userInfoManager/info!exchange.php?flag=1"
											theme="simple" onsubmit="return validateForm('1')">
											<table width="639" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td height="35" align="right">
															<div align="right">
																兑换人：
															</div>
														</td>
														<td>
															<s:property value="#session.userLogin.username" />
															你现在的发布点是：
															<s:property value="#session.userLogin.releaseDot" />
														</td>
													</tr>
													<tr>
														<td width="134" height="35" align="right">
															<div align="right">
																我想用：
															</div>
														</td>
														<td width="505">
															<s:textfield name="userVO.releaseDot" id="releaseDot_1"></s:textfield>
															个发布点来兑换金钱(每1个发布点可以兑换 0.5元,10个发布点起兑换)
														</td>
													</tr>
													<tr>
														<td height="35">
															<div align="right">
																操作密码：
															</div>
														</td>
														<td>
															<s:password name="userVO.operationCode"
																id="operationCode_1"></s:password>
														</td>
													</tr>
													<tr>
														<td height="35">
															&nbsp;
														</td>
														<td>
															<input type="submit" value="开始兑换" id="button"
																name="button">
														</td>
													</tr>
												</tbody>
											</table>
										</s:form>
										<table width="95%" cellspacing="0" cellpadding="3" border="0"
											bgcolor="#deeefa" align="center">
											<tbody>
												<tr>
													<td>
														<strong>&nbsp;发布点互赠</strong>
													</td>
												</tr>
											</tbody>
										</table>
										<s:form action="userInfoManager/info!exchange.php?flag=2"
											theme="simple" onsubmit="return validateForm('2')">
											<div>
												<table width="614" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td height="35" align="right">
																<div align="right">
																	赠送人：
																</div>
															</td>
															<td>
																xgj1988 你现在的发布点是：
																<s:property value="#session.userLogin.releaseDot" />
															</td>
														</tr>
														<tr>
															<td width="134" height="35" align="right">
																<div align="right">
																	我要赠送给：
																</div>
															</td>
															<td width="480">
																<s:textfield name="userVO.username" id="username"></s:textfield>
																对方17win帐号
															</td>
														</tr>
														<tr>
															<td height="35">
																<div align="right">
																	赠送数量：
																</div>
															</td>
															<td>
																<s:textfield name="userVO.releaseDot" id="releaseDot_2"></s:textfield>
																个发布点
															</td>
														</tr>
														<tr>
															<td height="35">
																<div align="right">
																	操作密码：
																</div>
															</td>
															<td>
																<s:password name="userVO.operationCode"
																	id="operationCode_2"></s:password>
															</td>
														</tr>
														<tr>
															<td height="35">
																&nbsp;
															</td>
															<td>
																<input type="submit" value="开始赠送" id="button"
																	name="button">
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</s:form>
										<table width="95%" cellspacing="0" cellpadding="3" border="0"
											bgcolor="#deeefa" align="center">
											<tbody>
												<tr>
													<td>
														<strong>&nbsp;积分换发布点</strong>
													</td>
												</tr>
											</tbody>
										</table>
										<s:form action="userInfoManager/info!exchange.php?flag=3"
											theme="simple" onsubmit="return validateForm('3')">
											<div>
												<table width="614" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td height="35" align="right">
																<div align="right">
																	兑换人：
																</div>
															</td>
															<td>
																xgj1988 你现在的积分是：
																<s:property value="#session.userLogin.convertScore" />
															</td>
														</tr>
														<tr>
															<td width="134" height="35" align="right">
																<div align="right">
																	我想获得：
																</div>
															</td>
															<td width="480">
																<s:textfield name="userVO.releaseDot" id="releaseDot_3"></s:textfield>
																个发布点( 每200积分可兑换1个发布点)
															</td>
														</tr>
														<tr>
															<td height="35">
																<div align="right">
																	操作密码：
																</div>
															</td>
															<td>
																<s:password name="userVO.operationCode"
																	id="operationCode_3"></s:password>
															</td>
														</tr>
														<tr>
															<td height="35">
																&nbsp;
															</td>
															<td>
																<input type="submit" value="开始兑换" id="button"
																	name="button">
															</td>
														</tr>
													</tbody>
												</table>
											</div>
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
		<s:property value="#request.msg" escape="false"/>
	</BODY>
</HTML>
