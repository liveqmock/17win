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
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/task.js" type="text/javascript"></SCRIPT>
		<script type="text/javascript" src="js/utils.js">
		</script>
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
								<A href="javascript:allQuery()"><SPAN class=anniu>全部任务</SPAN>
								</A>
								<A href="javascript:moneyAsc()"><SPAN class=anniu>价低排列</SPAN>
								</A>
								<A href="javascript:moneyDesc()"><SPAN class=anniu>价高排列</SPAN>
								</A>
								<A href="javascript:money1_40()"><SPAN class=anniu>1-40元区</SPAN>
								</A>
								<A href="javascript:money40_100()"><SPAN class=anniu>40-100元区</SPAN>
								</A>
								<A href="javascript:money100_200()"><SPAN class=anniu>100-200元区</SPAN>
								</A>
								<A href="javascript:money200_500()"><SPAN class=anniu>200-500元区</SPAN>
								</A>
								<A href="javascript:money500()"><SPAN class=anniu>500元以上区</SPAN>
								</A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								刷新时间
								<input type="text"
									value="<s:property value="#request.autoRefresh"/>"
									style="width: 25px" id="autoreFresh" title="必须大于5秒，空表示不刷新！" />
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								<input type="hidden" id="currTaskId" />
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
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								发布人
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								任务价格
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								打分/好评
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								任务状态
							</td>
							<td
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #006600; TEXT-ALIGN: center">
								操&nbsp;&nbsp;作
							</td>
						</tr>
						<s:iterator value="#request.result" status="status" id="task">
							<tr
								<s:if test="#task[14]!=null">  style="background: #FFEAEA" </s:if>>
								<td valign="top" align="center">
									<s:property value="#task[0]" />
									<br>
									<s:date name="#task[1]" format="yyyy-MM-dd HH-mm-ss" />
								</td>
								<td valign="top" align="center">
									<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
										href="javascript:reply('<s:property value='#task[2]' />');"
										title="发送站内信息"><s:property value="#task[2]" /> </a> </SPAN>
									<br>
									<img
										src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[3])" />"
										alt="刷客经验积分：<s:property value="#task[3]" />">
								</td>
								<td valign="top" align="center">
									<font color="red"><s:property value="#task[4]" /> </font><font
										color="blue">+<s:property value="#task[15]" /> </font> 元 (
									<s:if test="#task[5]">
												需改价格
									</s:if>
									<s:else>
												金额相等
									</s:else>
									)
									<br>
									<font color="red"><s:property value="#task[7]" /> </font><font
										color="blue">+<s:property value="#task[16]" /> </font> 个发布点
								</td>
								<td valign="top" align="center">

									<s:if test="#task[13]==1">全部打5分</s:if>
									<s:elseif test="#task[13]==2">全部不打分</s:elseif>
									<s:elseif test="#task[13]==3">带字5分好评</s:elseif>
									<br>
									<s:if test="#task[6]==1">马上好评</s:if>
									<s:elseif test="#task[6]==2">一天后好评</s:elseif>
									<s:elseif test="#task[6]==3">两天后好评</s:elseif>
									<s:elseif test="#task[6]==4">三天后好评</s:elseif>
									<s:elseif test="#task[6]==5">自定义好评(<s:property
											value="#task[9]" />小时候后)</s:elseif>


								</td>
								<td valign="top" align="center">
									<s:if test="#task[8]==1">
										<font color="red" style="font-weight: bold;">等待接收人...</font>
									</s:if>
									<s:elseif test="#task[8]==6">
										<font color="green" style="font-weight: bold;">任务已经结束</font>
									</s:elseif>
									<s:else>
										<font color="green" style="font-weight: bold;">此任务进行中...</font>
									</s:else>
								</td>
								<td valign="top" align="center">
									<s:if test="#task[17]!=null">
											dsfsfs
									</s:if>
									<s:if test="#task[8]==1">
										<s:if test="#task[2]==#session.userLogin.username">
											<font color="red"> 不能接自己的任务</font>
										</s:if>
										<s:else>
											<s:if
												test="#task[17]!=null && #task[17]!=#session.userLogin.username">
												<font color="blue" style="font-weight: bold;">特殊任务,你不是指定人</font>
											</s:if>
											<s:if
												test="#task[17]!=null && #task[17]!=#session.userLogin.username">
												<font color="blue" style="font-weight: bold;">特殊任务,你不是指定人</font>
											</s:if>
											<s:else>
												<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
													onClick="receiveTask('<s:property value="#task[12]"/>')">
													<span class="anniu">接手任务</span> </a>

											</s:else>
										</s:else>
									</s:if>
									<s:elseif test="#task[8]==6">
										<font color="green" style="font-weight: bold;">完成</font>
									</s:elseif>
									<s:else>
										<font color="green" style="font-weight: bold;">操作中...</font>
									</s:else>
								</TD>
							</tr>

							<TR
								<s:if test="#task[14]!=null">  style="background: #FFEAEA" </s:if>>
								<TD align="left" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" style="font-weight: bold;"> 任务描述：<s:property
											value="#task[10]" /> </font>
								</TD>
							</TR>
							<!-- 分隔 -->
							<tr>
								<td colspan="7">
									<hr width="98%"
										style="height: 1px; border: none; border-top: 1px dashed #0066CC;">
								</td>
							</tr>
						</s:iterator>
						<TR>
							<TD colspan="6">
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
								共
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
					</table>
				</div>
			</div>
		</s:form>
		<s:include value="../common/footDuan.jsp"></s:include>

		<s:if test="#request.noBuyser!=null">
			<input value="true" id="noBuyerId" type="hidden" />
		</s:if>
		<s:else>
			<input value="false" id="noBuyerId" type="hidden" />
			<div id="buyerDIV" title="选择接手买号">
				<s:iterator value="#request.resultBuyers" id="buyer" status="status">
					<s:if test="#status.index==0">
						<input type="radio" value="<s:property value="#buyer.id" />"
							checked="checked" name="buyerName" />
					</s:if>
					<s:else>
						<input type="radio" value="<s:property value="#buyer.id" />"
							name="buyerName" />
					</s:else>
					<s:property value="#buyer.name" />
				</s:iterator>
			</div>
		</s:else>
	</BODY>
</HTML>