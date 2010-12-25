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
		<SCRIPT src="js/jquery.tablesorter.min.js" type="text/javascript"></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
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
										<s:form action="smsManager/sms!querySms.php"
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
														短信类型：
														<s:select id="smsVO" listKey="key" listValue="value"
															name="smsVO.type" headerKey="" headerValue="--请选择--"
															list="#{'1':'系统短信','2':'普通短信'}">
														</s:select>
													</td>
													<td>
														<input type="submit" value="查&nbsp;&nbsp;询"
															style="cursor: pointer;">
													</td>
												</tr>
											</table>
										</s:form>
										<br>
										<table id="myTable" class="tablesorter" cellpadding="1"
											style="table-layout: fixed">
											<thead>
												<tr>
													<th style="font-size: 12px" nowrap="nowrap">
														发送人
													</th>
													<th style="font-size: 12px" nowrap="nowrap">
														接收人
													</th>
													<th style="font-size: 12px" nowrap="nowrap">
														类型
													</th>
													<th style="font-size: 12px" nowrap="nowrap">
														标题
													</th>
													<th style="font-size: 12px" nowrap="nowrap">
														发送时间
													</th>
													<th style="font-size: 12px" nowrap="nowrap">
														操作
													</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="#request.result" id="sms">
													<tr>
														<td id="td_<s:property value="#sms.id"/>"
															fromUserName="<s:property value="#sms.fromUserName" />">
															<s:if
																test="!#sms.read &&  #sms.fromUserName!=#session.userLogin.username">
																<b>*<s:property value="#sms.fromUserName" /> </b>
															</s:if>
															<s:else>
																<s:property value="#sms.fromUserName" />
															</s:else>
														</td>
														<td>
															<s:property value="#sms.toUserName" />
														</td>
														<td>
															<s:if test="#sms.type==1">
																	系统消息
															</s:if>
															<s:else>
																普通消息
															</s:else>
														</td>
														<td
															style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
															<s:property value="#sms.title" />
														</td>
														<td>
															<s:date name="#sms.sendDate" format="yyyy-MM-dd HH-mm-ss" />
														</td>
														<td>
															<a content="<s:property value="#sms.content"/>"
																title="<s:property value="#sms.title"/>"
																id="a_<s:property value="#sms.id"/>"
																href="javascript:brower(<s:property value="#sms.id" />,<s:property value="#sms.read" />,this);">浏览</a>
															<s:if
																test="#sms.type==2 && #sms.fromUserName!=#session.userLogin.username">
																<a
																	href="javascript:reply('<s:property value="#sms.fromUserName" />');">回复</a>
															</s:if>
															<a
																href="javascript:deleteSms(<s:property value="#sms.id" />);">删除</a>
														</td>
													</tr>
												</s:iterator>
											</tbody>
											<s:if test="#request.result.size()==0">
												<tr>
													<th colspan="6" align="center">
														您当前没有短信！
													</th>
												</tr>
											</s:if>
											<s:else>
												<tfoot>
													<tr>
														<th colspan="6">
															<div style="float: left;">
																<a href="javascript:firstPage()">首页</a>
																<a href="javascript:prevPage()">上一页</a>&nbsp;
																<a href="javascript:nextPage()">下一页</a>&nbsp;
																<a href="javascript:lastPage()">尾页</a>&nbsp;
															</div>
															<div style="float: left;">
																跳转到
																<select id='toPageSelect' size='1' onchange="jumpPage()">
																	<s:iterator begin="1" end="smsVO.pageCount" step="1"
																		var="index">
																		<option value="<s:property value="#index" />">
																			第
																			<s:property value="#index" />
																			页
																		</option>
																	</s:iterator>
																</select>
															</div>
															<input type="hidden" name="smsVO.nowPage"
																value="<s:property
											value="smsVO.nowPage" />"
																id="nowPage">
															<input type="hidden"
																value="<s:property
										value="smsVO.pageCount" />"
																id="pageCount">
														</th>
													</tr>
												</tfoot>
											</s:else>
										</table>

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

		<div id="browerSms" title="站内信">
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
