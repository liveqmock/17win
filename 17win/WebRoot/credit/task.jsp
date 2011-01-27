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
								<A href="javascript:allQuery()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">全部任务</SPAN>
								</A>
								<A href="javascript:moneyAsc()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">价低排列</SPAN>
								</A>
								<A href="javascript:moneyDesc()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">价高排列</SPAN>
								</A>
								<A href="javascript:money1_40()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">1-40元区</SPAN>
								</A>
								<A href="javascript:money40_100()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">40-100元区</SPAN>
								</A>
								<A href="javascript:money100_200()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">100-200元区</SPAN>
								</A>
								<A href="javascript:money200_500()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">200-500元区</SPAN>
								</A>
								<A href="javascript:money500()"><SPAN class="taskAnniu<s:property value="#request.platformType"/>">500元以上区</SPAN>
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
									class="yell_font"> <SPAN class="taskAnniu<s:property value="#request.platformType"/>">刷新页面</SPAN> </A>
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
									任务价格
								</td>
								<td nowrap="nowrap" align="center">
									打分/好评
								</td>
								<td nowrap="nowrap" align="center">
									任务状态
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
										<img src="images/swType.jpg" title="实物类型" />
										<s:property value="#task[0]" />
										<br>
										<s:if test="#task[17]!=null">
											<img src="images/tdTask.gif" title="特定任务" />
										</s:if>
										<s:else>
											<img src="images/ptTask.jpg" title="普通任务" />
										</s:else>
										<s:date name="#task[1]" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td align="center">
										<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
											href="javascript:reply('<s:property value='#task[2]' />');"
											title="发送站内信息"><s:property value="#task[2]" /> </a> </SPAN>
										<br>
										<img
											src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task[3])" />"
											alt="刷客经验积分：<s:property value="#task[3]" />">
									</td>
									<td align="center">
										<s:property value="#task[4]" />
										+
										<s:property value="#task[15]" />
										<br>
										<s:property value="#task[7]" />
										+
										<s:property value="#task[16]" />
									</td>
									<td align="center">
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
									<td align="center">
										<s:if test="#task[8]==1">
											<font color="red" style="font-weight: bold;">等待接收人</font>
										</s:if>
										<s:elseif test="#task[8]==6">
											<font color="green" style="font-weight: bold;">任务已结束</font>
										</s:elseif>
										<s:else>
											<font color="green" style="font-weight: bold;">任务进行中</font>
										</s:else>
									</td>
									<td align="center">
										<s:if test="#task[8]==1">
											<s:if test="#task[2]==#session.userLogin.username">
												<img src="images/disable.gif" title="不能接自己的任务，特定任务" />
											</s:if>
											<s:else>
												<s:if
													test="#task[17]!=null && #task[17]!=#session.userLogin.username">
													<img src="images/disable.gif" title="特定任务,你不是指定人" />
												</s:if>
												<s:elseif
													test="#task[17]!=null && #task[17]==#session.userLogin.username ">
													<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
														onClick="receiveTask('<s:property value="#task[12]"/>')">
														<img src="images/qiang.gif" /> </a>
												</s:elseif>
												<s:else>
													<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
														onClick="receiveTask('<s:property value="#task[12]"/>')">
														<img src="images/qiang.gif" /> </a>
												</s:else>
											</s:else>
										</s:if>
										<s:elseif test="#task[8]==6">
											<img src="images/disable.gif" title="任务已经完成" />
										</s:elseif>
										<s:else>
											<img src="images/disable.gif" title="任务操作中" />
										</s:else>
									</TD>
								</tr>
								<!-- 分隔 -->
								<tr>
									<td colspan="7">
										<hr width="98%"
											style="height: 1px; border: none; border-top: 1px dashed #0066CC;">
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<TR>
								<TD colspan="6">
									<input type="hidden"
										value="<s:property value="#request.queryType"/>"
										id="queryType">
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
						</tfoot>
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