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
		<script src="js/x_alt.js" type="text/javascript"></script>
		<s:if test="creditTaskVO.refreshSec!=null">
			<script type="text/javascript">
			setTimeout("refreshPage()",<s:property value='creditTaskVO.refreshSec'/>*1000);  
		</script>
		</s:if>
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
		<s:form action="taskManager/task!initReceivedTast.php" theme="simple"
			id="mainForm" onsubmit="return validateForm()">
			<div align="center" id="partdiv">
				<div align="center">
					<DIV
						style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
						<!-- xgj navigation.jsp -->
						<s:include value="../common/task/navigation.jsp"></s:include>
					</DIV>
					<div style="WIDTH: 910px">
						<div style="display: block;" id="J_SearchBox">
							<table class="table-6">
								<tbody>
									<tr>
										<td>
											<label>
												任&nbsp;务&nbsp;I&nbsp;D：
											</label>
											<s:textfield name="creditTaskVO.testID" maxlength="17">
											</s:textfield>
										</td>
										<td>
											<label>
												发布时间：
											</label>
											<input type="text" style="width: 80px"
												name="creditTaskVO.fbStartDate" id="fbStartDate"
												readonly="readonly"
												value="<s:date name="creditTaskVO.fbStartDate" format="yyyy-MM-dd" />"
												onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})" />
											至
											<input type="text" style="width: 80px"
												name="creditTaskVO.fbEndDate" id="fbEndDate"
												readonly="readonly"
												value="<s:date name="creditTaskVO.fbEndDate" format="yyyy-MM-dd" />"
												onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})" />
										</td>
										<td>
											<label>
												任务状态：
											</label>
											<s:select listKey="key" listValue="value"
												name="creditTaskVO.status"
												list="#{'':'全部','-1':'申诉中的任务','-2':'已经接手，等待对方审核','2':'已经接手，等待我支付','3':'已经支付，等待对方发货','4':'已经发货，等待我好评','5':'已经好评，等待对方好评','6':'任务已完成'}">
											</s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												会 员 名：
											</label>
											<s:textfield name="creditTaskVO.jsUsername" maxlength="12">
											</s:textfield>
										</td>
										<td>
											<label>
												接手时间：
											</label>
											<input type="text" style="width: 80px"
												name="creditTaskVO.jsStartDate" id="jsStartDate"
												readonly="readonly"
												value="<s:date name="creditTaskVO.jsStartDate" format="yyyy-MM-dd" />"
												onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})" />
											至
											<input type="text" style="width: 80px"
												name="creditTaskVO.jsEndDate" id="jsEndDate"
												readonly="readonly"
												value="<s:date name="creditTaskVO.jsEndDate" format="yyyy-MM-dd" />"
												onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})" />
										</td>
										<td align="left">
											<label>
												任务分类：
											</label>
											<s:select listKey="key" listValue="value"
												name="creditTaskVO.taskType"
												list="#{'':'全部','1':'虚拟','2':'实物','3':'套餐'}">
											</s:select>
										</td>
									</tr>
									<tr>
										<td class="col1">
											<label>
												掌 柜 名：
											</label>
											<s:textfield name="creditTaskVO.sellname" maxlength="20">
											</s:textfield>
										</td>
										<td align="left">
											<label>
												接手小号：
											</label>
											<s:textfield name="creditTaskVO.buyername" maxlength="20">
											</s:textfield>
										</td>
										<td align="left">
											<input type="submit" value="搜索" name="btnSearch">
											<input type="hidden" name="platformType"
												value='<s:property value="#request.platformType"/>'
												id="platformType">
											<input type="hidden" name="creditTaskVO.nowPage"
												value="<s:property
											value="creditTaskVO.nowPage" />"
												id="nowPage">
											<input type="hidden"
												value="<s:property
										value="creditTaskVO.pageCount" />"
												id="pageCount">
											<input type="hidden" name="creditTaskVO.id" id="taskId">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
						<DIV
							style="BACKGROUND: url(images/task_bg_01.jpg) repeat-x 50% top; WIDTH: 910px; HEIGHT: 38px">
							<DIV style="MARGIN-TOP: 10px; FLOAT: left">
								<IMG src="images/task_02.gif">
							</DIV>
							<DIV style="MARGIN-TOP: 12px; FLOAT: left; MARGIN-LEFT: 10px">
								<A href="javascript:sort('');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">全部任务</SPAN>
								</A>
								<A href="javascript:sort('-2');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待审核</SPAN>
								</A>
								<A href="javascript:sort('2');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待支付</SPAN>
								</A>
								<A href="javascript:sort('3');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待发货</SPAN>
								</A>
								<A href="javascript:sort('4');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待好评</SPAN>
								</A>
								<A href="javascript:sort('5')"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待完成</SPAN>
								</A>
								<A href="javascript:sort('6')"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">已经完成</SPAN>
								</A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								自定义刷新时间
								<input type="text" name="creditTaskVO.refreshSec"
									value="<s:property value="creditTaskVO.refreshSec"/>"
									style="width: 25px" id="autoreFresh" alt="必须大于5秒，空表示不刷新！" />
								秒
								<A alt="点击刷新" href="javascript:refreshPage();" class="yell_font">
									<SPAN
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
									价格/发布点
								</td>
								<td nowrap="nowrap" align="center">
									商品信息
								</td>
								<td nowrap="nowrap" align="center">
									发布方信息
								</td>
								<td nowrap="nowrap" align="center">
									接手方信息
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
										<s:if test="#task.taskType==1">
											<img src="images/xnType.jpg" alt="虚拟任务" />
										</s:if>
										<s:elseif test="#task.taskType==2">
											<img src="images/swType.jpg" alt="实物任务" />
										</s:elseif>
										<s:elseif test="#task.taskType==3">
											<img src="images/tcType.jpg" alt="套餐任务" />
										</s:elseif>
										<s:property value="#task.testID" />
										<br>
										<s:if test="#task.assignUser!=null && #task.assignUser!=''">
											<img src="images/tdTask.gif"
												alt="特定任务，发布人是<s:property value="#task.fbUsername"/>" />
										</s:if>
										<s:else>
											<img src="images/ptTask.jpg"
												alt="普通任务，发布人是<s:property value="#task.fbUsername"/>" />
										</s:else>
										<s:date name="#task.releaseDate" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td align="center">
										<s:property value="#task.money" />
										+
										<s:property value="#task.addtionMoney" />
										<br>
										<s:property value="#task.releaseDot" />
										+
										<s:property value="#task.addtionReleaseDot" />
									</td>
									<td align="center" valign="middle">
										<a style="cursor: pointer;"
											href="javascript:showItemUrl('<s:property value="#task.itemUrl" />','<s:property value="#task.updatePrice" />','<s:property value="#task.grade" />','<s:property value="#task.comment" />','<s:property value="#task.address" />','<s:property value="#task.status" />');">
											<img src="images/renwu-3.png" border="0"> </a>
										<br>
										<a alt="点此直接打开商品地址"
											href="javascript:openItemUrl('<s:property value="#task.itemUrl" />','<s:property value="#task.updatePrice" />','<s:property value="#task.grade" />','<s:property value="#task.comment" />','<s:property value="#task.address" />','<s:property value="#task.status" />');">
											<img src="images/open.gif" border="0"> </a>
									</td>
									<td align="center">

										<img width="25" height="17" border="0" class="qqConnection"
											style="vertical-align: middle;" class="tip"
											src="http://wpa.qq.com/pa?p=1:<s:property value="#task.fbQQ" />:17">
										<div class="tooltip"
											style="background-image: url('images/blackArrowBig.png');">
											<table style="margin-top: 8px">
												<tr>
													<td align="center" style="color: #ffffff">
														<a style="color: white; text-decoration: underline;"
															href="tencent://message/?uin=<s:property value="#task.fbQQ" />">【临时会话】</a>
														<br>
														<a style="color: white; text-decoration: underline;"
															href="javascript:copyToClipboard('<s:property value="#task.fbQQ" />');">复制QQ号码</a>
													</td>
												</tr>
											</table>
										</div>
										<a
											href="javascript:openTelephoneDiv('<s:property value="#task.fbTelphone" />','<s:property value="#task.jsUsername" />')"><img
												alt="发送手机短信" style="vertical-align: middle;"
												src="images/sendTelphone.png"> </a>
										<!-- 
										<a
											href="javascript:openTelephoneDiv('<s:property value="#task.fbTelphone" />','<s:property value="#task.jsUsername" />')"><img
												alt alt="发送站内信" style="vertical-align: middle;"
												src="images/sms.gif"> </a>
												 -->
										<s:if test="#task.jsWW!=null && #task.fbWW!=''">
											<img border="0" class="qqConnection"
												style="vertical-align: middle;" class="tip"
												src="http://amos1.taobao.com/online.ww?v=2&uid=<s:property value="#task.fbWW" />&s=2">
											<div class="tooltip"
												style="background-image: url('images/blackArrowBig.png');">
												<table style="margin-top: 8px">
													<tr>
														<td align="center" style="color: #ffffff">
															<a style="color: white; text-decoration: underline;"
																href="javascript:callWW('http://amos1.taobao.com/msg.ww?v=2&uid=<s:property value="#task.fbWW" />&s=2')">【临时会话】</a>
															<br>
															<a style="color: white; text-decoration: underline;"
																onclick="copyToClipboard('<s:property value="#task.fbWW" />');"
																href="javascript:void(0)">复制旺旺号</a>
														</td>
													</tr>
												</table>
											</div>
										</s:if>
										<br>
										<a
											style="color: white; text-decoration: underline; cursor: pointer;"
											alt="复制掌柜"
											onclick="copyToClipboard('<s:property value="#task.sellname" />');"
											href="javascript:void(0)"><font color="#FF0000"><s:property
													value="#task.sellname" /> </font> </a>

									</td>
									<td align="center">
										<s:date name="#task.receiveDate" format="yyyy-MM-dd HH:mm" />
										<br>
										<a
											style="color: white; text-decoration: underline; cursor: pointer;"
											alt="复制买号"
											onclick="copyToClipboard('<s:property value="#task.buyername" />');"
											href="javascript:void(0)"><font color="#FF0000"><s:property
													value="#task.buyername" /> </font> </a>
									</td>
									<td align="center">
										<s:if test="#task.status==-2">
												等待审核
												<br>
											<s:if test="#task.remainTime>0">
											剩余
											<font color="red"> <s:property
														value="#task.remainTime" /> </font>
											分钟
											</s:if>
											<s:else>
												时间已到
											</s:else>
										</s:if>
										<s:elseif test="#task.status==-1">
												任务被申诉中
											</s:elseif>
										<s:elseif test="#task.status==2">
													等待付款<br>
											<s:if test="#task.remainTime>0">
											剩余
											<font color="red"> <s:property
														value="#task.remainTime" /> </font>
											分钟
											</s:if>
											<s:else>
												时间已到
											</s:else>
										</s:elseif>
										<s:elseif test="#task.status==3">
												已经付款
												<br>等待卖家发货
											</s:elseif>
										<s:elseif test="#task.status==4">
											<s:if test="#task.remainTime<=0">
													卖家已发货<br>等待您确认好评
												</s:if>
											<s:else>
												<s:if test="#task.remainTime>=1440">
													剩余：<s:property value="#task.remainTime/1440" />天<s:property
														value="#task.remainTime%1440/60" />时<s:property
														value="#task.remainTime%60" />分
												</s:if>
												<s:elseif test="#task.remainTime>=60">
													剩余：<s:property value="#task.remainTime/60" />时<s:property
														value="#task.remainTime%60" />分
												</s:elseif>
												<s:else>
													剩余：<s:property value="#task.remainTime" />分
												</s:else>

											</s:else>
										</s:elseif>
										<s:elseif test="#task.status==5">
												您已确认好评<br>等待卖家确认好评
											</s:elseif>
										<s:elseif test="#task.status==6">
												任务完成
											</s:elseif>
									</td>
									<td align="center">
										<s:if test="#task.status==-2">
											<a alt="退出任务" class="anniu"
												href="javascript:quitTask('<s:property value="#task.id"/>')">退出任务</a>
										</s:if>
										<s:elseif test="#task.status==-1">
												此任务被申述中
											</s:elseif>

										<s:elseif test="#task.status==2">
											<a alt="如果您已经付款，请确认支付"
												href="javascript:payMoney('<s:property value="#task.id"/>')"><span
												class="anniu">已经支付</span> </a>
											<br>
											<a alt="退出任务，并且返回金钱和发布点给您"
												href="javascript:quitTask('<s:property value="#task.id"/>')"><span
												class="anniu2">退出任务</span> </a>
										</s:elseif>
										<s:elseif test="#task.status==3">
											<a alt="撤销上次支付操作！"
												href="javascript:rollbackPay('<s:property value="#task.id"/>')"><span
												class="anniu">撤消支付</span> </a>
										</s:elseif>
										<s:elseif test="#task.status==4">
											<s:if test="#task.remainTime<=0">
												<a alt="买家评价"
													href="javascript:buyerEvaluate('<s:property value="#task.id"/>')"><span
													class="anniu">我已评价</span> </a>
											</s:if>
											<s:else>
												时间还没到
												</s:else>
										</s:elseif>
										<s:elseif test="#task.status==5">
												已经好评
												<br>
												对方好评
											</s:elseif>
										<s:elseif test="#task.status==6">
												任务完成
												<br>
												双方互评
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
													value="creditTaskVO.dataCount" /> </b> </font> 条记录&nbsp;&nbsp;&nbsp;
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
			</div>
		</s:form>
		<div id="sendSmsDIV" title="发送手机短信" style="display: none">
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
						<font color="red">（70个字收取0.1元）</font>
					</td>
				</tr>
			</table>
		</div>
		<!-- 显示地址 -->
		<div id="addressDIV" title="发布方提醒" style="display: none">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead>
					<tr>
						<td colspan="99">
							<font style="font: 14px;" color="red"><b> 您的要求:</b> </font>
							<input type="hidden" id="nowAddressTaskStatus" />
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td nowrap="nowrap" valign="top">
							是否修改价格：
						</td>
						<td>
							<span id="updatePrice"></span>
						</td>
						<td nowrap="nowrap" valign="top">
							<span alertSpan="alertSpan" style="display: none"><input
									type="checkbox" name="alertCheckedBox">我已知道 </span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							好评要求：
						</td>
						<td>
							<span id="grade"></span>
						</td>
						<td>
							<span style="display: none" alertSpan="alertSpan"><input
									type="checkbox" name="alertCheckedBox">我已知道 </span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							自定义评语：
						</td>
						<td>
							<span id="comment"
								style="text-decoration: underline; color: red; cursor: pointer"></span>
						</td>
						<td valign="top">
							<span style="display: none" alertSpan="alertSpan"><input
									type="checkbox" name="alertCheckedBox">我已知道 </span>
						</td>
					</tr>
					<tr>
						<td valign="top">
							收货地址：
						</td>
						<td>
							<span id="address"
								style="text-decoration: underline; color: red; cursor: pointer"></span>
						</td>
						<td valign="top">
							<span style="display: none" alertSpan="alertSpan"><input
									type="checkbox" name="alertCheckedBox">我已知道 </span>
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead>
					<tr>
						<td colspan="3" align="sleft">
							<font style="font: 14px;" color="red"><b> 商品地址：</b>
						</td>
					</tr>
				</thead>
				<tbody id="itemContent">
				</tbody>
			</table>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>