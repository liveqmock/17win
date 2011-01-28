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
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/taskRepository.js" type="text/javascript"></SCRIPT>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<div align="center" id="partdiv">
			<div align="center">
				<DIV
					style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
					<!-- xgj navigation.jsp -->
					<s:include value="../common/task/navigation.jsp"></s:include>
				</DIV>
				<table cellspacing="1" class="taskTable">
					<thead>
						<tr>
							<td nowrap="nowrap" align="center">
								商品价格
								<input type="hidden"
									value="<s:property value="#request.platformType"/>"
									id="platformType" />
								<input type="hidden"
									value="<s:property
											value="creditTaskRepositoryVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="creditTaskRepositoryVO.pageCount" />"
									id="pageCount">
							</td>
							<td nowrap="nowrap" align="center">
								商品信息
							</td>
							<td nowrap="nowrap" align="center">
								备注
							</td>
							<td nowrap="nowrap" align="center">
								上次发布时间
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
							<tr style="BORDER-BOTTOM: #06314a 1px dashed;">
								<td valign="top" align="center">
									<font color="red"><s:property value="#task.money" /> </font>
									<font color="blue">+<s:property
											value="#task.addtionMoney" /> </font>元
									<s:if test="#test.updatePrice">(需改价格)</s:if>
									<s:else>(全额相等)</s:else>
									<br>
									<font color="red"><s:property value="#task.releaseDot" />
									</font>
									<font color="blue">+<s:property
											value="#task.addtionReleaseDot" /> </font> 个发布点
								</td>
								<td valign="top" align="center">
									<input type="text" readonly="readonly"
										title="<s:property value="#task.itemUrl" />"
										style="width: 60px"
										value="<s:property value="#task.itemUrl" />" />
									<br>
									<input type="button" value="GO" style="cursor: pointer;"
										class="goItemButton">
									<br>
									<font color="#FF0000">掌柜:<s:property
											value="#task.sellerName" /> </font>
								</td>
								<td valign="top" align="center">
									<s:if test="#task.protect">
									 	需要审核
									</s:if>
									<s:else>
									不需审核
								</s:else>
									<s:if test="#task.address">
									系统提供地址
									 </s:if>
								</td>
								<td valign="top" align="center">
									<s:if test="#task.lastDispathDate!=null">
										<s:date name="#task.lastDispathDate"
											format="yyyy-MM-dd HH-mm-ss" />
									</s:if>
									<s:else>
									还没发送过！
								</s:else>
								</td>

								<td valign="top" align="center">
									<s:property value="#task.dispathCount" default="0" />
								</td>
								<td valign="top" align="center">
									<a title="可能由于你填写错误，可以重新进行填写！"
										href="javascript:releaseRepository(<s:property value="#task.id"/>)"><span
										class="anniu">发布任务</span> </a>
									<br>
									<a title="刷新排前可以使您的任务在发布区靠前！"
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
					</tfoot>
				</table>
			</div>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>