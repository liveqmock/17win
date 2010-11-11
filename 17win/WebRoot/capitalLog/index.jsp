<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">

		<LINK href="css/center.css" type="text/css" rel="stylesheet">

		<LINK href="css/blue/style.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery.tablesorter.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
		<SCRIPT src="capitalLog/index.js" type="text/javascript"></SCRIPT>

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
		<s:form action="capitalLogManager/capitalLog!queryLogs.php"
			onsubmit="return validateForm()" theme="simple">
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
												您现在的位置是：个人中心 &gt;&gt; 财物管理 &gt;&gt; 资产记录 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>记录列表</strong>
											</div>
											<br>
											<table width="100%" cellpadding="1" cellspacing="1"
												border="0px" style="background: #DDEDFA">
												<tr>
													<td>
														记录类型：
														<s:select listKey="key" listValue="value"
															name="capitalLogVO.type" headerKey=""
															headerValue="--请选择--" list="#{'1':'金额记录','2':'发布点记录'}">
														</s:select>
													</td>
													<td>
														数值范围：
														<s:textfield name="capitalLogVO.startValue"
															id="startValue" cssStyle="width:40px">
														</s:textfield>
														至
														<s:textfield name="capitalLogVO.endValue" id="endValue"
															cssStyle="width:40px">
														</s:textfield>
													</td>
												</tr>
												<tr>
													<td>
														记录日期：
														<s:textfield name="capitalLogVO.startDate" id="startDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
														至
														<s:textfield name="capitalLogVO.endDate" id="endDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
													</td>
													<td>
														<input type="submit" value="查&nbsp;&nbsp;询"
															style="cursor: pointer;">
													</td>
												</tr>
											</table>
											<br>
											<table id="myTable" class="tablesorter" cellpadding="1">
												<thead>
													<tr>
														<th style="font-size: 12px" nowrap="nowrap">
															资产类型
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															值
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															操作日期
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															描述
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="#request.result" id="capitalLog">
														<tr
															<s:if test="#status.odd"> style="background: #FFC278"</s:if>>
															<td>
																<s:if test="#capitalLog.type==1">
																  金额
																</s:if>
																<s:elseif test="#capitalLog.type==2">
																	发布点
																</s:elseif>
															</td>
															</td>
															<td>
																<s:property value="#capitalLog.value" />
															</td>
															<td>
																<s:date name="#capitalLog.logTim"
																	format="yyyy-MM-dd HH-mm-ss" />
															</td>
															<td>
																<s:property value="#capitalLog.desc" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
												<s:if test="#request.result.size()==0">
													<tr>
														<th colspan="9" align="center">
															没有数据！
														</th>
													</tr>
												</s:if>
												<s:else>
													<tfoot>
														<tr>
															<th colspan="7">
																<div style="float: left;">
																	<a href="javascript:firstPage()">首页</a>
																	<a href="javascript:prevPage()">上一页</a>&nbsp;
																	<a href="javascript:nextPage()">下一页</a>&nbsp;
																	<a href="javascript:lastPage()">尾页</a>&nbsp;
																</div>
																<div style="float: left;">
																	<s:property value="capitalLogVO.nowPage" />
																	/
																	<s:property value="capitalLogVO.pageCount" />
																	跳转到
																	<select id='toPageSelect' size='1'
																		onchange="jumpPage()">
																		<s:iterator begin="1" end="capitalLogVO.pageCount"
																			step="1" var="index">
																			<option value="<s:property value="#index" />">
																				第
																				<s:property value="#index" />
																				页
																			</option>
																		</s:iterator>
																	</select>
																</div>
																<input type="hidden" name="capitalLogVO.nowPage"
																	value="<s:property
											value="capitalLogVO.nowPage" />"
																	id="nowPage">
																<input type="hidden"
																	value="<s:property
										value="capitalLogVO.pageCount" />"
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
		</s:form>
	</BODY>
</HTML>
