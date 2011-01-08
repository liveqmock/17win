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
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
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
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"  defer="defer" 
			type="text/javascript"></script>
		<SCRIPT src="logistics/useLogisticsLog.js" type="text/javascript"></SCRIPT>

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
		<s:form action="logisticsManager/logistics!useLogisticsLog.php"
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
												您现在的位置是：个人中心 &gt;&gt; 物流管理 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>我使用的物流 </strong>
											</div>
											<br>
											<table width="100%" cellpadding="1" cellspacing="1"
												border="0px" style="background: #DDEDFA">
												<tr>
													<td>
														发货日期：
														<s:textfield name="logisticsVO.startDate" id="startDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
														至
														<s:textfield name="logisticsVO.endDate" id="endDate"
															readonly="true"
															onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
															cssStyle="width:110px">
														</s:textfield>
													</td>
													<td>
														物流单号：
														<s:textfield name="logisticsVO.waybill"></s:textfield>
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
															物流公司
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															物流单号
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															发货时间
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															预计收货时间
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															发货信息
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															收货信息
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															备注
														</th>
														<th style="font-size: 12px" nowrap="nowrap">
															操作
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="#request.result" id="logistics">
														<tr
															<s:if test="#status.odd"> style="background: #FFC278"</s:if>>
															<td class='toCopy_<s:property value="#logistics.id"/>'>
																<s:property value="#logistics.expressCompany" />
															</td>
															<td class="toCopy_<s:property value="#logistics.id"/>">
																<s:property value="#logistics.waybill" />
															</td>
															<td>
																<s:date name="#logistics.sendDate" format="yyyy-MM-dd" />
															</td>
															<td>
																<s:date name="#logistics.arrivalDate"
																	format="yyyy-MM-dd" />
															</td>
															<td>
																<s:property value="#logistics.releaseInfo" />
															</td>
															<td class="toCopy_<s:property value="#logistics.id"/>">
																<s:property value="#logistics.receieveInfo" />
															</td>
															<td>
																<s:property value="#logistics.remark" />
															</td>
															<td nowrap="nowrap">
																<s:if test="#logistics.status==1">
																	<a
																		href="javascript:toCopy('<s:property value="#logistics.id" />')">复制</a>
																</s:if>
																<s:elseif test="#logistics.status==2">
																	不能使用，当前被冻结
																</s:elseif>
															</td>
														</tr>
													</s:iterator>
												</tbody>
												<s:if test="#request.result.size()==0">
													<tr>
														<th colspan="10" align="center">
															您目前还没有使用任何物流信息！
														</th>
													</tr>
												</s:if>
												<s:else>
													<tfoot>
														<tr>
															<th colspan="10">
																<div style="float: left;">
																	<a href="javascript:firstPage()">首页</a>
																	<a href="javascript:prevPage()">上一页</a>&nbsp;
																	<a href="javascript:nextPage()">下一页</a>&nbsp;
																	<a href="javascript:lastPage()">尾页</a>&nbsp;
																</div>
																<div style="float: left;">
																	<s:property value="logisticsVO.nowPage" />
																	/
																	<s:property value="logisticsVO.pageCount" />
																	跳转到
																	<select id='toPageSelect' size='1'
																		onchange="jumpPage()">
																		<s:iterator begin="1" end="logisticsVO.pageCount"
																			step="1" var="index">
																			<option value="<s:property value="#index" />">
																				第
																				<s:property value="#index" />
																				页
																			</option>
																		</s:iterator>
																	</select>
																</div>
																<input type="hidden" name="logisticsVO.nowPage"
																	value="<s:property
											value="logisticsVO.nowPage" />"
																	id="nowPage">
																<input type="hidden"
																	value="<s:property
										value="logisticsVO.pageCount" />"
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
