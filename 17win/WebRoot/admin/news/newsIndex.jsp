<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="paid/newsIndex.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminNewsManager/adminNews!queryNews.php"
			theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td nowrap="nowrap">
						标题：
						<s:textfield name="newsVO.title">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						添加日期：
						<s:textfield name="newsVO.startDate"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							readonly="true" cssStyle="width:80px">
						</s:textfield>
						至
						<s:textfield name="newsVO.endDate" readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:80px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
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
							标题
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							URL
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							时间
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="newsVO">
						<tr>
							<td>
								<s:property value="#newsVO.title" />
							</td>
							<td>
								<s:property value="#newsVO.url" />
							</td>
							<td>
								<s:date name="#newsVO.date" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<a
									href="javascript:addMoney('<s:property value="#newsVO.id" />')">浏览</a>
								<a
									href="javascript:deleteMoney('<s:property value="#newsVO.id" />')">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="4" align="center">
							没有记录！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="5" style="font-size: 12px;">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp;
								</div>
								<div style="float: left;">
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="newsVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="newsVO.nowPage"
									value="<s:property
											value="newsVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="newsVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
	</body>
</html>
