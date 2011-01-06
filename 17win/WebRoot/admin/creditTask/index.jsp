<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<s:include value="/admin/common/header.jsp"></s:include>

		<SCRIPT type="text/javascript" src="creditTask/index.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminTaskManager/adminTask!initCreditTask.php"
			id="queryForm" onsubmit="return validateForm()" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td nowrap="nowrap">
						任务ID:
						<s:textfield name="adminCreditTaskVO.testID">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						平台类型:
						<s:select listKey="key" listValue="value"
							name="adminCreditTaskVO.type" headerKey="" headerValue="--请选择--"
							list="#{'1':'淘宝','2':'拍拍','3':'有啊'}">
						</s:select>
					</td>
					<td nowrap="nowrap">
						状态
						<s:select listKey="key" listValue="value"
							name="adminCreditTaskVO.status" headerKey=""
							headerValue="--请选择--"
							list="#{'-1':'暂停','0':'定时任务','1':'等待接手','2':'等待付款','3':'等待发货','4':'等待接受人好评','5':'等待卖家确认','6':'完成'}">
						</s:select>
					</td>
					<td nowrap="nowrap">
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
					</td>
				</tr>
				<tr>

				</tr>
			</table>
			<br>
			<table width="100%" cellpadding="1" id="myTable" class="tablesorter"
				style="font-size: 12px;" style="table-layout: fixed;">
				<thead>
					<tr>
						<th nowrap="nowrap" style="font-size: 12px;">
							<input type="checkbox" onclick="selectAll(this,'selectTaskName')">
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							任务ID
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							平台
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							状态
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							金额
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发布点
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发布人
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							接受人
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发布时间
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="task">
						<tr>
							<td>
								<input name="taskIDs" type="checkbox"
									value="<s:property value="#task.id" />"
									selectTaskName="selectTaskName" />
							</td>
							<td title="<s:property value="#task.testID" />">
								<s:property value="#task.testID" />
							</td>
							<td
								title="<s:if test="#task.type==1">淘宝</s:if><s:elseif test="#task.type==2">拍拍</s:elseif><s:elseif test="#task.type==3">有啊</s:elseif>">
								<s:if test="#task.type==1">淘宝</s:if>
								<s:elseif test="#task.type==2">拍拍</s:elseif>
								<s:elseif test="#task.type==3">有啊</s:elseif>
							</td>
							<td
								title="<s:if test="#task.status==-1">暂停</s:if><s:elseif test="#task.status==0">定时任务</s:elseif><s:elseif test="#task.status==1">等待接手</s:elseif><s:elseif test="#task.status==2">等待付款</s:elseif><s:elseif test="#task.status==3">等待发货</s:elseif><s:elseif test="#task.status==4">等待接受人确认</s:elseif><s:elseif test="#task.status==5">等待卖家确认</s:elseif><s:elseif test="#task.status==6">完成</s:elseif>">
								<s:if test="#task.status==-1">暂停</s:if>
								<s:elseif test="#task.status==0">定时任务</s:elseif>
								<s:elseif test="#task.status==1">等待接手</s:elseif>
								<s:elseif test="#task.status==2">等待付款</s:elseif>
								<s:elseif test="#task.status==3">等待发货</s:elseif>
								<s:elseif test="#task.status==4">等待接手人确认</s:elseif>
								<s:elseif test="#task.status==5">等待卖家确认</s:elseif>
								<s:elseif test="#task.status==6">完成</s:elseif>
							</td>
							<td
								title="<s:property value="#task.money" />+<s:property value="#task.addtionMoney" />">
								<s:property value="#task.money" />
								+
								<font color="red"> <s:property value="#task.addtionMoney" />
								</font>
							</td>

							<td
								title="<s:property value="#task.releaseDot" />+<s:property value="#task.addtionReleaseDot" />">
								<s:property value="#task.releaseDot" />
								+
								<font color="red"> <s:property
										value="#task.addtionReleaseDot" /> </font>
							</td>
							<td title="<s:property value="#task.releaseUser" />">
								<s:property value="#task.releaseUser" />
							</td>
							<td title="<s:property value="#task.receiveUser" />">
								<s:property value="#task.receiveUser" />
							</td>
							<td
								title="<s:date name="#task.releaseDate" format="yyyy-MM-dd HH:mm:ss" />">
								<s:date name="#task.releaseDate" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td align="center">
								<s:if test="#task.status==3">
									<a
										href="adminTaskManager/adminTask!updateRollbackPay.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为并没付款</a>
									<br>
									<a
										href="adminTaskManager/adminTask!updateDispatch.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为卖家已发货 </a>
								</s:if>
								<s:elseif test="#task.status==4">
									<a
										href="adminTaskManager/adminTask!updatePayTask.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为买家已付款</a>
									<br>
									<a
										href="adminTaskManager/adminTask!updateBuyerEvaluate.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为等待卖家好评</a>
								</s:elseif>
								<s:elseif test="#task.status==5">
									<a
										href="adminTaskManager/adminTask!updateDispatch.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为卖家已发货</a>
									<br>
									<a
										href="adminTaskManager/adminTask!updateSellerEvaluate.php?adminCreditTaskVO.id=<s:property value="#task.id" />"
										target="_self">切换为完成任务</a>
								</s:elseif>
								<s:else>
								N/A
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="10" align="center">
							没有数据！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="10" style="font-size: 12px;">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp; 当前任务数:
									<s:property value="adminCreditTaskVO.dataCount" />
									&nbsp;
									<s:property value="adminCreditTaskVO.nowPage" />
									/
									<s:property value="adminCreditTaskVO.pageCount" />

									&nbsp; 每页显示：
									<s:textfield name="adminCreditTaskVO.eachPage"
										cssStyle="width:40px"></s:textfield>
									&nbsp;

								</div>
								<div style="float: left;">
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="adminCreditTaskVO.pageCount"
											step="1" var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="adminCreditTaskVO.nowPage"
									value="<s:property
											value="adminCreditTaskVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="adminCreditTaskVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
	</body>
</html>
