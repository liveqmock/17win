<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<base href="<%=basePath%>">
		<title>淘宝刷信誉 -淘宝刷钻 _刷钻平台_互刷平台_免费刷钻首选灵谷刷客平台</title>
		<meta
			content="淘宝刷信誉,淘宝刷钻,刷钻平台,互刷平台,免费刷钻首选灵谷刷客平台.第三方担保,保证用户在淘宝刷信誉,淘宝刷钻的资金安全。灵谷互刷平台更可免费刷钻。"
			name="description" />
		<meta content="淘宝刷信誉,淘宝刷钻,刷钻平台,互刷平台,免费刷钻" name="keywords" />
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/service.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/jieducm_pupu.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/jquery-1.4.2.min.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type=text/javascript></SCRIPT>
		<SCRIPT src="index.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/service.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/jquery.messager.js" type="text/javascript"></SCRIPT>
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
								<TD class=K_mtcontent
									style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
									colSpan=6>
									<TABLE class=LeftNews cellSpacing=0 cellPadding=0 width="100%"
										border=0>
										<tr>
											<td>
												<a href='../news.asp?/1405.html' target="_blank"
													title='我是新手，我该如何操作？'><font color=>我是新手，我该如何操作？</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1404.html' target="_blank"
													title='灵谷平台制度'><font color=>灵谷平台制度</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1401.html' target="_blank"
													title='VIP服务'><font color=>VIP服务</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1400.html' target="_blank"
													title='刷客常见问题'><font color=>刷客常见问题</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1399.html' target="_blank"
													title='新手教程！新人必看！'><font color=>新手教程！新人必看！</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1398.html' target="_blank"
													title='什么是买家信誉页面'><font color=>什么是买家信誉页面</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1397.html' target="_blank"
													title='新手必看-平台刷钻原理及问题解答'><font color=>新手必看-平台刷钻原理及问题解</font>
												</a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1396.html' target="_blank"
													title='这平台刷要钱么？'><font color=>这平台刷要钱么？</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1395.html' target="_blank"
													title='新会员接任务的步骤是怎样的？'><font color=>新会员接任务的步骤是怎样的？</font>
												</a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1394.html' target="_blank"
													title='【新手入门】教您如何预防骗子'><font color=>【新手入门】教您如何预防骗子</font>
												</a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1393.html' target="_blank"
													title='[推荐]买家详细规则'><font color=>[推荐]买家详细规则</font> </a>
											</td>
										</tr>
										<tr>
											<td>
												<a href='../news.asp?/1354.html' target="_blank"
													title='淘宝信用炒作规则标准公布'><font color=>淘宝信用炒作规则标准公布</font> </a>
											</td>
										</tr>
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

linkarr[1] = "register.asp";
picarr[1] = "images/indexSlider/20098138353780097.gif";
textarr[1] = "新平台更加稳定，更加人性化，操作更方便！";
linkarr[2] = "register.asp";
picarr[2] = "images/indexSlider/20098138453136263.gif";
textarr[2] = "想刷就刷，刷的开心，刷的放心！";
linkarr[3] = "register.asp";
picarr[3] = "images/indexSlider/20098138461028694.gif";
textarr[3] = "互刷改变你生活，永久免费";
linkarr[4] = "register.asp";
picarr[4] = "images/indexSlider/20098138463632056.gif";
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
																						<table class=LeftNews cellSpacing=0 cellPadding=0
																							width="100%" border=0>
																							<tr>
																								<td>
																									<a href='../news.asp?/1448.html'
																										target="_blank" title='淘宝刷钻-帐号不再被盗-------我有妙招'><font
																										color=>淘宝刷钻-帐号不再被盗----</font> </a>

																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1447.html'
																										target="_blank" title='免费刷钻-宝首个获风投网店现身深圳'><font
																										color=>免费刷钻-宝首个获风投网店现身</font> </a>
																								</td>
																							</tr>
																							<tr>

																								<td>
																									<a href='../news.asp?/1446.html'
																										target="_blank" title='淘宝刷信誉-哪句话让你感到最郁闷？'><font
																										color=>淘宝刷信誉-哪句话让你感到最郁</font> </a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1445.html'
																										target="_blank" title='免费刷钻-公务员兼职淘宝三年买房买车'><font
																										color=>免费刷钻-公务员兼职淘宝三年买</font> </a>

																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1444.html'
																										target="_blank"
																										title='淘宝刷钻-QQ广播之病毒式传播，小卖家也有大流量'><font
																										color=>淘宝刷钻-QQ广播之病毒式传播</font> </a>
																								</td>
																							</tr>
																							<tr>

																								<td>
																									<a href='../news.asp?/1443.html'
																										target="_blank" title='淘宝刷钻-如何处理顾客砍价问题？'><font
																										color=>淘宝刷钻-如何处理顾客砍价问题</font> </a>
																								</td>
																							</tr>
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
																						<table class=LeftNews cellSpacing=0 cellPadding=0
																							width="100%" border=0>
																							<tr>

																								<td>
																									<a href='../news.asp?/1449.html'
																										target="_blank" title='互刷平台-第三方诈骗的详细过程和防骗经验'><font
																										color=>互刷平台-第三方诈骗的详细过程</font> </a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1440.html'
																										target="_blank" title='淘宝刷钻-阿里巴巴注资搜狗，意在淘宝'><font
																										color=>淘宝刷钻-阿里巴巴注资搜狗，意</font> </a>

																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1435.html'
																										target="_blank" title='互刷平台-淘宝排名八大规则'><font
																										color=>互刷平台-淘宝排名八大规则</font> </a>
																								</td>
																							</tr>
																							<tr>

																								<td>
																									<a href='../news.asp?/1434.html'
																										target="_blank" title='淘宝刷信誉-网店的核心价值是什么'><font
																										color=>淘宝刷信誉-网店的核心价值是什</font> </a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1432.html'
																										target="_blank" title='免费刷钻-买家必读'><font
																										color=>免费刷钻-买家必读</font> </a>

																								</td>
																							</tr>
																							<tr>
																								<td>
																									<a href='../news.asp?/1431.html'
																										target="_blank" title='刷钻平台-淘宝买家也要讲评价的诚信'><font
																										color=>刷钻平台-淘宝买家也要讲评价的</font> </a>
																								</td>
																							</tr>
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
				<TR align=left>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=拍拍刷信誉><IMG
								title=拍拍刷信誉 height=55 src=images/jieducm_04.gif width=109
								border=0> </A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=拍拍刷信誉><IMG
								title=拍拍刷信誉 height=55 src=images/jieducm_03.gif width=109
								border=0> </A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=刷钻网><IMG
								title=刷钻网 height=55 src=images/jieducm_05.gif width=109 border=0>
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=淘宝互刷><IMG
								title=淘宝互刷 height=55 src=images/jieducm_06.gif width=109
								border=0> </A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=传媒><IMG
								title=传媒 height=55 src=images/jieducm_011.gif width=109 border=0>
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=传媒><IMG
								title=传媒 height=55 src=images/jieducm_022.gif width=109 border=0>
						</A>
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
				<TR align=left>
					<TD>
						<a href='http://www.sunwukong.cn/' target='_blank' title=孙悟空>[孙悟空]
						</A>
					</TD>
					<TD>
						<a href='http://www.at-lib.com' target='_blank' title=分类目录>[分类目录]
						</A>
					</TD>
					<TD>
						<a href='http://www.all-list.cn' target='_blank' title=中文分类目录>[中文分类目录]
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=淘宝刷信誉>[淘宝刷信誉]
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title=淘宝刷钻>[淘宝刷钻]
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title="刷钻平台">[刷钻平台]
						</A>
					</TD>
					<TD>
						<a href='http://www.tenpay.com/' target='_blank' title="互刷平台">[互刷平台]
						</A>
					</TD>
					<TD>
						<a href='http://www.2000w.net' target='_blank' title="免费刷钻">[免费刷钻]
						</A>
					</TD>
					<TD>
						<a href='http://www.paipai.com/' target='_blank' title="拍拍网">[拍拍网]
						</A>
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
							<a href="../help/about.asp">关于我们</a> |
							<a href="../help/pay_introduce.asp">汇款方式</a> |
							<a href="../help/link.asp">友情链接</a> |
							<a href="../help/lianxi.asp">联系我们</a> |
							<a href="../help/viewreturn.asp">建议留言</a> |
							<a href="../help/">帮助中心</a> |
							<a href="#">版权声明</a> |
							<a href="#">服务条款</a>
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
							CopyRight 2009-2010, jieducm.cn, Inc. All Rights Reserved
							豫ICP备05329849号
							<br />
							本站服务导航:
							<a href="http://www.2000w.net/" target="_blank">淘宝刷信誉</a>|
							<a href="http://www.2000w.net/" target="_blank">刷钻平台</a>|
							<a href="http://www.2000w.net/" target="_blank">淘宝刷钻</a>|
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
							<a class="serviceA" href="tencent://message/?uin=1348001415">新手帮助</a>
						</dd>
						<dd>
							<a class="serviceA" href="tencent://message/?uin=1181045171">客服一号</a>
						</dd>
						<dd>
							<a href="tencent://message/?uin=1395324789">客服二号</a>
						</dd>
						<dd>
							<a href="tencent://message/?uin=1348001415">客服三号</a>
						</dd>
						<dd>
							<a href="tencent://message/?uin=1181045171">客服四号</a>
						</dd>
						<dd>
							<a href="tencent://message/?uin=1395324789">充值帮助</a>
						</dd>
						<dd>
							<a href="tencent://message/?uin=1097238420">提现帮助</a>
						</dd>
						<dd class="esp_1">
							<a href="tencent://message/?uin=2750697">投诉建议</a>
						</dd>
						<dt>
							QQ交流群
						</dt>
						<dd class="esp_2">
							1-23群已满
						</dd>
						<dd class="esp_3">
							24群：120645229
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</BODY>

</HTML>