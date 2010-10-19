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
		<table cellpadding="1" cellspacing="1" border="0px" align="center"
			width="90%">
			<tr>
				<td align="center">
					<h3>
						<s:property value="newsVO.title" />
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center" style="border-bottom: 1px dashed #0365BE;color: #0365BE;font-size: 12px">
					发布时间：<s:date name="newsVO.date" format="yyyy-MM-dd" />
				</td>
			</tr>
		 
			<tr>
				<td>
					<s:property value="newsVO.content"  escape="false"/>
				</td>
			</tr>

			<tr>
				<td>
					<input type="button"
						onclick="javascript:window.location.href ='adminNewsManager/adminNews!queryNews.php'"
						value="返回">
				</td>
			</tr>
		</table>
	</body>
</html>
