<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/task.js" type="text/javascript"></SCRIPT>
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
								<A href="?sort=2"><SPAN class=anniu>价低排列</SPAN> </A>
								<A href="?sort=1"><SPAN class=anniu>价高排列</SPAN> </A>
								<A href="?sort=3"><SPAN class=anniu>1-40元区</SPAN> </A>
								<A href="?sort=4"><SPAN class=anniu>40-100元区</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>100-200元区</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>200-500元区</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>500元以上区</SPAN> </A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								刷新时间
								<input type="text" value="" style="width: 25px"
									title="不填写或则0表示不自动刷新" />
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								<input type="hidden" id="currTaskId" />
								秒
								<A title=点击刷新 href="javascript:location.reload(true);"
									class="yell_font"> <SPAN class=anniu>刷新页面 任务不断跳出</SPAN> </A>
							</DIV>
						</DIV>
					</DIV>
					<DIV style="MARGIN-TOP: 2px; BACKGROUND: #F0F9FF; WIDTH: 910px">
						<DIV style="CLEAR: both; WIDTH: 910px; BACKGROUND-COLOR: #F0F9FF">
							<DIV style="CLEAR: both; WIDTH: 100%">
								<DIV style="CLEAR: both; WIDTH: 100%; HEIGHT: 45px">
									<DIV class=c_01 style="WIDTH: 15%">
										任务编号
									</DIV>
									<DIV class=c_01 style="WIDTH: 15%">
										发布人
									</DIV>
									<DIV class=c_01 style="WIDTH: 15%">
										任务价格
									</DIV>
									<DIV class=c_01 style="WIDTH: 15%">
										评价方式
									</DIV>
									<DIV class=c_01 style="WIDTH: 20%">
										任务状态
									</DIV>
									<DIV class=c_01 style="CLEAR: right; WIDTH: 20%">
										操&nbsp;&nbsp;作
									</DIV>
								</DIV>
							</DIV>
						</DIV>
					</DIV>

					<DIV style="MARGIN-TOP: 4px; WIDTH: 910px;">
						<s:iterator value="#request.result" status="status" id="task">
							<TABLE
								<s:if test="status.odd"  >
									style="MARGIN: 3px; background: #cde0ee"  
								</s:if>
								<s:else>
									style="MARGIN: 3px; background: #FFECD5"  
								</s:else>
								cellSpacing="2" cellPadding="0" width="100%" border="0">
								<TBODY>
									<TR>
										<TD width="15%" align="left">
											<s:property value="#task[0]" />
										</TD>
										<TD>
										<TD align="center" width="15%" nowrap="nowrap">
											<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
												href="../user/send_message.asp?sendname=shengjun0911"
												title="发送站内信息" target="_blank"><s:property
														value="#task[2]" /> </a> </SPAN>(
											<font color=red>在线</font>）
										</TD>
										<TD align="center" width="15%">
											<s:property value="#task[4]" />
											元
											<img src="images/zf.gif" width="13" height="16" title="平台担保">
										</TD>
										<TD align="center" width="15%">
											<s:if test="#task[6]==1">马上好评</s:if>
											<s:elseif test="#task[6]==2">一天后好评</s:elseif>
											<s:elseif test="#task[6]==3">两天后好评</s:elseif>
											<s:elseif test="#task[6]==4">三天后好评</s:elseif>
											<s:elseif test="#task[6]==5">自定义好评(<s:property
													value="#task[9]" />)</s:elseif>

										</TD>
										<TD width="20%">
											<s:if test="#task[8]==1">
												<font color="red" style="font-weight: bold;">等待接收人...</font>
											</s:if>
											<s:elseif test="#task[8]==6">
												<font color="green" style="font-weight: bold;">任务已经结束</font>
											</s:elseif>
											<s:else>
												<font color="green" style="font-weight: bold;">此任务进行中...</font>
											</s:else>
										</TD>
										<TD style="PADDING-LEFT: 50px" width="20%">
											<s:if test="#task[8]==1">
												<s:if test="#task[2]==#session.userLogin.username">
													<font color="red"> 不能接自己的任务</font>
												</s:if>
												<s:else>
													<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
														onClick="receiveTask('<s:property value="#task[12]"/>')">
														<span class="anniu">接手任务</span> </a>
												</s:else>
											</s:if>
											<s:elseif test="#task[8]==6">
												<font color="green" style="font-weight: bold;">完成</font>
											</s:elseif>
											<s:else>
												<font color="green" style="font-weight: bold;">操作中...</font>
											</s:else>
										</TD>
									</TR>
									<TR>
										<TD align="left">
											<s:date name="#task[1]" format="yyyy-MM-dd hh-mm-ss" />
										</TD>
										<TD>
										<TD align="center" width="15%">

											<img
												src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[3])" />"
												alt="刷客经验积分：<s:property value="#task[3]" />">
										</TD>
										<TD align="center" width="15%">
											<s:if test="#task[5]">
												需改价格
											</s:if>
											<s:else>
												金额相等
											</s:else>
										</TD>
										<TD align="center" width="15%">
											<IMG alt=延迟收货 src=images/shiwu.gif>
											发布点
											<s:property value="#task[7]" />
											个
										</TD>
										<TD style="PADDING-LEFT: 50px" width="20%">
										</TD>
										<TD style="PADDING-LEFT: 50px" width="20%">
										</TD>
									</TR>
									<TR>
										<TD align="left" colspan="6">
											<font color="red" style="font-weight: bold;"> 任务描述：<s:property
													value="#task[10]" /> </font>
										</TD>
									</TR>


									<TR>
										<TD colspan="6" align="left">
											<font color="red" style="font-weight: bold;"> 收货地址：<s:property
													value="#task[11]" /> </font>
										</TD>

									</TR>
								</TBODY>
							</TABLE>
						</s:iterator>
					</DIV>
					<DIV class="tt5">
						共
						<font color=blue><b>100</b> </font> 条主题&nbsp;&nbsp;&nbsp;首页
						上一页&nbsp;
						<a href='index.asp?PageNo=2'>下一页</a>&nbsp;
						<a href='index.asp?PageNo=7'>尾页</a>&nbsp;页次：
						<strong><font color="red">1</font>/7</strong>页 &nbsp;
						<b>15</b>条主题/页&nbsp;转到：
						<select name='page' size='1'
							onchange="javascript:window.location='index.asp?PageNo='+this.options[this.selectedIndex].value;">
							<option value='1'>
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
							<option value='5'>
								第5页
							</option>
							<option value='6'>
								第6页
							</option>
							<option value='7'>
								第7页
							</option>
						</select>
					</div>
				</div>
			</div>
		</s:form>
		<s:include value="../common/footDuan.jsp"></s:include>
		<div id="buyerDIV" title="选择接手小号">
			<s:iterator value="#request.resultBuyers" id="buyer" status="status">
				<s:if test="#status.index==0">
					<input type="radio" value="<s:property value="#buyer.id" />"  
					checked="checked"	name="buyerName" />
				</s:if>
				<s:else>
					<input type="radio" value="<s:property value="#buyer.id" />"
						name="buyerName" />
				</s:else>
				<s:property value="#buyer.name" />
			</s:iterator>
		</div>
	</BODY>
</HTML>