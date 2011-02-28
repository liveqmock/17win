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
		<script src="js/x_alt.js" type="text/javascript"></script>
		<s:if test="creditTaskVO.refreshSec!=null">
			<script type="text/javascript">
			setTimeout("refreshPage()",<s:property value='creditTaskVO.refreshSec'/>*1000);  
		</script>
		</s:if>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<s:form action="taskManager/task!initTask.php" theme="simple"
			id="mainForm" onsubmit="return validateForm()">
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
								<A href="javascript:allQuery()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">全部任务</SPAN>
								</A>
								<A href="javascript:moneyAsc()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">价低排列</SPAN>
								</A>
								<A href="javascript:moneyDesc()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">价高排列</SPAN>
								</A>
								<A href="javascript:money1_40()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">1-40元区</SPAN>
								</A>
								<A href="javascript:money40_100()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">40-100元区</SPAN>
								</A>
								<A href="javascript:money100_200()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">100-200元区</SPAN>
								</A>
								<A href="javascript:money200_500()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">200-500元区</SPAN>
								</A>
								<A href="javascript:money500()"><SPAN
									class="taskAnniu<s:property value="#request.platformType"/>">500元以上区</SPAN>
								</A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								刷新时间
								<input type="text" name="creditTaskVO.refreshSec"
									value="<s:property value="creditTaskVO.refreshSec"/>"
									style="width: 25px" id="autoreFresh" alt="必须大于5秒，空表示不刷新！" />
								<input type="hidden" name="platformType"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								<input type="hidden" id="currTaskId" />
								秒
								<A alt=点击刷新 href="javascript:location.reload(true);"
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
									改价/好评
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
										<s:if test="#task.assignUser!=null &&  #task.assignUser!=''">
											<img src="images/tdTask.gif" alt="特定任务" />
										</s:if>
										<s:else>
											<img src="images/ptTask.jpg" alt="普通任务" />
										</s:else>
										<span alt="任务发布时间"> <s:date name="#task.releaseDate"
												format="yyyy-MM-dd HH:mm:ss" /> </span>
									</td>
									<td align="center" nowrap="nowrap">
										<s:property value="#task.fbUsername" />
										<br>
										<img
											src="images/<s:property value="@net.win.utils.StrategyUtils@getLevelImg(#task.fbUpgradeScore)" />"
											alt="刷客经验积分：<s:property value="#task.fbUpgradeScore" />">
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
									<td align="center" nowrap="nowrap">
										<s:if test="#task.updatePrice">
											需修修改价格
										</s:if>
										<s:else>
											不需修改价格
										</s:else>
										<br>
										<s:property value="#task.grade" />
									</td>
									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==1">
											<font color="red" style="font-weight: bold;">等待接收人</font>
										</s:if>
										<s:elseif test="#task.status==6">
											<font color="green" style="font-weight: bold;">任务已结束</font>
										</s:elseif>
										<s:else>
											<font color="green" style="font-weight: bold;">任务进行中</font>
										</s:else>
									</td>
									<td align="center" nowrap="nowrap">
										<s:if test="#task.status==1">
											<s:if test="#task.fbUsername==#session.userLogin.username">
												<img src="images/disable.gif" alt="不能接自己的任务，特定任务" />
											</s:if>
											<s:else>
												<s:if
													test="(#task.assignUser!='' && #task.assignUser!=null )&& #task.assignUser!=#session.userLogin.username">
													<img src="images/disable.gif" alt="特定任务,你不是指定人" />
												</s:if>
												<s:elseif
													test="(#task.assignUser!='' &&  #task.assignUser!=null) && #task.assignUser==#session.userLogin.username ">
													<a alt="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
														onClick="receiveTask('<s:property value="#task.id"/>')">
														<img src="images/qiang.gif" /> </a>
												</s:elseif>
												<s:else>
													<a alt="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
														onClick="receiveTask('<s:property value="#task.id"/>')">
														<img src="images/qiang.gif" /> </a>
												</s:else>
											</s:else>
										</s:if>
										<s:elseif test="#task.status==6">
											<img src="images/disable.gif" alt="任务已经完成" />
										</s:elseif>
										<s:else>
											<img src="images/disable.gif" alt="任务操作中" />
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
							<s:if test="#request.result.size()==0">
								<tr
									style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
									<TD colspan="7">
										没有记录！
									</TD>
								</tr>
							</s:if>
							<s:else>
								<TR>
									<TD colspan="6">
										<input type="hidden" name="creditTaskVO.moneyFlag"
											value="<s:property value="creditTaskVO.moneyFlag"/>"
											id="moneyFlag">
										<input type="hidden" name="creditTaskVO.nowPage"
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
		<s:include value="../common/footDuan.jsp"></s:include>

		<s:if test="#request.noBuyser!=null">
			<input value="true" id="noBuyerId" type="hidden" />
		</s:if>
		<s:else>
			<input value="false" id="noBuyerId" type="hidden" />
			<div id="buyerDIV" title="选择接手买号" style="display: none">
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
				<hr>
				<ul style="color: red;">
					<li>
						1：选择的小号要和您登录淘宝购买商品的名称要一致，乱购买者，一经发现严惩。
					</li>
					<li>
						2：接手任务后，请按照对方的要求购买商品。
					</li>
					<li>
						3：平台每一步双方必须先进行淘宝严格核实，淘宝操作同步平台操作，淘宝一步平台一步。
					</li>
					<li>
						4：如有疑问加QQ群询问(QQ群：100103766)。
					</li>
				</ul>
			</div>
		</s:else>
	</BODY>
</HTML>