<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="news/newsType.js"></SCRIPT>
	</head>

	<body>
		<table width="100%" cellpadding="1" cellspacing="1" border="0px"
			style="background: #DDEDFA">
			<tr>
				<td nowrap="nowrap">
					<input type="button" onclick="addNewsType()" value="添加">
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" cellpadding="1" id="myTable" class="tablesorter"
			style="font-size: 12px;" style="table-layout: fixed;">
			<thead>
				<tr>
					<th nowrap="nowrap" style="font-size: 12px;">
						名字
					</th>
					<th nowrap="nowrap" style="font-size: 12px;">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.result" id="newsType">
					<tr>
						<td>
							<s:property value="#newsType.name" />
						</td>
						<td>
							<a
								href="javascript:updateNewsType('<s:property value="#newsType.id" />','<s:property value="#newsType.name" />')">修改</a>
							<a
								href="javascript:deleteNewsType('<s:property value="#newsType.id" />')">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<s:if test="#request.result.size()==0">
				<tr>
					<th colspan="2" align="center">
						没有类别
					</th>
				</tr>
			</s:if>
		</table>
		<div id="updateDIV" title="新增">
			<s:form action="adminUserManager/adminUser!addMoney.php"
				id="moneyForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle">
							名字：
						</td>
						<td>
							<input type="text" name="newsTypeVO.name" id="newsName"
								style="width: 80px">
							<input type="hidden" name="newsTypeVO.id" id="currID">
						</td>

					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
