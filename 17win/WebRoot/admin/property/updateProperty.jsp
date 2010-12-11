<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="news/updateNews.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminPropertyManager/adminProperty!updateProperty.php"
			theme="simple" target="mainFrame">
			<table cellpadding="1" cellspacing="1" border="0px">
				<tr>
					<td nowrap="nowrap">
						属性名：
					</td>
					<td>
						<s:textfield name="propertyVO.name" readonly="true"></s:textfield>
						<s:hidden name="propertyVO.id"></s:hidden>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						数值值：
					</td>
					<td>
						<s:textfield name="propertyVO.numberValue"></s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						字符值：
					</td>
					<td>
						<s:textfield name="propertyVO.stringValue"></s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" valign="top">
						描述：
					</td>
					<td>
						<s:textfield  cssStyle="width:400px" name="propertyVO.desc"></s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="2" align="center">
						<input type="submit" value="提交">
						<input type="button"
							onclick="javascript:window.location.href ='adminPropertyManager/adminProperty!queryProperty.php'"
							value="返回">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
