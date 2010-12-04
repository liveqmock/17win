<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<s:include value="/admin/common/header.jsp"></s:include>

		<SCRIPT type="text/javascript" src="logistics/index.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminLogisticsManager/adminLogistics!queryLogisticsLog.php"
			onsubmit="return validateForm()" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td>
						发货日期：
						<s:textfield name="logisticsVO.startDate" id="startDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="logisticsVO.endDate" id="endDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td>
						物流单号：
						<s:textfield name="logisticsVO.waybill"></s:textfield>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						使用次数：
						<s:textfield name="logisticsVO.startUseCount" id="startUseCount"
							cssStyle="width:80px">
						</s:textfield>
						至
						<s:textfield name="logisticsVO.endUseCount" id="endUseCount"
							cssStyle="width:80px">
						</s:textfield>
					</td>
					<td>
						提&nbsp;&nbsp;交&nbsp;&nbsp;人：
						<s:textfield name="logisticsVO.releaseUsername"></s:textfield>
					</td>
					<td>
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
					</td>
				</tr>
			</table>
			<br>
			<table id="myTable" class="tablesorter" cellpadding="1">
				<thead>
					<tr>
						<th style="font-size: 12px" nowrap="nowrap">
							提交人
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							状态
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							物流公司
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							物流单号
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							发货时间
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							预计收货时间
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							发货信息
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							收货信息
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							使用次数
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							备注
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="logistics">
						<tr <s:if test="#status.odd"> style="background: #FFC278"</s:if>>
							<td>
								<s:property value="#logistics.releaseUsername" />
							</td>
							<td>
								<s:if test="#logistics.status==1">
									正常
								</s:if>
								<s:else test="#logistics.status==2">
									冻结
								</s:else>
							</td>
							<td>
								<s:property value="#logistics.expressCompany" />
							</td>
							<td>
								<s:property value="#logistics.waybill" />
							</td>
							<td>
								<s:date name="#logistics.sendDate" format="yyyy-MM-dd" />
							</td>
							<td>
								<s:date name="#logistics.arrivalDate" format="yyyy-MM-dd" />
							</td>
							<td>
								<s:property value="#logistics.releaseInfo" />
							</td>
							<td>
								<s:property value="#logistics.receieveInfo" />
							</td>
							<td>
								<s:property value="#logistics.useCount" />
							</td>
							<td>
								<s:property value="#logistics.remark" />
							</td>
							<td nowrap="nowrap">
								撤销
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="10" align="center">
							没有记录！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="10">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp;
								</div>
								<div style="float: left;">
									<s:property value="logisticsVO.nowPage" />
									/
									<s:property value="logisticsVO.pageCount" />
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="logisticsVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="logisticsVO.nowPage"
									value="<s:property
											value="logisticsVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="logisticsVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
	</body>
</html>
