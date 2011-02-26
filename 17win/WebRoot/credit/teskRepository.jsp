<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
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
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/taskRepository.js" type="text/javascript"></SCRIPT>
		<script src="js/x_alt.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js">
		</script>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<s:form
			action="taskRepositoryManager/taskRepository!queryRepositories.php"
			theme="simple" id="mainForm">
			<div align="center" id="partdiv">
				<div align="center">
					<DIV
						style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
						<!-- xgj navigation.jsp -->
						<s:include value="../common/task/navigation.jsp"></s:include>
					</DIV>
					<table cellspacing="1" class="taskTable"
						style="table-layout: fixed">
						<thead>
							<tr>
								<td nowrap="nowrap" align="center">
									名称
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
								</td>
								<td nowrap="nowrap" align="center">
									商品价格
								</td>
								<td nowrap="nowrap" align="center">
									商品信息
								</td>
								<td nowrap="nowrap" align="center">
									好评信息
								</td>
								<td nowrap="nowrap" align="center">
									改价/地址
								</td>
								<td nowrap="nowrap" align="center">
									发布次数
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
									<td valign="middle" align="center" nowrap="nowrap"
										style="overflow: hidden; text-overflow: ellipsis;">
										<s:if test="#task.taskType==1">
											<img src="images/xnType.jpg" alt="虚拟任务" />
										</s:if>
										<s:elseif test="#task.taskType==2">
											<img src="images/swType.jpg" alt="实物任务" />
										</s:elseif>
										<s:elseif test="#task.taskType==3">
											<img src="images/tcType.jpg" alt="套餐任务" />
										</s:elseif>
										<span alt="名称：<s:property value="#task.name" />"> <s:property
												value="#task.name" /> </span>
										<br>
										<s:if test="#task.assignUser!=null && #task.assignUser!=''">
											<img src="images/tdTask.gif"
												alt="特定任务，接手人是<s:property value="#task.assignUser"/>" />
										</s:if>
										<s:else>
											<img src="images/ptTask.jpg" alt="普通任务" />
										</s:else>
										是否任务保护：
										<s:if test="#task.protect">是</s:if>
										<s:else>否</s:else>

									</td>
									<td valign="middle" align="center" nowrap="nowrap">
										<s:property value="#task.money" />
										+
										<s:property value="#task.addtionMoney" />
										<br>
										<s:property value="#task.releaseDot" />
										+
										<s:property value="#task.addtionReleaseDot" />
									</td>
									<td valign="middle" align="center" nowrap="nowrap">
										<a style="cursor: pointer;"
											href="javascript:showItemUrl('<s:property value="#task.itemUrl" />');">
											<img src="images/renwu-3.png" border="0"> </a>
										<br>
										<a alt="点此直接打开商品地址"
											href="javascript:openItemUrl('<s:property value="#task.itemUrl" />');">
											<img src="images/open.gif" border="0"> </a>
									</td>
									<td valign="middle" align="center" nowrap="nowrap"
										style="overflow: hidden; text-overflow: ellipsis;">
										<s:property value="#task.grade" />
										<br>
										<s:if test="#task.comment==null || #task.comment==''">
										评语:无
										</s:if>
										<s:else>
										评语:
											<s:if test="#task.comment=='-1'">
												接手人自己想
											</s:if>
											<s:else>
												<span alt="<s:property value="#task.comment" />"> <s:property
														value="#task.comment" /> </span>
											</s:else>
										</s:else>
									</td>
									<td valign="middle" align="center" nowrap="nowrap">
										<s:if test="#task.updatePrice">
											需修改价
										</s:if>
										<s:else>
											不需改价
										</s:else>
										<Br>
										是否自动生成地址：
										<s:if test="#task.address">
											是
										</s:if>
										<s:else>
											否
										</s:else>
									</td>

									<td valign="middle" align="center" nowrap="nowrap">
										<s:property value="#task.dispathCount" default="0" />
										<br>
										<s:if test="#task.lastDispathDate!=null">
											<s:date name="#task.lastDispathDate"
												format="yyyy-MM-dd HH-mm-ss" />
										</s:if>
										<s:else>
									没发送过
									</s:else>
									</td>
									<td valign="middle" align="center" nowrap="nowrap">
										<a alt="发布任务！"
											href="javascript:releaseRepository(<s:property value="#task.id"/>)"><span
											class="anniu">发布任务</span> </a>
										<br>
										<a alt="删除任务！"
											href="javascript:deleteRepository(<s:property value="#task.id"/>)"><span
											class="anniu2">删除任务</span> </a>
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
							<s:if test="#request.result==null">
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
													value="creditTaskRepositoryVO.dataCount" /> </b> </font>
										条主题&nbsp;&nbsp;&nbsp;
										<a href="javascript:firstPage()">首页</a>
										<a href="javascript:prevPage()">上一页</a>&nbsp;
										<a href="javascript:nextPage()">下一页</a>&nbsp;
										<a href="javascript:lastPage()">尾页</a>&nbsp;页次：
										<strong><font color="red"><s:property
													value="creditTaskRepositoryVO.nowPage" /> </font>/<s:property
												value="creditTaskRepositoryVO.pageCount" /> </strong>页 &nbsp;
										<b><s:property value="creditTaskRepositoryVO.eachPage" />
										</b>条主题/页&nbsp;转到：
										<select id='toPageSelect' size='1' onchange="jumpPage()">
											<s:iterator begin="1" end="creditTaskRepositoryVO.pageCount"
												step="1" var="index">
												<option value="<s:property value="#index" />"
													<s:if test="creditTaskRepositoryVO.nowPage==#index">
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

		<form
			action="taskManager/task!initReleaseTask.php?platformType=<s:property value="#request.platformType"/>"
			id="taskForm" method="post">
			<input type="hidden" name="taskRepId" id="taskRepCurrId" />
		</form>
		<!-- 显示地址 -->
		<div id="addressDIV" title="商品地址" style="display: none">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tbody id="itemContent">
				</tbody>
			</table>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>