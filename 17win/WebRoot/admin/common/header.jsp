<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePathRoot = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/admin/";
%>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<script type="text/javascript" src="/js/jquery-1.4.2.min.js">
</script>
<script type="text/javascript" src="/js/validater.js">
</script>
<script type="text/javascript" src="/js/utils.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/jquery-ui-1.8.4.custom.min.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/jquery.tablesorter.min.js">
</script>