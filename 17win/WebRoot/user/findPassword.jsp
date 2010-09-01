<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="../common/header.jsp"></s:include>
	</head>
	<body>
		<h3>
			下次要注意哦！
		</h3>
		<s:form action="userManager/base!register.php" theme="simple"
			onsubmit="return validateForm()">
			<s:hidden name="userVO.userEntity.username"></s:hidden>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						密&nbsp;&nbsp;&nbsp;&nbsp;码：
					</td>
					<td>
						<s:password id="password" name="userVO.userEntity.loginPassword"
							size="30" cssStyle="width:210px"></s:password>
					</td>
				</tr>
				<tr>
					<td>
						确认密码：
					</td>
					<td>
						<input type="password" id="rePassword" size="30"
							style="width: 210px">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="提交">
					</td>
				</tr>
			</table>
		</s:form>
		<s:property value="#request.msg" escape="false" />
	</body>
</html>
