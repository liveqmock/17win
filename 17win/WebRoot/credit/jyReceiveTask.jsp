<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
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
								<A href="?sort=2"><SPAN class=anniu>全部任务</SPAN> </A>
								<A href="?sort=1"><SPAN class=anniu>已接收</SPAN> </A>
								<A href="?sort=3"><SPAN class=anniu>已支付</SPAN> </A>
								<A href="?sort=4"><SPAN class=anniu>已发货</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>已好评</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>已完成</SPAN> </A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								自定义刷新时间
								<input type="text" value="" style="width: 25px"
									title="不填写或则0表示不自动刷新" />
								秒
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
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
									<s:date name="#task[1]" format="yyyy-MM-dd hh-mm-ss" />
								</td>
								<td valign="top" align="center">
									<s:property value="#task[2]" />
									（
									<font color=red>在线</font>）
									<br>
									<a
										href="tencent://message/?uin=<s:property value="#task[3]" />">
										<img border="0"
											src="http://wpa.qq.com/pa?p=1:<s:property value="#task[3]" />:41">
									</a>
									<br>
									<img src="images/xin_1.gif" alt=刷客经验积分：2779>
								</td>
								<td valign="top" align="center">
									<font color="red"><s:property value="#task[4]" /> </font> 元
									<s:if test="#test[5]">(需改价格)</s:if>
									<s:else>(全额相等)</s:else>
									<br>
									<font color="red"><s:property value="#task[6]" /> </font> 个发布点
									<IMG alt="延迟收货" src=images/shiwu.gif>
								</td>
								<td align="center" valign="top">
									<s:if test="#task[12]==-2">
												需要审核，请QQ联系卖家
											</s:if>
									<s:else>
										<input type="text" title="<s:property value="#task[7]" />"
											style="width: 60px" value="<s:property value="#task[7]" />" />
										<input type="button" value="GO">
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
												已接受，等待对方审核
												<br>
									剩余
											<font color="red"><s:property value="#task[13]" /> </font>分钟
											</s:if>
									<s:elseif test="#task[12]==-1">
												任务被申述中
											</s:elseif>
									<s:elseif test="#task[12]==2">
												您已接手，等待您付款
												<br>
												剩余
												<font color="red"><s:property value="#task[13]" /> </font>分钟
											</s:elseif>
									<s:elseif test="#task[12]==3">
												您已付款
												<br>等待卖家确认发货
											</s:elseif>
									<s:elseif test="#task[12]==4">
												卖家发货了。等待您确认好评
											</s:elseif>
									<s:elseif test="#task[12]==5">
												您已确认好评。等待卖家确认好评
											</s:elseif>
									<s:elseif test="#task[12]==6">
												任务完成...
											</s:elseif>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[12]==-2">
												已经接手,等待审核
												<br>
												退出任务
												<br>
												联系对方可加时
											</s:if>
									<s:elseif test="#task[12]==-1">
												此任务被申述中
											</s:elseif>
									<s:elseif test="#task[12]==2">
										<a title="如果您已经付款，请确认支付"
											href="javascript:payMoney('<s:property value="#task[19]"/>')">已经支付</a>
										</br>
										<a title="退出任务，并且返回金钱和发布点给您"
											href="javascript:quitTask('<s:property value="#task[19]"/>')">退出任务</a>
										<br>
											联系对方可加时
									</s:elseif>
									<s:elseif test="#task[12]==3">
												并未支付
											</s:elseif>
									<s:elseif test="#task[12]==4">
												我已评价
											</s:elseif>
									<s:elseif test="#task[12]==5">
												QQ联系对方好评，完成任务
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
									<font color="red" style="font-weight: bold;">快递单号：</font>
								</td>
								<td colspan="4" align="left">
									<font color="red" style="font-weight: bold;">打分/好评：</font>
								</td>
							</Tr>
							<Tr>
								<td colspan="3" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;">收货地址：</font>
								</td>
								<td colspan="4" align="left">
									<font color="red" style="font-weight: bold;">详细描述：</font>
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
								style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
								<td colspan="7">
									共
									<font color="blue"><b>34</b> </font> 条主题&nbsp;&nbsp;&nbsp;首页
									上一页&nbsp;
									<a href='mymission.asp?PageNo=2'>下一页</a>&nbsp;
									<a href='mymission.asp?PageNo=4'>尾页</a>&nbsp;页次：
									<strong><font color="red">1</font>/4</strong>页 &nbsp;
									<b>10</b>条主题/页&nbsp;转到：
									<select name='page' size='1'
										onchange="javascript:window.location='mymission.asp?PageNo='+this.options[this.selectedIndex].value;">
										<option value='1' selected="selected">
											第1页
										</option>
										<option value='2'>
											第2页
										</option>
										<option value='3'>
											第3页
										</option>
										<option value='4'>
											第4页
										</option>
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