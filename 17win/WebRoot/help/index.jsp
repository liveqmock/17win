<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/jieducm_pupu.js" type=text/javascript></SCRIPT>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.f12bt {
	font-size: 12px;
	color: #024E68;
	line-height: 20px;
	text-decoration: none;
	font-weight: bold;
}
-->
</style>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>

		<TABLE cellSpacing="0" cellPadding="0" width="960" align="center"
			border="0">
			<tr>
				<td valign="top">
					<table width="960" border="0" align="center" cellpadding="0"
						cellspacing="0" style="margin: 10px 0 10px 0;">
						<tr>
							<td colspan="2" valign="top">
							</td>
						</tr>
						<tr>
							<td width="271" valign="top">
								<table width="260" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<TABLE cellSpacing=0 cellPadding=0 width=260 border=0>

												<TBODY>
													<TR>
														<TD width=20>
															<IMG height=32 src="images/Top_10.gif" width=20>
														</TD>
														<TD width=10>
															<IMG height=32 src="images/Top_9.gif" width=10>
														</TD>
														<TD class=K_mttitle width=95 background=images/Top_12.gif>
															网站公告
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
															<TABLE class=LeftNews cellSpacing=0 cellPadding=0
																width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.wzResult" status="status"
																		id="obj">
																		<tr>
																			<td>
																				<a href='#' target="_blank"
																					title='<s:property value="#obj.title"/>'><font><s:property
																							value="#obj.title" /> </font> </a>
																			</td>
																		</tr>
																	</s:iterator>
																</TBODY>
															</TABLE>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</td>

									</tr>
									<tr height="10">
										<td></td>
									</tr>
									<tr>
										<td>
											<TABLE cellSpacing=0 cellPadding=0 width=260 border=0>
												<TBODY>
													<TR>
														<TD width=20>
															<IMG height=32 src="images/Top_10.gif" width=20>
														</TD>
														<TD width=10>
															<IMG height=32 src="images/Top_9.gif" width=10>
														</TD>
														<TD class=K_mttitle width=95 background=images/Top_12.gif>
															推荐文章
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
															<TABLE class=LeftNews cellSpacing=0 cellPadding=0
																width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.tjResult" status="status"
																		id="obj">
																		<tr>
																			<td>
																				<a href='#' target="_blank"
																					title='<s:property value="#obj.title"/>'><font><s:property
																							value="#obj.title" /> </font> </a>
																			</td>
																		</tr>
																	</s:iterator>
																</TBODY>
															</TABLE>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</td>

									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="349" valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	新手入门
																</td>

																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=新手入门'));">更多>></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<TR>
													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.xsResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
										<td width="350" valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	刷客必读
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=刷客必读'));">更多>></a>
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<TR>
													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.skResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											&nbsp;
										</td>
										<td valign="top">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>

													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	卖家必读
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=卖家必读'));">更多>></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<TR>

													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.mjResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	买家必读
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=买家必读'));">更多>></a>
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<TR>
													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.majResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											&nbsp;
										</td>
										<td valign="top">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>

													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	店铺推广
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=店铺推广'));">更多>></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<TR>

													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.dpResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	赚钱窍门
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=赚钱窍门'));">更多>></a>
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<TR>
													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.zqResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											&nbsp;
										</td>
										<td valign="top">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>

													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	网络营销
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=网络营销'));">更多>></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<TR>

													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.wlResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
										<td valign="top">
											<table width="334" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="24" background="images/lun-1.gif">
														<table width="310" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="61" class="f12bt">
																	服务项目
																</td>
																<td width="249" align="right">
																	<a
																		href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=服务项目'));">更多>></a>
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<TR>
													<TD class=K_mtcontent
														style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
														colSpan=6>
														<TABLE class=LeftNews cellSpacing=0 cellPadding=0
															width="100%" border=0>
															<TBODY>
																<s:iterator value="#request.fwResult" status="status"
																	id="obj">
																	<tr>
																		<td>
																			<a href='#' target="_blank"
																				title='<s:property value="#obj.title"/>'><font><s:property
																						value="#obj.title" /> </font> </a>
																		</td>
																	</tr>
																</s:iterator>
															</TBODY>
														</TABLE>
													</TD>
												</TR>

											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


		<s:include value="../common/footKuan.jsp"></s:include>
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