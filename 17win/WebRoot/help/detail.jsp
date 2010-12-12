<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
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
	<BODY onload="getUserLogin()">
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
														<TD class="K_mtcontent" width="260"
															style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
															colspan="99">
															<TABLE class=LeftNews cellSpacing=0 cellPadding=0
																style="table-layout: fixed" width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.wzResult" status="status"
																		id="obj">
																		<tr>
																			<td nowrap="nowrap" width="100%"
																				style="overflow: hidden; text-overflow: ellipsis;">
																				<a href='#' target="_blank"
																					title='<s:property value="#obj.title"/>'><font
																					style="font-size: 12px"> <s:property
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
														<TD class="K_mtcontent" width="260"
															style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
															colspan="99">
															<TABLE class=LeftNews cellSpacing=0 cellPadding=0
																style="table-layout: fixed" width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.tjResult" status="status"
																		id="obj">
																		<tr>
																			<td nowrap="nowrap" width="100%"
																				style="overflow: hidden; text-overflow: ellipsis;">
																				<a href='#' target="_blank"
																					title='<s:property value="#obj.title"/>'><font
																					style="font-size: 12px"><s:property
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
							<td width="699" valign="top">
								<table width="690" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td height="26" background="images/news1.gif"
												style="border: 1px,">
												<table width="655" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td class="f12bt">
																当前位置：
																<a href="help/index.html">帮助中心</a> &gt;
																<a
																	href="javascript:window.location.href=encodeURI(encodeURI('adminNewsManager/adminNews!listNews.php?newsVO.typeName=<s:property
																		value="#request.result.typeName" />'));"><s:property
																		value="#request.result.typeName" /> </a>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" background="images/news2.gif">
												<table width="669" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td height="30">
																<table width="644" cellspacing="0" cellpadding="0"
																	border="0" align="center">
																	<tbody>
																		<tr>
																			<td width="644" valign="bottom" height="60"
																				align="center" class="f12bt STYLE1">
																				<s:property value="#request.result.title" />
																			</td>
																		</tr>
																		<tr>
																			<td height="33" align="center"
																				style="border-bottom: 1px dashed rgb(3, 101, 190); height: 30px; color: #0365BE; font-size: 12px"
																				class="f12ls1">
																				<s:property value="#request.result.date" />
																			</td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td height="30">
																<table width="644" cellspacing="0" cellpadding="0"
																	border="0" align="center">
																	<tbody>
																		<tr>
																			<td width="644" height="145" class="f14d">
																				<s:property value="#request.result.content"
																					escape="false" />
																			</td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
													</tbody>
												</table>
												`
											</td>
										</tr>
										<tr>
											<td height="26" background="images/news3.gif">
												<table width="655" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td>
																<span class="hd"> <s:if
																		test="#request.prevNews!=null">
																	上一篇： <a
																			href="help/<s:property
																				value="#request.prevNews.url" />"><s:property
																				value="#request.prevNews.title" /> </a>
																	</s:if> <s:if test="#request.afterNews!=null">
																	下一篇： <a
																			href="help/<s:property
																					value="#request.afterNews.url" /> "><s:property
																				value="#request.afterNews.title" /> </a>
																	</s:if> </span>
															</td>
														</tr>
													</tbody>
												</table>

												<table width="655" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footKuan.jsp"></s:include>
	</BODY>
</HTML>