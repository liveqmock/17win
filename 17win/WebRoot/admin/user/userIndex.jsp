<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="admin/user/userIndex.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminUserManager/adminUser!queryPay.php"
			onsubmit="return validateForm()" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td>
						用户名：
						<s:textfield name="adminUserVO.username">
						</s:textfield>
					</td>
					<td>
						发布点：
						<s:textfield name="adminUserVO.startReleaseDot" id="startDate"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReleaseDot" id="endDate"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						余额：
						<s:textfield name="adminUserVO.startMoney" id="startDate"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endMoney" id="endDate"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						注册日期：
						<s:textfield name="adminUserVO.regeditStartDate" id="startDate"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							readonly="true" cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.regeditEndDate" id="endDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						电子邮件：
						<s:textfield name="adminUserVO.email">
						</s:textfield>
					</td>
					<td>
						手机：
						<s:textfield name="adminUserVO.telphone" maxlength="11">
						</s:textfield>
					</td>
					<td>
						推广积分：
						<s:textfield name="adminUserVO.startSpreadScore" id="startDate"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endSpreadScore" id="endDate"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						发任务数：
						<s:textfield name="adminUserVO.startReleaseTaskCount">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReleaseTaskCount">
						</s:textfield>
					</td>

				</tr>
				<tr>
					<td>
						接任务数：
						<s:textfield name="adminUserVO.startReceieveTaskCount">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReceieveTaskCount;">
						</s:textfield>
					</td>
					<td>
						VIP成长值：
						<s:textfield name="adminUserVO.startVipGrowValue;" id="startDate"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endVipGrowValue" id="endDate"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						是否VIP：
						<s:select listKey="key" listValue="value"
							name="adminUserVO.vipEnable" headerKey="" headerValue="--请选择--"
							list="#{'true':'是','false':'不是'}">
						</s:select>
					</td>
					<td>
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
					</td>
				</tr>
				<tr>

				</tr>
			</table>
			<br>
			<table width="100%" cellpadding="1" id="myTable" class="tablesorter"
				style="table-layout: fixed;">
				<thead>
					<tr>
						<th nowrap="nowrap" style="font-size: 12px;">
							用户名
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发布点
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							余额
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							注册时间
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							电子邮件
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							手机
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							推广积分
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发任务数
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							接任务数
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							是否VIP
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							VIP成长值
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="user">
						<tr>
							<td>
								<s:property value="#user[0]" />
							</td>
							<td>
								<s:property value="#user[1]" />
							</td>
							<td>
								<s:property value="#user[2]" />
							</td>
							<td>
								<s:date name="#user[3]" format="yyyy-MM-dd HH-mm-ss" />
							</td>
							<td>
								<s:property value="#user[4]" />
							</td>
							<td>
								<s:property value="#user[5]" />
							</td>
							<td>
								<s:property value="#user[6]" />
							</td>
							<td>
								<s:property value="#user[7]" />
							</td>
							<td>
								<s:property value="#user[8]" />
							</td>
							<td>
								<s:property value="#user[9]" />
							</td>
							<td>
								<s:property value="#user[10]" />
							</td>
							<td>
								操作
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="6" align="center">
							没有用户！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="6">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp;
								</div>
								<div style="float: left;">
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="adminUserVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="adminUserVO.nowPage"
									value="<s:property
											value="adminUserVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="adminUserVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
	</body>
</html>
