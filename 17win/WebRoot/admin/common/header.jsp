<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePathRoot = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/admin/";
%>
<%
	//让浏览器不缓存jsp页面 
	response.setHeader("Pragma", "No-cache");// http1.0 
	response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
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
<SCRIPT type="text/javascript"
	src="<%=basePathRoot%>/js/jquery.tablesorter.min.js"></SCRIPT>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/jqueryPluginExt.js">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/validater.js">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/utils.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/ckeditor/ckeditor.js"></script>

<script type="text/javascript"
	src="<%=basePathRoot%>/js/jquery-ui-1.8.4.custom.min.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/jquery.tablesorter.min.js">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/aop.js">
</script>
<script type="text/javascript"
	src="<%=basePathRoot%>/js/My97DatePicker/WdatePicker.js" defer="defer">
</script>
<script type="text/javascript" src="<%=basePathRoot%>/js/x_alt.js"> </script>
<s:property value="#request.msg" escape="false" />

