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
		<SCRIPT src="user/withdrawalsLog.js" type="text/javascript"></SCRIPT>

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
		<s:form action="withdrawalsManager/withdrawals!withdrawalsLog.php"
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
												您现在的位置是：个人中心 &gt;&gt; 财物管理 &gt;&gt; 提现记录 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>提现列表</strong>
											</div>
											<br>
											<table width="100%" cellpadding="1" cellspacing="1"
												border="0px" style="background: #DDEDFA">
												<tr>
													<td>
														提现类型：
														<s:select id="withdrawalsType" listKey="key"
															listValue="value" name="withdrawalsVO.type" headerKey=""
															headerValue="--请选择--"
															list="#{'1':'店铺地址提现','2':'支付宝提现','3':'财付通提现'}">
														</s:select>
														<span style="display: none" id="shopType">
															&nbsp;&nbsp;店铺类型：<s:select name="withdrawalsVO.shopType"
																listKey="key" listValue="value" headerKey=""
																headerValue="--请选择--"
																list="#{'1':'淘宝','2':'拍怕','3':'有啊'}">
															</s:select> </span>
													</td>
													<td>
														提现金额：
														<s:textfield name="withdrawalsVO.startMoney"
															id="startMoney" cssStyle="width:40px">
														</s:textfield>
														至
														<s:textfield name="withdrawalsVO.endMoney" id="endMoney"
															cssStyle="width:40px">
														</s:textfield>
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td>
														操作日期：
														<s:textfield name="withdrawalsVO.startDate" id="startDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
														至
														<s:textfield name="withdrawalsVO.endDate" id="endDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
													</td>
													<td>
														状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
														<s:select name="withdrawalsVO.status" listKey="key"
															listValue="value" headerKey="" headerValue="--请选择--"
															list="#{'1':'申请中','4':'被驳回','3':'已完成'}">
														</s:select>
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
															提现类型
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															平台类型
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															提现链接
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															提现金额
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															操作日期
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															状态
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															描述
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															操作
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="#request.result" id="withdrawals">
														<tr
															<s:if test="#status.odd"> style="background: #FFC278"</s:if>>
															<td>
																<s:if test="#withdrawals.type==1">
																	店铺地址提现
																</s:if>
																<s:elseif test="#withdrawals.type==2">
																	支付宝提现
																</s:elseif>
																<s:else>
																	财付通提现
																</s:else>
															</td>
															<td>
																<s:if test="#withdrawals.type==1">
																	<s:if test="#withdrawals.shopType==1">
																		淘宝
																	</s:if>
																	<s:elseif test="#withdrawals.shopType==2">
																		拍拍
																	</s:elseif>
																	<s:else>
																		有啊
																	</s:else>
																</s:if>
																<s:else>
																	-
																</s:else>
															</td>
															<td>
																<s:property value="#withdrawals.realIdentity" />
															</td>
															<td>
																<s:property value="#withdrawals.money" />
															</td>
															<td>
																<s:date name="#withdrawals.operationDate"
																	format="yyyy-MM-dd HH-mm-ss" />
															</td>
															<td>
																<s:if test="#withdrawals.status==1">
																		申请中
																	</s:if>
																<s:elseif test="#withdrawals.status==2">
																		被驳回
																	</s:elseif>
																<s:else>
																		已完成
																	</s:else>
															</td>
															<td>
																<s:property value="#withdrawals.statusDesc" />
															</td>
															<td>
																<a href="#">删除</a>
															</td>
														</tr>
													</s:iterator>
												</tbody>
												<s:if test="#request.result.size()==0">
													<tr>
														<th colspan="9" align="center">
															您目前还没有进行过提现！
														</th>
													</tr>
												</s:if>
												<s:else>
													<tfoot>
														<tr>
															<th colspan="9">
																<div style="float: left;">
																	<img src="images/page/_firstpg.gif"
																		style="cursor: pointer;" />
																	&nbsp;&nbsp;
																	<img src="images/page/_prevpg.gif"
																		style="cursor: pointer;" />
																	&nbsp;&nbsp;
																	<img src="images/page/_nextpg.gif"
																		style="cursor: pointer;" />
																	&nbsp;&nbsp;
																	<img src="images/page/_lastpg.gif"
																		style="cursor: pointer;" />
																</div>
																<div style="float: left;">
																	1/1 跳转到
																	<select>
																	</select>
																</div>
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
