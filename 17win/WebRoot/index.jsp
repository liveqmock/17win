<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<link href="http://www.17win.net/favicon.ico" rel="shortcut icon" />
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<base href="<%=basePath%>">
		<title>淘宝刷信誉 拍拍刷信誉  -淘宝刷钻
			_刷钻平台_互刷平台_全球首个完全免费刷钻_真实快递首选一起赢(17win)平台</title>
		<meta
			content="淘宝刷信誉,拍拍刷信誉,淘宝刷钻,刷钻平台,互刷平台,真实快递,全球首个完全免费刷钻首选一起赢(17win)刷客平台.第三方担保,保证用户在淘宝刷信誉,淘宝刷钻的资金安全。一起赢(17win)互刷平台更可免费刷钻。"
			name="description" />
		<meta content="淘宝刷信誉,淘宝刷钻,刷钻平台,互刷平台,全球首个完全免费刷钻,真实快递" name="keywords" />
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/service.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/jieducm_pupu.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/jquery-1.4.2.min.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/service.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/x_alt.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="index.js" type="text/javascript"></SCRIPT>
	</HEAD>
	<BODY>
		<s:form action="userManager/base!login.php" theme="simple"
			onsubmit="return validateForm()">
			<s:token></s:token>
			<s:include value="common/title.jsp"></s:include>
			<TABLE cellSpacing="0" cellPadding="0" width="960" align="center"
				border="0">
				<TR>
					<TD vAlign=top width=270>
						<table width="260" cellspacing="0" cellpadding="0" border="0">
							<tbody>
								<tr>
									<td width="20">
										<img width="20" height="32" src="images/Top_10.gif">
									</td>
									<td width="10">
										<img width="10" height="32" src="images/Top_9.gif">
									</td>
									<td width="95" background="images/Top_12.gif" class="K_mttitle">
										用户登录
									</td>
									<td width="10">
										<img width="10" height="32" src="images/Top_13.gif">
									</td>
									<td width="119" background="images/Top_11.gif"></td>
									<td width="6">
										<img width="6" height="32" src="images/Top_14.gif">
									</td>
								</tr>
								<tr>
									<td colspan="6" style="padding: 10px;" class="K_mtcontent"
										id="tableLoginUserInfoID">
										<table width="100%" cellspacing="0" cellpadding="0" border="0"
											class="LeftNews">
											<tbody>
												<tr>
													<td>
														用户名：
													</td>
													<td>
														<input type="text" name="userVO.userEntity.username"
															size="30" maxlength="12" value="" tabindex="0"
															id="username" style="width: 120px;">
														<span><img title="验证成功" src="images/icon_ok.gif">
														</span>
													</td>
												</tr>
												<tr>
													<td>
														密 码：
													</td>
													<td>
														<input type="password"
															name="userVO.userEntity.loginPassword" size="30"
															maxlength="20" id="password" style="width: 120px;">
														<span> </span>
													</td>
												</tr>
												<tr>
													<td>
														验证码：
													</td>
													<td>
														<input type="text" name="userVO.verificationCode"
															size="30" maxlength="4" value="" id="verificationCode"
															style="width: 60px;">
														<img id="verificationCodeID"
															onclick="changeValidateCode(this)" title="点击图片刷新验证码"
															style="cursor: pointer;">
														<span> </span>
													</td>
												</tr>
												<tr>
													<td nowrap="nowrap" colspan="2">
														<input type="image" border="0" name="imageField"
															src="images/login-q_r4_c3.gif" onfocus="this.blur()">
														<a href="userManager/base!initRegister.php"><img
																vspace="0" hspace="5" border="0"
																src="images/login-q_r4_c31.gif"> </a>
														<a href="userInfoManager/info!initFindPassword.php"
															id="findPWA" border="0">找回密码</a>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>

						<table width=260 border=0 cellPadding=0 cellSpacing=0>
							<tr height="3">
								<td height="3"></td>
							</tr>
						</table>
						<table width=260 border=0 cellPadding=0 cellSpacing=0>
							<tr height="3">
								<td height="3"></td>
							</tr>
						</table>
						<TABLE cellSpacing=0 cellPadding=0 width=260 border=0>
							<TR>
								<TD width=20>
									<IMG height=32 src="images/Top_10.gif" width=20>
								</TD>
								<TD width=10>
									<IMG height=32 src="images/Top_9.gif" width=10>
								</TD>
								<TD class=K_mttitle width=95 background=images/Top_12.gif>
									新手入门
								</TD>
								<TD width=10>
									<IMG height=32 src="images/Top_13.gif" width=10>
								</TD>
								<TD width=119 background=images/Top_11.gif></TD>
								<TD width=6>
									<IMG height=32 src="images/Top_14.gif" width=6>
								</TD>
							</TR>
							<TR>
								<TD class="K_mtcontent" width="260"
									style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
									colSpan="99">
									<TABLE class="LeftNews" cellSpacing=0 cellPadding=0
										style="table-layout: fixed" width="100%" border="0">
										<s:iterator value="#request.xsResult" status="status" id="obj">
											<tr>
												<td width="100%" nowrap="nowrap"
													style="overflow: hidden; text-overflow: ellipsis;">
													<a href='help/<s:property value="#obj.url"/>'
														target="_blank" title='<s:property value="#obj.title"/>'><font
														style="font-size: 12px"><s:property
																value="#obj.title" /> </font> </a>
												</td>
											</tr>
										</s:iterator>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</TD>
					<TD vAlign=top align=right width=690>
						<table width=100% border=0 cellPadding=0 cellSpacing=0
							style="BORDER-TOP: #72c2ed 1px solid; BORDER-RIGHT: #72c2ed 1px solid; BORDER-LEFT: #72c2ed 1px solid; BORDER-BOTTOM: #72c2ed 1px solid">
							<tr>
								<td>
									<div align="center">


										<SCRIPT language=javascript>
linkarr = new Array();
picarr = new Array();
textarr = new Array();
var swf_width=688;
var swf_height=200;
var files = "";
var links = "";
var texts = "";

linkarr[1] = "taskManager/task!initTask.php?platformType=1";
picarr[1] = "images/indexSlider/slider1.gif";
textarr[1] = "新平台更加稳定，更加人性化，操作更方便"; 
linkarr[2] = "logisticsManager/logistics!queryLogisticsLog.php";
picarr[2] = "images/indexSlider/slider2.gif";
textarr[2] = "真实快递信息您做主！";
linkarr[3] = "user/login.html";
picarr[3] = "images/indexSlider/slider3.gif";
textarr[3] = "互刷改变你生活，永久免费";
linkarr[4] = "taskManager/task!initTask.php?platformType=1";
picarr[4] = "images/indexSlider/slider4.gif";
textarr[4] = "边做生意边刷信誉";
for(i=1;i<picarr.length;i++){
  if(files=="") files = picarr[i];
  else files += "|"+picarr[i];
}

for(i=1;i<linkarr.length;i++){
  if(links=="") links = linkarr[i];
  else links += "|"+linkarr[i];
}

for(i=1;i<textarr.length;i++){
  if(texts=="") texts = textarr[i];
  else texts += "|"+textarr[i];
}

document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
document.write('<param name="movie" value="images/bcastr3.swf"><param name="quality" value="high">');
document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'">');
document.write('<embed src="images/bcastr3.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>'); 
</SCRIPT>

									</div>
								</td>
							</tr>
						</table>
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>

								<TR height="3">
									<TD height="3"></TD>
								</TR>
								<TR>
									<TD>
										<TABLE cellSpacing=0 cellPadding=0 width=690 border=0>
											<TBODY>
												<TR>
													<TD class=index_tt
														style="BORDER-RIGHT: #6bbfec 1px solid; BORDER-TOP: #6bbfec 1px solid; BORDER-LEFT: #6bbfec 1px solid; BORDER-BOTTOM: #6bbfec 1px"
														align=left colSpan=2 height=30>

														&nbsp; 买、卖家教程
													</TD>
												</TR>
												<TR>
													<TD align=left background=images/Top_25.gif colSpan=2>
														<IMG src="images/Top_26.gif">
													</TD>
												</TR>
												<TR>

													<TD align=left width=346>
														<TABLE style="BACKGROUND: url(Images/Top_30.gif) repeat-y"
															cellSpacing=0 cellPadding=0 width=340 border=0>
															<TBODY>
																<TR>
																	<TD class=index_tt
																		style="BACKGROUND: url(Images/Top_28.gif) no-repeat"
																		height=45>
																		&nbsp; 卖家教程
																	</TD>
																</TR>
																<TR>

																	<TD height=120>
																		<TABLE cellSpacing=0 cellPadding=0 width=300
																			align=center border=0>
																			<TBODY>
																				<TR>
																					<TD width="65%" height=30>
																						<table class="LeftNews" cellSpacing=0
																							cellPadding=0 style="table-layout: fixed"
																							width="100%" border=0>
																							<s:iterator value="#request.sellerResult"
																								status="status" id="obj">
																								<tr>
																									<td nowrap="nowrap"
																										style="overflow: hidden; text-overflow: ellipsis;">
																										<a href='help/<s:property value="#obj.url"/>'
																											target="_blank"
																											title='<s:property value="#obj.title"/>'><font
																											style="font-size: 12px"><s:property
																													value="#obj.title" /> </font> </a>
																									</td>
																								</tr>
																							</s:iterator>

																						</table>
																					</TD>
																					<TD align=right width="35%" rowSpan=4>
																						<a href="newmore.asp?action=92"><IMG
																								src="images/jieducm_27.gif" width=84 height=88
																								border="0"> </a>

																					</TD>
																				</TR>
																			</TBODY>
																		</TABLE>
																	</TD>
																</TR>
																<TR>
																	<TD
																		style="BACKGROUND: url(Images/Top_31.gif) no-repeat"
																		height=7></TD>
																</TR>

															</TBODY>
														</TABLE>
													</TD>
													<TD align=right width=344>
														<TABLE style="BACKGROUND: url(Images/Top_30.gif) repeat-y"
															cellSpacing=0 cellPadding=0 width=340 border=0>
															<TBODY>
																<TR>
																	<TD class=index_tt
																		style="BACKGROUND: url(Images/Top_28.gif) no-repeat"
																		align=left height=45>
																		&nbsp; 买家教程
																	</TD>

																</TR>
																<TR>
																	<TD height=120>
																		<TABLE cellSpacing=0 cellPadding=0 width=300
																			align=center border=0>
																			<TBODY>
																				<TR>
																					<TD align=left width="65%" height=30>
																						<table class="LeftNews" cellSpacing=0
																							cellPadding=0 style="table-layout: fixed"
																							width="100%" border=0>
																							<s:iterator value="#request.buyerResult"
																								status="status" id="obj">
																								<tr>
																									<td nowrap="nowrap"
																										style="overflow: hidden; text-overflow: ellipsis;">
																										<a href='help/<s:property value="#obj.url"/>'
																											target="_blank"
																											title='<s:property value="#obj.title"/>'><font
																											style="font-size: 12px"><s:property
																													value="#obj.title" /> </font> </a>
																									</td>
																								</tr>
																							</s:iterator>
																						</table>

																					</TD>
																					<TD align=right width="35%" rowSpan=4>
																						<a href="newmore.asp?action=93"><IMG
																								src="images/jieducm_29.gif" width=89 height=94
																								border="0"> </a>
																					</TD>
																				</TR>
																			</TBODY>
																		</TABLE>
																	</TD>

																</TR>
																<TR>
																	<TD
																		style="BACKGROUND: url(Images/Top_31.gif) no-repeat"
																		height=7></TD>
																</TR>
															</TBODY>
														</TABLE>
													</TD>
												</TR>
											</TBODY>

										</TABLE>
									</TD>
								</TR>
								<TR height="3">
									<TD headers="3"></TD>
								</TR>
								<TR>
									<TD>
										<table width=100% border=0 align="center" cellPadding=0
											cellSpacing=0
											style="BORDER-TOP: #72c2ed 1px solid; BORDER-RIGHT: #72c2ed 1px solid; BORDER-LEFT: #72c2ed 1px solid; BORDER-BOTTOM: #72c2ed 1px solid">

											<tr>
												<td>

													<div
														style="float: left; height: 70px; width: 680px; text-align: center; padding: 3px 1px 3px 1px;">
														<a href="http://www.taobao.com" target="_blank"><img
																src="images/200973016215234242.gif" border="0"
																width="680px" height="70px"> </a>
													</div>
												</td>
											</tr>
										</table>

									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			<table width="960" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr height="3">
					<td height="3"></td>
				</tr>
				<tr>
					<td>
						<table cellSpacing=0 cellPadding=0 width=260 border=0
							style="BORDER-TOP: #72c2ed 1px solid; BORDER-RIGHT: #72c2ed 1px solid; BORDER-LEFT: #72c2ed 1px solid; BORDER-BOTTOM: #72c2ed 1px solid">
							<tr>
								<td>
									<img src="images/jieducm_footer.gif" width="958" height="91">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>

			<TABLE class=Regttkk cellSpacing=0 cellPadding=5 width="100%"
				align=center border=0>
				<TR align="center">
					<TD>
						<a href='http://china.alibaba.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_04.gif width=109 border=0> </A>
					</TD>
					<TD>
						<a href='https://www.alipay.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_03.gif width=109 border=0> </A>
					</TD>
					<TD>
						<a href='https://www.alipay.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_05.gif width=109 border=0> </A>
					</TD>
					<TD>
						<a href='http://www.chinabank.com.cn/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_06.gif width=109 border=0> </A>
					</TD>
					<TD>
						<a href='http://www.tenpay.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_011.gif width=109 border=0> </A>
					</TD>
					<TD>
						<a href='http://www.allbuy.cn/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）"><IMG
								height=55 src=images/jieducm_022.gif width=109 border=0> </A>
					</TD>
				</TR>
			</TABLE>
			<table width="100%" border="0">
				<tr>
					<td></td>
				</tr>
			</table>

			<TABLE class=Regttkk cellSpacing=0 cellPadding=5 width="100%"
				align=center border=0>
				<TR align="center">
					<TD>
						<a href='http://www.taobao.cn/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[淘宝网] </A>
					</TD>
					<TD>
						<a href='http://www.paipai.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[拍拍网] </A>
					</TD>
					<TD>
						<a href='http://www.youa.com' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[有啊网] </A>
					</TD>
					<TD>
						<a href='https://www.alipay.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[支付宝平台] </A>
					</TD>
					<TD>
						<a href='http://www.tenpay.com/' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[财付通平台] </A>
					</TD>
					<TD>
						<a href='http://www.17win.net' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[淘宝刷信誉] </A>
					</TD>
					<TD>
						<a href='http://www.17win.net' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[淘宝刷钻] </A>
					</TD>
					<TD>
						<a href='http://www.17win.net' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[刷钻平台] </A>
					</TD>
					<TD>
						<a href='http://www.17win.net' target='_blank'
							alt="此广告位招租，联系QQ:30756500，联系电话：13554783576（谢先生）">[免费刷钻] </A>
					</TD>
				</TR>
			</TABLE>
			<br>
			<TABLE cellSpacing=0 cellPadding=0 width=940 align=center border=0>
				<tr>
					<td height="32" align="middle" colspan="3">
						<div align="center">
							<script language="javascript">Ofty="Left"</script>
							<script src="../js/Foot.js" language="javascript"></script>
							<a href="/help/about.html">关于我们</a> |
							<a href="mailto:service@17win.net">联系我们</a> |
							<a href="mailto:service@17win.net">建议留言</a> |
							<a href="/help/index.html">帮助中心</a>
						</div>
					</td>
				</tr>
				<TR>
					<TD align=right width=190 height=50>
						<div align="right">
							<IMG height=39 src="images/jieducm_110.gif" width=92>
						</div>
					</TD>
					<TD align=middle width=539>
						<div align="center">
							CopyRight 2010-2011, www.17win.net, Inc. All Rights Reserved
							黔ICP备10201109号
							<br />
							本站服务导航:
							<a href="http://www.taobao.net/" target="_blank">淘宝网</a>|
							<a href="http://www.paipai.net/" target="_blank">拍拍网</a>|
							<a href="http://www.youa.net/" target="_blank">有啊网</a>|
							<script
								src="http://s16.cnzz.com/stat.php?id=2694599&web_id=2694599&show=pic"
								language="JavaScript"></script>
						</div>
					</TD>
					<TD width=211>
						<div align="left">
							<IMG height=31 src="images/jieducm_jb.gif" width=96>
						</div>
					</TD>
				</TR>

			</TABLE>
		</s:form>
		<s:property value="#request.msg" escape="false" />

		<div class="service2  service serviceA " id="divSerWin">
			<div class="service-open" id="divMySer">
				<div onclick="ClickSer();" class="service-button"></div>
				<div class="service-inside">
					<dl>
						<dt class='title'>
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
						<dt class='title'>
							客服团队
						</dt>
						<dt class="content">

							<!-- 1348001415 -->
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" /> 新手帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" />客服帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" />充值帮助</a>
						</dt>
						<dt class="content">
							<a href="tencent://message/?uin=1442418675"> <img width="25"
									height="17" border="0" class="qqConnection"
									style="vertical-align: middle;" class="tip"
									src="http://wpa.qq.com/pa?p=1:1442418675:17" /> 投诉建议</a>
						</dt>
						<dt class="content">
							<a target="_blank"
								href="http://amos1.taobao.com/msg.ww?v=2&uid=lxq380712448&s=1"><img
									border="0"
									src="http://amos1.taobao.com/online.ww?v=2&uid=lxq380712448&s=1"
									alt="联系旺旺客服" /> </a>
						</dt>
						<dt class='title'>
							QQ交流群
						</dt>
						<dd class="esp_3">
							132533972
						</dd>
						<dt class='title'>
							我们的宗旨
						</dt>
						<dd class="esp_4">
							安全第一
						</dd>
						<dd class="esp_4">
							和谐互刷
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