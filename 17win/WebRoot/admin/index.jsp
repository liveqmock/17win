<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<title>17win Admin登录界面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="index.js"></script>
	</head>
	<body>
		<s:form theme="simple" action="userManager/base!adminLogin.php">
			<table align="center">
				<Tr>
					<td>
						登录名:
					</td>
					<td>
						<input name="userVO.userEntity.username" id="usernameID"
							tabindex="1">
					</td>
				</Tr>
				<Tr>
					<td>
						密码:
					</td>
					<td>
						<input type="password" name="userVO.userEntity.loginPassword">
					</td>
				</Tr>
				<Tr>
					<td>
						验证码:
					</td>
					<td>
						<input type="text" name="userVO.verificationCode"
							style="width: 60px" maxlength="4" />
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
