<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/blue/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">
		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			defer="defer" type="text/javascript"></script>
		<script src="js/utils.js" type="text/javascript"></script>
		<script src="sms/mySms.js" type="text/javascript"></script>
		<style type="text/css">
body {
	
}

img {
	vertical-align: bottom;
	border: 0px;
}

.sec_menu {
	border-left: 1px solid white;
	border-right: 1px solid white;
	border-bottom: 1px solid white;
	overflow: hidden;
}

.menu_title {
	
}

.menu_title span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}

.menu_title2 {
	
}

.menu_title2 span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}
</style>

	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="760" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
					<table width="910" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<!-- xgj   user left menu-->
							<s:include value="../common/user/infoMenu.jsp"></s:include>
							<!-- end xgj -->
							<td width="15">
								&nbsp;
							</td>

							<!-- 要插入的 -->
							<td valign="top">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td height="5"></td>
										</tr>
									</tbody>
								</table>
								<div class="pp9">
									<div style="padding-bottom: 15px; width: 97%;">
										<div class="pp7">
											您现在的位置是：个人中心 &gt;&gt;短信管理 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>我的站内信</strong>
										</div>
										<!-- xgj -->
										<br>
										<s:form action="smsManager/sms!querySJXSms.php"
											onsubmit="return validateForm()" theme="simple">
											<table width="100%" cellpadding="1" cellspacing="1"
												border="0px" style="background: #DDEDFA">
												<tr>
													<td>
														发送日期：
														<s:textfield name="smsVO.startDate" id="startDate"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															readonly="true" cssStyle="width:110px">
														</s:textfield>
														至
														<s:textfield name="smsVO.endDate" id="endDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
													</td>
													<td>
														<input type="submit" value="查&nbsp;&nbsp;询"
															style="cursor: pointer;">
														<s:hidden name="smsVO.queryTypeFlag" />
													</td>
												</tr>
											</table>
										</s:form>
										<br>
										<div>
											<div id="tabs">
												<ul>
													<li>
														<a href="#tabs-1" queryFlag="sjx"
															onclick="javascript:changeQuery(this);">收件箱</a>
													</li>
													<li>
														<a href="#tabs-2" queryFlag="fjx"
															onclick="javascript:changeQuery(this);">发件箱</a>
													</li>
												</ul>
												<div id="tabs-1">
													<table cellspacing="1" width="100%">
														<thead>
															<tr>
																<th>
																	<input type="checkbox" onclick="selectSJAll(this);">
																</th>

																<th>
																	标题
																</th>

																<th>
																	发件人
																</th>

																<th>
																	发送时间
																</th>
															</tr>
														</thead>
														<tbody>
															<s:iterator value="#request.result" status="status"
																id="sms">
																<tr>
																	<td align="center">
																		<input type="checkbox" name="sjSmsIDs"
																			sjSmsID="sjSmsID"
																			value="<s:property value="#sms.id"/>">
																	</td>
																	<td>
																		<s:if test="!#sms.read">
																			*
																		</s:if>
																		<font
																			style="text-decoration: underline;"> <s:property
																				value="#sms.title" /> </font>
																	</td>
																	<td align="center">
																		<s:property value="#sms.fromUserName" />
																	</td>
																	<td align="center">
																		<s:date name="#sms.sendDate"
																			format="yyyy-MM-dd HH:mm:ss" />
																	</td>
																</tr>
															</s:iterator>
														</tbody>
														<tfoot>
															<s:if test="#request.result.size()==0">
																<tr>
																	<td colspan="4" align="center">
																		没有记录！
																	</td>
																</tr>
															</s:if>
															<s:else>
																<tr>
																	<td colspan="4">
																		<input type="button" value="删除" id="deleteBtn1">
																	</td>
																</tr>
															</s:else>
														</tfoot>
													</table>
												</div>
												<div id="tabs-2">
													<table cellspacing="1" width="100%">
														<thead>
															<tr>
																<th>
																	<input type="checkbox" onclick="selectFJAll(this)">
																</th>

																<th>
																	标题
																</th>

																<th>
																	收件人
																</th>

																<th>
																	发送时间
																</th>
															</tr>
														</thead>
														<tbody>
															<s:iterator value="#request.result" status="status"
																id="sms">
																<tr>
																	<td align="center">
																		<input type="checkbox" name="fjSmsIDs"
																			fjSmsID="fjSmsID"
																			value="<s:property value="#sms.id"/>">
																	</td>
																	<td>
																		<font style="text-decoration: underline;"> <s:property
																				value="#sms.title" /> </font>
																	</td>
																	<td align="center">
																		<s:property value="#sms.toUserName" />
																	</td>
																	<td align="center">
																		<s:date name="#sms.sendDate"
																			format="yyyy-MM-dd HH:mm:ss" />
																	</td>
																</tr>
															</s:iterator>
														</tbody>
														<tfoot>
															<s:if test="#request.result.size()==0">
																<tr>
																	<td colspan="4" align="center">
																		没有记录！
																	</td>
																</tr>
															</s:if>
															<s:else>
																<tr>
																	<td colspan="4">
																		<input type="button" value="删除" id="deleteBtn2">
																	</td>
																</tr>
															</s:else>
														</tfoot>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</td>
							<!-- end -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
		<div style="display: none" id="browerSms" title="站内信">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap">
						标题:
					</td>
					<td>
						<input type="text" id="title" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" valign="top">
						内容:
					</td>
					<td>
						<textarea cols="50" rows="8" id="content" readonly="readonly"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</BODY>
</HTML>
