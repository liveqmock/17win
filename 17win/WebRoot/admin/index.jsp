<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="index.js"></script>
	</head>
	<body>
		<s:form theme="simple" action="/adminManager/admin!login.php">
			<table align="center">
				<Tr>
					<td>
						登录名:
					</td>
					<td>
						<input name="username" id="usernameID" tabindex="1">
					</td>
				</Tr>
				<Tr>
					<td>
						登录名:
					</td>
					<td>
						<input type="password" name="password">
					</td>
				</Tr>
				<Tr>
					<td>
						验证码:
					</td>
					<td>
						<input type="text" name="code" style="width: 60px" maxlength="4" />
						<img src="verify/verificationCode.php"
							onclick="changeValidateCode(this)" title="点击图片刷新验证码"
							style="cursor: pointer;">
					</td>
				</Tr>
				<Tr>
					<td colspan="2">
						<input type="submit" value="提交">
					</td>
				</Tr>
			</table>
		</s:form>
		<s:property value="#request.msg" escape="false" />
	</body>
</html>
