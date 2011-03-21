<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.win.utils.WinUtils"%>
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
								color=#ff0000>平台改版，不需要发布点就可以发布任务</FONT> </a>
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
						<div style="width: 350px;" id="logo">
							<a href="http://www.17win.net/" style="float: left;"><img
									border="0" src="images/logo.gif"> </a>
							<div style="float: left; padding-top: 14px; padding-left: 5px;">
								<embed width="85" height="30" style="vertical-align: middle;"
									wmode="transparent"
									src="http://www.shuazuan.com/images/clock_4.swf?TimeZone=GMT0800"
									quality="high"
									pluginspage="http://www.adobe.com/go/getflashplayer"
									type="application/x-shockwave-flash">
								<br>
								<a
									href="javascript:alert('此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）');"><img
										border="0" src="images/tubiao.png"> </a>
							</div>
						</div>
					</TD>
					<TD vAlign=top width=528>
						<TABLE cellSpacing=0 cellPadding=0 width=535 align=right border=0>
							<TR>
								<TD height=32 align="right" id="userLoginId">
									您当前的IP地址：
									<span id="ipAddress"><%=WinUtils.getIPAddress(request)%></span>&nbsp;|&nbsp;
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
										href="javascript:window.external.addFavorite('http://www.17win.net','17win(一起赢)互刷平台');"
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
												<A href="javascript:alert('暂不开放');">刷客排行</A>
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
		<UL id="showTitle">
			<LI class=white>
				<A
					<s:if test="#request.showIndexType=='index'">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else> href="index.html"
					onmouseover="Mea(this);"><SPAN>首&nbsp;页</SPAN> </A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==2">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else>
					href="taskManager/task!initTask.php?platformType=1"
					onmouseover="Mea(this);">淘宝互刷</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==3">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else>
					href="taskManager/task!initTask.php?platformType=2"
					onmouseover="Mea(this);">拍拍互刷</A>
			</LI>
			<LI class=white>
				<A class="li0" href="javascript:alert('开发中，敬请期待！');"
					onmouseover="Mea(this);">淘宝流量</A>
			</LI>
			<LI class=white>
				<A class="li0" href="javascript:alert('开发中，敬请期待！');"
					onmouseover="Mea(this);">拍拍流量</A>
			</LI>
			<LI class=white>
				<A class="li0" href="javascript:alert('开发中，敬请期待！');"
					onmouseover="Mea(this);">淘宝收藏</A>
			</LI>
			<LI class=white>
				<A class="li0" href="javascript:alert('开发中，敬请期待！');"
					onmouseover="Mea(this);">拍拍收藏</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==5">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else>
					href="payManager/pay!initPay.php" onmouseover="Mea(this);">账号充值</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType==11">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else>
					href="javascript:alert('暂不开放');" onmouseover="Mea(this);">刷客排行</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType=='help'">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else> href="help/index.html"
					onmouseover="Mea(this);">新手入门</A>
			</LI>
			<LI class=white>
				<A
					<s:if test="#request.showIndexType=='logistics'">
					class="li1 white"  	style="COLOR: #000000"
				</s:if>
					<s:else>
						class="li0"
				</s:else>
					href="logisticsManager/logistics!queryLogisticsLog.php"
					onmouseover="Mea(this);">真实快递</A>
			</LI>
		</UL>
	</DIV>
</DIV>
