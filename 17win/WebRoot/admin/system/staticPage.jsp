<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<table cellpadding="1" cellspacing="1" border="0px">
			<tr>
				<td nowrap="nowrap">
					<input type="button"
						onclick="window.location.href='adminSystemManager/adminSystem!staticNewsPage.php'"
						value="生成新闻">
					<input type="button" value="生成首页">
				</td>
			</tr>
		</table>
	</body>
</html>
