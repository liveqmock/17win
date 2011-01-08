<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
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
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js">
		</script>
		<script
			src="http://cdn.jquerytools.org/1.2.1/tiny/jquery.tools.min.js"></script>

		<SCRIPT src="credit/jyReleaseTask.js" type="text/javascript"></SCRIPT>
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
					<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
						<DIV
							style="BACKGROUND: url(images/task_bg_01.jpg) repeat-x 50% top; WIDTH: 910px; HEIGHT: 38px">
							<DIV style="MARGIN-TOP: 10px; FLOAT: left">
								<IMG src="images/task_02.gif">
							</DIV>
							<DIV style="MARGIN-TOP: 12px; FLOAT: left; MARGIN-LEFT: 10px">
								<A href="javascript:sort1()"><SPAN class=anniu>全部任务</SPAN> </A>
								<A href="javascript:sort9()"><SPAN class=anniu>定时任务</SPAN> </A>
								<A href="javascript:sort7()"><SPAN class=anniu>等待接手</SPAN> </A>
								<A href="javascript:sort8()"><SPAN class=anniu>等待审核</SPAN> </A>
								<A href="javascript:sort2()"><SPAN class=anniu>等待支付</SPAN> </A>
								<A href="javascript:sort3()"><SPAN class=anniu>等待发货</SPAN> </A>
								<A href="javascript:sort4()"><SPAN class=anniu>等待好评</SPAN> </A>
								<A href="javascript:sort5()"><SPAN class=anniu>等待完成</SPAN> </A>
								<A href="javascript:sort6()"><SPAN class=anniu>已完成</SPAN> </A>
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
									class="yell_font"> <SPAN class=anniu>刷新页面</SPAN> </A>
							</DIV>
						</DIV>
					</DIV>
					<table cellpadding="0" cellspacing="0"
						style="CLEAR: both; BORDER-RIGHT: #abbec8 1px solid; BORDER-TOP: #abbec8 1px solid; MARGIN-TOP: 0px; BORDER-LEFT: #abbec8 1px solid; WIDTH: 910px; BORDER-BOTTOM: #abbec8 1px solid;">
						<tr
							style="CLEAR: both; WIDTH: 98%; LINE-HEIGHT: 35px; HEIGHT: 35px; background: #E5F7FB">
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 145px; COLOR: #006600; TEXT-ALIGN: center">
								<img src="images/j_z.gif" width="13" height="16" title="平台担保">
								任务编号
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 125px; COLOR: #006600; TEXT-ALIGN: center">
								价格
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 95px; COLOR: #006600; TEXT-ALIGN: center">
								商品信息
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 120px; COLOR: #006600; TEXT-ALIGN: center">
								17win号
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 115px; COLOR: #006600; TEXT-ALIGN: center">
								接受号
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 130px; COLOR: #006600; TEXT-ALIGN: center">
								状&nbsp;&nbsp;态
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; WIDTH: 150px; COLOR: #006600; TEXT-ALIGN: center">
								操&nbsp;&nbsp;作
							</td>
						</tr>
						<s:iterator value="#request.result" status="status" id="task">
							<tr style="BORDER-BOTTOM: #06314a 1px dashed;">
								<td valign="top" align="center">
									<s:property value="#task[0]" />
									<br>
									<s:date name="#task[1]" format="yyyy-MM-dd HH-mm-ss" />
								</td>
								<td valign="top" align="center">
									<font color="red"><s:property value="#task[2]" /> </font><font
										color="blue">+<s:property value="#task[24]" /> 元 <s:if
											test="#test[3]">(需改价格)</s:if> <s:else>(全额相等)</s:else> <br>

										<font color="red"><s:property value="#task[4]" /> </font><font
										color="blue">+<s:property value="#task[25]" /> 个发布点 
								</td>
								<td valign="top" align="center">
									<input type="text" readonly="readonly"
										title="<s:property value="#task[5]" />" style="width: 60px"
										value="<s:property value="#task[5]" />" />
									<br>
									<input type="button" value="GO" style="cursor: pointer;"
										class="goItemButton">
									<br>
									<a href="<s:property value="#task[19]" />"
										title="前往店铺：<s:property value="#task[19]" />"> <font
										color="#FF0000">掌柜:<s:property value="#task[6]" /> </font> </a>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[7]==0">
												定时任务
												<br>
												没接手人
											</s:if>
									<s:else>
										<s:if test="#task[7]==1">
												暂无接收人
											</s:if>
										<s:else>
											<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
												href="javascript:reply('<s:property value='#task[8]' />');"
												title="发送站内信息"><s:property value="#task[8]" /> </a> </SPAN>
											<br>
											<img alt="刷客经验积分：<s:property value="#task[11]" />"
												src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />" />
										</s:else>
									</s:else>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[7]==1">
												暂无接收人
											</s:if>
									<s:elseif test="#task[7]==-1">
										申诉中
									</s:elseif>
									<s:elseif test="#task[7]==0">
										定时任务
									</s:elseif>
									<s:else>
										<font color="#FF0000"><s:property value="#task[9]" />
										</font>
									</s:else>
								</td>

								<td valign="top" align="center">
									<s:if test="#task[7]==1">
										<s:if test="#task[26]!=null">
											  等待<s:property value="#task[26]" />接手
										</s:if>
										<s:else>
												等待接收人
										</s:else>
									</s:if>
									<s:elseif test="#task[7]==-1">
										申诉中
									</s:elseif>
									<s:elseif test="#task[7]==0">
										<s:if test="#task[26]!=null">
											定时任务
											<br>
											 等待<s:property value="#task[26]" />接手
										</s:if>
										<s:else>
											定时任务
										</s:else>
									</s:elseif>
									<s:elseif test="#task[7]==-2">
												等待您审核<br>
										<s:if test="#task[7]!=1">
											剩余
											<font color="red"> <s:property value="#task[12]" /> </font>
											分钟
											</s:if>
									</s:elseif>
									<s:elseif test="#task[7]==2">
												等待买家付款<br>
										<s:if test="#task[7]!=1">
											剩余
												<font color="red"> <s:property value="#task[12]" />
											</font>
											分钟
											</s:if>
									</s:elseif>
									<s:elseif test="#task[7]==3">
												等待您确认发货
											</s:elseif>
									<s:elseif test="#task[7]==4">
										<s:if test="#task[12]==0">
													等待买家确认好评
												</s:if>
										<s:else>
											<font color="red"> <s:if test="#task[12]>1">
													<s:property value="#task[12]" />小时后好评 
											</s:if> <s:else>
													<s:property value="%{#task[12]*60}" />分钟后好评
											</s:else> </font>
										</s:else>
									</s:elseif>
									<s:elseif test="#task[7]==5">
												等待您确认好评
											</s:elseif>
									<s:else>
												完成
											</s:else>
									<br>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[7]==0">
										<a title="可能由于你填写错误，可以重新进行填写！"
											href="javascript:cancelTask(<s:property value="#task[18]"/>)"><span
											class="anniu">取消重填</span> </a>
										<br>
										<font color="red">定时时间：<s:date name="#task[23]"
												format="yyyy-MM-dd HH-mm-ss" /> </font>
									</s:if>
									<s:elseif test="#task[7]==1">
										<a title="可能由于你填写错误，可以重新进行填写！"
											href="javascript:cancelTask(<s:property value="#task[18]"/>)"><span
											class="anniu">取消重填</span> </a>
										<br>
										<a title="刷新排前可以使您的任务在发布区靠前！"
											href="javascript:toFirstTask(<s:property value="#task[18]"/>)"><span
											class="anniu2">刷新排前</span> </a>
									</s:elseif>
									<s:elseif test="#task[7]==-2">
										<a title="您对该人信任之后，可以允许他接您的任务！"
											href="javascript:audiReceiver(<s:property value="#task[18]"/>)"><span
											class="anniu">审核接收人</span> </a>
										<br>
										<a title="如果对方没有被您审核过，可以清理买家！"
											href="javascript:clearReceiver(<s:property value="#task[18]"/>)"><span
											class="anniu2">清理买家</span> </a>
										<br>
										<a title="为对方加时"
											href="javascript:addTime('<s:property value="#task[18]"/>')"><span
											class="anniu">为他加时</span> </a>
									</s:elseif>
									<s:elseif test="#task[7]==2">
										<a title="为对方加时"
											href="javascript:addTime('<s:property value="#task[18]"/>')"><span
											class="anniu">为他加时</span> </a>
									</s:elseif>
									<s:elseif test="#task[7]==3">
										<a title="请您在确认后，进行发货！"
											href="javascript:dispatch('<s:property value="#task[18]"/>')"><span
											class="anniu">我已发货</span> </a>
									</s:elseif>
									<s:elseif test="#task[7]==4">
										<s:if test="#task[12]==0">
													等待对方评价
												</s:if>
										<s:else>
												时间还没到
												</s:else>
									</s:elseif>
									<s:elseif test="#task[7]==5">
										<a title="请您好评，结束任务！"
											href="javascript:sellerEvaluate('<s:property value="#task[18]"/>')"><span
											class="anniu">确认好评</span> </a>
									</s:elseif>
									<s:else>
												完成
											</s:else>
								</td>
							</tr>
							<tr>
								<td colspan="3" valign="top" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;">快递单号：<s:property
											value="#task[22]" /> </font>
								</td>
								<td colspan="3" valign="top" align="left">
									<font color="red" style="font-weight: bold;">打分/好评： <s:if
											test="#task[17]==1">
													全部5分
												</s:if> <s:elseif test="#task[17]==2">
												全部不打分
											</s:elseif> <s:elseif test="#task[17]==3">
												带字5分好评
											</s:elseif>/<s:if test="#task[13]==1">
													马上好评
												</s:if> <s:elseif test="#task[13]==2">
												一天后好评
											</s:elseif> <s:elseif test="#task[13]==3">
												两天后好评
											</s:elseif> <s:elseif test="#task[13]==4">
												三天后好评
											</s:elseif> <s:elseif test="#task[13]==5">
											    自定义好评(<s:property value="#task[14]" />小时)
											</s:elseif> </font>
								</td>
								<td align="left" valign="top">
									<s:if test="#task[7]==0">
										定时任务没接手人
									</s:if>
									<s:elseif test="#task[7]!=1">
										<font color="red" style="font-weight: bold;">普通联系：</font>
										<img width="25" height="17" border="0" class="qqConnection"
											style="vertical-align: middle;" class="tip"
											src="http://wpa.qq.com/pa?p=1:<s:property value="#task[10]" />:17">
										<div class="tooltip"
											style="background-image: url('images/blackArrowBig.png');">
											<table style="margin-top: 8px">
												<tr>
													<td align="center" style="color: #ffffff">
														<a style="color: white; text-decoration: underline;"
															href="tencent://message/?uin=<s:property value="#task[10]" />">【临时会话】</a>
														<br>
														<a style="color: white; text-decoration: underline;"
															onclick="copyToClipboard('<s:property value="#task[10]" />');"
															href="javascript:void(0)">复制QQ号码</a>
													</td>
												</tr>
											</table>
										</div>
										<a
											href="javascript:openTelephoneDiv('<s:property value="#task[27]" />','<s:property value="#task[28]" />')"><img
												title="发送手机短信" style="vertical-align: middle;"
												src="images/sendTelphone.png"> </a>
									</s:elseif>
								</td>
							</tr>
							<tr>
								<td colspan="3" valign="top" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;">收货地址：<s:property
											value="#task[16]" /> </font>
								</td>
								<td colspan="3" valign="top" align="left">
									<font color="red" style="font-weight: bold;">详细描述：<s:property
											value="#task[15]" /> </font>
								</td>
								<td align="left" valign="top" nowrap="nowrap">
									<s:if test="#task[7]==0">
										定时任务没接手人
									</s:if>
									<s:elseif test="#task[7]!=1">
										<font color="red" style="font-weight: bold;">旺旺联系：</font>
										<s:if test="@net.win.utils.StringUtils@isBlank(#task[21])">接手方没提供旺旺号</s:if>
										<s:else>
											<a
												href="javascript:callWW('http://amos1.taobao.com/msg.ww?v=2&uid=<s:property value="#task[21]" />&s=1')"><img
													border="0"
													src="http://amos1.taobao.com/online.ww?v=2&uid=<s:property value="#task[21]" />&s=1"
													alt="点击这里给我发消息" /> </a>
										</s:else>
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
						<s:if test="#request.result.size()==0">
							<tr
								style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
								<TD colspan="7">
									没有记录！
									<font color="red"><a
										href="taskManager/task!initReleaseTask.php?platformType=<s:property value="#request.platformType"/>">点此进入发布任务</a>
									</font>！
								</TD>
							</tr>
						</s:if>
						<s:else>
							<TR
								style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px;">
								<TD colspan="7" align="left">
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
								</TD>
							</TR>
						</s:else>
					</table>
				</div>
			</div>
		</s:form>
		<div id="sendSmsDIV" title="发送手机短信" style="display: none">
			<s:form action="taskManager/task!sendMsg.php"  target="_blank"
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
							<span id="showTip">0</span>个字<font color="red">（一条短信收取0.1元，字数为70个字）</font> 
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>