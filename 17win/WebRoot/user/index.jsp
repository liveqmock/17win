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
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/index.js" type="text/javascript"></SCRIPT>
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
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td height="5"></td>
									</tr>
								</table>

								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									bgcolor="#fefcde" class="bordera">
									<tr>
										<td height="30" align="left" background="images/zc-bg.gif">
											&nbsp;&nbsp;个人中心：
											<s:fielderror></s:fielderror>
										</td>
									</tr>
									<tr>
										<td height="500" valign="top">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" id="con_three_1">

												<tr>
													<td height="55" align="center" class="borderc" valign="top">
														<b>你好： </b>
													</td>

													<td align="left" class="borderc" nowrap="nowrap">
														<Font color="#FF0000"><s:property
																value="#session.userLogin.username"></s:property>(<s:if
																test="#session.userLogin.vipEnable">
																<img
																	src="images/vip/<s:property value="@net.win.utils.StrategyUtils@getVipImg(#session.userLogin.vipType,#session.userLogin.vipEnable)" />" />
															到期时间：<s:date name="#session.userLogin.vipEndDate"
																	format="yyyy-MM-dd" />
															当前成长值：<s:property value="#session.userLogin.vipGrowValue" />
															</s:if> <s:else>
														您还没有加入VIP
														</s:else>)<b><br />(<img
																	src="images/<s:property value="#session.userLogin.levelImg" />" />注册时间：<s:date format="yyyy-MM-dd hh:mm:ss"
																	name="#session.userLogin.registerTime" />)</b> </Font>
													</td>
													<td width="63%" align="left" class="borderc">
														&nbsp;
														<a href="md5_pay.asp"><img src="images/congzhi.jpg"
																title="我要充值" width="140" height="40" border="0" /> </a>&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
												<tr>
													<td height="55" align="center" class="borderc">
														&nbsp;
													</td>
													<td colspan="2" align="left" class="borderc">
														你上次登陆的时间是：
														<s:date format="yyyy-MM-dd hh:mm:ss"
															name="#session.userLogin.lastLoginTime" />
														<font color="#FF0000"></font>，如果你在这个时间没有登陆过，请联系客服。
													</td>

												</tr>
												<tr>
													<td height="55" align="center" class="borderc">
														&nbsp;
													</td>
													<td colspan="2" align="left" class="borderc">
														你的存款：
														<font color="#FF0000"><s:property
																value="#session.userLogin.money"></s:property> </font> 元； 发布点：
														<font color="#FF0000"><s:property
																value="#session.userLogin.releaseDot"></s:property> </font>点；
														刷客级别：
														<img style="cursor: pointer;"
															src="images/<s:property value="#session.userLogin.levelImg"/>"
															alt="您的刷客级别为：<s:property value="#session.userLogin.level"/>"
															align="top">

													</td>
												</tr>

												<tr>
													<td height="55" align="center" class="borderc">
														&nbsp;
													</td>
													<td colspan="2" align="left" class="borderc">
														<font color="#FF0000"><font color="#FF0000"></font>&nbsp;
														</font>
														<a href="record.asp">查看交易记录</a>
													</td>
												</tr>
												<!-- 
												<tr>

													<td height="55" align="center" class="borderc">
														&nbsp;
													</td>

													<td colspan="2" align="left" class="borderc">
														你现在有
														<A href="user.asp"><font color="#FF0000">0</font>
															封系统站内信</A>没有阅读，
														<A href="users.asp"><font color="#FF0000">0 </font>封私人站内信</A>没有阅读
													</td>
												</tr>
												<tr>
													<td width="14%" height="55" align="center" class="borderc">
														&nbsp;
													</td>

													<td colspan="2" align="left" class="borderc">
														你现在收到
														<A href="complaintre.asp"><font color="#FF0000">0</font>个</A>被申诉，请尽快处理
													</td>
												</tr>
												 -->
												<tr>
													<td height="55" align="center" class="borderc" valign="top"
														nowrap="nowrap">
														<b> 信誉互刷（买家操作） </b>
													</td>
													<td colspan="2" align="left" class="borderc">
														<table cellpadding="1" cellspacing="1" border="0px"
															align="center" width="100%">
															<tr align="center">
																<th style="background: #D1ECF9">
																	平台
																</th>
																<th style="background: #D1ECF9">
																	等待我付款
																</th>
																<th style="background: #D1ECF9">
																	等待卖家发货
																</th>
																<th style="background: #D1ECF9">
																	等待我收货好评
																</th>
																<th style="background: #D1ECF9">
																	等待卖家好评
																</th>
																<th style="background: #D1ECF9">
																	合计
																</th>
															</tr>
															<s:iterator value="#request.sellTasks" id="selltask">
																<tr>
																	<s:iterator value="selltask" id="task">
																		<td align="center" />
																			<s:property value="task" />
																		</td>
																	</s:iterator>
																</tr>
															</s:iterator>
														</table>
													</td>

												</tr>
												<tr>
													<td height="55" align="center" class="borderc" valign="top">
														<b> 信誉互刷（卖家操作） </b>
													</td>
													<td colspan="2" align="left" class="borderc">
														<table cellpadding="1" cellspacing="1" border="0px"
															width="100%">
															<tr align="center">
																<th style="background: #D1ECF9">
																	平台
																</th>
																<th style="background: #D1ECF9">
																	等待接手
																</th>
																<th style="background: #D1ECF9">
																	等待审核
																</th>
																<th style="background: #D1ECF9">
																	等待卖家付款
																</th>
																<th style="background: #D1ECF9">
																	等待我发货
																</th>
																<th style="background: #D1ECF9">
																	等待买家确认好评
																</th>
																<th style="background: #D1ECF9">
																	等待我好评
																</th>
																<th style="background: #D1ECF9">
																	定时任务
																</th>
																<th style="background: #D1ECF9">
																	合计
																</th>
															</tr>
															<s:iterator value="#request.buyTasks" id="buyTask">
																<tr>
																	<s:iterator value="buyTask" id="task">
																		<td align="center"">
																			<s:property value="task" />
																		</td>
																	</s:iterator>
																</tr>
															</s:iterator>
														</table>
													</td>

												</tr>
												<tr>
													<td height="55" align="center" class="borderc">
														&nbsp;
													</td>
													<td colspan="2" align="left" class="borderc">
														平台提示：
														<font color="#FF0000">请尽快核对货款，按卖家规定时间完成平台任务，超过规定时间12小时后，会员有权进行申诉处理！</font>
														<br />
													</td>
												</tr>
											</table>
											<!--2项 -->
										</td>
									</tr>


								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
