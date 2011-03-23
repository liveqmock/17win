<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<s:include value="/admin/common/header.jsp"></s:include>
		<SCRIPT type="text/javascript" src="capitalLog/index.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminCapitalLogManager/adminCapitalLog!queryLogs.php"
			onsubmit="return validateForm()" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td>
						用户名：
						<s:textfield name="capitalLogVO.username"></s:textfield>
					</td>
					<td>
						记录类型：
						<s:select listKey="key" listValue="value" name="capitalLogVO.type"
							headerKey="" headerValue="--请选择--"
							list="#{'1':'金额记录','3':'积分记录'}">
						</s:select>
					</td>
					<td>
						数值范围：
						<s:textfield name="capitalLogVO.startValue" id="startValue"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="capitalLogVO.endValue" id="endValue"
							cssStyle="width:40px">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						记录日期：
						<s:textfield name="capitalLogVO.startDate" id="startDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd HH:mm:ss','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
						至
						<s:textfield name="capitalLogVO.endDate" id="endDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd HH:mm:ss','skin':'blue'})"
							cssStyle="width:110px">
						</s:textfield>
					</td>
					<td colspan="2">
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
					</td>
				</tr>
			</table>
			<br>
			<table id="myTable" style="font-size: 12px;" class="tablesorter"
				cellpadding="1">
				<thead>
					<tr>
						<th style="font-size: 12px" nowrap="nowrap">
							用户名
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							资产类型
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							流动资产
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							剩余资产
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							操作日期
						</th>
						<th style="font-size: 12px" nowrap="nowrap">
							描述
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="capitalLog">
						<tr <s:if test="#status.odd"> style="background: #FFC278"</s:if>>
							<td>
								<s:property value="#capitalLog.username" />
							</td>
							<td>
								<s:if test="#capitalLog.type==1">
																  金额
																</s:if>
								<s:elseif test="#capitalLog.type==3">
																	积分
																</s:elseif>
							</td>
							<td>
								<s:property value="#capitalLog.value" />
							</td>
							<td>
								<s:property value="#capitalLog.remainValue" />
							</td>
							<td>
								<s:date name="#capitalLog.logTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<s:property value="#capitalLog.desc" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="6" align="center">
							没有数据！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="6" style="font-size: 12px">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp; 当前记录数:
									<s:property value="capitalLogVO.dataCount" />
									&nbsp;
									<s:property value="capitalLogVO.nowPage" />
									/
									<s:property value="capitalLogVO.pageCount" />

									&nbsp; 每页显示：
									<s:textfield name="capitalLogVO.eachPage" cssStyle="width:40px"></s:textfield>
									&nbsp;
								</div>
								<div style="float: left;">
									<s:property value="capitalLogVO.nowPage" />
									/
									<s:property value="capitalLogVO.pageCount" />
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="capitalLogVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="capitalLogVO.nowPage"
									value="<s:property
											value="capitalLogVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="capitalLogVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
	</body>
</html>
