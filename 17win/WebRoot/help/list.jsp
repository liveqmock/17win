<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="help/list.js" type=text/javascript></SCRIPT>
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
														<TD class="K_mtcontent"
															style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
															width="100%" colspan="99">
															<TABLE class=LeftNews cellSpacing=0 cellPadding=0
																style="table-layout: fixed" width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.wzResult" status="status"
																		id="obj">
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
														<TD class="K_mtcontent"
															style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px"
															width="100%" colspan="99">
															<TABLE class="LeftNews" cellSpacing=0 cellPadding=0
																style="table-layout: fixed" width="100%" border=0>
																<TBODY>
																	<s:iterator value="#request.tjResult" status="status"
																		id="obj">
																		<tr>
																			<td nowrap="nowrap" width="100%"
																				style="overflow: hidden; text-overflow: ellipsis;">
																				<a href='help/<s:property value="#obj.url"/>'
																					target="_blank"
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
											<td height="26" background="images/news1.gif">
												<table width="655" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td class="f12ls1">
																当前位置：
																<a href="help/index.html">帮助中心</a> &gt;
																<s:property value="newsVO.typeName" />
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
														<s:iterator value="#request.result" status="status"
															id="newsVO">
															<tr>
																<td height="30" background="images/news4.gif">
																	<table width="659" cellspacing="0" cellpadding="0"
																		border="0" align="right" class="LeftNews">
																		<tbody>
																			<tr>
																				<td width="644" class="f12hs">
																					<a title="<s:property value="#newsVO.title"/>"
																						target="_blank"
																						href="help/<s:property value="#newsVO.url"/>">
																						<s:property value="#newsVO.title" /> </a>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</td>
										</tr>
										<tr>
											<td height="26" background="images/news3.gif">
												<table width="655" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<TD>
																<input type="hidden"
																	value="<s:property
											value="newsVO.typeName" />"
																	id="typeName">
																<input type="hidden"
																	value="<s:property
											value="newsVO.nowPage" />"
																	id="nowPage">
																<input type="hidden"
																	value="<s:property
										value="newsVO.pageCount" />"
																	id="pageCount">
																共
																<font color="blue"><b><s:property
																			value="newsVO.dataCount" /> </b> </font> 条主题&nbsp;&nbsp;&nbsp;
																<a href="javascript:firstPage();">首页</a>
																<a href="javascript:prevPage();">上一页</a>&nbsp;
																<a href="javascript:nextPage();">下一页</a>&nbsp;
																<a href="javascript:lastPage();">尾页</a>&nbsp;页次：
																<strong><font color="red"><s:property
																			value="newsVO.nowPage" /> </font>/<s:property
																		value="newsVO.pageCount" /> </strong>页 &nbsp;
																<b><s:property value="newsVO.eachPage" /> </b>条主题/页&nbsp;转到：
																<select id='toPageSelect' size='1' onchange="jumpPage()">
																	<s:iterator begin="1" end="newsVO.pageCount" step="1"
																		var="index">
																		<option value="<s:property value="#index" />">
																			第
																			<s:property value="#index" />
																			页
																		</option>
																	</s:iterator>
																</select>
															</TD>
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