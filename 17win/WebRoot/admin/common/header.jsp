<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<LINK href="<%=basePathRoot%>/css/blue/style.css" type="text/css"
	rel="stylesheet">
<link
	href="<%=basePathRoot%>/css/excite-bike/jquery-ui-1.8.4.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=basePathRoot%>/js/jquery-1.4.2.min.js">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/validater.js">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/utils.js">
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
<s:property value="#request.msg" escape="false" />