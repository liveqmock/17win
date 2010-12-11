<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="sms/addSms.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminNewsManager/adminNews!addNews.php" theme="simple"
			target="mainFrame">
			<table cellpadding="1" cellspacing="1" border="0px">
				<tr>
					<td nowrap="nowrap">
						对方ID：
					</td>
					<td>
						<s:textfield maxlength="12" name="smsVO.toUserName"
							id="toUsername">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						标题
					</td>
					<td>
						<s:textfield maxlength="50" name="smsVO.title" id="title">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" valign="top">
						内容：
					</td>
					<td>
						<s:textarea name="smsVO.content" id="content" cols="50" rows="8">
						</s:textarea>
						<br>
						<font id="showTip" color="red">0/200</font>
						暂时不要的功能
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="2" align="center">
						<input type="submit" value="提交">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
