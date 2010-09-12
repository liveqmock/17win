<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="images/index_top_bg.jpg">
	<tr>
		<td>
			<table width="963" border="0" align="center"
				background="images/index_top_bg.jpg">
				<TR>
					<TD>
						<MARQUEE onmouseover=this.stop() onmouseout=this.start()
							scrollAmount=2 direction=left height=18>

							<a href='news.asp?/1302.html' target="_blank" title='公告'><FONT
								color=#ff0000>8月11日起每天发10个任务当天即送5个发布点,结束时间再通知.&nbsp;&nbsp;
									接发任务时严禁用旺旺联系</FONT> </a>


						</MARQUEE>
					</TD>
				</TR>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="images/index_top_bg.jpg">
	<tr>
		<td>
			<TABLE width=969 border=0 align=center cellPadding=2 cellSpacing=0>
				<TR>
					<TD width=433>
						<A href="http://www.2000w.net" title="淘宝刷信誉,淘宝刷钻,刷钻平台"><IMG
								alt="淘宝刷信誉,淘宝刷钻,刷钻平台" src="images/201063012565665641.jpg"
								border=0> </A>
					</TD>
					<TD vAlign=top width=528>
						<TABLE cellSpacing=0 cellPadding=0 width=535 align=right border=0>
							<TR>
								<TD height=32 align="right">

									<span class="yell_font">欢迎您！</span>
									<s:if test="#session.userLogin==null">
										<A href="userManager/base!initLogin.php" target="_top">登陆</A> |
									<A href="userManager/base!initRegister.php" target="_top">
											注册</A> |
									</s:if>
									<s:else>
										<font color="red"><b><s:property
													value="#session.userLogin.username" /> 
										
										</b>
										</font> 回来 |
											<A href="userManager/base!loginOut.php" target="_self">
											[安全退出] </A>
										|
										<A href="userInfoManager/info!init.php" target="_self">
											[个人中心] </A>|
									</s:else>
									<A href="#" title=添加到收藏夹
										onclick="window.external.addFavorite('http://www.2000w.net','淘宝刷信誉')">[收藏本站]</A>
									<!-- xgj|
									<A href="help/link.asp" target="_top">广告服务</A>
									 -->
								</TD>
							</TR>
							<TR>
								<TD height=20>
									<DIV id=index_top_r2>
										<ul>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn6.jpg) no-repeat">
												<A onClick="return Button3_onclick()"
													href="javascript:void(0)">客服中心</A>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn5.jpg) no-repeat">
												<A href="system/shuakeRank.jsp">刷客排行</A>
											</LI>
											<!--  xgj
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn4.jpg) no-repeat">
												<A href="user/sms.asp">手机短信</A>
											</LI> -->
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn3.jpg) no-repeat">
												<A href="user/exchange.jsp">我要兑换</A>
											</LI>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn2.jpg) no-repeat">
												<A href="user/buyDot.jsp">买发布点</A>
											</LI>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn1.jpg) no-repeat">
												<A href="user/paid.jsp">账号充值</A>
											</LI>
										</ul>
									</DIV>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
		</td>
	</tr>
</table>

<DIV class=index_menu style="MARGIN: 0px auto">
	<DIV id=list>
		<UL>
			<LI class=white>
				<A class="li1 white" href="menuManager/menu!toIndex.php" id="a0"
					onmouseover="Mea(0);" onMouseOut="setAuto()"><SPAN
					style="COLOR: #000000">首&nbsp;页</SPAN> </A>
			</LI>
			<LI class=white>
				<A class="li0 " href="taskManager/task!initTask.php?platformType=1" id="a1" onmouseover="Mea(1);"
					onMouseOut="setAuto()">淘宝互刷</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="taskManager/task!initTask.php?platformType=2" onmouseover="Mea(2);"
					onMouseOut="setAuto()">拍拍互刷</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="taskManager/task!initTask.php?platformType=3" onmouseover="Mea(3);"
					onMouseOut="setAuto()">有啊互刷</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="credit/task.jsp" id="a4" onmouseover="Mea(4);"
					onMouseOut="setAuto()">流量互刷</A>
				<br>
			</LI>
			<LI class=white>
				<A class="li0 " href="credit/task.jsp" id="a5" onmouseover="Mea(5);"
					onMouseOut="setAuto()">收藏互刷</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="user/exchange.jsp" id="a6"
					onmouseover="Mea(6);" onMouseOut="setAuto()">赠送兑换</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="spread/index.jsp" id="a7"
					onmouseover="Mea(7);" onMouseOut="setAuto()">推广赚钱</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="user/vip.jsp" id="a8" onmouseover="Mea(8);"
					onMouseOut="setAuto()">加入VIP</A>
			</LI>
			<LI class=white>
				<A class="li0 " href="system/newer.jsp" id="a9"
					onmouseover="Mea(9);" onMouseOut="setAuto()">新手入门</A>
			</LI>
		</UL>
	</DIV>
</DIV>
