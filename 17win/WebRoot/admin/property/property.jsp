<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="property/property.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminPropertyManager/adminProperty!queryProperty.php"
			theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td nowrap="nowrap">
						属性名：
						<s:textfield name="propertyVO.name">
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
							属性名
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							数值值
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							字符值
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
					<s:iterator value="#request.result" id="propertyVO">
						<tr>
							<td>
								<s:property value="#propertyVO.name" />
							</td>
							<td>
								<s:property value="#propertyVO.numberValue" />
							</td>
							<td>
								<s:property value="#propertyVO.stringValue" />
							</td>
							<td>
								<s:property value="#propertyVO.desc" />
							</td>
							<td>
								<a
									href="javascript:updateNews('<s:property value="#propertyVO.id" />')">修改</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="5" align="center">
							没有记录！
						</th>
					</tr>
				</s:if>
			</table>
		</s:form>
	</body>
</html>
