<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<td width="140" valign="top">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="5">
			</td>
		</tr>
	</table>
	<table id="menuToolBar" width="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="FFFFFF">
				<DIV
					style="BORDER-RIGHT: #8fc2d1 1px solid; BORDER-TOP: #8fc2d1 1px solid; BACKGROUND: url(/image/task_bg_02.jpg) #ffffff repeat-x 50% top; FLOAT: left; BORDER-LEFT: #8fc2d1 1px solid; WIDTH: 160px; BORDER-BOTTOM: #8fc2d1 1px solid">
					<table cellpadding=0 cellspacing=0 width=158 align=center>
						<tr>
							<td height=25 class="menu_title"
								onmouseover="this.className=
															'menu_title';"
								onmouseout="this.className=
															'menu_title';"
								background="images/membertitle.gif" id="menuTitle29"
								onClick="showsubmenu(29)" style="cursor: hand;">
								<span>我的账户</span>
							</td>
						</tr>
						<tr>

							<td id='submenu29'>
								<div class=sec_menu style="width: 158">
									<table cellpadding=0 cellspacing=0 align=center width=130>
										<tr>
											<td height=20>
												会&nbsp;&nbsp;&nbsp;&nbsp;员：
												<font color="#ff0000">${userLogin.username}</font> 您好
											</td>
										</tr>
										<tr>
											<td height=20>
												您拥有：
												<font color=#ff0000><s:property
														value="#session.userLogin.money"></s:property> </font> 元
											</td>
										</tr>

										<tr>
											<td height=20>
												发布点：
												<font color=#ff0000> <s:property
														value="#session.userLogin.releaseDot"></s:property> </font> 点
											</td>
										</tr>
										<tr>
											<td height=20>
												积&nbsp;&nbsp;&nbsp;&nbsp;分：
												<font color=#ff0000> <s:property
														value="#session.userLogin.convertScore"></s:property> </font>点
											</td>
										</tr>
										<tr>
											<td height=20>
												状&nbsp;&nbsp;&nbsp;&nbsp;态：
												<s:if test="#session.userLogin.status==0">
													<font color=#ff0000>没有激活</font>
												</s:if>

											</td>
										</tr>
										<tr>
											<td nowrap="nowrap">
												<a href="userInfoManager/info!init.php">点击此处进入个人中心</a>
											</td>
										</tr>
									</table>
								</div>

							</td>
						</tr>
					</table>

					<table cellpadding=0 cellspacing=0 width=158 align=center>
						<tr>
							<td height=25 class="menu_title"
								onmouseover="this.className=
															'menu_title2';"
								onmouseout="this.className=
															'menu_title';"
								background="images/membertitle.gif" id="menuTitle8"
								onClick="showsubmenu(243)" style="cursor: hand;">
								<span>个人资料管理</span>
							</td>
						</tr>
						<tr>

							<td id='submenu243'>
								<div class=sec_menu style="width: 158">
									<table cellpadding=0 cellspacing=0 align=center width=130
										class=LeftNews>
										<tr>
											<td height=20>
												<A href="userInfoManager/info!initUpdateInfo.php">修改资料</A>
											</td>
										</tr>
										<tr>
											<td height=20>
												<A href="userInfoManager/info!initUpdateInfo.php">卖号/买号</A>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="userInfoManager/info!initUpdatePassword.php">密码/操作码</a>
											</td>
										</tr>
										<tr>

											<td height=20>
												<A href="userInfoManager/info!referee.php" target="_blank">我要推广</A>
											</td>
										</tr>
										<tr>
											<td height=20>
												<A href="userInfoManager/info!myRefee.php">我的推广</A>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="system/shuakeRank.jsp">刷客排行</a>
											</td>

										</tr>

										<!-- xgj
										<tr>
											<td height=20>
												<A href="user/user.asp">站内信</A>
											</td>
										</tr>
 -->

										<tr>
											<td height=20>
												<a href="user/vip.jsp"><font color="#FF0000">加入VIP</font>
												</a>
											</td>
										</tr>
										<!-- xgj 
										<tr>

											<td height=20>
												<A onclick="return confirm('确定退出操作吗？');"
													href="user/exit.asp"> <FONT color=red>安全退出</FONT> </A>
											</td>
										</tr>
										-->
									</table>
								</div>
							</td>
						</tr>
					</table>

					<table cellpadding=0 cellspacing=0 width=158 align=center>
						<tr>
							<td height=25 class="menu_title"
								onmouseover="this.className=
															'menu_title2';"
								onmouseout="this.className=
															'menu_title';"
								background="images/membertitle.gif" id="menuTitle8"
								onClick="showsubmenu(24)" style="cursor: hand;">
								<span>财物管理</span>
							</td>
						</tr>
						<tr>
							<td id='submenu24'>
								<div class=sec_menu style="width: 158">
									<table cellpadding=0 cellspacing=0 align=center width=130
										class=LeftNews>
										<tr>

											<td height=20>
												<a href="user/paid.jsp">帐号充值</a>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="userInfoManager/info!initBuyDot.php">购买发布点</a>
											</td>

										</tr>
										<tr>
											<td height=20>
												<a href="userInfoManager/info!initExchange.php">我要兑换</a>
											</td>
										</tr>

										<tr>
											<td height=20>
												<a href="withdrawalsManager/withdrawals!initWithdrawals.php">我要提现</a>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="withdrawalsManager/withdrawals!withdrawalsLog.php">提现记录</a>
											</td>
										</tr>
										<tr>

											<td height=20>
												<a href="user/operationLog.jsp">操作记录</a>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<!--  xgj
					<table cellpadding=0 cellspacing=0 width=158 align=center>
						<tr>

							<td height=25 class="menu_title"
								onmouseover="this.className=
															'menu_title2';"
								onmouseout="this.className=
															'menu_title';"
								background="images/membertitle.gif" id=menuTitle8
								onClick="showsubmenu(242)" style="cursor: hand;">
								<span>部落管理</span>
							</td>
						</tr>
						<tr>
							<td id='submenu242'>
								<div class=sec_menu style="width: 158">
									<table cellpadding=0 cellspacing=0 align=center width=130
										class=LeftNews>
										<tr>
											<td height=20>
												<a href="../tribe/">我的部落</a>
											</td>

										</tr>
										<tr>
											<td height=20>
												<a href="../tribe/applytribe.asp">建立部落</a>
											</td>
										</tr>

										<tr>
											<td height=20>
												<a href="../tribe/manage.asp">管理部落</a>
											</td>
										</tr>

									</table>

								</div>
							</td>
						</tr>
					</table>
					
					-->
					<table cellpadding=0 cellspacing=0 width=158 align=center>
						<tr>
							<td height=25 class="menu_title"
								onmouseover="this.className=
															'menu_title2';"
								onmouseout="this.className=
															'menu_title';"
								background="images/membertitle.gif" id="menuTitle8"
								onClick="showsubmenu(241)" style="cursor: hand;">
								<span>申诉中心</span>
							</td>
						</tr>
						<tr>

							<td id='submenu241'>
								<div class=sec_menu style="width: 158">
									<table cellpadding=0 cellspacing=0 align=center width=130
										class=LeftNews>
										<tr>
											<td height=20>
												<a href="user/appeal.jsp">我要申诉</a>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="user/appealTome.jsp">我受到的申诉</a>
											</td>
										</tr>

										<tr>
											<td height=20>
												<a href="user/myAppeal.jsp">我的申诉</a><font color="#0000FF">&nbsp;</font>
											</td>
										</tr>
										<!-- xgj -->
										<tr>
											<td height=20>
												<a href="user/officalBlackList.jsp">官方黑名单</a><font
													color="#0000FF">&nbsp;</font>
											</td>
										</tr>
										<tr>
											<td height=20>
												<a href="user/myBlackList.jsp">我的黑名单</a><font
													color="#0000FF">&nbsp;</font>
											</td>

										</tr>
										<tr>
											<td height=20>
												<a href="user/officalRP.jsp">官方奖罚</a><font color="#0000FF">&nbsp;</font>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>

					</table>
				</DIV>
			</TD>
		</TR>
	</table>
</td>