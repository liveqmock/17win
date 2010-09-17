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
		<SCRIPT src="credit/jyReleaseTask.js" type="text/javascript"></SCRIPT>
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
								<A href="?sort=1"><SPAN class=anniu>等待支付</SPAN> </A>
								<A href="?sort=3"><SPAN class=anniu>等待发货</SPAN> </A>
								<A href="?sort=4"><SPAN class=anniu>等待好评</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>等待完成</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>已完成</SPAN> </A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								自定义刷新时间
								<input type="text" value="" style="width: 25px"
									title="不填写或则0表示不自动刷新" />
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								秒
								<A title=点击刷新 href="javascript:location.reload(true);"
									class="yell_font"> <SPAN class=anniu>刷新页面</SPAN> </A>
							</DIV>
						</DIV>
					</DIV>
					<DIV
						style="CLEAR: both; BORDER-RIGHT: #abbec8 1px solid; BORDER-TOP: #abbec8 1px solid; MARGIN-TOP: 0px; BORDER-LEFT: #abbec8 1px solid; WIDTH: 910px; BORDER-BOTTOM: #abbec8 1px solid; BACKGROUND-COLOR: #e6f7fb">
						<DIV
							style="CLEAR: both; WIDTH: 98%; LINE-HEIGHT: 35px; HEIGHT: 35px">
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 145px; COLOR: #006600; TEXT-ALIGN: center">
								任务编号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 125px; COLOR: #006600; TEXT-ALIGN: center">
								价格
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 95px; COLOR: #006600; TEXT-ALIGN: center">
								商品信息
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 120px; COLOR: #006600; TEXT-ALIGN: center">
								17win号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 115px; COLOR: #006600; TEXT-ALIGN: center">
								<s:property value="#request.platform" />
								接受号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 130px; COLOR: #006600; TEXT-ALIGN: center">
								状&nbsp;&nbsp;态
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 150px; COLOR: #006600; TEXT-ALIGN: center">
								操&nbsp;&nbsp;作
							</DIV>
						</DIV>
					</DIV>
					<DIV
						style="CLEAR: both; BORDER-RIGHT: #abbec8 1px solid; BORDER-TOP: #abbec8 1px; BORDER-LEFT: #abbec8 1px solid; WIDTH: 910px; BORDER-BOTTOM: #abbec8 1px solid; BACKGROUND-COLOR: #ffffff">
						<s:iterator value="#request.result" status="status" id="task">
							<DIV class=missionbg
								style="WIDTH: 98%; PADDING-TOP: 10px; BORDER-BOTTOM: #06314a 1px dashed; HEIGHT: 90px">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td width="145px" align="left" nowrap="nowrap">
											<s:property value="#task[0]" />
										</td>
										<td width="125px">
											<img src="images/j_z.gif" width="13" height="16" title="平台担保">
											<s:property value="#task[2]" />
											元
											<s:if test="#test[3]">(需改价格)</s:if>
											<s:else>(全额相等)</s:else>

										</td>
										<td width="130px" nowrap="nowrap">
											<input name="2010915238272637" type="text"
												title="<s:property value="#task[5]" />"
												id="2010915238272637" style="width: 60px"
												value="<s:property value="#task[5]" />" />
											<input type="button" value="GO">
										</td>
										<td width="120px" align="left">
											<s:if test="#task[7]==1">
												暂无接收人
											</s:if>
											<s:else>
												<a href="#" title="发送站内信息" target="_blank"><s:property
														value="#task[8]" />（<font color=red>在线</font>）</a>
											</s:else>
										</td>
										<td style="width: 115px">
											<s:if test="#task[7]==1">
												暂无接收人
											</s:if>
											<s:else>
												<font color="#FF0000"><s:property value="#task[9]" />
												</font>
											</s:else>
										</td>

										<td width="130px">
											<s:if test="#task[7]==1">
												等待接收人...
											</s:if>
											<s:elseif test="#task[7]==-2">
												等待您审核...
											</s:elseif>
											<s:elseif test="#task[7]==2">
												等待买家付款
											</s:elseif>
											<s:elseif test="#task[7]==3">
												等待您确认发货
											</s:elseif>
											<s:elseif test="#task[7]==4">
												等待买家确认好评
											</s:elseif>
											<s:elseif test="#task[7]==5">
												等待您确认好评
											</s:elseif>
											<s:else>
												完成
											</s:else>

										</td>
										<td width="150px">
											<s:if test="#task[7]==1">
												<a
													href="javascript:cancelTask(<s:property value="#task[18]"/>)">取消重填</a>
											</s:if>
											<s:elseif test="#task[7]==-2">
												审核接收人 清理买家
											</s:elseif>
											<s:elseif test="#task[7]==2">
												已审核
											</s:elseif>
											<s:elseif test="#task[7]==3">
												我已发货
											</s:elseif>
											<s:elseif test="#task[7]==4">
												联系对方好评
											</s:elseif>
											<s:elseif test="#task[7]==5">
												确认好评
											</s:elseif>
											<s:else>
												完成
											</s:else>

										</td>
									</tr>
									<tr>
										<td width="145px" align="left">
											<s:date name="#task[1]" format="yyyy-MM-dd hh-mm-ss" />
										</td>
										<td width="115px">
											<IMG alt=延迟收货 src=images/shiwu.gif>
											发布点
											<s:property value="#task[4]" />
											个
										</td>
										<td width="130px" nowrap="nowrap">
											<a href="<s:property value="#task[19]" />"
												title="前往店铺：<s:property value="#task[19]" />"> <font
												color="#FF0000">掌柜:<s:property value="#task[6]" /> </font>
											</a>
										</td>
										<td width="120px" align="left">
											<s:if test="#task[7]<>1">
												<a
													href="tencent://message/?uin=<s:property value="#task[8]"/>"><img
														src="http://wpa.qq.com/pa?p=1:1317225767:41" border="0" />
												</a>
											</s:if>
										</td>
										<td width="115px">
											<s:if test="#task[7]<>1">
												<img
													src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />"
													alt=刷客经验积分：
													<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[11])" />>
											</s:if>
										</td>
										<td width="130px">
											<s:if test="#task[7]<>1">
											剩余
											<s:property value="#task[12]" />
											分钟
											</s:if>
										</td>
										<td width="150px">
											<s:if test="#task[7]==1">
												<a
													href="javascript:toFirstTask(<s:property value="#task[18]"/>)">刷新排前</a>
											</s:if>
											<s:if test="#task[7]==-2">
												为对方加时
											</s:if>
											<s:elseif test="#task[7]==2">
												为对方加时
											</s:elseif>
											<s:elseif test="#task[7]==3">
												审核后请发货平台处理
											</s:elseif>
											<s:elseif test="#task[7]==4">
											    等待对方好评
											</s:elseif>
											<s:elseif test="#task[7]==5">
												核实后请确认好评
											</s:elseif>
										</td>
									</tr>
									<tr>
										<td colspan="3" align="left">
											<font color="red" style="font-weight: bold;">快递单号：</font>
										</td>
										<td colspan="4" align="left">
											<font color="red" style="font-weight: bold;">打分/好评：
											<s:if test="#task[13]==1">
													马上好评
												</s:if>
											<s:elseif test="#task[13]==2">
												一天后好评
											</s:elseif>
											<s:elseif test="#task[13]==3">
												两天后好评
											</s:elseif>
											<s:elseif test="#task[13]==4">
												三天后好评
											</s:elseif>
											<s:elseif test="#task[13]==5">
											    自定义好评(<s:property value="#task[14]" />小时)
											</s:elseif>
											/
											<s:if test="#task[17]==1">
													全部5分
												</s:if>
											<s:elseif test="#task[17]==2">
												全部不打分
											</s:elseif>
											<s:elseif test="#task[17]==3">
												带字5分好评
											</s:elseif>
											 </font>
										</td>
									</tr>
									<tr>
										<td colspan="3" align="left">
											<font color="red" style="font-weight: bold;">收货地址：<s:property
													value="#task[16]" /> </font>
										</td>
										<td colspan="4" align="left">
											<font color="red" style="font-weight: bold;">详细描述：<s:property
													value="#task[15]" /> </font>
										</td>
									</tr>
								</table>
							</DIV>
						</s:iterator>
						<s:if test="#request.result.size()==0">
							<DIV
								style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
								您当前还没有在
								<s:property value="#request.platform" />
								发布区发布过任务，
								<font color="red"><a
									href="taskManager/task!initReleaseTask.php?platformType=1">点此进入发布任务</a>
								</font>！
							</DIV>
						</s:if>
						<s:else>
							<DIV
								style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
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
							</DIV>
						</s:else>
					</div>
				</DIV>
			</div>
		</s:form>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>