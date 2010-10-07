<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.4.2.min.js">
</script>
<script type="text/javascript" src="js/validater.js">
</script>
<script type="text/javascript" src="js/utils.js">
</script>