<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="adminInfo/index.js"></script>
	</head>
	<body>
		<s:form theme="simple"
			action="userManager/base!updateAdminPassword.php"
			onsubmit="return validateform();">
			<table>
				<Tr>
					<td>
						新密码:
					</td>
					<td>
						<input type="password" name="password" id="passwordID">
					</td>
				</Tr>
				<Tr>
					<td>
						确认密码:
					</td>
					<td>
						<input type="password" name="repassword" id="repasswordID">
					</td>
				</Tr>
				<Tr>
					<td>
						验证码:
					</td>
					<td>
						<input type="text" name="verificationCode" style="width: 60px"
							maxlength="4" id="codeID" />
						<img id="verificationCodeID" onclick="changeValidateCode(this)"
							title="点击图片刷新验证码" style="cursor: pointer;">
					</td>
				</Tr>
				<Tr>
					<td colspan="2">
						<input type="submit" value="提交">
					</td>
				</Tr>
			</table>
		</s:form>
	</body>
</html>
