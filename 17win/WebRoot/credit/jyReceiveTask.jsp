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
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/jyReceiveTask.js" type="text/javascript"></SCRIPT>
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
								<A href="javascript:sort2()"><SPAN class=anniu>已接手</SPAN> </A>
								<A href="javascript:sort3()"><SPAN class=anniu>已支付</SPAN> </A>
								<A href="javascript:sort4()"><SPAN class=anniu>已发货</SPAN> </A>
								<A href="javascript:sort5()"><SPAN class=anniu>已好评</SPAN> </A>
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
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								<img src="images/j_z.gif" width="13" height="16" title="平台担保">
								任务编号
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								发布人
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								任务价格
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								商品信息
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								接手账号
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								状&nbsp;&nbsp;态
							</td>
							<td nowrap="nowrap"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
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
									<s:property value="#task[2]" />
									<br>
									<img src="images/xin_1.gif" alt=刷客经验积分：2779>
								</td>
								<td valign="top" align="center">
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
								<td valign="top" align="center">
									<font color="#FF0000"><s:property value="#task[10]" />
									</font>
									<br>
									<img
										alt="
										刷客经验积分：
													<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />"
										src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />" />
								</td>
								<td valign="top" align="center">
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
											<font color="red"><s:property value="#task[13]" /> </font>小时后好评
												</s:else>
									</s:elseif>
									<s:elseif test="#task[12]==5">
												您已确认好评<br>等待卖家确认好评
											</s:elseif>
									<s:elseif test="#task[12]==6">
												任务完成
											</s:elseif>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[12]==-2">
										<span class="anniu">等待审核</span>
										<br>
										<a title="退出任务" class="anniu"
											href="javascript:quitTask('<s:property value="#task[19]"/>')">退出任务</a>
										<br>
												可联系对方加时
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
							<!-- 地址 其他信息 -->
							<Tr>
								<td colspan="3" valign="top" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;">快递单号：只有发布方可看！<!-- 22 -->
										 </font>
								</td>
								<td colspan="3" align="left">
									<font color="red" style="font-weight: bold;">打分/好评： <s:if
											test="#task[18]==1">
													全部5分
												</s:if> <s:elseif test="#task[18]==2">
												全部不打分
											</s:elseif> <s:elseif test="#task[18]==3">
												带字5分好评
											</s:elseif>/<s:if test="#task[14]==1">
													马上好评
												</s:if> <s:elseif test="#task[14]==2">
												一天后好评
											</s:elseif> <s:elseif test="#task[14]==3">
												两天后好评
											</s:elseif> <s:elseif test="#task[14]==4">
												三天后好评
											</s:elseif> <s:elseif test="#task[14]==5">
											    自定义好评(<s:property value="#task[15]" />小时)
											</s:elseif> </font>
								</td>
								<td align="left" valign="top">
									<font color="red" style="font-weight: bold;">Q&nbsp;&nbsp;Q联系：</font>
									<a
										href="tencent://message/?uin=<s:property value="#task[3]" />">
										<img border="0"
											src="http://wpa.qq.com/pa?p=1:<s:property value="#task[3]" />:41">
									</a>
								</td>
							</Tr>
							<Tr>
								<td colspan="3" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;">收货地址：<s:property
											value="#task[17]" /> </font>
								</td>
								<td colspan="3" align="left">
									<font color="red" style="font-weight: bold;">详细描述：<s:property
											value="#task[16]" /> </font>
								</td>
								<td align="left" valign="top" nowrap="nowrap">
									<font color="red" style="font-weight: bold;">旺旺联系：</font>
									<s:if test="@net.win.utils.StringUtils@isBlank(#task[21])">发布方没提供旺旺号</s:if>
									<s:else>
										<a
											href="javascript:callWW('http://amos1.taobao.com/msg.ww?v=2&uid=<s:property value="#task[21]" />&s=1')"><img
												border="0"
												src="http://amos1.taobao.com/online.ww?v=2&uid=<s:property value="#task[21]" />&s=1"
												alt="点击这里给我发消息" /> </a>
									</s:else>
								</td>
							</Tr>

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
								<td colspan="7">
									您当前还没有在
									<s:property value="#request.platform" />
									发布区接手过任务，
									<font color="red"><a
										href="taskManager/task!initTask.php?platformType=<s:property value="#request.platformType"/>">点此进入发布任务</a>
									</font>
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
					</table>
				</div>
		</s:form>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>