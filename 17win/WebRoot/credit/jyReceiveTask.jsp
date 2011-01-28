<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
		<%
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ "/";
		%>
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
		<s:if test="#request.autoRefresh!=null">
			<meta http-equiv="refresh"
				content="<s:property value="#request.autoRefresh"/>">
		</s:if>
		<s:include value="../common/header.jsp"></s:include>

		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js">
		</script>
		<script
			src="http://cdn.jquerytools.org/1.2.1/tiny/jquery.tools.min.js"></script>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			defer="defer" type="text/javascript"></script>
		<SCRIPT src="credit/jyReceiveTask.js" type="text/javascript"></SCRIPT>
		<style>
/* trigger button */
.qqConnection {
	cursor: pointer;
	overflow: hidden;
}

/* mouseover state */
.qqConnection :hover {
	background-position: 0 -44px;
}

/* clicked state */
.qqConnection :focus {
	background-position: 0 -88px;
}

/* tooltip styling */
.tooltip {
	display: none;
	height: 60px;
	width: 117px;
	font-size: 11px;
	font-weight: bold;
}
</style>

	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<s:form action="taskManager/task!initTask.php" theme="simple"
			onsubmit="return validateForm()">
			<div align="center" id="partdiv">
				<div align="center">
					<DIV
						style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
						<!-- xgj navigation.jsp -->
						<s:include value="../common/task/navigation.jsp"></s:include>
					</DIV>
					<div style="WIDTH: 910px">
						<form action="http://www.shuazuan.com/task/novice.php"
							method="get" name="queryConditionForm" id="queryConditionForm">
							<input type="hidden" value="alreadycatcher" name="action">
							<div style="display: block;" id="J_SearchBox">
								<table class="table-6">
									<tbody>
										<tr>
											<td>
												<label>
													任&nbsp;务&nbsp;I&nbsp;D：
												</label>
												<input type="text" size="15" id="order_sn" name="order_sn"
													value="">
											</td>
											<td>
												<label>
													发布时间：
												</label>
												<s:textfield name="creditTaskVO.fbStartDate" id="startDate"
													readonly="true"
													onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
													cssStyle="width:80px">
												</s:textfield>
												至
												<s:textfield name="creditTaskVO.fbEndDate" id="endDate"
													readonly="true"
													onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
													cssStyle="width:80px">
												</s:textfield>
											</td>
											<td>
												<label>
													任务状态：
												</label>
												<select id="statusid" name="statusid" value="">
													<option value="ALL">
														全部
													</option>
													<option value="1">
														等待接手人
													</option>
													<option value="2">
														已接手，等待接手方支付
													</option>
													<option value="3">
														已支付，等待发布方发货
													</option>
													<option value="4">
														已发货，等待接手方确认好评
													</option>
													<option value="5">
														接手方已确认好评，等待发布方好评
													</option>
													<option value="6">
														任务已完成
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													&nbsp;会 员 名：
												</label>
												<input type="text" size="15" id="username" name="username"
													value="">
											</td>
											<td>
												<label>
													接手时间：
												</label>
												<s:textfield name="creditTaskVO.jsStartDate" id="startDate"
													cssStyle="width:80px" readonly="true"
													onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})">
												</s:textfield>
												至
												<s:textfield name="creditTaskVO.jsEndDate" id="endDate"
													cssStyle="width:80px" readonly="true"
													onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})">
												</s:textfield>
											</td>
											<td align="left">
												<label>
													任务分类：
												</label>
												<select id="t_type" name="t_type" value=""
													style="width: 75px;">
													<option value="ALL">
														全部
													</option>
													<option value="virtual">
														虚拟
													</option>
													<option value="realobject">
														实物
													</option>
													<option value="realobject">
														套餐
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<td class="col1">
												<label>
													掌 柜 名：
												</label>
												<input type="text" size="15" id="shopkeeper"
													name="shopkeeper" value="">
											</td>
											<td align="left">
												<label>
													接手小号：
												</label>
												<input type="text" size="15" id="catcher_shopkeeper"
													name="catcher_shopkeeper" value="">
											</td>
											<td align="left">
												<input type="submit" value="搜索" name="btnSearch">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</form>
					</div>
					<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
						<DIV
							style="BACKGROUND: url(images/task_bg_01.jpg) repeat-x 50% top; WIDTH: 910px; HEIGHT: 38px">
							<DIV style="MARGIN-TOP: 10px; FLOAT: left">
								<IMG src="images/task_02.gif">
							</DIV>
							<DIV style="MARGIN-TOP: 12px; FLOAT: left; MARGIN-LEFT: 10px">
								<A href="javascript:sort1()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">全部任务</SPAN>
								</A>
								<A href="javascript:sort2()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已接手</SPAN>
								</A>
								<A href="javascript:sort3()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已支付</SPAN>
								</A>
								<A href="javascript:sort4()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已发货</SPAN>
								</A>
								<A href="javascript:sort5()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已好评</SPAN>
								</A>
								<A href="javascript:sort6()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已完成</SPAN>
								</A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								自定义刷新时间
								<input type="text"
									value="<s:property value="#request.autoRefresh"/>"
									style="width: 25px" id="autoreFresh" title="必须大于5秒，空表示不刷新！" />
								<input type="hidden"
									value="<s:property value="#request.queryType"/>" id="queryType">
								<input type="hidden"
									value="<s:property
											value="creditTaskVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="creditTaskVO.pageCount" />"
									id="pageCount">
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								秒
								<A title=点击刷新 href="javascript:location.reload(true);"
									class="yell_font"> <SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">刷新页面</SPAN>
								</A>
							</DIV>
						</DIV>
					</DIV>
					<table cellspacing="1" class="taskTable">
						<thead>
							<tr>
								<td nowrap="nowrap" align="center">
									任务编号
								</td>
								<td nowrap="nowrap" align="center">
									发布人
								</td>
								<td nowrap="nowrap" align="center">
									价格/发布点
								</td>
								<td nowrap="nowrap" align="center">
									商品信息
								</td>
								<td nowrap="nowrap" align="center">
									接手账号
								</td>
								<td nowrap="nowrap" align="center">
									状&nbsp;&nbsp;态
								</td>
								<td nowrap="nowrap" align="center">
									操&nbsp;&nbsp;作
								</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.result" status="status" id="task">
								<tr onmouseover="this.className='over'"
									onmouseout="this.className='out'">
									<td align="center">
										<s:property value="#task[0]" />
										<br>
										<s:date name="#task[1]" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td align="center">
										<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
											href="javascript:reply('<s:property value='#task[2]' />');"
											title="发送站内信息"><s:property value="#task[2]" /> </a> </SPAN>
										<br>
										<img
											src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[25])" />"
											alt="刷客经验积分：<s:property value="#task[25]" />">
									</td>
									<td align="center">
										<font color="red"><s:property value="#task[4]" /> </font><font
											color="blue">+<s:property value="#task[23]" /> 元 <s:if
												test="#test[5]">(需改价格)</s:if> <s:else>(全额相等)</s:else> <br>
											<font color="red"><s:property value="#task[6]" /> </font><font
											color="blue">+<s:property value="#task[24]" /> 个发布点 
									</td>
									<td align="center" valign="top">
										<s:if test="#task[12]==-2">
												需要审核<br>请QQ联系卖家
											</s:if>
										<s:else>
											<input type="text" title="<s:property value="#task[7]" />"
												readonly="readonly" style="width: 60px"
												value="<s:property value="#task[7]" />" />
											<br>
											<input type="button" value="GO" style="cursor: pointer;"
												class="goItemButton">
										</s:else>
										<br>
										<a href="<s:property value="#task[9]" />"
											title="前往店铺：<s:property value="#task[9]" />"> <font
											color="#FF0000">掌柜:<s:property value="#task[8]" /> </font> </a>
									</td>
									<td align="center">
										<font color="#FF0000"><s:property value="#task[10]" />
										</font>
										<br>
										<img alt="刷客经验积分：<s:property value="#task[11]" />"
											src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />" />
									</td>
									<td align="center">
										<s:if test="#task[12]==-2">
												已接受<br>等待对方审核
												<br>
												剩余
											<font color="red"><s:property value="#task[13]" /> </font>分钟
											</s:if>
										<s:elseif test="#task[12]==-1">
												任务被申述中
											</s:elseif>
										<s:elseif test="#task[12]==2">
												您已接手<br>等待您付款
												<br>
												剩余
												<font color="red"><s:property value="#task[13]" /> </font>分钟
											</s:elseif>
										<s:elseif test="#task[12]==3">
												您已付款
												<br>等待卖家确认发货
											</s:elseif>
										<s:elseif test="#task[12]==4">
											<s:if test="#task[13]==0">
													卖家已发货<br>等待您确认好评
												</s:if>
											<s:else>
												<font color="red"><s:if test="#task[13]>1">
														<s:property value="#task[13]" />小时后好评
												</s:if> <s:else>
														<s:property value="%{#task[13]*60}" />分钟后好评
												</s:else> </font>
											</s:else>
										</s:elseif>
										<s:elseif test="#task[12]==5">
												您已确认好评<br>等待卖家确认好评
											</s:elseif>
										<s:elseif test="#task[12]==6">
												任务完成
											</s:elseif>
									</td>
									<td align="center">
										<s:if test="#task[12]==-2">
										可联系对方加时
										<br>
											<a title="退出任务" class="anniu"
												href="javascript:quitTask('<s:property value="#task[19]"/>')">退出任务</a>
										</s:if>
										<s:elseif test="#task[12]==-1">
												此任务被申述中
											</s:elseif>

										<s:elseif test="#task[12]==2">
											<a title="如果您已经付款，请确认支付"
												href="javascript:payMoney('<s:property value="#task[19]"/>')"><span
												class="anniu">已经支付</span> </a>
											<br>
											<a title="退出任务，并且返回金钱和发布点给您"
												href="javascript:quitTask('<s:property value="#task[19]"/>')"><span
												class="anniu2">退出任务</span> </a>
											<br>
											可联系对方加时
									</s:elseif>
										<s:elseif test="#task[12]==3">
											<a title="撤销上次支付操作！"
												href="javascript:rollbackPay('<s:property value="#task[19]"/>')"><span
												class="anniu">并未支付</span> </a>
										</s:elseif>
										<s:elseif test="#task[12]==4">
											<s:if test="#task[13]==0">
												<a title="买家评价"
													href="javascript:buyerEvaluate('<s:property value="#task[19]"/>')"><span
													class="anniu">我已评价</span> </a>
											</s:if>
											<s:else>
												时间还没到
												</s:else>
										</s:elseif>
										<s:elseif test="#task[12]==5">
												QQ联系对方好评
												<br>
												完成任务
											</s:elseif>
										<s:elseif test="#task[12]==6">
												完成
											</s:elseif>
									</td>
								</tr>
								<tr>
									<td colspan="7">
										<hr width="98%"
											style="height: 1px; border: none; border-top: 1px dashed #0066CC;">
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<s:if test="#request.result.size()==0">
								<tr
									style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
									<td colspan="7">
										没有记录！
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr
									style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px;">
									<td colspan="7" align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 共
										<font color="blue"><b><s:property
													value="creditTaskVO.dataCount" /> </b> </font> 条主题&nbsp;&nbsp;&nbsp;
										<a href="javascript:firstPage()">首页</a>
										<a href="javascript:prevPage()">上一页</a>&nbsp;
										<a href="javascript:nextPage()">下一页</a>&nbsp;
										<a href="javascript:lastPage()">尾页</a>&nbsp;页次：
										<strong><font color="red"><s:property
													value="creditTaskVO.nowPage" /> </font>/<s:property
												value="creditTaskVO.pageCount" /> </strong>页 &nbsp;
										<b><s:property value="creditTaskVO.eachPage" /> </b>条主题/页&nbsp;转到：
										<select id='toPageSelect' size='1' onchange="jumpPage()">
											<s:iterator begin="1" end="creditTaskVO.pageCount" step="1"
												var="index">
												<option value="<s:property value="#index" />">
													第
													<s:property value="#index" />
													页
												</option>
											</s:iterator>
										</select>
									</td>
								</tr>
							</s:else>
						</tfoot>
					</table>
				</div>
		</s:form>
		<div id="sendSmsDIV" title="发送手机短信" style="display: none">
			<s:form action="taskManager/task!sendMsg.php" target="_blank"
				id="sendSmsForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td nowrap="nowrap">
							手机号码：
						</td>
						<td>
							<input name="telphone" id="telphoneID" readonly="readonly"
								maxlength="50">
						</td>
					</tr>
					<tr>
						<td valign="top" nowrap="nowrap">
							发送内容：
						</td>
						<td>
							<textarea name="content" cols="40" rows="4" id="contentID"></textarea>
						</td>
					</tr>
					<tr>
						<td valign="top" nowrap="nowrap">
							&nbsp;
						</td>
						<td>
							<span id="showTip">0</span>个字
							<font color="red">（一条短信收取0.1元，字数为70个字）</font>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>