<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<s:include value="/admin/common/header.jsp"></s:include>
		<SCRIPT type="text/javascript" src="withdrawals/withdrawalsIndex.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminWithdrawalsManager/adminWithdrawals!queryLog.php"
			onsubmit="return validateForm();" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td>
						用户名：
						<s:textfield name="withdrawalsVO.username" id="username"></s:textfield>
					</td>
					<td>
						提现金额：
						<s:textfield name="withdrawalsVO.startMoney" id="startMoney"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="withdrawalsVO.endMoney" id="endMoney"
							cssStyle="width:40px">
						</s:textfield>
					</td>
					<td>
						提现类型：
						<s:select id="withdrawalsType" listKey="key" listValue="value"
							name="withdrawalsVO.type" headerKey="" headerValue="--请选择--"
							list="#{'1':'店铺地址提现','2':'支付宝提现','3':'财付通提现'}">
						</s:select>

						<span
							<s:if test="withdrawalsVO.type!=1">style="display: none"</s:if>
							id="shopType"> &nbsp;&nbsp;店铺类型：<s:select
								name="withdrawalsVO.shopType" listKey="key" listValue="value"
								headerKey="" headerValue="--请选择--"
								list="#{'1':'淘宝','2':'拍拍','3':'有啊'}">
							</s:select> </span>
					</td>
				</tr>
				<tr>
					<td>
						操作日期：
						<s:textfield name="withdrawalsVO.startDate" id="startDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="withdrawalsVO.endDate" id="endDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
						<s:select name="withdrawalsVO.status" listKey="key"
							listValue="value" headerKey="" headerValue="--请选择--"
							list="#{'1':'申请中','4':'被驳回','3':'已完成'}">
						</s:select>
					</td>
					<td>
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
					</td>
				</tr>
			</table>
			<br>
			<table width="100%" cellpadding="1" id="myTable" class="tablesorter"
				style="font-size: 12px;" style="table-layout: fixed;">
				<thead>
					<tr>
						<th nowrap="nowrap" style="font-size: 12px;">
							用户名
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							提现类型
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							平台类型
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							提现链接
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							提现金额
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作日期
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							状态
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							描述
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="withdrawals">
						<tr <s:if test="#status.odd"> style="background: #FFC278"</s:if>>
							<td>
								<s:property value="#withdrawals.username" />
							</td>
							<td>
								<s:if test="#withdrawals.type==1">
																	店铺地址提现
																</s:if>
								<s:elseif test="#withdrawals.type==2">
																	支付宝提现
																</s:elseif>
								<s:else>
																	财付通提现
																</s:else>
							</td>
							<td>
								<s:if test="#withdrawals.type==1">
									<s:if test="#withdrawals.shopType==1">
																		淘宝
																	</s:if>
									<s:elseif test="#withdrawals.shopType==2">
																		拍拍
																	</s:elseif>
									<s:elseif test="#withdrawals.shopType==3">
																		有啊
																	</s:elseif>
								</s:if>
								<s:else>
																	-
																</s:else>
							</td>
							<td>
								<s:property value="#withdrawals.realIdentity" />
							</td>
							<td>
								<s:property value="#withdrawals.money" />
							</td>
							<td>
								<s:date name="#withdrawals.operationDate"
									format="yyyy-MM-dd HH-mm-ss" />
							</td>
							<td>
								<s:if test="#withdrawals.status==1">
																		申请中
																	</s:if>
								<s:elseif test="#withdrawals.status==2">
																		被驳回
																	</s:elseif>
								<s:else>
																		已完成
																	</s:else>
							</td>
							<td>
								<s:property value="#withdrawals.statusDesc" />
							</td>
							<td nowrap="nowrap">
								<s:if test="#withdrawals.status==1">
									<a
										href="javascript:updateLog('<s:property value="#withdrawals.id" />','reject');">驳回</a>
									<a
										href="adminWithdrawalsManager/adminWithdrawals!updateLog.php?flag=over&withdrawalsVO.id=<s:property value="#withdrawals.id" />">完成</a>
								</s:if>
								<s:else>
									N/A
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="9" align="center">
							没有充值记录！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="9" style="font-size: 12px;">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp;
								</div>
								<div style="float: left;">
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="withdrawalsVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="withdrawalsVO.nowPage"
									value="<s:property
											value="withdrawalsVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="withdrawalsVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>

		<div id="updateTableDIV" title="修改状态">
			<s:form onsubmit="return validateUpdateForm();" id="updateForm"
				action="adminWithdrawalsManager/adminWithdrawals!updateLog.php">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle">
							<font color="red">*</font>状态描述：
						</td>
						<td valign="middle">
							<input name="flag" id="flagID" type="hidden">
							<input name="withdrawalsVO.id" id="withdrawalsVOID" type="hidden">
							<input type="text" id="statusDesc"
								name="withdrawalsVO.statusDesc">
						</td>
					</tr>
					<tr>
						<td valign="middle" colspan="2">
							<input type="submit" value="修改	">
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
