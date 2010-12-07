<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<s:include value="../common/header.jsp"></s:include>
		<link href="css/union.css" type="text/css" rel="stylesheet" />
		<link href="css/unionCss.css" type="text/css" rel="stylesheet" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<style type="text/css">
.STYLE5 {
	font-size: 25px
}

.STYLE6 {
	font-size: 14px;
	font-weight: bold;
}
</style>
		<script type="text/javascript">
		$(document).ready(function() {
			$("#copyBtn").bind("click",function(){ 
				if (!$.browser.msie) {
							alert("仅在IE浏览器中支持！");
							return;
				}else{
				copy_code($("#page_url").val());
				alert("复制成功，请粘贴到你的QQ/QQ空间/MSN上推荐给你的好友"); }
				 });
				
			});
		</script>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="960" border="0" align="center">
			<tr>
				<td nowrap="nowrap">
					快速获得推广链接，直接进行推广：
					<input id="page_url" type="text" style="width: 500px"
						value="<%=basePath%>userManager/base!initRegister.php?spreadUsername=<s:property value="#session.userLogin.username"/>" />
				</td>
				<td align="left">
					<input type="button" id="copyBtn" style="cursor: pointer;"
						value="复&nbsp;&nbsp;制" />
				</td>
				<td align="left" width="200px">
				</td>
			</tr>
		</table>
		<table width="960" border="0" align="center">
			<tr>
				<td>
					<img src="images/gude58.jpg" width="960" height="204" />
				</td>
			</tr>
		</table>
		<table width="960" height="975" border="0" align="center">
			<tr>
				<td width="680" height="971" valign="top">
					<table width="95%" border="0">
						<tr>

							<td>
								直接联系QQ:
								<a href="tencent://message/?uin=1632630010">1632630010</a>,获得更多推广经验,咨询你心中的疑惑:
								<a href="tencent://message/?uin=1632630010"><img
										src="images/6_online.gif" width="68" height="29"
										align="absmiddle" border="0" /> </a>
							</td>
						</tr>
					</table>
					<table width="100%" height="417" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="40" background="images/Top_1.gif">
								<table width="96%" height="40" border="0" cellpadding="0"
									cellspacing="0">
									<tr>

										<td width="6%">
											&nbsp;
										</td>
										<td width="18%" height="40" background="images/tag1.gif">
											<div align="center">
												<a href="#" class="STYLE6">推广员产品</a>
											</div>
										</td>
										<td width="1%">
											<img src="images/tg_x.gif" width="2" height="40" />
										</td>
										<td width="18%">
											<div align="center" class="white_14">
												<a href="userInfoManager/info!refereeCode.php">推广代码</a>
											</div>
										</td>

										<!-- 
										<td width="18%">
											<div align="center" class="white_14">
												<a   href="userInfoManager/info!code.php"  class="white_14">经验交流</a>
											</div>
										</td>
										<td width="1%">
											<img src="images/tg_x.gif" width="2" height="40" />
										</td>

										<td width="18%">
											<div align="center" class="white_14">
												<a  href="userInfoManager/info!code.php">代码放置教程</a>
											</div>
										</td>
										
										<td width="1%">
											<img src="images/tg_x.gif" width="2" height="40" />
										</td>
										 -->
										<td width="18%"></td>

									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="162" class="wenzi">
								<a href="#union/"></a>

								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="15%">
											<img src="images/infotitle.gif" width="70" height="70"
												align="left" />
										</td>
										<td width="85%">
											什么是推广业务员？
											<br />
											广员每一个推广员都将自动获得一个自己的宣传链接，别人通过你的宣传链接注册成为会员，只要他发任务或消费，都能给您带来现金或者积分的收益！
										</td>
									</tr>
								</table>
								<div></div>
							</td>

						</tr>
						<tr>
							<td>
								<table width="100%" height="586" border="0" cellpadding="0"
									cellspacing="0" bgcolor="#E1EFF7">
									<tr>
										<td height="586">
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF" class="margin-bottom">
												<tr valign="middle">
													<td width="17%">
														<p align="center" class="da_h_zi">
															1
														</p>
														<p>
															&nbsp;
														</p>
													</td>

													<td width="45%" valign="middle" class="union_line">
														通过你的宣传链接注册的会员累计接受100次任务
														<br />
														<span class="STYLE5">你的收益=10元</span>
													</td>
													<td width="38%" valign="middle">
														<img src="images/g1.jpg" width="249" height="76" />
													</td>
												</tr>
											</table>
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF" class="margin-bottom">
												<tr valign="middle">
													<td width="17%">
														<p align="center" class="da_h_zi">
															2
														</p>

														<p>
															&nbsp;
														</p>
													</td>
													<td width="45%" valign="middle" class="union_line">
														通过你的宣传链接注册的会员积分每上升1000
														<br />
														<span class="STYLE5">你的收益=100积分</span>
													</td>
													<td width="38%" valign="middle">
														<img src="images/g2.jpg" width="243" height="67" />
													</td>
												</tr>
											</table>
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF" class="margin-bottom">
												<tr valign="middle">

													<td width="17%">
														<p align="center" class="da_h_zi">
															3
														</p>
														<p>
															&nbsp;
														</p>
													</td>
													<td width="45%" valign="middle" class="union_line">
														通过你的宣传链接注册的会员购买VIP
														<br />
														<span class="STYLE5">你的收益=5元</span>
													</td>
													<td width="38%" valign="middle">
														<img src="images/g3.jpg" width="233" height="65" />
													</td>
												</tr>
											</table>

											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF" class="margin-bottom">
												<tr valign="middle">
													<td width="17%">
														<p align="center" class="da_h_zi">
															4
														</p>
														<p>
															&nbsp;
														</p>
													</td>
													<td width="45%" valign="middle" class="union_line">
														通过你的宣传链接注册的会员购买点卡
														<br />
														<span class="STYLE5">你的收益=点卡金额*10%</span>
													</td>
													<td width="38%" valign="middle">
														<img src="images/g4.jpg" width="241" height="66" />
													</td>

												</tr>
											</table>
										</td>
									</tr>

								</table>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>

							<td>
								<img src="images/gude59.gif" width="674" height="145" />
							</td>
						</tr>
					</table>
				</td>
				<td width="10" valign="top">
					&nbsp;
				</td>
				<td width="261" valign="top">
					<table width="100%" height="215" border="0" cellpadding="0"
						cellspacing="0" class="margin-bottom">
						<tr>
							<td height="215" valign="top" background="images/tj1.gif">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="30" class="STYLE6">
											推广达人轻松月入千元
										</td>

									</tr>
									<tr>
										<td height="182" valign="top" class="wenzi1">
											<table width="104%" border="0" cellpadding="0">
												<tr>
													<td class="border_hui">
														<img src="images/no.jpg" width="48" height="48"
															align="left" />
													</td>
													<td>
														达人资料
														<br />

														会员名：
														<s:property value="#session.userLogin.username" />
														<br />
														推荐会员：
														<s:property value="#request.userCount" />
														人
														<br />
													</td>
												</tr>
												<tr>
													<td colspan="2" valign="top">
														我推广即得奖金
														<s:property value="#request.refereeMoney" />
														元
														<br />
														我推广
														<s:property value="#request.vipCount" />
														个会员成为VIP会员
														<br />
														我推广
														<s:property value="#request.userCount" />
														个会员来购买网站点卡
														<br />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table width="260" border="0" cellpadding="0" cellspacing="0"
						class="margin-bottom">
						<tbody>
							<tr>
								<td height="28" background="images/title_top.jpg">
									<span class="white_font">推广公告 &nbsp;&nbsp;&nbsp;</span>
								</td>
							</tr>
							<tr>
								<td height="203" valign="top" class="border_hui">
									<table width="95%" border="0" align="center" cellpadding="1"
										cellspacing="0" class="bleak">
										<tr>
											<td>
												<a href='../news.asp?/1412.html' target="_blank"
													title='推广会员名单'><font><strong>推广会员名单</strong> </font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1403.html' target="_blank"
													title='推广本平台成功注册一名舍员即送5元'><font color=>推广本平台成功注册一名舍员即送5元</font>
												</a>
											</td>
										</tr>
									</table>
								</td>

							</tr>
						</tbody>
					</table>
					<table width="260" border="0" cellpadding="0" cellspacing="0"
						class="margin-bottom">
						<tbody>
							<tr>
								<td height="28" background="images/title_top.jpg">
									<span class="white_font">营销排行 &nbsp;&nbsp;&nbsp;&nbsp;</span>
								</td>
							</tr>

							<tr>
								<td height="203" valign="middle" class="border_hui">
									<table width="90%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td colspan="2">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td class="border_hui">
												<div align="center">
													<img src="images/no.jpg" width="48" height="48" />
												</div>
											</td>
											<td class="wenzi1">
												a509726
												<br />

												佣金：0元
												<br />
												推荐会员：124人
											</td>
										</tr>
									</table>
									<table width="95%" height="28" border="0" align="center"
										cellpadding="8" cellspacing="0">

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													ejianqin
												</div>
											</td>

											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													380元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													13472291000
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													340元
												</div>
											</td>
										</tr>

										<tr>

											<td class="border_bottom_lan">
												<div align="center">
													0610gf
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													280元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													262605736
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													250元
												</div>
											</td>
										</tr>


										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													ke288f
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													120元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													center
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													120元
												</div>
											</td>

										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													ainidexu
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													100元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													ttqsef
												</div>
											</td>

											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													100元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													cuichuyan
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													80元
												</div>
											</td>
										</tr>

										<tr>

											<td class="border_bottom_lan">
												<div align="center">
													yiyeguan
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													80元
												</div>
											</td>
										</tr>

										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													一夜皇冠
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													70元
												</div>
											</td>
										</tr>


										<tr>
											<td class="border_bottom_lan">
												<div align="center">
													aoweidongjac
												</div>
											</td>
											<td class="border_bottom_lan">
												<div align="center" class="yell_font">
													60元
												</div>
											</td>
										</tr>

									</table>
								</td>
							</tr>
						</tbody>
					</table>

				</td>
			</tr>
		</table>
		<table width="100%" height="100" border="0" cellpadding="0"
			cellspacing="0" background="images/tg_bg.jpg">
			<tr>
				<td height="100" valign="top" class="wenzi">
					<div align="center">
						<A href="../help/about.asp"><font color="#666666">关于我们</font>
						</A> |
						<A href="../help/pay_introduce.asp"><font color="#666666">汇款方式</font>
						</A> |
						<A href="../help/link.asp"><font color="#666666">友情链接</font> </A>
						|
						<A href="../help/lianxi.asp"><font color="#666666">联系我们</font>
						</A>|
						<a href="../help/index.asp"><font color="#666666">帮助中心</font>
						</a>
						<br />

						QQ：
						<a href="tencent://message/?uin=904555181">904555181</a> 电话：
						<br />
						Copyright 2008 - 2009 版权所有 沪ICP备09035337号
						<br />
					</div>
				</td>
			</tr>
		</table>

		<div class="service2  service serviceA " id="divSerWin">
	<div class="service-open" id="divMySer">
		<div onclick="ClickSer();" class="service-button"></div>
		<div class="service-inside">
			<dl>
				<dt>
					客服工作时间
				</dt>
				<dd class="esp_4">
					周一至周五
				</dd>
				<dd class="esp_4">
					9:00 - 18:30
				</dd>
				<dd class="esp_5">
					<!--<a href="#" target="_blank">自助服务</a>-->
				</dd>
				<dt>
					客服团队
				</dt>
				<dd>

					<!-- 1348001415 -->
					<a class="serviceA" href="tencent://message/?uin=1442418675">新手帮助</a>
				</dd>
				<dd>
					<a class="serviceA" href="tencent://message/?uin=1632630010">客户帮助</a>
				</dd>
				<dd>
					<a href="tencent://message/?uin=1637708507">充值帮助</a>
				</dd>
				<dd>
					<a href="tencent://message/?uin=1637708507">提现帮助</a>
				</dd>
				<dd class="esp_1">
					<a href="tencent://message/?uin=1206418689">投诉建议</a>
				</dd>
				<dt>
					QQ交流群
				</dt>
				<dd class="esp_3">
					100103766
				</dd>
				<dt >
					我们的宗旨
				</dt>
				<dd class="esp_4">
					安全第一
				</dd>
				<dd class="esp_4">
					和谐刷钻
				</dd>
				<dd class="esp_4">
					大家互赢
				</dd>
				<dd class="esp_4">
					互相监督
				</dd>
			</dl>
		</div>
	</div>
</div>
	</BODY>
</HTML>
