<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="refresh"
			content="0;url=<s:property value="#request.jump" />">
	</head>
	<body>
		<s:property value="msg" escape="false"/>
	</body>
</html>

