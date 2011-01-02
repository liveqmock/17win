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
		<s:form action="adminNewsManager/adminNews!updateNews.php"
			theme="simple" target="mainFrame">
			<table cellpadding="1" cellspacing="1" border="0px">
				<tr>
					<td nowrap="nowrap">
						类型：
					</td>
					<td>
						<s:select name="newsVO.typeId" list="#request.newsTpyes" id="typeID"
							listKey="id" listValue="name">
						</s:select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						标题
					</td>
					<td>
						<s:textfield name="newsVO.title"></s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						顺序
					</td>
					<td>
						<s:textfield name="newsVO.sort"  id="sort"></s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" valign="top">
						内容：
					</td>
					<td>
						<s:textarea name="newsVO.content"></s:textarea>
						<s:hidden name="newsVO.id" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="2" align="center">
						<input type="submit" value="提交">
						<input type="reset" value="重置">
						<input type="button"
							onclick="javascript:window.location.href ='adminNewsManager/adminNews!queryNews.php'"
							value="返回">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
