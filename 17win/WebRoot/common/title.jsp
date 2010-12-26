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

							<a href='help/detail_61.html' target="_blank" title='公告'><FONT
								color=#ff0000>推广送U盘+VIP+发布点</FONT> </a>
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
						<A href="http://www.17win.net" title="淘宝刷信誉,淘宝刷钻,刷钻平台"><IMG
								alt="淘宝刷信誉,淘宝刷钻,刷钻平台" src="images/logo.gif" border=0> </A>
					</TD>
					<TD vAlign=top width=528>
						<TABLE cellSpacing=0 cellPadding=0 width=535 align=right border=0>
							<TR>
								<TD height=32 align="right" id="userLoginId">
									<s:if test="#session.userLogin==null">
										<span class="yell_font">您还没登录！</span>
										<A href="user/login.html" target="_top">登陆</A> |
									<A href="userManager/base!initRegister.php" target="_top">
											注册</A> |
									</s:if>
									<s:else>
										<span class="yell_font">欢迎您！</span>
										<font color="red"><b><s:property
													value="#session.userLogin.username" /> </b> </font> 回来 |
											<A href="userManager/base!loginOut.php" target="_self">
											[安全退出] </A>
										|
										<A href="userInfoManager/info!init.php" target="_self">
											[个人中心] </A>|
									</s:else>
									<A
										href="javascript:window.external.addFavorite('http://www.17win.net','淘宝刷信誉');"
										title="添加到收藏夹">[收藏本站]</A>
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
												style="BACKGROUND: url(images/jieducm_top_btn5.jpg) no-repeat">
												<A href="shuake/index.html">刷客排行</A>
											</LI>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn3.jpg) no-repeat">
												<A href="userInfoManager/info!initExchange.php">我要兑换</A>
											</LI>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn2.jpg) no-repeat">
												<A href="userInfoManager/info!initBuyDot.php">买发布点</A>
											</LI>
											<LI
												style="BACKGROUND: url(images/jieducm_top_btn1.jpg) no-repeat">
												<A href="payManager/pay!initPay.php">账号充值</A>
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
				<A
					<s:if test="#request.showIndexType==1">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else> href="index.html"
					id="a0" onmouseover="Mea(0);" testclass=""><SPAN>首&nbsp;页</SPAN>
				</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==2">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="taskManager/task!initTask.php?platformType=1" id="a1"
					onmouseover="Mea(1);" testclass="">淘宝互刷</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==3">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="taskManager/task!initTask.php?platformType=2" id="a2"
					onmouseover="Mea(2);" testclass="">拍拍互刷</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==4">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="taskManager/task!initTask.php?platformType=3" id="a3"
					onmouseover="Mea(3);" testclass="">有啊互刷</A>
			</LI>
			<!-- 
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==5">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else> href="credit/task.jsp"
					id="a4" onmouseover="Mea(4);" testclass="">双色球</A>
				<br>
			</LI>
			 -->
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==6">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="payManager/pay!initPay.php" id="a5" onmouseover="Mea(5);"
					testclass="">账号充值</A>
			</LI>
			<!-- 
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==7">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="userInfoManager/info!initExchange.php" id="a6"
					onmouseover="Mea(6);" testclass="">赠送兑换</A>
			</LI>
			 -->
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==8">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="userInfoManager/info!referee.php" target="_blank" id="a7"
					onmouseover="Mea(7);" testclass="">推广赚钱</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==9">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="vipManager/vip!initVip.php" id="a8" onmouseover="Mea(8);"
					testclass="">加入VIP</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==11">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else>
					href="shuake/index.html" id="a10" onmouseover="Mea(10);"
					testclass="">刷客排行</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==10">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else> href="help/index.html"
					id="a9" onmouseover="Mea(9);" testclass="">新手入门</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==11">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0 "
				</s:else> href="logisticsManager/logistics!queryLogisticsLog.php"
					id="a9" onmouseover="Mea(10);" testclass="">真实物流</A>
			</LI>
		</UL>
	</DIV>
</DIV>
