<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>系统错误</title>
	</head>

	<body>
		通用错误，联系管理员（QQ:909219625）！
		<br />
		<a href="index.jsp">返回首页</a>
	</body>
</html>
