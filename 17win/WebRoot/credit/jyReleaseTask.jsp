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
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript">
		</script>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			defer="defer" type="text/javascript"></script>
		<script
			src="http://cdn.jquerytools.org/1.2.1/tiny/jquery.tools.min.js"></script>
		<script src="js/x_alt.js" type="text/javascript"></script>
		<SCRIPT src="credit/jyReleaseTask.js" type="text/javascript"></SCRIPT>
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
	<BODY onkeydown="return refuseF5();">
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<s:form action="taskManager/task!initReleasedTast.php" theme="simple"
			id="mainForm" onsubmit="return validateForm()">
			<div align="center" id="partdiv">
				<div align="center">
					<DIV
						style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
						<!-- xgj navigation.jsp -->
						<s:include value="../common/task/navigation.jsp"></s:include>
					</DIV>
					<div style="WIDTH: 910px">
						<div style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
							<table>
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
												list="#{'':'全部','-1':'申诉中的任务','0':'定时任务','1':'等待接手人','-2':'已经接手，等待我审核','2':'已经接手，等待对方支付','3':'已经支付，等待我发货','4':'已经发货，等待对方好评','5':'已经好评，等待我好评','6':'任务已完成'}">
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
										<td>
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
											<input type="submit" style="cursor: pointer;" value="搜索"
												name="btnSearch">
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
								<A href="javascript:sort('0');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">定时任务</SPAN>
								</A>
								<A href="javascript:sort('1');"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">等待接手</SPAN>
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
									接手方信息
								</td>
								<td nowrap="nowrap" align="center">
									发布方信息
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
									<td align="center" nowrap="nowrap">
										<s:if test="#task.taskType==1">
											<img src="images/xnType.jpg" alt="虚拟任务" />
										</s:if>
										<s:elseif test="#task.taskType==2">
											<img src="images/swType.jpg" alt="实物任务" />
										</s:elseif>
										<s:elseif test="#task.taskType==3">
											<img src="images/tcType.jpg" alt="套餐任务" />
										</s:elseif>
										<span alt="任务ID"><s:property value="#task.testID" /> </span>
										<br>
										<s:if test="#task.assignUser!=null && #task.assignUser!=''">
											<img src="images/tdTask.gif"
												alt="特定任务，接手人是<s:property value="#task.assignUser"/>" />
										</s:if>
										<s:else>
											<img src="images/ptTask.jpg"
												alt="普通任务，接手人是<s:property value="#task.jsUsername"/>" />
										</s:else>
										<span alt="任务发布时间"> <s:date name="#task.releaseDate"
												format="yyyy-MM-dd HH:mm:ss" /> </span>
									</td>
									<td align="center" nowrap="nowrap">
										<span
											alt="任务金额：<s:property value="#task.money" />，附加金额：<s:property
												value="#task.addtionMoney" />"><s:property
												value="#task.money" /> + <s:property
												value="#task.addtionMoney" /> </span>
										<br>
										<span
											alt="任务发布点：<s:property value="#task.money" />，附加发布点：<s:property
												value="#task.addtionMoney" />">
											<s:property value="#task.releaseDot" /> + <s:property
												value="#task.addtionReleaseDot" /> </span>
									</td>
									<td valign="middle" align="center" nowrap="nowrap">
										<a style="cursor: pointer;"
											href="javascript:showItemUrl('<s:property value="#task.itemUrl" />','<s:property value="#task.updatePrice" />','<s:property value="#task.grade" />','<s:property value="#task.comment" />','<s:property value="#task.address" />','<s:property value="#task.waybill" />',<s:property value="#task.intervalHour" />);">
											<img src="images/renwu-3.png" border="0"> </a>
										<br>
										<a alt="点此直接打开商品地址"
											href="javascript:openItemUrl('<s:property value="#task.itemUrl" />','<s:property value="#task.updatePrice" />','<s:property value="#task.grade" />','<s:property value="#task.comment" />','<s:property value="#task.address" />','<s:property value="#task.waybill" />',<s:property value="#task.intervalHour" />);">
											<img src="images/open.gif" border="0"> </a>
									</td>
									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==0">
												定时任务
												<br>
												没接手人
											</s:if>
										<s:else>
											<s:if test="#task.status==1">
												无接手人
												<br>
												请等片刻
											</s:if>
											<s:else>

												<img width="25" height="17" border="0" class="qqConnection"
													style="vertical-align: middle;" class="tip"
													src="http://wpa.qq.com/pa?p=1:<s:property value="#task.jsQQ" />:17">
												<div class="tooltip"
													style="background-image: url('images/blackArrowBig.png');">
													<table style="margin-top: 8px">
														<tr>
															<td align="center" style="color: #ffffff">
																<a style="color: white; text-decoration: underline;"
																	href="tencent://message/?uin=<s:property value="#task.jsQQ" />">【临时会话】</a>
																<br>
																<a style="color: white; text-decoration: underline;"
																	onclick="copyToClipboard('<s:property value="#task.jsQQ" />');"
																	href="javascript:void(0)">复制QQ号码</a>
															</td>
														</tr>
													</table>
												</div>
												<a
													href="javascript:openTelephoneDiv('<s:property value="#task.jsTelphone" />','<s:property value="#task.fbUsername" />')"><img
														alt="发送手机短信" style="vertical-align: middle;"
														src="images/sendTelphone.png"> </a>
												<!-- 
												<a
													href="javascript:openTelephoneDiv('<s:property value="#task.jsTelphone" />','<s:property value="#task.fbUsername" />')"><img
														alt alt="发送站内信" style="vertical-align: middle;"
														src="images/sms.gif"> </a>
														 -->
												<s:if test="#task.jsWW!=null && #task.jsWW!=''">
													<img border="0" class="qqConnection"
														style="vertical-align: middle;" class="tip"
														src="http://amos1.taobao.com/online.ww?v=2&uid=<s:property value="#task.jsWW" />&s=2">
													<div class="tooltip"
														style="background-image: url('images/blackArrowBig.png');">
														<table style="margin-top: 8px">
															<tr>
																<td align="center" style="color: #ffffff">
																	<a style="color: white; text-decoration: underline;"
																		href="javascript:callWW('http://amos1.taobao.com/msg.ww?v=2&uid=<s:property value="#task.jsWW" />&s=2')">【临时会话】</a>
																	<br>
																	<a style="color: white; text-decoration: underline;"
																		onclick="copyToClipboard('<s:property value="#task.jsWW" />');"
																		href="javascript:void(0)">复制旺旺号</a>
																</td>
															</tr>
														</table>
													</div>
												</s:if>
												<br>
												<a
													style="color: white; text-decoration: underline; cursor: pointer;"
													alt="复制买号"
													onclick="copyToClipboard('<s:property value="#task.buyername" />');"
													href="javascript:void(0)"><font color="#FF0000"><s:property
															value="#task.buyername" /> </font> </a>
											</s:else>
										</s:else>
									</td>
									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==1">
											无接手人
												<br>
												请等片刻
											</s:if>
										<s:elseif test="#task.status==-1">
										在申诉中
										<br>
										不能操作
									</s:elseif>
										<s:elseif test="#task.status==0">
										定时任务
										<br>
										没接手人
									</s:elseif>
										<s:else>
											<span
												alt="接手日期：<s:date name="#task.receiveDate" format="yyyy-MM-dd HH:mm" />">
												<s:date name="#task.receiveDate" format="yyyy-MM-dd HH:mm" />
											</span>
											<br>
											<a
												style="color: white; text-decoration: underline; cursor: pointer;"
												alt="复制掌柜"
												href="javascript:copyToClipboard('<s:property value="#task.sellname" />');"><font
												color="#FF0000"><s:property value="#task.sellname" />
											</font> </a>
										</s:else>
									</td>

									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==1">
											等待接手
										</s:if>
										<s:elseif test="#task.status==-1">
										任务被申诉中
										</s:elseif>
										<s:elseif test="#task.status==0">
											定时任务
											<br>
											没到时间
										</s:elseif>
										<s:elseif test="#task.status==-2">
												等待审核<br>
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
												等待您发货
											</s:elseif>
										<s:elseif test="#task.status==4">
											<s:if test="#task.remainTime<=0">
													等待对方好评
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
												等待您确认好评
											</s:elseif>
										<s:else>
												任务完成
											</s:else>
									</td>
									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==0">
											<a alt="取消任务！"
												href="javascript:cancelTask(<s:property value="#task.id"/>)"><span
												class="anniu">取消任务</span> </a>
											<br>
											<font color="red">定时时间：<s:date
													name="#task.timeingTime" format="yyyy-MM-dd HH-mm-ss" /> </font>
										</s:if>
										<s:elseif test="#task.status==1">
											<a alt="取消任务！"
												href="javascript:cancelTask(<s:property value="#task.id"/>)"><span
												class="anniu">取消任务</span> </a>
											<!-- 
											<br>
											<a alt="刷新排前可以使您的任务在发布区靠前！"
												href="javascript:toFirstTask(<s:property value="#task.id"/>)"><span
												class="anniu2">刷新排前</span> </a>
												 -->
										</s:elseif>
										<s:elseif test="#task.status==-2">
											<a alt="您对该人信任之后，可以允许他接您的任务！"
												href="javascript:audiReceiver(<s:property value="#task.id"/>)"><span
												class="anniu">审核对方</span> </a>
											<br>
											<a alt="如果对方没有被您审核过，可以清除买家！"
												href="javascript:clearReceiver(<s:property value="#task.id"/>)"><span
												class="anniu2">清除对方</span> </a>
											<br>
										</s:elseif>
										<s:elseif test="#task.status==2">
											<s:if test="#task.remainTime<=0">
												<a alt="如果对方长时间没操作可以清除对方！"
													href="javascript:clearReceiver(<s:property value="#task.id"/>)"><span
													class="anniu2">清除对方</span> </a>
											</s:if>
											<s:else>
												等待付款
											</s:else>
										</s:elseif>
										<s:elseif test="#task.status==3">
											<a alt="请您在确认后，进行发货！"
												href="javascript:dispatch('<s:property value="#task.id"/>')"><span
												class="anniu">我已发货</span> </a>
										</s:elseif>
										<s:elseif test="#task.status==4">
											<s:if test="#task.remainTime==0">
													等待对方评价
												</s:if>
											<s:else>
												时间还没到
												</s:else>
										</s:elseif>
										<s:elseif test="#task.status==5">
											<a alt="请您好评，结束任务！"
												href="javascript:sellerEvaluate('<s:property value="#task.id"/>')"><span
												class="anniu">确认好评</span> </a>
										</s:elseif>
										<s:else>
												任务完成
												<br>
												双方互评
											</s:else>
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
									<TD colspan="7">
										没有记录！
									</TD>
								</tr>
							</s:if>
							<s:else>
								<TR
									style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px;">
									<TD colspan="7" align="left">
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
												<option value="<s:property value="#index" />"
													<s:if test="creditTaskVO.nowPage==#index">
													selected='selected'
												</s:if>>

													第
													<s:property value="#index" />
													页
												</option>
											</s:iterator>
										</select>
									</TD>
								</TR>
							</s:else>
						</tfoot>
					</table>
				</div>
			</div>
		</s:form>
		<!-- 发送手机短信层 -->
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
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td nowrap="nowrap">
							是否修改价格：
						</td>
						<td width="70%">
							<span id="updatePrice"></span>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							好评要求：
						</td>
						<td>
							<span id="grade"></span>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							快递单号：
						</td>
						<td>
							<span id="waybill"
								style="text-decoration: underline; color: red; cursor: pointer"></span>
						</td>
						<td>
							&nbsp;
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
						<td>
							&nbsp;
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
						<td>
							&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead>
					<tr>
						<td colspan="3" align="left">
							<font style="font: 14px;" color="red"><b> 商品地址：</b> </font>
							<img alt="打开全部商品地址" onclick="openAllItemUrl()"
								style="cursor: pointer;" border='0' src='images/open.gif'/>
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